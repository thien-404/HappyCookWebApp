<%@page import="mylib.OrderUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Order.OrderDTO"%>
<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
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

        <% UserDTO acc = (UserDTO) session.getAttribute("LoginedUser");
            if (acc == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else if ("US".equals(acc.getRole())) {
                response.sendRedirect("MainController");
            }
            ArrayList<OrderDTO> list = (ArrayList<OrderDTO>) request.getAttribute("ActicveOrder");
        %>
        <div class="container-fluid">
            <div class="row">
                <%-- Sidebar --%>
                <jsp:include page="sideBarAdmin.jsp"></jsp:include>
                <%-- Main --%>
                <main class="col-md-10 content">
                    <br>
                    <div class="row">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card text-white bg-info">
                                <div class="card-body">
                                    <div class="card-title"><i class="fas fa-user"></i> Total Users</div>
                                    <h2 class="card-text">${totalUser}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card text-white bg-success">
                                <div class="card-body">
                                    <div class="card-title"><i class="fas fa-receipt"></i> Total Orders</div>
                                    <h2 class="card-text">${totalOrder}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card text-white bg-primary">
                                <div class="card-body">
                                    <div class="card-title">$ Total Revenue</div>
                                    <h2 class="card-text">${totalPrice} $</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Recent Orders</h5>
                                    <table class="table table-striped text-center">
                                        <thead>
                                            <tr>
                                                <th>Order ID</th>
                                                <th>Customer Name</th>
                                                <th>Total Price</th>
                                                <th>Status</th>
                                                <th>Address</th>
                                                <th>Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for(OrderDTO od :list) {%>
                                            <tr>
                                                <td><%= od.getOrderId() %></td>
                                                <td><%= od.getFullName() %></td>
                                                <td><%= od.getTotalPrice() %></td>
                                                <td><%= OrderUtil.getStatusText(od.getStatus())%></td>
                                                <td><%= od.getAddress() %></td>
                                                <td><%= od.getOrderDate() %></td>
                                              
                                            </tr>
                                                        <% }%> 
                                            <!-- Add more rows as needed -->
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>


                </main>
            </div>
        </div>

        <%--script--%>                        
        <script src="js/admin.js"></script>
        <script src="js/sidebarAdmin.js"></script>
    </body>
</html>
