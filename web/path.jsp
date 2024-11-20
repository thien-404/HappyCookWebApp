<%-- 
    Document   : path
    Created on : Jul 6, 2024, 4:00:39 PM
    Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Test Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" crossorigin="anonymous">
        <link rel="stylesheet" href="css/styleHome.css" type="text/css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="MainController">Trang chủ</a></li>                                                   
                            <c:choose>
                                <c:when test="${type == '1'}">
                                    <li class="breadcrumb-item">Món Mặn</li>
                                </c:when>
                                <c:when test="${type == '2'}">
                                    <li class="breadcrumb-item">Món Chay</li>
                                </c:when>
                                <c:when test="${type == '3'}">
                                    <li class="breadcrumb-item">Đồ Uống</li>
                                </c:when>
                                <c:when test="${mealName != null}">
                                    <li class="breadcrumb-item">Kết Quả Tìm Kiếm Cho "${mealName}"</li>
                                </c:when>
                                <c:when test="${mealToId != null}">
                                <li class="breadcrumb-item">${mealToId.mealName}</li>
                                </c:when>
                            </c:choose>                            
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>
