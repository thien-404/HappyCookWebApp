<%@page import="java.util.ArrayList"%>
<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Ingredients</title>
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>

    <body>
        <%
            UserDTO acc = (UserDTO) session.getAttribute("LoginedUser");
            if (acc == null) {
                request.getRequestDispatcher("MainController?action=LoginPage").forward(request, response);
            } else if ("US".equals(acc.getRole())) {
                response.sendRedirect("MainController");
            }
        %>
        <div class="container-fluid">
            <div class="row">
                <%-- Sidebar --%>
                <jsp:include page="sideBarAdmin.jsp"></jsp:include>

                <%-- Main --%>   
                <main class="content">

                    <form action="MainController" method="POST" accept-charset="UTF-8" >
                        <h2 class="my-4">Add Ingredients</h2>
                        <div class="row">
                            <div class="form-group">
                                <label for="ingredientName">Ingredient Name</label>
                                <input type="text" class="form-control" id="ingredientName" name="ingredientName" placeholder="Enter ingredient name" required>
                            </div>
                            <div class="form-group">
                                <label for="ingredientPrice">Price</label>
                                <input type="number" step="0.01" class="form-control" id="ingredientPrice" name="ingredientPrice" placeholder="Enter ingredient price" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label for="unit">Unit</label>
                                <input type="text" class="form-control" id="unit" name="unit" placeholder="Enter ingredient unit" required>
                            </div>
                            <div class="form-group">
                                <label for="role">Role</label>
                                <input type="text" class="form-control" id="role" name="role" placeholder="Enter ingredient role" required>
                            </div>
                        </div>

                        <button type="submit" name="action" value="CreateIngredient" class="btn btn-primary btn-sm">Create</button>
                        <div class="form-group row">   
                            <div class="col-lg-12">
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

                    </form>
                </main>
            </div>
        </div>

        <script src="js/sidebarAdmin.js"></script>                            
        <script src="js/adminCatalory.js"></script>
        <script src="js/admin.js"></script>

    </body>
</html>
