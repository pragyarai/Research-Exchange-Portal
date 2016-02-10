package Mypackage;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pragyarai
 */
public class User implements Serializable{
 private String name;
 private String email;

 private int coins;
 private int participants ;// how many studies have he/she created
 private int participation ;// how many studies have he/she participated in

    /**
     * @return the Name
     */
 
    public User() {
    }

    public String getName() {
        return name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param Email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * @param Coins the coins to set
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * @return the participants
     */
    public int getParticipants() {
        return participants;
    }

    /**
     * @param Participants the participants to set
     */
    public void setParticipants(int participants) {
        this.participants = participants;
    }

    /**
     * @return the participation
     */
    public int getParticipation() {
        return participation;
    }

    /**
     * @param Participation the participation to set
     */
    public void setParticipation(int participation) {
        this.participation = participation;
    }
    
}
