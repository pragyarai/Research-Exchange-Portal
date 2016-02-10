/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mypackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pragyarai
 */
public class Study {
    
    
private String name;
private String code;//int
private Date dateCreated ;
private String email ;//(Creator)
private String question;
private double average;
private double minimum;
private double maximum;
private double SD;

private int requestedParticipants;
private int numOfParticipants;
private String description;
private String status;
private String imageURL;
    List<Answer> ansList = new ArrayList<Answer>();   
    private ArrayList<Answer> listAns;

public Study() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the requestedParticipants
     */
    public int getRequestedParticipants() {
        return requestedParticipants;
    }

    /**
     * @param requestedParticipants the requestedParticipants to set
     */
    public void setRequestedParticipants(int requestedParticipants) {
        this.requestedParticipants = requestedParticipants;
    }

    /**
     * @return the numOfParticipants
     */
    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    /**
     * @param numOfParticipants the numOfParticipants to set
     */
    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

  
    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
     public List<Answer> getAnswer() {            
		return ansList;
	}
     public Answer getAnswer(String email) {  
         //Answer ans =ansList.get(email);
		return null;
	}
    
     public void setAnswer(ArrayList<Answer> listAns) { 
        this.listAns = listAns;        
	}

     public void setAnswerObj(String email,int choice) {	 
        Answer ans = new Answer(email,choice);
        ansList.add(ans);       
	}  
     public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        
        // code to calculate average
        this.average = average;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        // code to calculate minimum
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        // code to calculate maximum
        this.maximum = maximum;
    }

    public double getSD() {
        return SD;
    }

    public void setSD(double SD) {
        // code to calculate SD
        this.SD = SD;
    }
    
    

     public Study(String name,String email,String question,int requestedParticipants,String description,String status)
    {
        
        this.name=name;
        this.email = email;
        this.question=question;
        this.requestedParticipants=requestedParticipants;
        this.description=description;
        this.status = status;
        
    }
    
    public Study(String name, String code,String email,String question,int requestedParticipants,int numOfParticipants, String description, String status)
    {
        this.name=name;
        this.code=code;
        //this.dateCreated=dateCreated;
        this.email=email;
        this.question=question;
        this.requestedParticipants=requestedParticipants;
        this.numOfParticipants=numOfParticipants;
        this.description=description;
        this.status=status;
         
    }

    
}
