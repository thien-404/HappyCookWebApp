
<%@page import="User.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LoginedUser");
        %>



        <%--LoginForm--%>
        <div class="site-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-12 text-center">
                        <h2 class="h3 mb-3 text-black">Login</h2>
                    </div>
                    <div class="col-md-7 mx-auto">

                        <form action="MainController" method="POST">

                            <div class="p-3 p-lg-5 border " style="box-shadow: 0px 0px 10px -1px #ebebeb;">
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="c_username" class="text-black">Email <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" id="c_username" name="email" placeholder="Email" required="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="c_password" class="text-black">Password <span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" id="c_password" name="password" placeholder="Password" required="">
                                    </div>
                                </div>
                                <%
                                    String errorMessage = (String) request.getAttribute("message");
                                    String successful = (String) request.getAttribute("successful");
                                    if (successful != null && !successful.isEmpty()) {
                                %>
                                <div class="alert alert-success">
                                    <%= successful%>
                                </div>
                                <%
                                    }
                                    if (errorMessage != null && !errorMessage.isEmpty()) {
                                %>
                                <div class="alert alert-danger">
                                    <%= errorMessage%>
                                </div>
                                <% }%>

                                <div class="form-group row">
                                    <div class="col-lg-12">
                                        <input type="submit" name="action"class="btn btn-primary btn-lg btn-block" value="Login">
                                    </div>
                                </div>
                                <p class="text-center text-black">Do not have an account? 
                                    <a href="MainController?action=RegisterPage" >Register now.</a>
                                </p>

                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>


        <%-- footer --%>                        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
