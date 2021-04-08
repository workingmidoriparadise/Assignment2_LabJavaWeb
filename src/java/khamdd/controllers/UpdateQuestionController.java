/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khamdd.daos.QuestionDAO;
import khamdd.dtos.QuestionDTO;
import khamdd.dtos.UpdateQuestionErrorObject;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class UpdateQuestionController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(UpdateQuestionController.class);
    private static final String SUCCESS = "SearchController";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "search.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String questionID = request.getParameter("updateQuestionID");
        String updateCorrectAnswer = request.getParameter("rdUpdateCorrectAnswer");
        String updateAnswer1 = request.getParameter("txtUpdateAnswer1");
        String updateAnswer2 = request.getParameter("txtUpdateAnswer2");
        String updateAnswer3 = request.getParameter("txtUpdateAnswer3");
        String updateAnswer4 = request.getParameter("txtUpdateAnswer4");
        String updateQuestion = request.getParameter("txtUpdateQuestion");
        String url = ERROR;

        boolean check = true;
        try {
            UpdateQuestionErrorObject updateError = new UpdateQuestionErrorObject();
            if(updateAnswer1.equals("")){
                updateError.setAnswer1Error("Answer can't be blank");
                check = false;
            }
            if(updateAnswer2.equals("")){
                updateError.setAnswer2Error("Answer can't be blank");
                check = false;
            }
            if(updateAnswer3.equals("")){
                updateError.setAnswer3Error("Answer can't be blank");
                check = false;
            }
            if(updateAnswer4.equals("")){
                updateError.setAnswer4Error("Answer can't be blank");
                check = false;
            }
            if(updateQuestion.equals("")){
                updateError.setQuestionError("Question can't be blank");
                check = false;
            }
            if(check){
                String action = request.getParameter("action");
                QuestionDAO dao = new QuestionDAO();
                QuestionDTO dto = null;
                switch(action){
                    case "Update":{
                        dto = new QuestionDTO();
                        dto.setAnswer1(updateAnswer1);
                        dto.setAnswer2(updateAnswer2);
                        dto.setAnswer3(updateAnswer3);
                        dto.setAnswer4(updateAnswer4);
                        dto.setQuestionContent(updateQuestion);
                        dto.setAnswerCorrect(updateCorrectAnswer);
                        dto.setQuestionID(Integer.parseInt(questionID));
                        if(dao.updateQuestion(dto)){
                            request.setAttribute("checkAction", "success");
                            request.setAttribute("NOTE", "Update successfully");
                            url = SUCCESS;
                        } else{
                            request.setAttribute("checkAction", "failed");
                            request.setAttribute("NOTE", "Update failed");
                            url = INVALID;
                        }
                        break;
                    }
                    case "Delete":{
                        if(dao.deleteQuestion(Integer.parseInt(questionID))){
                            request.setAttribute("checkAction", "success");
                            request.setAttribute("NOTE", "Delete Successfully");
                            url = SUCCESS;
                        } else {
                            request.setAttribute("checkAction", "failed");
                            request.setAttribute("NOTE", "Delete Failed");
                            url = INVALID;
                        }
                        break;
                    }
                    case "Restore":{
                        if(dao.restoreQuestion(Integer.parseInt(questionID))){
                            request.setAttribute("checkAction", "success");
                            request.setAttribute("NOTE", "Restore Successfully");
                            url = SUCCESS;
                        } else {
                            request.setAttribute("checkAction", "failed");
                            request.setAttribute("NOTE", "Restore Successfully");
                            url = INVALID;
                        }
                        break;
                    }
                }
            }else {
                url = INVALID;
                request.setAttribute("updateInvalid", updateError);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
