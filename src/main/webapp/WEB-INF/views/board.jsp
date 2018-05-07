<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <ul class="nav navbar-nav">
            <li class="active"><a href="/board-list">Home</a></li>
        </ul>
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
                <a type="button" class="btn btn-info" onclick="changeDisplayForTwoElements('menu', 'edit')">No
                    thanks</a>
            </form:form>
        </div>
    </div>

    <section class="task-list-container">
        <c:forEach items="${list}" var="taskList">
            <div class="task-list">
                <h4>${taskList.name}</h4>
                <ul class="list">
                    <c:forEach items="${taskList.tasks}" var="task">
                        <li class="task">${task.name}</li>
                    </c:forEach>
                </ul>
                <div id="new-${taskList.id}" class="add-task">
                    <a onclick="changeDisplayForTwoElements('save-${taskList.id}', 'new-${taskList.id}')">Add task ...</a>
                </div>
                <div id="save-${taskList.id}" class="save-task">
                    <form:form method="post" modelAttribute="task" action="/add-task?id=${taskList.id}">
                        <fieldset>
                            <form:input class="title-task" path="name" placeholder="Add task title" type="text"/>
                        </fieldset>
                        <button type="submit" class="btn btn-success">Save</button>
                        <button type="button" onclick="changeDisplayForTwoElements('new-${taskList.id}', 'save-${taskList.id}')"
                                class="close" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </form:form>
                </div>
            </div>
        </c:forEach>

        <div id="new-list" class="add-list" onclick="changeDisplayForTwoElements('save-list', 'new-list')">
            <h4>Add a list... </h4>
        </div>

        <div id="save-list" class="task-list" style="display: none;">
            <form:form method="post" action="/add-task-list?id=${board.id}" modelAttribute="newList">
                <form:input class="title-task-list" path="name" placeholder="Add task-list title" type="text"/>
                <button type="submit" class="btn btn-success">Save</button>
                <button type="button" class="close" aria-label="Close">
                    <span aria-hidden="true"
                          onclick="changeDisplayForTwoElements('new-list', 'save-list')">&times;</span>
                </button>
            </form:form>
        </div>
    </section>
</div>
</body>
</html>
