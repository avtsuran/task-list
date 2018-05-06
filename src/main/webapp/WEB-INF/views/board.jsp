<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: avtsu
  Date: 06.05.2018
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board | ${board.name}</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/board.css" rel="stylesheet">
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>

<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="#" class="navbar-brand">TASK-LIST</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>
<div class="board-container">
    <div class="head">
        <div id="menu">
            <div class="main-menu">
                <div class="board-name">${board.name}</div>
                <a type="button" class="btn btn-default" onclick="changeDisplayForTwoElements('edit', 'menu')">Edit</a>
            </div>
        </div>
        <div id="edit">
            <form:form class="form-edit" method="post" modelAttribute="board">
                <form:hidden path="id"/>
                <form:input class="edit-input" path="name" placeholder="Edit board " type="text"/>
                <button type="submit" class="btn btn-success" style="margin-right: 11px">Save</button>
                <a type="button" class="btn btn-info" onclick="changeDisplayForTwoElements('menu', 'edit')">No thanks</a>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
