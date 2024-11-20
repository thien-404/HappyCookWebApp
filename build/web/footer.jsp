<%-- 
    Document   : footer
    Created on : Jun 29, 2024, 10:31:57 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
                <footer class="site-footer border-top" style="background-color: rgb(52 58 64)">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <div class="row">
                            <div class="col-md-12 ">
                                <h3 class="footer-heading mb-4 text-white">Navigations</h3>
                            </div>
                            <div class="col-md-6 col-lg-4">
                                <ul class="list-unstyled">
                                    <li><a href="MainController?action=RegisterPage" class="text-white">Create new account</a></li>
                                    <li><a href="MainController?action=LoginPage" class="text-white">Login</a></li>
                                    <li><a href="MainController" class="text-white">Menu</a></li>

                                </ul>
                            </div>
                            <div class="col-md-6 col-lg-4">
                                <ul class="list-unstyled">
                                    <%--      <li><a href="shop.jsp">Shop</a></li>--%>

                                    <li><a href="about.jsp" class="text-white">About</a></li>

                                </ul>
                            </div>

                        </div>
                    </div>
                    
                    <div class="col-md-6 col-lg-3 mb-4 mb-lg-0">
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <div class="block-5 mb-5">
                            <h3 class="footer-heading mb-4 text-white">  Contact Info</h3>
                            <ul class="list-unstyled">
                                <li class="text-white"><i class="fas fa-map-marker-alt"></i>  FPT University</li>
                                <li><i class="fas fa-phone text-white"></i><a href="tel://1234567"  class="text-white">  +2 392 3929 210</a></li>
                                <li class="text-white"><i class="fas fa-envelope"></i>  cooking@fpt.edu.vn</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </footer>
    </body>
</html>
