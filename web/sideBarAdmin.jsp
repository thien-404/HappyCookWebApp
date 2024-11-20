<%@page import="User.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="css/admin.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <% UserDTO acc = (UserDTO) session.getAttribute("LoginedUser");%>

        <aside class="col-md-2 sidebar">

            <div class="admin-info text-center my-3">
                <img src="images/admin.jpg" alt="Admin Avatar" class="admin-avatar mb-2">
                <p>Welcome, <strong><%= acc.getFullName()%></strong></p>
            </div>
            <nav class="menu">
                <ul class="nav flex-column">
                    <li class="nav-item"><a class="nav-link text-white" href="MainController?action=Dashboard"><i class="fas fa-tachometer-alt"></i> Dashboard</a></li>
                    <li class="nav-item">
                        <a class="nav-link text-white nav-link-parent" href="#"><i class="fas fa-user"></i> Manage Users</a>
                        <ul id="manageUsers" class="nav flex-column ml-3 submenu">
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=ManageUsers">Edit Users</a></li>
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=BlockUsersList">Blocked Users</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white nav-link-parent" href="#"><i class="fas fa-utensils"></i> Manage Meal</a>
                        <ul id="manageMeal" class="nav flex-column ml-3 submenu">
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=AddMealPage">Add Meal</a></li>
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=ManageMeal">Edit Meal</a></li>
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=DeletedMealList">Deleted Meal</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white nav-link-parent" href="#"><i class="fas fa-carrot"></i> Manage Ingredient</a>
                        <ul id="manageIngredient" class="nav flex-column ml-3 submenu">
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=AddIngredientPage">Add Ingredient</a></li>
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=EditIngredient">Edit Ingredient</a></li>
                            <li class="nav-item"><a class="nav-link text-white" href="MainController?action=DeletedIngredientList">Deleted Ingredient</a></li>
                        </ul>
                    </li>
                     <li class="nav-item"><a class="nav-link text-white" href="MainController?action=ManageOrder"><i class="fas fa-receipt"></i> Manage Orders</a></li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="#"><i class="fas fa-calendar-alt"></i> Manage Menu</a>  
                    </li>           
                </ul>  

            </nav>
            <div class="logout-section">
                <a class="btn btn-danger btn-block" href="MainController?action=Logout">Logout</a>
            </div>
        </aside>



    </body>
</html>
