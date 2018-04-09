<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>
<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="#" class="navbar-brand">TASK-LIST</a>
    </div>
</nav>

<div class="container" style="display: flex; justify-content: center;">
    <div class="col-md-4">
        <form action="/login" method="post">

            <c:if test="${empty showMessage}">
                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                    <div class="alert alert-danger">
                        <p>Invalid login or password.</p>
                    </div>
                </c:if>
            </c:if>

            <c:if test="${showMessage}">
                <div class="alert alert-success">
                    <p>${message}</p>
                </div>
            </c:if>

            <fieldset class="form-group">
                <label path="">Login</label>
                <input name="login" type="text" class="form-control"/>
            </fieldset>
            <fieldset class="form-group">
                <label>Password</label>
                <input name="password" type="password" class="form-control"/>
            </fieldset>
            <button type="submit" class="btn btn-success">Log in</button>
            <a type="button" class="btn btn-default" href="/registration">Sing up</a>

        </form>
    </div>
</div>
</body>
</html>
