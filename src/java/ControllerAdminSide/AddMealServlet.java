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

/**
 *
 * @author acer
 */
@MultipartConfig
public class AddMealServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "addMeal.jsp"; 
        MealDAO d = new MealDAO();
        try {
            //sử lý image
            Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            //write the file to the server
            File uploads = new File(getServletContext().getRealPath("/")+"/images");
            File file = new File(uploads, fileName);
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            //end
            
            String name = request.getParameter("mealName");
            double price = Double.parseDouble(request.getParameter("mealPrice"));
            String content = request.getParameter("content");
            String description = request.getParameter("description");
            int discountId = Integer.parseInt(request.getParameter("discountID"));
            int typeOfFood = Integer.parseInt(request.getParameter("typeOfFood"));

            if (price >= 0 && discountId >= 0) {
                MealDTO meal = new MealDTO(name, price, content, description, fileName, 1, discountId, typeOfFood);
                boolean rs = d.insertMeal(meal);
                if (rs) {
                    request.setAttribute("success", "Meal added successfully!");
                } else {
                    request.setAttribute("message", "Failed to add meal!");
                }
            } else {
              request.setAttribute("message", "The value must greater than 0!!");
            }
        } catch (Exception e) {
            log("Error at AddMealSevlet" + e.toString());
        } finally{
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
