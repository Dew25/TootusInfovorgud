/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Melnikov
 */
@Entity
public class Question implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @ElementCollection
    private Map<String,Boolean> answer;
    private String namePic;

    public Question() {
    }

    public Question(String question, Map<String, Boolean> answer, String namePic) {
        this.question = question;
        this.answer = answer;
        this.namePic = namePic;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.question);
        hash = 97 * hash + Objects.hashCode(this.answer);
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
        final Question other = (Question) obj;
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String,Boolean> getAnswer() {
        return answer;
    }

    public void setAnswer(Map<String,Boolean> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id
                + ", question=" + question 
                + ", answer=" + answer 
                + '}';
    }

    public String getNamePic() {
        return namePic;
    }

    public void setNamePic(String namePic) {
        this.namePic = namePic;
    }
    
    
}
