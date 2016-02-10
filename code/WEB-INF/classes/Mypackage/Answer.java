package Mypackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Answer implements Serializable {
    
    private String email;
    private int choice;
    private Date dateSubmitted ;
    private String code;
   
    private ArrayList<Answer> listAns;

    public Answer() {
        email="";
        choice=0;
        
        }

    public Answer(String email, int choice) {
        this.email = email;
        this.choice = choice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
     public Date getDateSubmitted() {
        return 	dateSubmitted;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateSubmitted(Date dateSubmitted) {
        this.	dateSubmitted = dateSubmitted;
    }
    
       
    }
