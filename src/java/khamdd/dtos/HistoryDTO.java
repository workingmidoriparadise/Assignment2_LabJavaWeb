/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author KHAM
 */
public class HistoryDTO implements Serializable{
    private String email;
    private int score;
    private Timestamp submittedTime;
    private boolean status;
    private int historyID;
    private int subjectID;
    
    public HistoryDTO() {
    }

    public HistoryDTO(String email, int score, Timestamp submittedTime, boolean status, int historyID, int subjectID) {
        this.email = email;
        this.score = score;
        this.submittedTime = submittedTime;
        this.status = status;
        this.historyID = historyID;
        this.subjectID = subjectID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(Timestamp submittedTime) {
        this.submittedTime = submittedTime;
    }

    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }
    
}
