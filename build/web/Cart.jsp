
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>

    <body>
        <c:if test="${sessionScope.LoginedUser == null}">
            <% request.getRequestDispatcher("MainController?action=LoginPage&message=Ban can dang nhap de mua do").forward(request, response); %>
        </c:if>
        <jsp:include page="header.jsp"></jsp:include>        
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                                        
                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Đơn Giá</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Số Lượng</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Xóa</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="entry" items="${sessionScope.cart.entrySet()}">
                                                <c:set var="key" value="${entry.key}" />
                                                <c:set var="value" value="${entry.value}" />
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="images/${key.imageUrl}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="MainController?action=ShowDetail&id=${key.mealId}" class="text-dark d-inline-block">${key.mealName}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${key.mealPrice}</strong></td>
                                                    <td class="align-middle">
                                                        <a href="MainController?action=AddToCart&mealId=${key.mealId}&isMinus=1"><button class="btnSub">-</button></a> 
                                                        <strong>${value}</strong>
                                                        <a href="MainController?action=AddToCart&mealId=${key.mealId}"><button class="btnAdd">+</button></a>
                                                    </td>
                                                    <td class="align-middle"><a href="MainController?action=CartController&mealId=${key.mealId}&backHome=1" class="text-dark">
                                                            <button type="button" class="btn btn-danger">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr>
                                                <c:set var="meal" value="${meal + (key.mealPrice*value)}" />
                                            </c:forEach>
                                                
                                            <c:forEach var="entry2" items="${sessionScope.Ingrecart.entrySet()}">
                                                <c:set var="key2" value="${entry2.key}" />
                                                <c:set var="value2" value="${entry2.value}" />
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="MainController?action=ShowDetail&id=${key2.mealId}" class="text-dark d-inline-block">Nguyên Liệu ${key2.mealName}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${key2.totalPrice}</strong></td>
                                                    <td class="align-middle">
                                                        <a href="MainController?action=AddIngredientToCart&mealId=${key2.mealId}&isMinus=1"><button class="btnSub">-</button></a> 
                                                        <strong>${value2}</strong>
                                                        <a href="MainController?action=AddIngredientToCart&mealId=${key2.mealId}"><button class="btnAdd">+</button></a>
                                                    </td>
                                                    <td class="align-middle"><a href="MainController?action=IngredientCartController&mealId=${key2.mealId}&backHome=1" class="text-dark">
                                                            <button type="button" class="btn btn-danger">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr>
                                                <c:set var="ingredient" value="${ingredient + (key2.totalPrice*value2)}" />
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                                <c:if test="${message != null}">
                                    <div class="col-lg-12 d-flex justify-content-center align-middle text-danger p"><h5>${message}</h5></div>         
                                </c:if>                               
                            </div>                                
                        </div>
                            
                        <c:set var="total" value="${meal+ingredient}" />    
                        <c:set var="vat" value="${total*10/100}" />
                        <c:set var="all" value="${total + vat}" />
                        <div class="row py-5 p-4 bg-white rounded shadow-sm">                           
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
                                <div class="p-4">
                                    <div class="input-group mb-4 border rounded-pill p-2">
                                        <input type="text" placeholder="Nhập Voucher" aria-describedby="button-addon3" class="form-control border-0">
                                        <div class="input-group-append border-0">
                                            <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Sử dụng</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong>${total} $</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Phí vận chuyển</strong><strong>Free ship</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>${vat}</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng thanh toán</strong>
                                            <h5 class="font-weight-bold">${all} $</h5>
                                        </li>
                                    </ul><a href="MainController?action=SaveOrder&total=${all}" class="btn btn-dark rounded-pill py-2 btn-block">Mua hàng</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
</html>
