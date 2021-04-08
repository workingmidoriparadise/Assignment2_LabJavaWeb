/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khamdd.dtos.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class SearchChangePageController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(SearchChangePageController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            int page = Integer.parseInt(request.getParameter("page"));
            ArrayList<QuestionDTO> listTotalQuestion = (ArrayList<QuestionDTO>) session.getAttribute("listTotalQuestion");
            ArrayList<QuestionDTO> listQuestion = null;
            ArrayList<QuestionDTO>listPRJ321 = null;
            ArrayList<QuestionDTO> listPRJ311 = null;
            
            if (page == 1) {
                try {
                    listQuestion = new ArrayList<>(listTotalQuestion.subList(0, 5));
                } catch (Exception e) {
                    listQuestion = new ArrayList<>(listTotalQuestion.subList(0, listTotalQuestion.size()));
                }
            } else {
                try {
                    listQuestion = new ArrayList<>(listTotalQuestion.subList((page - 1) * 5, page * 5));
                } catch (Exception e) {
                    listQuestion = new ArrayList<>(listTotalQuestion.subList((page - 1) * 5, listTotalQuestion.size()));
                }
            }
            
            listPRJ311 = new ArrayList<>();
            listPRJ321 = new ArrayList<>();
            for (QuestionDTO quesDTO : listQuestion) {
                if (quesDTO.getSubjectID() == 1) {
                    listPRJ311.add(quesDTO);
                }
                if (quesDTO.getSubjectID() == 2) {
                    listPRJ321.add(quesDTO);
                }
            }
            request.setAttribute("searchType", request.getParameter("searchType"));
            request.setAttribute("rdSearchBySubject", request.getParameter("rdSearchBySubject"));
            request.setAttribute("txtSearchByName", request.getParameter("txtSearchByName"));
            request.setAttribute("rdSearchByStatus", request.getParameter("rdSearchByStatus"));
            session.setAttribute("listPRJ311", listPRJ311);
            session.setAttribute("listPRJ321", listPRJ321);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            request.getRequestDispatcher("search.jsp").forward(request, response);
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
