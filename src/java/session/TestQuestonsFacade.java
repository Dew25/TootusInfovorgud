/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Test;
import entity.TestQuestions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class TestQuestonsFacade extends AbstractFacade<TestQuestions> {

    @PersistenceContext(unitName = "TootusInfovorgudPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestQuestonsFacade() {
        super(TestQuestions.class);
    }

    public List<TestQuestions> findByTest(Test test) {
        try {
            return em.createQuery("SELECT tq FROM TestQuestions tq WHERE tq.test=:test")
                    .setParameter("test", test)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
