<%@page import="Product.MealDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Meal</title>
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
                        <h2>Manage Meals</h2>
                        <button type="button" class="btn btn-info add-ingredient" data-toggle="modal" data-target="#ingredientModal"><i class="fas fa-plus"></i>Add Ingredients</button>
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col" style="width: 5%;">ID</th>
                                        <th scope="col" style="width: 12%;">Name</th>
                                        <th scope="col" style="width: 25%;">Content</th>
                                        <th scope="col" style="width: 25%;">Description</th>
                                        <th scope="col" style="width: 8%;">Price</th>
                                        <th scope="col" style="width: 5%;">DiscountID</th>
                                        <th scope="col" style="width: 12%;">Type Of Food</th>
                                        <th scope="col" style="width: 25%;">Image</th>
                                        <th scope="col" style="width: 8%;">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<MealDTO> list = (ArrayList<MealDTO>) request.getAttribute("ManageMealList");
                                        if (list != null) {
                                            for (MealDTO meal : list) {
                                    %>
                                <form action="MainController" method="POST" enctype="multipart/form-data">
                                    <tr>
                                        <td><input type="hidden" name="mealID" value="<%= meal.getMealId()%>"><%= meal.getMealId()%></td>
                                    <input type="hidden" name="mealStatus" value="<%= meal.getMealStatus()%>">
                                    <input type="hidden" name="image" value="<%= meal.getImageUrl()%>">
                                    <td><textarea name="mealName" style="width: 100%; height: 60px;"><%= meal.getMealName()%></textarea></td>
                                    <td><textarea name="content" style="width: 100%; height: 80px;"><%= meal.getContent()%></textarea></td>
                                    <td><textarea name="description" style="width: 100%; height: 80px;"><%= meal.getDecription()%></textarea></td>
                                    <td><input type="number" name="mealPrice" value="<%= meal.getMealPrice()%>" step="0.01" style="width: 100%;"></td>
                                    <td><input type="number" name="discountID" value="<%= meal.getDiscountId()%>" style="width: 100%;"></td>
                                    <td><select class="form-control" id="typeOfFood" name="typeOfFood" required style="width: 100%" >
                                            <option value="1" <%= meal.getTypeOfFood() == 1 ? "selected" : ""%>>Món Mặn</option>
                                            <option value="2" <%= meal.getTypeOfFood() == 2 ? "selected" : ""%>>Món Chay</option>
                                            <option value="3" <%= meal.getTypeOfFood() == 3 ? "selected" : ""%>>Món Nước</option>
                                            <option value="4" <%= meal.getTypeOfFood() == 4 ? "selected" : ""%>>Khác</option>
                                        </select></td>
                                    <td>
                                        <img id="preview" src="images/<%= meal.getImageUrl()%>" alt="<%= meal.getMealName()%>" class="img-thumbnail" width="100">
                                    </td>
                                    <td>
                                        <button type="submit" name="action" value="UpdateMeal" class="btn btn-primary btn-sm">Update</button>
                                        <button type="submit" name="action" value="DeleteMeal" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?Do you want to delete this meal?')">Delete</button>  
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
                                <h3 style="color: #28a745"><%= success %></h3>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <%-- Modal for Adding Ingredients --%>
        <div class="modal fade" id="ingredientModal" tabindex="-1" role="dialog" aria-labelledby="ingredientModalLabel" aria-hidden="true">
            <form action="MainController" method="POST" id="ingredientForm">

                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="ingredientModalLabel">Add Ingredients</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Ingredients</label>
                                <div id="ingredientsList">
                                    <div class="input-group mb-2">
                                        <input type="number" class="form-control" name="MealIDs[]" placeholder="Enter Meal ID" autocomplete="off" required>
                                        <input type="number" class="form-control" name="ingredientIDs[]" placeholder="Enter ingredient ID" autocomplete="off" required>
                                        <input type="number" class="form-control" name="ingredientQuantities[]" placeholder="Enter ingredient quantity" autocomplete="off" required>
                                        <div class="input-group-append">
                                            <button type="button" class="btn btn-danger remove-ingredient"><i class="fas fa-minus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-success" id="addIngredient"><i class="fas fa-plus"></i> Add Ingredient</button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="mealID" id="modalMealID">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" name="action" value="SaveMealIngredient">Save Ingredients</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@coreui/coreui/dist/js/coreui.bundle.min.js"></script>
        <script src="js/sidebarAdmin.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/adminCatalory.js"></script>
        <script src="js/editMeal.js"></script>

    </body>
</html>
