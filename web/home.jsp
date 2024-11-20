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
        <%--header--%>
        <jsp:include page="header.jsp"></jsp:include>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">ĐỒ ĂN NGON PHẢI DO TAY BẠN NẤU</h1>
                <p class="lead text-muted mb-0">Uy tín 100% khâu nguyên liệu tươi ngon, Vận chuyển trong 1h, Hướng dẫn siêu dễ dàng</p>
            </div>
        </section>
        
        <%--Duong dan--%>
        <jsp:include page="path.jsp"></jsp:include>
        

        <%--Main Body--%>
        <div class="container">
            <div class="row">
                <%--thanh tac vu--%>
                <jsp:include page="left.jsp"></jsp:include>               

                <%--show san pham--%>      
                <div class="col-sm-9">
                    <div class="row">
                        <c:forEach items="${mealList}" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img style=" height: 200px" class="card-img-top" src="images/${o.imageUrl}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="MainController?action=ShowDetail&id=${o.mealId}" title="View Product">${o.mealName}</a></h4>
                                        <p class="card-text show_txt">${o.content}</p>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${o.mealPrice} $</p>
                                            </div>
                                            <div class="col">
                                                <a href="MainController?action=AddToCart&mealId=${o.mealId}&backHome=1" class="btn btn-success btn-block">Thêm Vào Giỏ Hàng</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>                
            </div>
        </div>              
        <%--Footer--%>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
