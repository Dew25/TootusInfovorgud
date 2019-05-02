
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


        <h1>Создайте новый вопрос:</h1>
    <div class="col-md-5 offset-md-3 align-self-center">
    <h2 class="text-center">Регистрация</h2>
        <p class="text-center" >${info}</p>
    <h5 class="text-center">Введите данные пользователя</h5>
    <a href="addNewPicture">Добавить новое изображение</a><br>
    <form action="createQuestion" method="POST">
        <div class="form-group">
          <label for="question">Вопрос:</label>
          <input type="text" class="form-control" id="question" name="question" placeholder="">
        </div>
        
        <div class="form-group">
          <label for="trueAnswer">Правильный ответ:</label>
          <input type="text" class="form-control" id="trueAnswer" name="trueAnswer" placeholder="">
        </div>
        
        <div class="form-group">
          <label for="falseAnswer_1">Правильный ответ:</label>
          <input type="text" class="form-control" id="falseAnswer_1" name="falseAnswers" placeholder="">
        </div>
        <div class="form-group">
          <label for="falseAnswer_">Правильный ответ:</label>
          <input type="text" class="form-control" id="falseAnswer_" name="falseAnswers" placeholder="">
        </div>
        <div class="form-group">
          <label for="falseAnswer_3">Правильный ответ:</label>
          <input type="text" class="form-control" id="falseAnswer_3" name="falseAnswers" placeholder="">
        </div>
        <select name="picForQuestion">
            <c:forEach var="cover" items="${listCovers}">
                <option value="${cover.id}">${cover.name}</option>
            </c:forEach>
        </select>
        <button type="submit" id="registration" class="btn btn-primary">Создать вопрос</button>
    </form>
    <p>Список вопросов в тесте: </p>
    <c:forEach var="testQuestions" items="${listTestQuestions}">
        <li>${testQuestions.question}</li>
    </c:forEach>