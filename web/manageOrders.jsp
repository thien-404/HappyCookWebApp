<%@page import="Order.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Orders</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@coreui/coreui/dist/css/coreui.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="css/admin.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <%
            UserDTO acc = (UserDTO) session.getAttribute("LoginedUser");
            if (acc == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else if ("US".equals(acc.getRole())) {
                response.sendRedirect("MainController");
            }
        %>

        <div class="container-fluid">
            <div class="row">
                <%-- Sidebar --%>
                <jsp:include page="sideBarAdmin.jsp"></jsp:include>
                <%-- Main --%>
                <main class="col-md-10 content row">
                    <div class="search-bar my-3">
                        <form action="MainController" method="POST">
                            <div class="input-group">
                                <input type="date" name="OrderDate" class="form-control" placeholder="Enter Orders Date..." value="<%= request.getParameter("OrderDate")%>">
                                <div class="input-group-append">
                                    <button type="submit" name="action" value="SearchOrderByDate" class="btn btn-primary">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="my-3">
                        <h2>Manage Orders</h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped text-center">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">User Name</th>
                                        <th scope="col">Order Date</th>
                                        <th scope="col">Total Price</th>
                                        <th scope="col">Status</th>
                                        <th scope="col" style="width: 15%;">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<OrderDTO> list = (ArrayList<OrderDTO>) session.getAttribute("OrderList");
                                        if (list != null && !list.isEmpty()) {
                                            for (OrderDTO order : list) {
                                    %>
                                <form action="MainController" method="POST" accept-charset="UTF-8">
                                    <tr>
                                        <td>
                                            <input type="hidden" name="orderid" value="<%= order.getOrderId()%>">
                                            <%= order.getOrderId()%>
                                        </td>
                                        <td>
                                            <input type="hidden" name="accName" value="<%= order.getFullName()%>">
                                            <%= order.getFullName()%>
                                        </td>
                                        <td>
                                            <input type="hidden" name="orderDate" value="<%= order.getOrderDate()%>">
                                            <%= order.getOrderDate()%>
                                        </td>
                                        <td>
                                            <input type="hidden" name="totalPrice" value="<%= order.getTotalPrice()%>">
                                            <%= order.getTotalPrice()%>
                                        </td>
                                        <td>
                                            <select class="form-control text-center" id="status" name="status" <%= (order.getStatus() == 3 || order.getStatus() == 4) ? "disabled" : ""%> required>
                                                <% if (order.getStatus() != 2) {%>
                                                <option value="1" <%= (order.getStatus() == 1) ? "selected" : ""%>>Pending</option>
                                                <% }%>
                                                <option value="2" <%= (order.getStatus() == 2) ? "selected" : ""%>>Processing</option>
                                                <option value="3" <%= (order.getStatus() == 3) ? "selected" : ""%>>Complete</option>
                                                <option value="4" <%= (order.getStatus() == 4) ? "selected" : ""%>>Cancel</option>
                                            </select>
                                        </td>
                                        <td>
                                            <% if (order.getStatus() == 1 || order.getStatus() == 2) { %>
                                            <button type="submit" name="action" value="UpdateOrder" class="btn btn-primary btn-sm">Update</button>
                                            <% } %>
                                            <button type="submit" name="action" value="OrderDetail" class="btn btn-primary btn-sm">Show detail</button>
                                        </td>
                                    </tr>
                                </form>
                                <%
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="6" class="text-center">No orders found for the selected status.</td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>

                            <%
                                String message = (String) request.getAttribute("message");
                                String success = (String) request.getAttribute("success");
                                if (message == null) {
                                    message = "";
                                }
                                if (success == null) {
                                    success = "";
                                }
                            %>
                            <div class="d-flex justify-content-center">
                                <h3 style="color: red"><%= message%></h3>
                            </div>
                            <div class="d-flex justify-content-center">
                                <h3 style="color: #28a745"><%= success%></h3>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <%-- Scripts --%>
        <script src="js/admin.js"></script>
        <script src="js/sidebarAdmin.js"></script>
    </body>
</html>
