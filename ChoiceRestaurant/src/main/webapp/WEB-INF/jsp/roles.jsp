<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Role list</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Roles</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        </thead>
        <c:forEach items="${roles}" var="role">
            <jsp:useBean id="role" type="ru.choicerestaurant.model.Role"/>
            <tr>
                <td>${role.id}</td>
                <td>${role.name}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>