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
import khamdd.daos.QuestionDAO;
import khamdd.dtos.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class DoQuestController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(DoQuestController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            ArrayList<QuestionDTO> listQuiz = null;
            String subjectName = request.getParameter("rdSelectSubject");
            int subjectID = 0;
            if (subjectName.equals("Prj311- Java Desktop")) {
                subjectID = 1;
            } else {
                subjectID = 2;
            }
            QuestionDAO dao = new QuestionDAO();
            listQuiz = dao.getQuiz(subjectID);
            session.setAttribute("listQuiz", listQuiz);
            long endTime = System.currentTimeMillis() + 300000;
            session.setAttribute("endTime", endTime);
            session.setAttribute("curTime", System.currentTimeMillis());
            HashMap<Integer, String> listExam = new HashMap<>();
            for(QuestionDTO dto : listQuiz){
                listExam.put(dto.getQuestionID(), dto.getAnswerCorrect());
            }
            int page = 0;
            request.setAttribute("page", page);
            session.setAttribute("listExam", listExam);
            request.setAttribute("curQuestion", listQuiz.get(page).getQuestionContent());
            request.setAttribute("curAnswer1", listQuiz.get(page).getAnswer1());
            request.setAttribute("curAnswer2", listQuiz.get(page).getAnswer2());
            request.setAttribute("curAnswer3", listQuiz.get(page).getAnswer3());
            request.setAttribute("curAnswer4", listQuiz.get(page).getAnswer4());
            request.setAttribute("curQuestionID", listQuiz.get(page).getQuestionID());
            session.setAttribute("subjectIDForQuiz", subjectID);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            request.getRequestDispatcher("doQuest.jsp").forward(request, response);
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
