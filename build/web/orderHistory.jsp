<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" crossorigin="anonymous">
        <link rel="stylesheet" href="css/styleHome.css" type="text/css">
        <title>Order History</title>
        <link rel="stylesheet" type="text/css" href="path/to/your/css/style.css">
        <style>
            .order-details {
                display: none;
            }
        </style>
    </head>
    <body>
        <%--header--%>
        <jsp:include page="header.jsp"></jsp:include>
            <br>
            <div class="container">
            <c:if test="${not empty ORDERHISTORY}">
                <c:forEach var="orderGroup" items="${requestScope.ORDERHISTORY}">
                    <h2>${orderGroup.key}</h2>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 100px">Order ID</th>
                                <th style="width: 250px">Order Date</th>
                                <th style="width: 250px">Total Price</th>
                                <th style="width: 250px">Status</th>
                                <th style="width: 210px"></th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orderGroup.value}">
                                <tr>
                                    <td>${order.orderId}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.totalPrice}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.status == 1}">
                                                <span class="badge badge-warning">Pending</span>
                                            </c:when>
                                            <c:when test="${order.status == 2}">
                                                <span class="badge badge-info">Processing</span>
                                            </c:when>
                                            <c:when test="${order.status == 3}">
                                                <span class="badge badge-success">Complete</span>
                                            </c:when>
                                            <c:when test="${order.status == 4}">
                                                <span class="badge badge-secondary">Cancel</span>
                                            </c:when>
                                            <c:otherwise>Unknown</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <button class="btn btn-primary view-details" data-order-id="${order.orderId}">View</button>
                                        <c:if test="${order.status == 1 || order.status == 2}">
                                            <form action="MainController" method="POSST" style="display:inline;">
                                                <input type="hidden" name="orderid" value="${order.orderId}">
                                                <button type="submit" name="action" value="Cancel" class="btn btn-danger" onclick="return confirm('Are you sure to cancel this order?')">Cancel</button>
                                            </form>
                                        </c:if>
                                    </td>

                                </tr>
                                <tr class="order-details" id="order-details-${order.orderId}">
                                    <td colspan="5">
                                        <div id="meals-${order.orderId}"></div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>
            </c:if>
            <c:if test="${empty ORDERHISTORY}">
                <p>No orders found.</p>
            </c:if>
        </div>
            <br>
        <%--Footer--%>
        <jsp:include page="footer.jsp"></jsp:include>
        
        
        
        <script>
            $(document).ready(function () {
                $('.view-details').click(function () {
                    var orderId = $(this).data('order-id');
                    var detailsRow = $('#order-details-' + orderId);
                    if (detailsRow.is(':visible')) {
                        detailsRow.hide();
                    } else {
                        if (detailsRow.data('loaded') !== true) {
                            $.ajax({
                                url: 'GetOrderHistoryDetailsServlet', // Servlet to fetch order details
                                type: 'GET',
                                data: {orderId: orderId},
                                success: function (response) {
                                    $('#meals-' + orderId).html(response);
                                    detailsRow.data('loaded', true);
                                }
                            });
                        }
                        detailsRow.show();
                    }
                });
            });
        </script>

    </body>
</html>