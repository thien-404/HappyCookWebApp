/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerUserSide;

import Product.IngredientDAO;
import Product.IngredientDTO;
import Product.IngredientMeal;
import Product.MealDAO;
import Product.MealDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class AddIngredientToCartServlet extends HttpServlet {

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
        try{                      
            /* TODO output your page here. You may use following sample code. */
            String mealId = request.getParameter("mealId");
            String mealName = request.getParameter("mealName");
            String isMinus = request.getParameter("isMinus");
            IngredientDAO i= new IngredientDAO();
            ArrayList<IngredientDTO> IngreList = i.getMealIngredient(mealId);
            IngredientMeal thisMealIngre = new IngredientMeal(Integer.parseInt(mealId), mealName, IngreList);
            //lay cart trong session
            HttpSession session = request.getSession();
            HashMap<IngredientMeal,Integer> Ingrecart = (HashMap<IngredientMeal,Integer>) session.getAttribute("Ingrecart");
            //de vao xem cart
            if(session.getAttribute("LoginedUser") == null){                
                request.getRequestDispatcher("ShowMealServlet").forward(request, response);
            }            
            //main func
            else if(Ingrecart == null && mealId !=null){
                Ingrecart = new HashMap<>();
                Ingrecart.put(thisMealIngre, 1);
            }
            else{
                IngredientMeal find = null;
                for(IngredientMeal o : Ingrecart.keySet()){
                    if(o.getMealId() == Integer.parseInt(mealId.trim())){
                        find = o;
                        break;
                    }
                }
                if(find!=null && isMinus == null){
                    int quantity = Ingrecart.get(find);
                    quantity++;
                    Ingrecart.put(find, quantity);
                }
                else if(find!=null && isMinus != null){
                    int quantity = Ingrecart.get(find);
                    if(quantity > 1)
                        quantity--;
                    Ingrecart.put(find, quantity);
                }
                else
                    Ingrecart.put(thisMealIngre, 1);               
            }
            session.setAttribute("Ingrecart", Ingrecart);
            if(request.getParameter("backHome") != null)
                request.getRequestDispatcher("ShowMealServlet").forward(request, response);    
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        }catch (Exception e){
            log("ERROR AT AddToCartServlet" + e.toString());
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
