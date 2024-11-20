/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerAdminSide;

import Product.MealDAO;
import Product.MealDTO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



public class UpdateMealServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        MealDAO d = new MealDAO();
        try {
            int mealId = Integer.parseInt(request.getParameter("mealID"));
            String mealName = request.getParameter("mealName");
            String content = request.getParameter("content");
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("mealPrice"));
            int discountID = Integer.parseInt(request.getParameter("discountID"));
            int typeOfFood = Integer.parseInt(request.getParameter("typeOfFood"));

            MealDTO meal = new MealDTO(mealId, mealName, price, content, description, image, 1, discountID, typeOfFood);
            boolean rs = d.updateMeal(meal);
            if (rs) {
                request.setAttribute("success", "Update success!");
            } else {
                request.setAttribute("message", "Update fail!");
            }
        } catch (Exception e) {
            log("ERROR AT UpdateMealServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher("ManageMealServlet").forward(request, response);
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
