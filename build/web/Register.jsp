<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="site-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-12 text-center">
                        <h2 class="h3 mb-3 text-black">Register Account</h2>
                    </div>
                    <div class="col-md-7 mx-auto">
                     
                        <%-- Form UTF-8 --%>
                        <form action="MainController" method="POST" accept-charset="UTF-8">
                            <div class="p-3 p-lg-5 border " style="box-shadow: 0px 0px 10px -1px #ebebeb;">
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="fullName" class="text-black">Full Name <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" id="fullName" name="fullName">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="email" class="text-black">Email <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" id="email" name="email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="phone" class="text-black">Phone <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" id="phone" name="phone">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="address" class="text-black">Address <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" id="address" name="address">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="password" class="text-black">Password <span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" id="password" name="password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="confirm" class="text-black">Confirm Password <span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" id="confirm" name="confirm">
                                    </div>
                                </div>
                                   <% String message = (String) request.getAttribute("message"); %>
                        <% if (message != null) {%>
                        <div class="alert alert-danger"><%= message%></div>
                        <% }%>
                                <div class="form-group row">
                                    <div class="col-lg-12">
                                        <input type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="Register">
                                    </div>
                               
                                </div>  
                                <p class="text-center text-black">Already have an account?
                                    <a href="MainController?action=LoginPage">Login</a>
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
