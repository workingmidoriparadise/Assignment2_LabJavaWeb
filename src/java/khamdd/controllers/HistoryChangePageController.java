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
import khamdd.dtos.HistoryDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class HistoryChangePageController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(HistoryChangePageController.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            int page = Integer.parseInt(request.getParameter("page"));
            ArrayList<HistoryDTO> listTotalSearchHistory = (ArrayList<HistoryDTO>) session.getAttribute("listTotalSearchHistory");
            ArrayList<HistoryDTO> listSearchHistory = null;
            if (page == 1) {
                try {
                    listSearchHistory = new ArrayList<>(listTotalSearchHistory.subList(0, 6));
                } catch (Exception e) {
                    listSearchHistory = new ArrayList<>(listTotalSearchHistory.subList(0, listTotalSearchHistory.size()));
                }
            } else {
                try {
                    listSearchHistory = new ArrayList<>(listTotalSearchHistory.subList((page - 1) * 6, page * 6));
                } catch (Exception e) {
                    listSearchHistory = new ArrayList<>(listTotalSearchHistory.subList((page - 1) * 6, listTotalSearchHistory.size()));
                }
            }
            session.setAttribute("listSearchHistory", listSearchHistory);
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
