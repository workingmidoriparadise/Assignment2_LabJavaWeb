/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khamdd.daos.RegistrationDAO;
import khamdd.dtos.RegistrationErrorObject;
import org.apache.log4j.Logger;

/**
 *
 * @author KHAM
 */
public class CreateAccountController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(CreateAccountController.class);
    private static final String SUCCESS = "login.jsp";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "createAccount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("txtEmail1");
            String password = request.getParameter("txtPassword1");
            String name = request.getParameter("txtName1");
            String confirm = request.getParameter("txtConfirm1");
            RegistrationErrorObject errorObj = new RegistrationErrorObject();
            if (email.equals("")) {
                errorObj.setEmailError("Email can't not be blank");
                check = false;
            }
            if (password.equals("")) {
                errorObj.setPasswordError("Password can't not be blank");
                check = false;
            }
            if (name.equals("")) {
                errorObj.setNameError("Name can't not be blank");
                check = false;
            }
            if (confirm.equals("")) {
                errorObj.setConfirmError("Confirm can't not be blank");
                check = false;
            }
            if (!password.equals(confirm)) {
                errorObj.setConfirmError("Password and confirm are not the same");
                check = false;
            }
            if (check) {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
                BigInteger number = new BigInteger(1, hash);
                StringBuilder hexString = new StringBuilder(number.toString(16));
                while (hexString.length() < 32) {
                    hexString.insert(0, '0');
                }
                password = hexString.toString();

                RegistrationDAO dao = new RegistrationDAO();
                dao.createAccount(email, password, name);
                session.setAttribute("email", email);
                session.setAttribute("name", name);
                request.setAttribute("createAccountNotice", "Create account successfully");
                url = SUCCESS;
            } else {
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }
        } catch (Exception e) {
            LOG.error("Error at CreateAccountController: " + e.getMessage());
            if(e.getMessage().contains("duplicate")){
                request.setAttribute("ERROR", "Email is already exist");
                url = ERROR;
            }
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
