/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khamdd.daos.HistoryDAO;
import khamdd.dtos.HistoryDTO;
import khamdd.dtos.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class SubmitExamController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(SubmitExamController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int score = 0;
            int historyID;
            String status = "FAILED";
            HttpSession session = request.getSession();
            ArrayList<QuestionDTO> listQuiz = (ArrayList<QuestionDTO>) session.getAttribute("listQuiz");
            HashMap<Integer, String> listExam = (HashMap<Integer, String>) session.getAttribute("listExam");
            HashMap<Integer, String> listAnswer = (HashMap<Integer, String>) session.getAttribute("listAnswer");
            for (int i = 0; i < listQuiz.size(); i++) {
                if (!listAnswer.containsKey(listQuiz.get(i).getQuestionID())) {
                    listAnswer.put(listQuiz.get(i).getQuestionID(), "");
                }
                if (listExam.get(listQuiz.get(i).getQuestionID()).equals(listAnswer.get(listQuiz.get(i).getQuestionID()))) {
                    score += 2;
                }
            }
            if (score >= 5) {
                status = "PASSED";
            }

            request.setAttribute("score", score);
            request.setAttribute("examStatus", status);

            HistoryDAO dao = new HistoryDAO();
            HistoryDTO dto = new HistoryDTO();
            dto.setEmail(session.getAttribute("email").toString());
            dto.setScore(score);
            dto.setSubmittedTime(new java.sql.Timestamp(new java.util.Date().getTime()));
            System.out.println((int) session.getAttribute("subjectIDForQuiz"));
//            dto.setSubjectID((int) session.getAttribute("subjectIDForQuiz"));
            if (status.equals("PASSED")) {
                dto.setStatus(true);
            } else {
                dto.setStatus(false);
            }
            historyID = dao.addHistory(dto);
            for (int i = 0; i < listQuiz.size(); i++) {
                dao.addHistoryDetail(historyID,
                        listQuiz.get(i).getQuestionID(),
                        listExam.get(listQuiz.get(i).getQuestionID()).equals(listAnswer.get(listQuiz.get(i).getQuestionID())),
                        listExam.get(listQuiz.get(i).getQuestionID()),
                        listAnswer.get(listQuiz.get(i).getQuestionID()));
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            request.getRequestDispatcher("showResult.jsp").forward(request, response);
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
