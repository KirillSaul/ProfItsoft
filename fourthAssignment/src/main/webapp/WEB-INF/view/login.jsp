<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Login</title>
    <%@include file="fragment/head.jsp" %>
</head>
<body>
<form method="post" action="/login">
    <div class="row d-flex justify-content-center">
        <div class="col-auto">
            <div class="row mt-3">
                <label for="username">Введите логин:</label>
                <input name="username" id="username" type="text" required>
            </div>
            <div class="row mt-3">
                <label for="password">Введите пароль:</label>
                <input name="password" id="password" type="password" required>
            </div>
            <div class="row mt-3">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
            <c:if test="${bindingResult != null}">
                <div class="row mt-3">
                    <div class="alert alert-danger alert-dismissible"
                         role="alert">
                            ${bindingResult.globalError.defaultMessage}
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</form>
</body>
</html>
