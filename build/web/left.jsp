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
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Danh mục sản phẩm</div>
                        <ul class="list-group category_block">                           
                            <li class="list-group-item text-white ${type == '1'? "active":""}"><a href="MainController?value=1">Món Mặn</a></li>
                            <li class="list-group-item text-white ${type == '2'? "active":""}"><a href="MainController?value=2">Món Chay</a></li>
                            <li class="list-group-item text-white ${type == '3'? "active":""}"><a href="MainController?value=3">Đồ uống</a></li>
                        </ul>
                    </div>
                    <%--blank--%>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Món Mới</div>
                        <div class="card-body">
                            <img class="img-fluid" src="images/${mealLast.imageUrl}" />
                            <h5 class="p-2 card-title"> <a href="MainController?action=ShowDetail&id=${mealLast.mealId}">${mealLast.mealName}</a></h5>
                            <p class="card-text">${mealLast.content}</p>
                            <p class="bloc_left_price">${mealLast.mealPrice} $</p>
                        </div>
                    </div>
                </div>
    </body>
</html>
