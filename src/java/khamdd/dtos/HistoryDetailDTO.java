/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author KHAM
 */
public class HistoryDetailDTO implements Serializable{
    private ArrayList<String> listCorrectAnswer;
    private ArrayList<String> listUserAnswer;
    private ArrayList<Boolean> listStatus;
    private ArrayList<Integer> listQuestionID;

    public HistoryDetailDTO() {
    }

    public HistoryDetailDTO(ArrayList<String> listCorrectAnswer, ArrayList<String> listUserAnswer, ArrayList<Boolean> listStatus, ArrayList<Integer> listQuestionID) {
        this.listCorrectAnswer = listCorrectAnswer;
        this.listUserAnswer = listUserAnswer;
        this.listStatus = listStatus;
        this.listQuestionID = listQuestionID;
    }

    public ArrayList<String> getListCorrectAnswer() {
        return listCorrectAnswer;
    }

    public void setListCorrectAnswer(ArrayList<String> listCorrectAnswer) {
        this.listCorrectAnswer = listCorrectAnswer;
    }

    public ArrayList<String> getListUserAnswer() {
        return listUserAnswer;
    }

    public void setListUserAnswer(ArrayList<String> listUserAnswer) {
        this.listUserAnswer = listUserAnswer;
    }

    public ArrayList<Boolean> getListStatus() {
        return listStatus;
    }

    public void setListStatus(ArrayList<Boolean> listStatus) {
        this.listStatus = listStatus;
    }

    public ArrayList<Integer> getListQuestionID() {
        return listQuestionID;
    }

    public void setListQuestionID(ArrayList<Integer> listQuestionID) {
        this.listQuestionID = listQuestionID;
    }
    
    
}
