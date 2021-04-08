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
import khamdd.dtos.QuestionErrorObj;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class CreateController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(CreateController.class);
    private static final String SUCCESS = "admin.jsp";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "create.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String questionContent = request.getParameter("txtQuestionContent");
        String answer1 = request.getParameter("txtAnswer1");
        String answer2 = request.getParameter("txtAnswer2");
        String answer3 = request.getParameter("txtAnswer3");
        String answer4 = request.getParameter("txtAnswer4");
        String correctAnswer = request.getParameter("rdCorrectAnswer");
        String subjectName = request.getParameter("txtSubject");

        boolean check = true;
        QuestionErrorObj questionError = new QuestionErrorObj();
        if (questionContent.equals("")) {
            questionError.setQuestionError("Question can't be blank");
            check = false;
        }
        if (answer1.equals("")) {
            questionError.setAnswer1Error("Answer can't be blank");
            check = false;
        }
        if (answer2.equals("")) {
            questionError.setAnswer2Error("Answer can't be blank");
            check = false;
        }
        if (answer3.equals("")) {
            questionError.setAnswer3Error("Answer can't be blank");
            check = false;
        }
        if (answer4.equals("")) {
            questionError.setAnswer4Error("Answer can't be blank");
            check = false;
        }
        System.out.println(correctAnswer);
        try {
            if (check) {
                int index = Integer.parseInt(correctAnswer);
                switch (index) {
                    case 1: {
                        correctAnswer = "a";
                        break;
                    }
                    case 2: {
                        correctAnswer = "b";
                        break;
                    }
                    case 3: {
                        correctAnswer = "c";
                        break;
                    }
                    case 4: {
                        correctAnswer = "d";
                        break;
                    }
                }
                QuestionDAO dao = new QuestionDAO();
                int subjectID = dao.getSubjectIDBySubjectName(subjectName);
                QuestionDTO dto = new QuestionDTO();
                dto.setAnswer1(answer1);
                dto.setAnswer2(answer2);
                dto.setAnswer3(answer3);
                dto.setAnswer4(answer4);
                dto.setAnswerCorrect(correctAnswer);
                dto.setQuestionContent(questionContent);
                dto.setStatus(true);
                dto.setSubjectID(subjectID);
                if (dao.createQuestion(dto)) {
                    url = SUCCESS;
                    request.setAttribute("NOTICE", "Create successfully");
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Create failed");
                }

            } else {
                url = INVALID;
                request.setAttribute("INVALID", questionError);
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
