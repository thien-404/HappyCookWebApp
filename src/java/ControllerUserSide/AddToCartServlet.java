/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerUserSide;

import Product.MealDAO;
import Product.MealDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try{                      
            /* TODO output your page here. You may use following sample code. */
            String mealId = request.getParameter("mealId");
            String isMinus = request.getParameter("isMinus");
            MealDAO m= new MealDAO();
            MealDTO thisMeal = m.getMealToId(mealId);
            //lay cart trong session
            HttpSession session = request.getSession();
            HashMap<MealDTO,Integer> cart = (HashMap<MealDTO,Integer>) session.getAttribute("cart");
            //de vao xem cart
            if(session.getAttribute("LoginedUser") == null){
                
                request.getRequestDispatcher("ShowMealServlet").forward(request, response);
            }
            else if(mealId == null)
                request.getRequestDispatcher("Cart.jsp").forward(request, response);
            //main func
            else if(cart == null && mealId !=null){
                cart = new HashMap<>();
                cart.put(thisMeal, 1);
            }
            else{
                MealDTO find = null;
                for(MealDTO i : cart.keySet()){
                    if(i.getMealId() == Integer.parseInt(mealId.trim())){
                        find = i;
                        break;
                    }
                }
                if(find!=null && isMinus == null){
                    int quantity = cart.get(find);
                    quantity++;
                    cart.put(find, quantity);
                }
                else if(find!=null && isMinus != null){
                    int quantity = cart.get(find);
                    if(quantity > 1)
                        quantity--;
                    cart.put(find, quantity);
                }
                else
                    cart.put(thisMeal, 1);               
            }
            session.setAttribute("cart", cart);
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
