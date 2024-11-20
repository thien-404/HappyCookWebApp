<%-- 
    Document   : header
    Created on : Jul 6, 2024, 3:36:02 PM
    Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Test Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> 
        <link rel="stylesheet" href="css/styleHome.css" type="text/css">
        <style>
            .navbar-nav {
                flex-direction: row;
            }
            .nav-item {
                margin-left: 15px;
            }
            .form-inline {
                display: flex;
                flex-direction: row;
                align-items: center;
            }
            .btn-group {
                display: flex;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark w-100">
            <div class="container-fluid">
                <a class="navbar-brand" href="MainController">BA MIỀN QUÁN</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-between" id="navbarsExampleDefault">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="MainController">Trang Chủ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?value=1">Món Mặn</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?value=2">Món Chay</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?value=3">Đồ Uống</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Thời Khóa Biểu</a>
                        </li>
                    </ul>

                    <form action="MainController" method="post" class="form-inline my-2 my-lg-0">
                        <div class="input-group input-group-sm">
                            <input name="mealName" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>                        
                    </form>
                    
                    <div class="btn-group">
                        <c:if test="${sessionScope.LoginedUser != null}">
                            <a class="btn btn-success btn-sm ml-3" href="MainController?action=AddToCart">
                            <i class="fa fa-shopping-cart"></i> Giỏ Hàng
                            <span class="badge badge-light">${sessionScope.cart.size()}</span>
                            </a>
                            <a class="btn btn-info btn-sm ml-3" href="MainController?action=OrderHistory">
                            <i class="fa fa-history"></i> Lịch sử mua hàng
                            </a>
                            <a class="btn btn-danger btn-sm ml-3" href="MainController?action=Logout">
                            <i class="fas fa-sign-in-alt"></i> Đăng Xuất                           
                            </a>
                        </c:if>
                        
                        <c:if test="${sessionScope.LoginedUser == null}">
                            <a class="btn btn-primary btn-sm ml-3" href="MainController?action=LoginPage">
                            <i class="fas fa-sign-in-alt"></i> Đăng nhập                           
                            </a>
                            <a class="btn btn-primary btn-sm ml-3" href="MainController?action=RegisterPage">
                            <i class="fas fa-user-plus"></i> Đăng Kí                          
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </nav>
    </body>
</html>
