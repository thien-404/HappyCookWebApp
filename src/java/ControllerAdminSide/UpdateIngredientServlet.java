/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControllerAdminSide;

import Product.IngredientDAO;
import Product.IngredientDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateIngredientServlet extends HttpServlet {
   
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try{
            int id = Integer.parseInt(request.getParameter("ingredientId"));
            String name = request.getParameter("ingredientName");
            double price = Double.parseDouble(request.getParameter("ingredientPrice"));
            String unit = request.getParameter("unit");
            String role = request.getParameter("role");
            IngredientDTO ingredient = new IngredientDTO(id, name, price, 1, unit, role);
            IngredientDAO d = new IngredientDAO();
            boolean rs = d.updateIngredient(ingredient);
            if (rs) {
                request.setAttribute("success", "Update success!!");
            } else {
                request.setAttribute("message", "Update fail!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("ManageIngredient").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
