<%@page import="Order.OrderMealDTO"%>
<%@page import="Order.OrderDTO"%>
<%@ page import="mylib.OrderUtil" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Detail</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/admin.css">
    </head>
    <body>
        
        <div class="container">
            <div class="row">
            <h2>Order Detail</h2>
            <%
                OrderDTO order = (OrderDTO) request.getAttribute("OrderDetail");
                List<OrderMealDTO> list = (List<OrderMealDTO>) request.getAttribute("MealDetails");
                if (list != null) {
            %>
            <table class="table table-bordered">
                <tr>
                    <th>Order ID</th>
                    <td><%= order.getOrderId()%></td>
                </tr>
                <tr>
                    <th>User ID</th>
                    <td><%= order.getAccId()%></td>
                </tr>
                <tr>
                    <th>Order Date</th>
                    <td><%= order.getOrderDate()%></td>
                </tr>
                <tr>
                    <th>Total Price</th>
                    <td><%= order.getTotalPrice()%></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>
                        <%= OrderUtil.getStatusText(order.getStatus()) %>
                    </td>
                </tr>
            </table>

            <h3>Meal Details</h3>
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Meal ID</th>
                        <th>Meal Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Type of Food</th>
                        <th>Is Ingredient</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (OrderMealDTO meal : list) {%>
                    <tr>
                        <td><img src="images/<%= meal.getImageUrl()%>" alt="<%= meal.getMealName()%>" style="width: 100px; height: 100px;"></td>
                        <td><%= meal.getMealId()%></td>
                        <td><%= meal.getMealName()%></td>
                        <td><%= meal.getMealPrice()%></td>
                        <td><%= meal.getQuantity()%></td>
                        <td><%= OrderUtil.getTypeOfFoodText(meal.getTypeOfFood()) %></td>
                           <td>
                               <input style="  width: 20px;  height: 20px; " type="checkbox" disabled <%= meal.getIsIngredient() == 1 ? "checked" : "" %> />
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

            <a href="manageOrders.jsp" class="btn btn-primary">Back to Orders</a>
            <%
            } else {
            %>
            <h3 style="color: red;"><%= request.getAttribute("ERROR") %></h3>
            <a href="manageOrders.jsp" class="btn btn-primary">Back to Orders</a>
            <% } %>
        </div>
        </div>
        
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
