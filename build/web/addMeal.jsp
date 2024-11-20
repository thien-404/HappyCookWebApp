<%@page import="java.util.ArrayList"%>
<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Meal</title>
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
                <main class="content">

                    <form action="MainController" method="POST" enctype="multipart/form-data" accept-charset="UTF-8" >
                        <h2 class="my-4">Add Meal</h2>
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="mealName">Meal Name</label>
                                <input type="text" class="form-control" id="mealName" name="mealName" placeholder="Enter meal name" required>
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="mealPrice">Price</label>
                                <input type="number" step="0.01" class="form-control" id="mealPrice" name="mealPrice" placeholder="Enter meal price" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea class="form-control" id="content" name="content" rows="3" placeholder="Enter meal content" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea class="form-control" id="description" name="description" rows="3" placeholder="Enter meal description" required></textarea>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="discountID">Discount ID</label>
                                <input type="text" class="form-control" id="discountID" name="discountID"  placeholder="Enter discount ID">
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="typeOfFood">Type of Food</label>
                                <select class="form-control" id="typeOfFood" name="typeOfFood" required >
                                    <option value="1">Món Mặn</option>
                                    <option value="2">Món Chay</option>
                                    <option value="3">Món Nước</option>
                                    <option value="4">Khác</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col-lg-6">
                                <label for="image">Image</label>
                                <input class="form-control" type="file" id="image" name="image">
                            </div>
                            <button type="submit" name="action" value="Create" class="btn btn-primary btn-sm">Create</button>
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
                                        <h3 style="color: #28a745"><%= success%></h3>
                                    </div>

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
        <script>
            function previewImage(event) {
                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('preview');
                    output.src = reader.result;
                    output.style.display = 'block';
                };
                reader.readAsDataURL(event.target.files[0]);
            }
        </script> 
    </body>
</html>
