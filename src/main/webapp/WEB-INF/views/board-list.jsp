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
        <div id="${board.id}" class="board" onmouseover="showOrHideElement('remove-${board.id}')"
             onmouseout="showOrHideElement('remove-${board.id}')">
            <a class="board-link" href="/board?id=${board.id}" >${board.name}</a>
            <button id="remove-${board.id}" class="remove" onclick="removeBoard('${board.id}')">
                <span aria-hidden="true" >&times;</span></button>
        </div>
        </c:forEach>

        <div class="save-board" id="save-board">
            <form:form method="post" modelAttribute="board" action="/board-list" autocomplete="off">
                <form:hidden path="id"/>
                <fieldset>
                    <form:input class="title-board" path="name" placeholder="Add board title" type="text"/>
                </fieldset>
                <button type="submit" class="btn btn-success" id="button-save">Save</button>
                <button type="button" class="close" onclick="changeDisplayForTwoElements('add-board', 'save-board')">
                    <span aria-hidden="true">&times;</span>
                </button>
            </form:form>
        </div>

        <div id="modal" class="modal-window">
            <div class="content">
                <div class="content-message">
                    <h3 id="remove">Remove the board?</h3>
                    <p>Once you remove a board, there is no going back. Please be certain.</p>
                </div>
                <a type="button" id="remove-board-by-id" class="btn btn-danger">Remove</a>
                <a type="button" class="btn btn-default" onclick="showOrHideElement('modal')">No thanks</a>
            </div>
        </div>

        <div class="create-board" id="add-board" onclick="changeDisplayForTwoElements('save-board', 'add-board')">
            <p>Create new board</p>
        </div>

    </div>
</div>
</body>
</html>
