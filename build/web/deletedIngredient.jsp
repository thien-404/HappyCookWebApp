<%@page import="Product.IngredientDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Deleted Ingredient</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@coreui/coreui/dist/css/coreui.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
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
                <%-- Main --%>
                <main class="col-md-10 content row">
                    <div class="my-3">
                        <h2>Deleted Ingredients List</h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Unit</th>
                                        <th scope="col">Role</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<IngredientDTO> IngredientList = (ArrayList<IngredientDTO>) request.getAttribute("DeletedIngredient");
                                        if (IngredientList != null) {
                                            for (IngredientDTO ingredient : IngredientList) {
                                    %>
                                <form action="MainController" method="POST">
                                    <tr>
                                        <td><input type="hidden" name="ingredientId" value="<%= ingredient.getIngredientId()%>"><%= ingredient.getIngredientId()%></td>
                                        <td><input type="text" name="ingredientName" value="<%= ingredient.getIngredientName()%>"></td>
                                        <td><input type="number" name="ingredientPrice" value="<%= ingredient.getIngredientPrice()%>"></td>
                                        <td><input type="text" name="unit" value="<%= ingredient.getUnit()%>"></td>
                                        <td><input type="text" name="role" value="<%= ingredient.getRole()%>"></td>
                                        <td>
                                            <button type="submit" name="action" value="RestoreInredient" class="btn btn-primary btn-sm">Restore</button>
                                        </td>
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
                                <h3 style="color: #28a745"><%= success%></h3>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@coreui/coreui/dist/js/coreui.bundle.min.js"></script>
        <script src="js/sidebarAdmin.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/adminCatalory.js"></script>
        <script src="js/editMeal.js"></script>

    </body>
</html>
