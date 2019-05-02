/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Melnikov
 */
@Entity
public class DoneTest implements Serializable {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Test test;
    @OneToOne
    private User user;
    private int balls;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDone;

    public DoneTest() {
    }

    public DoneTest(Test test, User user, int balls, Date dateDone) {
        this.test = test;
        this.user = user;
        this.balls = balls;
        this.dateDone = dateDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public Date getDateDone() {
        return dateDone;
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.test);
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + this.balls;
        hash = 97 * hash + Objects.hashCode(this.dateDone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DoneTest other = (DoneTest) obj;
        if (this.balls != other.balls) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.test, other.test)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.dateDone, other.dateDone)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DoneTest{" + "id=" + id 
                + ", test=" + test 
                + ", user=" + user 
                + ", balls=" + balls 
                + ", dateDone=" + dateDone 
                + '}';
    }
    
    
    
}
