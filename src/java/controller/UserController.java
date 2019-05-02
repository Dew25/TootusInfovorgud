/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Question;
import entity.Test;
import entity.TestQuestions;
import entity.User;
import entity.UserTest;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.persistence.metamodel.SetAttribute;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import securitylogic.RoleLogic;
import session.QuestionFacade;
import session.TestQuestonsFacade;
import session.UserTestFacade;
import utils.PagePathLoader;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "UserController", urlPatterns = {
    "/UserController",
    
})
public class UserController extends HttpServlet {
    @EJB TestQuestonsFacade testQuestonsFacade;
    @EJB UserTestFacade userTestFacade;
    @EJB QuestionFacade questionFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        RoleLogic rl = new RoleLogic();
        Calendar c = new GregorianCalendar();
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        if(!rl.isRole("USER", regUser)){
            request.setAttribute("info", "Вы должны войти!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        String path = request.getServletPath();
        switch (path) {
            case "addNewQuestion":
                UserTest userTest = userTestFacade.findTestByUser(regUser);
                request.setAttribute("userTest", userTest);
                request.getRequestDispatcher(PagePathLoader.getPagePath("addNewQuestion"))
                        .forward(request, response);
                break;
            case "createQuestion":
                String questionText = request.getParameter("question");
                String trueAnswer = request.getParameter("trueAnswer");
                String[] falseAnswers = request.getParameterValues("falseAnswers");
                Map<String, Boolean> listAnswers = new HashMap<>();
                listAnswers.put(trueAnswer, true);
                for(int i = 0;i<falseAnswers.length;i++){
                    listAnswers.put(falseAnswers[i], false);
                }
                String namePicture = request.getParameter("namePicture");
                Question question = new Question(questionText, listAnswers, namePicture);
                questionFacade.create(question);
                userTest = userTestFacade.findTestByUser(regUser);
                TestQuestions testQuestions = new TestQuestions(userTest.getTest(), question, false);
                testQuestonsFacade.create(testQuestions);
                List<TestQuestions>listTestQuestions = testQuestonsFacade.findByTest(userTest.getTest());
                request.setAttribute("listTestQuestions", listTestQuestions);
                request.getRequestDispatcher(PagePathLoader.getPagePath("addNewQuestion"))
                        .forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
