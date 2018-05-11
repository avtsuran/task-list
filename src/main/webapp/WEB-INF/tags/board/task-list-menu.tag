<%@ tag body-content="empty" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>

<div id="menu-list-${id}" class="hide-block">
    <a class="btn btn-info btn-sm" onclick="changeDisplayForTwoElements('form-edit-${id}', 'name-${id}')">
        <span class="glyphicon glyphicon-pencil" style="margin: 3px 0"></span>
    </a>
    <a class="btn btn-danger btn-sm" onclick="removeTaskList('${id}')">
        <span class="glyphicon glyphicon-trash" style="margin: 3px 0"></span>
    </a>
    <button type="button" class="close"
            onclick="taskListHead('open-menu-list-${id}', 'menu-list-${id}', 'form-edit-${id}', 'name-${id}')"
            style="margin: 5px">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<button id="open-menu-list-${id}" type="button" class="close"
        onclick="changeDisplayForTwoElements('menu-list-${id}', 'open-menu-list-${id}')">
    <span class="glyphicon glyphicon-option-horizontal"></span>
</button>