<%--
  Created by IntelliJ IDEA.
  User: kerich
  Date: 23.12.2022
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Greeting</title>
    <%@include file="fragment/head.jsp" %>
</head>
<body>
<div class="row d-flex justify-content-center mt-3">
    <div class="col-auto">
        <a href="/users-list" class="btn btn-primary" tabindex="-1" role="button">User list</a>
        <a href="/logout" class="btn btn-secondary" tabindex="-1" role="button">Logout</a>
    </div>
</div>
</body>
</html>
