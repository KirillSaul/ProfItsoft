<%--
  Created by IntelliJ IDEA.
  User: kerich
  Date: 25.12.2022
  Time: 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>UsersList</title>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"
              prefix="c" %>
    <%@include file="fragment/head.jsp" %>
</head>
<body>
<div class="row d-flex justify-content-start">
    <div class="col-auto">
        <a href="/greeting" class="btn btn-primary" tabindex="-1" role="button">Back</a>
    </div>
</div>
<div class="row d-flex justify-content-center">
    <div class="col-auto">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">UserName</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${usersList}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.username}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
