/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;

/**
 *
 * @author KHAM
 */
public class QuestionErrorObj implements Serializable{
    private String questionError, answer1Error, answer2Error, answer3Error, answer4Error;

    public QuestionErrorObj() {
    }

    public QuestionErrorObj(String questionError, String answer1Error, String answer2Error, String answer3Error, String answer4Error) {
        this.questionError = questionError;
        this.answer1Error = answer1Error;
        this.answer2Error = answer2Error;
        this.answer3Error = answer3Error;
        this.answer4Error = answer4Error;
    }

    public String getQuestionError() {
        return questionError;
    }

    public void setQuestionError(String questionError) {
        this.questionError = questionError;
    }

    public String getAnswer1Error() {
        return answer1Error;
    }

    public void setAnswer1Error(String answer1Error) {
        this.answer1Error = answer1Error;
    }

    public String getAnswer2Error() {
        return answer2Error;
    }

    public void setAnswer2Error(String answer2Error) {
        this.answer2Error = answer2Error;
    }

    public String getAnswer3Error() {
        return answer3Error;
    }

    public void setAnswer3Error(String answer3Error) {
        this.answer3Error = answer3Error;
    }

    public String getAnswer4Error() {
        return answer4Error;
    }

    public void setAnswer4Error(String answer4Error) {
        this.answer4Error = answer4Error;
    }

}
