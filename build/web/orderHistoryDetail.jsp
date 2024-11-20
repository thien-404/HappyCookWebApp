<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Meal Name</th>
            <th>Image</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>IsIngredient</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="meal" items="${meals}">
            <tr>
                <td>
                    <h5 class="mb-0"> <a href="MainController?action=ShowDetail&id=${meal.mealId}" class="text-dark d-inline-block">${meal.mealName}</a></h5>             
                </td>
                <td><img src="images/${meal.imageUrl}" alt="${meal.mealName}" height="60px" width="90px"></td>
                <td>${meal.mealPrice}</td>
                <td>${meal.quantity}</td>
                <td>
                    <input style="  width: 20px;  height: 20px; " type="checkbox" disabled   <c:if test="${meal.isIngredient == 1}">checked</c:if> />
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
