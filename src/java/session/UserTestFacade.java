/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import entity.UserTest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class UserTestFacade extends AbstractFacade<UserTest> {
    
    @PersistenceContext(unitName = "TootusInfovorgudPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTestFacade() {
        super(UserTest.class);
    }

    public UserTest findTestByUser(User regUser) {
        try {
            return (UserTest) em.createQuery("SELECT ut FROM UserTest ut WHERE ut.user = :user")
                    .setParameter("user", regUser)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
