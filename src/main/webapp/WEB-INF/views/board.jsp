<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="board" tagdir="/WEB-INF/tags/board" %>
<%@ taglib prefix="modal" tagdir="/WEB-INF/tags/modal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${board.name} | Board</title>
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
            <board:edit-board model="board"/>
        </div>
    </div>

    <section class="task-list-container">
        <c:forEach items="${list}" var="taskList">
            <div id="${taskList.id}" class="task-list">
                <div class="task-list-head">
                    <div id="name-${taskList.id}">
                        <h4>${taskList.name}</h4>
                    </div>
                    <div id="form-edit-${taskList.id}" class="hide-block">
                        <board:edit-task model="newList" id="${taskList.id}"/>
                    </div>
                    <div class="task-list-menu">
                        <board:task-list-menu id="${taskList.id}"/>
                    </div>
                </div>

                <section class="list">
                    <c:forEach items="${taskList.tasks}" var="task">
                        <div class="task">${task.name}</div>
                    </c:forEach>
                </section>

                <div id="new-${taskList.id}" class="add-task">
                    <a onclick="changeDisplayForTwoElements('save-${taskList.id}', 'new-${taskList.id}')">Add
                        task...</a>
                </div>
                <div id="save-${taskList.id}" class="save-task">
                    <board:save model="task" action="task?id=${taskList.id}" id="${taskList.id}" style="title-task"/>
                </div>
            </div>
        </c:forEach>

        <div id="new-list" class="add-list" onclick="changeDisplayForTwoElements('save-list', 'new-list')">
            <h4>Add a list...</h4>
        </div>
        <div id="save-list" class="task-list" style="display: none;">
            <board:save model="newList" action="task-list?id=${board.id}" id="list" style="title-task-list"/>
        </div>
    </section>
    <modal:remove name="list"/>
</body>
</html>
