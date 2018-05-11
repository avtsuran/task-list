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
            <form:form class="form-edit" method="post" modelAttribute="board" autocomplete="off">
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
            <div id="${taskList.id}" class="task-list">
                <div class="task-list-head">
                    <div id="name-${taskList.id}">
                        <h4>${taskList.name}</h4>
                    </div>
                    <div id="form-edit-${taskList.id}" class="hide-block">
                        <form:form class="form-edit" method="post" action="/edit-task-list?id=${taskList.id}" modelAttribute="newList" autocomplete="off">
                            <fieldset>
                                <form:input class="edit-title" path="name" placeholder="Edit task-list" type="text"/>
                            </fieldset>
                            <button type="submit" class="btn btn-success btn-sm" style="margin: 5px">
                                <span class="glyphicon glyphicon-ok"></span>
                            </button>
                        </form:form>
                    </div>
                    <div class="task-list-menu">
                        <div id="menu-list-${taskList.id}" class="hide-block">
                            <a class="btn btn-info btn-sm" onclick="changeDisplayForTwoElements('form-edit-${taskList.id}', 'name-${taskList.id}')">
                                <span class="glyphicon glyphicon-pencil" style="margin: 3px 0"></span>
                            </a>
                            <a href="#" class="btn btn-danger btn-sm" onclick="removeTaskList('${taskList.id}')">
                                <span class="glyphicon glyphicon-trash" style="margin: 3px 0"></span>
                            </a>
                            <button type="button" class="close"
                                    onclick="taskListHead('open-menu-list-${taskList.id}', 'menu-list-${taskList.id}', 'form-edit-${taskList.id}', 'name-${taskList.id}')"
                                    style="margin: 5px">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <button id="open-menu-list-${taskList.id}" type="button" class="close"
                                onclick="changeDisplayForTwoElements('menu-list-${taskList.id}', 'open-menu-list-${taskList.id}')">
                            <span class="glyphicon glyphicon-option-horizontal"></span>
                        </button>
                    </div>
                </div>
                <section class="list">
                    <c:forEach items="${taskList.tasks}" var="task">
                        <div class="task">${task.name}</div>
                    </c:forEach>
                </section>
                <div id="new-${taskList.id}" class="add-task">
                    <a onclick="changeDisplayForTwoElements('save-${taskList.id}', 'new-${taskList.id}')">Add task ...</a>
                </div>
                <div id="save-${taskList.id}" class="save-task">
                    <form:form method="post" modelAttribute="task" action="/add-task?id=${taskList.id}" autocomplete="off">
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
            <form:form method="post" action="/add-task-list?id=${board.id}" modelAttribute="newList" autocomplete="off">
                <form:input class="title-task-list" path="name" placeholder="Add task-list title" type="text"/>
                <button type="submit" class="btn btn-success">Save</button>
                <button type="button" class="close" aria-label="Close">
                    <span aria-hidden="true"
                          onclick="changeDisplayForTwoElements('new-list', 'save-list')">&times;</span>
                </button>
            </form:form>
        </div>
    </section>

    <div id="modal" class="modal-window">
        <div class="content">
            <div class="content-message">
                <h4 id="remove">Remove the task-list?</h4>
                <p>Once you remove a task-list, there is no going back. Please be certain.</p>
            </div>
            <a type="button" id="remove-list-by-id" class="btn btn-danger">Remove</a>
            <a type="button" class="btn btn-default" onclick="hideModal()">No, thanks.</a>
        </div>
    </div>

</div>
</body>
</html>
