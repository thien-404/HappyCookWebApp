/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerUserSide;

import Order.OrderDAO;
import Order.OrderDTO;
import Order.OrderMealDAO;
import Order.OrderMealDTO;
import Product.IngredientMeal;
import Product.MealDTO;
import User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class SaveOrderServlet extends HttpServlet {

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
        try{           
            //khai bao
            OrderDAO o = new OrderDAO();
            OrderMealDAO om = new OrderMealDAO();
            HttpSession session = request.getSession();            
            UserDTO user = (UserDTO) session.getAttribute("LoginedUser");
            HashMap<MealDTO,Integer> cart = (HashMap<MealDTO,Integer>) session.getAttribute("cart");
            HashMap<IngredientMeal,Integer> Ingrecart = (HashMap<IngredientMeal,Integer>) session.getAttribute("Ingrecart");
            
            //bien danh cho orderdto
            int userId = user.getUserID();
            double totalPrice = Double.parseDouble(request.getParameter("total"));
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());
            int status = 1;
            boolean rs = false;
            boolean isFinished = true;
                                                     
            //add du lieu vao bang orderdto
            if((cart != null && cart.size()>0) || (Ingrecart !=null && Ingrecart.size()>0)){
                OrderDTO newOrder = new OrderDTO(userId, orderDate, totalPrice, status);
                rs = o.insertOrder(newOrder);                
            }else {
                request.setAttribute("message", "Giỏ Hàng Trống Hãy Thêm Món Vào Giỏ");
            }
            
            //add du lieu vao ordermealdto neu ben tren thanh cong
            if(rs){
                //add danh cho do an
                if(cart != null){
                for(Map.Entry<MealDTO, Integer> entry: cart.entrySet()){                   
                    int mealId = entry.getKey().getMealId();
                    int orderId = o.getNewestOrderId();
                    int quantity = entry.getValue();
                    int isIngredient = 0;
                    OrderMealDTO newOrderMeal = new OrderMealDTO(mealId, orderId, quantity, isIngredient);
                    boolean ck = om.insertOrderMeal(newOrderMeal);
                    if (!ck) {
                    isFinished = false;
                    }
                }
                }
                //add danh cho nguyen lieu
                if(Ingrecart != null){
                for(Map.Entry<IngredientMeal,Integer> entry : Ingrecart.entrySet()){
                    int mealId = entry.getKey().getMealId();
                    int orderId = o.getNewestOrderId();
                    int quantity = entry.getValue();
                    int isIngredient = 1;
                    OrderMealDTO newOrderMeal = new OrderMealDTO(mealId, orderId, quantity, isIngredient);
                    boolean ck = om.insertOrderMeal(newOrderMeal);
                    if (!ck) {
                    isFinished = false;
                    }
                }
                }
                if(isFinished){
                    request.setAttribute("message", "Lưu Đơn Hàng Thành Công");
                    session.removeAttribute("Ingrecart");
                    session.removeAttribute("cart");
                }
                else{
                    request.setAttribute("message", "Lưu Đơn Hàng Thất Bại");
                }
            }
            
            //add du lieu vao bang ordermealdto
        }catch (Exception e) {
            log("Error at SaveOrder" + e.toString());
        }finally{
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
                    
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
