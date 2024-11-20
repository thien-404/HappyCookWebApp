/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControllerAdminSide;

import Order.OrderDAO;
import Order.OrderDTO;
import User.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DashboardServlet extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
           
        try {
            //total User
            UserDao ud = new UserDao();
            int totalUser = ud.getTotalUser(); 
            request.setAttribute("totalUser", totalUser); 
            
            //total order
            OrderDAO od = new OrderDAO();
            int totalOrder = od.getTotalOrders();
            request.setAttribute("totalOrder", totalOrder);
            
            //total revenue
            double totalPrice = od.getTotalPrice();
            request.setAttribute("totalPrice", totalPrice);
            
            
            ArrayList<OrderDTO> list = od.getActiveOrders();
            if(list != null){
                request.setAttribute("ActicveOrder", list);
            }
            
            
            request.getRequestDispatcher("admin.jsp").forward(request, response); 
        } catch (Exception e) {
            log("error at dashboardAdminServlet" + e);
        
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
