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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
public class MealngredientServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
              String[] mealIds = request.getParameterValues("MealIDs[]");
            String[] ingredientIds = request.getParameterValues("ingredientIDs[]");
            String[] ingredientQuantities = request.getParameterValues("ingredientQuantities[]");

            if (ingredientIds != null && ingredientQuantities != null) {
                ArrayList<IngredientDTO> ingredients = new ArrayList<>();
                for (int i = 0; i < mealIds.length; i++) {
                    if (!ingredientIds[i].isEmpty() && !ingredientQuantities[i].isEmpty() && !mealIds[i].isEmpty()) {
                        int mealId = Integer.parseInt(mealIds[i]);
                        int IngredientId = Integer.parseInt(ingredientIds[i]);
                        int quantity = Integer.parseInt(ingredientQuantities[i]);
                        ingredients.add(new IngredientDTO(mealId, IngredientId, quantity));
                    }
                }

                IngredientDAO d = new IngredientDAO();
                boolean rs = d.saveIngredientMeal(ingredients);
                if (rs) {
                    request.setAttribute("message", "Ingredients saved successfully.");
                } else {
                    request.setAttribute("message", "Failed to save ingredients.");
                }
            } else {
                request.setAttribute("message", "Ingredient IDs or quantities are missing.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid input. Please enter valid numbers.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while saving ingredients.");
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
