/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerUserSide;

import User.UserDTO;
import User.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
public class CreateUserServlet extends HttpServlet {

    private static final String SUCCESS = "Login.jsp";
    private static final String ERROR = "Register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String url = ERROR;
        try {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            UserDao d = new UserDao();
            UserDTO user = d.getUser(email);

            if (!password.equals(confirm)) {
                request.setAttribute("message", "Password confirmation does not match");
            } else if (user == null) { //email is not duplicated
                user = new UserDTO(fullName, phone, email, password, address, 1, "US");
                int rs = d.InsertUser(user);
                if (rs >= 1) {
                    request.setAttribute("successful", "Register successful");
                    url = SUCCESS;
                } else {
                    request.setAttribute("message", "Register failed. Please try again.");
                }
            } else { //email is duplicated
                request.setAttribute("message", "Email is already registered");
            }
        } catch (Exception e) {
            log("Error at RegisterServlet: " + e.toString());
            request.setAttribute("message", "An error occurred during registration. Please try again.");
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
