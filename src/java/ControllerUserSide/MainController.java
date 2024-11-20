/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerUserSide;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
@MultipartConfig
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String HOME = "ShowMealServlet";

    private static final String LOGIN_PAGE = "LoginPage";
    private static final String LOGIN_PAGE_CONTROLLER = "Login.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginServlet";

    private static final String ADMIN_PAGE = "Dashboard";
    private static final String ADMIN_CONTROLLER = "DashboardServlet";

    private static final String REGISTER = "Register";
    private static final String REGISTER_CONTROLLER = "CreateUserServlet";

    private static final String REGISTER_PAGE = "RegisterPage";
    private static final String REGISTER_PAGE_CONTROLLER = "Register.jsp";

    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutServlet";

    private static final String MANAGE_USERS = "ManageUsers";
    private static final String MANAGE_USERS_CONTROLLER = "ManageUserServlet";

    private static final String UPDATEUSERBYAD = "UpdateByAd";
    private static final String UPDATEBYAD_CONTROLLER = "UpdateUserByAdServlet";

    private static final String LISTBLOCKUSER = "BlockUsersList";
    private static final String LIST_BLOCK_CONTROLLER = "ListBlockUserServlet";

    private static final String BLOCKUSER = "Block";
    private static final String BLOCK_CONTROLLER = "BlockUserServlet";

    private static final String UNBLOCKUSER = "Unblock";
    private static final String UNBLOCK_CONTROLLER = "UnBlockUserServlet";

    private static final String ADDMEALPAGE = "AddMealPage";
    private static final String MEAL_PAGE_CONTROLLER = "addMeal.jsp";
    private static final String ADDMEAL = "Create";
    private static final String ADD_MEAL_CONTROLLER = "AddMealServlet";

    private static final String MANAGEMEAL = "ManageMeal";
    private static final String MANAGE_MEAL_CONTROLLER = "ManageMealServlet";

    private static final String UPDATEMEAL = "UpdateMeal";
    private static final String UPDATEMEAL_CONTROLLER = "UpdateMealServlet";

    private static final String DELETEMEAL = "DeleteMeal";
    private static final String DELETEMEAL_CONTROLLER = "DeleteMealServlet";

    private static final String SAVESMEALINGREDIENT = "SaveMealIngredient";
    private static final String SAVESMEALINGREDIENT_CONTROLLER = "MealngredientServlet";

    private static final String DELETEDMEALLIST = "DeletedMealList";
    private static final String DELETEDMEALLIST_CONTROLLER = "DeletedMealServlet";

    private static final String RESTOREMEAL = "RestoreMeal";
    private static final String RESTOREMEAL_CONTROLLER = "RestoreMealServlet";

    private static final String SHOWDETAIL = "ShowDetail";
    private static final String SHOWDETAIL_CONTROLLER = "ShowDetailServlet";

    private static final String ADDTOCART = "AddToCart";
    private static final String ADDTOCART_CONTROLLER = "AddToCartServlet";

    private static final String ADDINGREDIENTTOCART = "AddIngredientToCart";
    private static final String ADDINGREDIENTTOCART_CONTROLLER = "AddIngredientToCartServlet";

    private static final String CARTCONTROLLER = "CartController";
    private static final String CARTCONTROLLER_CONTROLLER = "CartControllerServlet";

    private static final String INGREDIENTCARTCONTROLLER = "IngredientCartController";
    private static final String INGREDIENTCARTCONTROLLER_CONTROLLER = "IngredientCartController";

    private static final String SAVEORDER = "SaveOrder";
    private static final String SAVEORDER_CONTROLLER = "SaveOrderServlet";

    private static final String ADDINGREDIENT = "CreateIngredient";
    private static final String ADDINGREDIENT_CONTROLLER = "AddIngredients";

    private static final String ADDINGREDIENTPAGE = "AddIngredientPage";
    private static final String ADDINGREDIENTPAGE_CONTROLLER = "addIngredients.jsp";

    private static final String EDITINGREDIENT = "EditIngredient";
    private static final String EDITINGREDIENT_CONTROLLER = "ManageIngredient";

    private static final String UPDATEINGREDIENT = "UpdateInredient";
    private static final String UPDATEINGREDIENT_CONTROLLER = "UpdateIngredientServlet";

    private static final String DELETEINGREDIENT = "DeleteInredient";
    private static final String DELETEINGREDIENT_CONTROLLER = "DeleteIngredientServlet";

    private static final String DELETEDINGREDIENTLIST = "DeletedIngredientList";
    private static final String DELETEDINGREDIENTLIST_CONTROLLER = "DeletedIngredientServlet";

    private static final String RESTOREINGREDIENT = "RestoreInredient";
    private static final String RESTOREINGREDIENT_CONTROLLER = "RestoreInredientServlet";

    private static final String SEARCHEMAILORPHONE = "SearchEmailOrPhone";
    private static final String SEARCHEMAILORPHONE_CONTROLLER = "SearchEmailOrPhoneServlet";

    private static final String SEARCHUSERBLOCK = "SearchUserBlock";
    private static final String SEARCHUSERBLOCK_CONTROLLER = "SearchUserBlockServlet";

    private static final String SEARCHORDERBYDATE = "SearchOrderByDate";
    private static final String SEARCHORDERBYDATE_CONTROLLER = "SearchOrderByDateServlet";

    private static final String UPDATEORDER = "UpdateOrder";
    private static final String UPDATEORDER_CONTROLLER = "UpdateOrdersServlet";

    private static final String ORDERDETAIL = "OrderDetail";
    private static final String ORDERDETAIL_CONTROLLER = "ShowOrderDetailServlet";

    private static final String ORDERHISTORY = "OrderHistory";
    private static final String ORDERHISTORY_CONTROLLER = "OrderHistoryServlet";

    private static final String MANAGEORDER = "ManageOrder";
    private static final String MANAGEORDER_CONTROLLER = "MangegeOrdersServlet";

    private static final String CANCELORDER = "Cancel";
    private static final String CANCELORDER_CONTROLLER = "CancelOrdersServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {

            String action = request.getParameter("action");
            log("Acctoin: " + action);
            if (null == action) {
                url = HOME;
            } else {
                switch (action) {
                    case LOGIN:
                        url = LOGIN_CONTROLLER;
                        break;
                    case REGISTER:
                        url = REGISTER_CONTROLLER;
                        break;
                    case LOGIN_PAGE:
                        url = LOGIN_PAGE_CONTROLLER;
                        break;
                    case REGISTER_PAGE:
                        url = REGISTER_PAGE_CONTROLLER;
                        break;
                    case ADMIN_PAGE:
                        url = ADMIN_CONTROLLER;
                        break;
                    case LOGOUT:
                        url = LOGOUT_CONTROLLER;
                        break;
                    case MANAGE_USERS:
                        url = MANAGE_USERS_CONTROLLER;
                        break;
                    case UPDATEUSERBYAD:
                        url = UPDATEBYAD_CONTROLLER;
                        break;
                    case SEARCHEMAILORPHONE:
                        url = SEARCHEMAILORPHONE_CONTROLLER;
                        break;
                    case BLOCKUSER:
                        url = BLOCK_CONTROLLER;
                        break;
                    case LISTBLOCKUSER:
                        url = LIST_BLOCK_CONTROLLER;
                        break;
                    case UNBLOCKUSER:
                        url = UNBLOCK_CONTROLLER;
                        break;
                    case SEARCHUSERBLOCK:
                        url = SEARCHUSERBLOCK_CONTROLLER;
                        break;
                    case ADDMEALPAGE:
                        url = MEAL_PAGE_CONTROLLER;
                        break;
                    case ADDMEAL:
                        url = ADD_MEAL_CONTROLLER;
                        break;
                    case MANAGEMEAL:
                        url = MANAGE_MEAL_CONTROLLER;
                        break;
                    case UPDATEMEAL:
                        url = UPDATEMEAL_CONTROLLER;
                        break;
                    case DELETEMEAL:
                        url = DELETEMEAL_CONTROLLER;
                        break;
                    case SAVESMEALINGREDIENT:
                        url = SAVESMEALINGREDIENT_CONTROLLER;
                        break;
                    case DELETEDMEALLIST:
                        url = DELETEDMEALLIST_CONTROLLER;
                        break;
                    case RESTOREMEAL:
                        url = RESTOREMEAL_CONTROLLER;
                        break;
                    case SHOWDETAIL:
                        url = SHOWDETAIL_CONTROLLER;
                        break;
                    case ADDTOCART:
                        url = ADDTOCART_CONTROLLER;
                        break;
                    case CARTCONTROLLER:
                        url = CARTCONTROLLER_CONTROLLER;
                        break;
                    case SAVEORDER:
                        url = SAVEORDER_CONTROLLER;
                        break;
                    case ADDINGREDIENT:
                        url = ADDINGREDIENT_CONTROLLER;
                        break;
                    case ADDINGREDIENTPAGE:
                        url = ADDINGREDIENTPAGE_CONTROLLER;
                        break;
                    case EDITINGREDIENT:
                        url = EDITINGREDIENT_CONTROLLER;
                        break;
                    case UPDATEINGREDIENT:
                        url = UPDATEINGREDIENT_CONTROLLER;
                        break;
                    case DELETEINGREDIENT:
                        url = DELETEINGREDIENT_CONTROLLER;
                        break;
                    case DELETEDINGREDIENTLIST:
                        url = DELETEDINGREDIENTLIST_CONTROLLER;
                        break;
                    case RESTOREINGREDIENT:
                        url = RESTOREINGREDIENT_CONTROLLER;
                        break;
                    case SEARCHORDERBYDATE:
                        url = SEARCHORDERBYDATE_CONTROLLER;
                        break;
                    case UPDATEORDER:
                        url = UPDATEORDER_CONTROLLER;
                        break;
                    case ORDERDETAIL:
                        url = ORDERDETAIL_CONTROLLER;
                        break;
                    case ADDINGREDIENTTOCART:
                        url = ADDINGREDIENTTOCART_CONTROLLER;
                        break;
                    case INGREDIENTCARTCONTROLLER:
                        url = INGREDIENTCARTCONTROLLER_CONTROLLER;
                        break;
                    case ORDERHISTORY:
                        url = ORDERHISTORY_CONTROLLER;
                        break;
                    case MANAGEORDER:
                        url = MANAGEORDER_CONTROLLER;
                        break;
                    case CANCELORDER:
                        url = CANCELORDER_CONTROLLER;
                        break;
                    default:
                        request.setAttribute("message", "Your action not support");
                        break;
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
