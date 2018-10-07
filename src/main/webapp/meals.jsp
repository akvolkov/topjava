<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 07.10.2018
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border = '1'>
        <tr>
            <th>Дата/Время</th>
            <th>Описание</th>
            <th>Калории</th>
        </tr>
        <c:forEach var="meal" items="${pageContext.request.getAttribute('list')}">
            <c:if test = "${meal.exceed eq true}">
                <tr>
                    <td>
                        <font color="red"> ${meal.dateTime}</font>
                    </td>
                    <td>
                        <font color="red"> ${meal.description}</font>
                    </td>
                    <td>
                        <font color="red"> ${meal.calories}</font>
                    </td>
                </tr>
            </c:if>
            <c:if test = "${meal.exceed eq false}">
                <tr>
                    <td>
                        <font color="green"> ${meal.dateTime}</font>
                    </td>
                    <td>
                        <font color="green"> ${meal.description}</font>
                    </td>
                    <td>
                        <font color="green"> ${meal.calories}</font>
                    </td>
                </tr>
            </c:if>

        </c:forEach>
    </table>
</body>
</html>
