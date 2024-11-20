
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="css/manager.css">
        <link rel="stylesheet" href="css/styleHome.css">
        <link rel="stylesheet" href="css/detail.css">       
    </head>
    <body>
        <%--Header--%>
        <jsp:include page="header.jsp"></jsp:include>      

        <%--Duong dan--%>
        <jsp:include page="path.jsp"></jsp:include>

        <%--Main Body--%>
        <div class="container">
            <div class="row">
                <%--show san pham moi--%>
                <jsp:include page="left.jsp"></jsp:include>

                <%--show san pham--%>      
                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <img src="images/${mealToId.imageUrl}" alt="Main Image" class="main-image"></div>
                                        </div>                                        
                                    </article> 
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${mealToId.mealName}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">US $</span><span class="num">${mealToId.mealPrice}</span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt class="mb-3">Nguyên Liệu </dt>
                                                    <dd>
                                                        <table class="table table-bordered table-striped" style="width:100px;"> 
                                                            <thead class="thead-light">
                                                            <div class="col-sm-6">
                                                                <tr >
                                                                    <th>Tên</th>
                                                                    <th>Số lượng</th>
                                                                    <th>Loại</th>
                                                                </tr>
                                                            </div>                                                                
                                                            <c:forEach items="${mealIngredient}" var="m">
                                                                <div class="col-sm-6">
                                                                    <tr class="col-sm-6">
                                                                        <td>${m.ingredientName}</td>
                                                                        <td>${m.quantity}</td>
                                                                        <td>${m.unit}</td>                                                                
                                                                    </tr> 
                                                                </div>
                                                                
                                                            </c:forEach>
                                                        </table>
                                                    </dd>
                                                </dl>  <!-- item-property .// -->
                                            </div> <!-- col.// -->

                                        </div> <!-- row.// -->
                                        <hr>
                                        <dl class="item-property">
                                            <dt>Cách làm</dt>
                                            <dd><p>
                                                    ${mealToId.decription}
                                                </p></dd>
                                        </dl>
                                        <hr>
                                        <a href="MainController?action=AddIngredientToCart&mealId=${mealToId.mealId}&mealName=${mealToId.mealName}" class="btn btn-lg btn-primary text-uppercase mb-3"> Mua Nguyên Liệu </a>
                                        <a href="MainController?action=AddToCart&mealId=${mealToId.mealId}&backHome=1" class="btn btn-lg btn-outline-primary text-uppercase mb-3"> <i class="fas fa-shopping-cart"></i> Thêm Vào Giỏ Hàng </a>
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->


                    </div>
                </div>
            </div>
        </div>

        <%--Footer--%>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
