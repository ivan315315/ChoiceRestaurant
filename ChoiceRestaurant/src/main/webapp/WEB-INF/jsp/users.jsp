<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>User list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Users</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>password</th>
            <th>registered</th>
            <th>enabled</th>

        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="ru.choicerestaurant.model.User"/>
            <tr class="${user.enabled ? 'normal' : 'excess'}">
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.registered}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>