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
import khamdd.daos.QuestionDAO;
import khamdd.dtos.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class SearchController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(SearchController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int pageCount = 0;
        int totalQuest = 0;
        ArrayList<QuestionDTO> listTotalQuestion = null;
        ArrayList<QuestionDTO> listQuestion = null;
        ArrayList<QuestionDTO> listPRJ321 = null;
        ArrayList<QuestionDTO> listPRJ311 = null;
        try {
            HttpSession session = request.getSession();
            String searchType = request.getParameter("searchType");
            QuestionDAO dao = new QuestionDAO();
            listTotalQuestion = new ArrayList<>();
            switch (searchType) {
                case "searchByQuestionName": {
                    String searchName = request.getParameter("txtSearchByName");
                    if (searchName == null) {
                        searchName = "";
                    }
                    listTotalQuestion = dao.searchByQuestionName(searchName);
                    request.setAttribute("txtSearchByName", searchName);
                    break;
                }
                case "searchByStatus": {
                    String searchStatus = request.getParameter("rdSearchByStatus");
                    if (searchStatus.equals("true")) {
                        listTotalQuestion = dao.searchByStatus(1);
                    } else {
                        listTotalQuestion = dao.searchByStatus(0);
                    }
                    request.setAttribute("rdSearchByStatus", searchStatus);
                    break;
                }
                case "searchBySubject": {
                    String searchSubject = request.getParameter("rdSearchBySubject");
                    if (searchSubject.equals("Prj311- Java Desktop")) {
                        listTotalQuestion = dao.searchBySubject(1);
                    } else {
                        listTotalQuestion = dao.searchBySubject(2);
                    }
                    request.setAttribute("rdSearchBySubject", searchSubject);
                    break;
                }
            }
            totalQuest = listTotalQuestion.size();
            if (totalQuest % 5 != 0) {
                pageCount = totalQuest / 5 + 1;
            } else {
                pageCount = totalQuest / 5;
            }
            try {
                listQuestion = new ArrayList<>(listTotalQuestion.subList(0, 5));
            } catch (Exception e) {
                listQuestion = new ArrayList<>(listTotalQuestion.subList(0, listTotalQuestion.size()));
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
            request.setAttribute("searchType", searchType);
            session.setAttribute("listTotalQuestion", listTotalQuestion);
            session.setAttribute("listPRJ311", listPRJ311);
            session.setAttribute("listPRJ321", listPRJ321);
            session.setAttribute("pageCount", pageCount);
            System.out.println(pageCount);
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
