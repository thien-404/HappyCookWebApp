<%@page import="java.util.ArrayList"%>
<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit User</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@coreui/coreui/dist/css/coreui.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/admin.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
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

                <%-- Main\ --%>
                <main class="col-md-10 content row">
                    <div class="search-bar my-3">
                        <form action="MainController" method="POST">
                            <div class="input-group">
                                <input type="text" name="EmailOrPhone" class="form-control " id="customer-name" placeholder="Enter Customer Email Or Phone.." value="<%= request.getParameter("EmailOrPhone") != null ? request.getParameter("EmailOrPhone") : "" %>">
                                <div class="input-group-append">
                                    <button type="submit" name="action" value="SearchEmailOrPhone" class="btn btn-primary">Search</button>
                                </div>
                            </div> 
                        </form>
                    </div>
                    <br>
                    <div class="my-3">
                        <h2>Manage Users</h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">User ID</th>
                                        <th scope="col">Full Name</th>
                                        <th scope="col">Phone</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">Role</th>
                                        <th scope="col">Update</th>
                                        <th scope="col">Block</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<UserDTO> userList = (ArrayList<UserDTO>) request.getAttribute("USER_LIST");
                                        if (userList != null) {
                                            for (UserDTO user : userList) {
                                    %>
                                <form action="MainController" method="POST" accept-charset="UTF-8">
                                    <tr>

                                    <input type="hidden" name="userid" value="<%= user.getUserID()%>">
                                    <td><%= user.getUserID()%></td>
                                    <td><input type="text" name="fullName" value="<%= user.getFullName()%>"></td>
                                    <td><input type="text" name="phone" value="<%= user.getPhone()%>"></td>
                                    <td><input type="text" name="email" value="<%= user.getEmail()%>"></td>
                                    <td><input type="text" name="address" value="<%= user.getAddress()%>"></td>
                                    <td><%= user.getRole()%></td>

                                    <input type="hidden" name="password" value="<%= user.getPassword()%>">
                                    <input type="hidden" name="status" value="<%= user.getStatus()%>">

                                    <% if (!"AD".equals(user.getRole())) {%>
                                    <td>
                                    
                                        <button type="submit" name="action" value="UpdateByAd" class="btn btn-primary btn-sm">Update</button>
                                    </td>
                                    <td>
                           
                                        <button type="submit" name="action" value="Block" class="btn btn-danger btn-sm" onclick=" return window.confirm('Do you want to block this person? ')">Block</button>
                                    </td>

                                    <% } %>
                                    </tr>
                                </form>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                            <%
                                String message = (String) request.getAttribute("message");
                                String success = (String) request.getAttribute("success");
                                if (success == null) {
                                    success = "";
                                }
                                if (message == null) {
                                    message = "";
                                }
                            %>
                            <div class="d-flex justify-content-center">
                                <h3 style="color: red"><%= message%></h3>
                            </div>
                             <div class="d-flex justify-content-center">
                                <h3 style="color: #28a745"><%= success %></h3>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>



        <script src="js/adminCatalory.js"></script>
        <script src="js/admin.js"></script>
        <script src="js/sidebarAdmin.js"></script>
    </body>
</html>
