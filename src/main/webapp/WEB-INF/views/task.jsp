<%@ taglib prefix="modal" tagdir="/WEB-INF/tags/modal" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/task.css" rel="stylesheet">
    <link href="css/modal.css" rel="stylesheet">
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <title>${task.name} | Task</title>
</head>
<body>
<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="#" class="navbar-brand">TASK-LIST</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/board-list">Home</a></li>
            <li><a href="/board?id=${task.taskList.board.id}">${task.taskList.board.name}</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="task">
    <div class="task-head">
        <p class="task-name"><span class="glyphicon glyphicon-tag priority-${task.priority}"></span> ${task.name}</p>
        <p>in list ${task.taskList.name}</p>
    </div>
    <div class="description">
        <div class="description-head">
            <p class="title">Description</p>
            <div class="btn-group" style="margin-top: 5px">
                <button type="button" class="close dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-option-horizontal"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#" onclick="changeDisplayForTwoElements('description', 'text')">Edit description</a>
                    </li>
                </ul>
            </div>
        </div>
        <div id="description">
            <form class="form-description" method="post">
                <textarea placeholder="Add more detailed description ..."></textarea>
                <div>
                    <button type="submit" class="btn btn-success btn-sm save">Save</button>
                    <button type="button" class="close" aria-label="close" style="margin: 6px">
                    <span aria-hidden="true"
                          onclick="changeDisplayForTwoElements('text', 'description')">&times;</span>
                    </button>
                </div>
            </form>
        </div>
        <p id="text" class="text-description">${task.description}</p>
    </div>
    <div class="additional">
        <div class="attachments">
            <p class="title">Attachments</p>
            <section class="attachment-list">
                <c:forEach items="${attachments}" var="attachment">
                    <div class="attachment">
                        <div class="label-link"></div>
                        <div class="attachment-body">
                            <p>${attachment.name}</p>
                            <div class="btn-group">
                                <button type="button" class="close dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-option-vertical"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Edit</a></li>
                                    <li><a href="#">Remove</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </section>
        </div>
        <div class="actions">
            <p class="title">Actions</p>
            <a type="button" class="btn btn-info action">Edit</a>
            <a type="button" class="btn btn-danger action" onclick="showOrHideElement('modal')">Remove</a>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle action" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    Move to <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">Doing</a></li>
                    <li><a href="#">Done</a></li>
                </ul>
            </div>

            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle action" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    Set priority <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">High</a></li>
                    <li><a href="#">Low</a></li>
                </ul>
            </div>
            <a type="button" class="btn btn-success action">Add an attachment</a>
        </div>
    </div>
</div>

<modal:remove name="task"/>
</body>
</html>
