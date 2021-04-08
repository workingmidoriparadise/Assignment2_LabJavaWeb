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
import khamdd.daos.HistoryDAO;
import khamdd.dtos.HistoryDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class SearchHistoryController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(SearchHistoryController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            String category = request.getParameter("searchHistoryCategory");
            String subject = request.getParameter("searchHistorySubject");
            String searchType = request.getParameter("searchHistoryType");
            HistoryDAO dao = new HistoryDAO();
            ArrayList<HistoryDTO> listTotalSearchHistory = null;
            ArrayList<HistoryDTO> listSearchHistory = null;
            int subjectID = 0;
            int pageCount = 0;
            
            if (searchType.equals("Search by Category")) {
                if (category.equals("True")) {
                    listTotalSearchHistory = dao.searchHistoryByCategory(true);
                } else{
                    listTotalSearchHistory = dao.searchHistoryByCategory(false);
                }
            } else {
                if (subject.equals("Prj311- Java Desktop")) {
                    subjectID = 1;
                } else {
                    subjectID = 2;
                }
                listTotalSearchHistory = dao.searchHistoryBySubject(subjectID);
            }
            if(listTotalSearchHistory.size() > 6 || listTotalSearchHistory.size() % 6 != 0){
                pageCount = listTotalSearchHistory.size() / 6 + 1;
            } else if(listTotalSearchHistory.size() > 6 || listTotalSearchHistory.size() % 6 == 0) {
                pageCount = listTotalSearchHistory.size() / 6;
            } else {
                pageCount = 1;
            }
            try{
                listSearchHistory = new ArrayList<>(listTotalSearchHistory.subList(0, 6));
            } catch(Exception e){
                listSearchHistory = new ArrayList<>(listTotalSearchHistory.subList(0, listTotalSearchHistory.size()));
            }
            session.setAttribute("listTotalSearchHistory", listTotalSearchHistory);
            session.setAttribute("listSearchHistory", listSearchHistory);
            session.setAttribute("searchPageCount", pageCount);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            request.getRequestDispatcher("viewHistory.jsp").forward(request, response);
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
