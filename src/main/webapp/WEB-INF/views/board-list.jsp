<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Boards</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="css/board-list.css" rel="stylesheet">
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
<div class="container">
    <div class="board-container">

        <c:forEach items="${boards}" var="board">
            <a class="board" href="/board?id=${board.id}">${board.name}</a>
        </c:forEach>

        <div class="save-board" id="save-board">
            <form:form method="post" modelAttribute="board" action="/board-list">
                <form:hidden path="id"/>
                <fieldset>
                    <form:input class="title-board" path="name" placeholder="Add board title" type="text"/>
                </fieldset>
                <button type="submit" class="btn btn-success" id="button-save">Save</button>
                <button type="button" class="close" aria-label="Close" >
                    <span aria-hidden="true" onclick="show('add-board', 'save-board')">&times;</span>
                </button>
            </form:form>
        </div>

        <div class="create-board" id="add-board" onclick="show('save-board', 'add-board')">Create new board</div>

    </div>
</div>
</body>
</html>
