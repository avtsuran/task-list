<%@ tag body-content="empty" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="model" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>

<form:form class="form-edit" method="post" action="/edit-task-list?id=${id}" modelAttribute="${model}" autocomplete="off">
    <fieldset>
        <form:input class="edit-title" path="name" placeholder="Edit task-list" type="text"/>
    </fieldset>
    <button type="submit" class="btn btn-success btn-sm" style="margin: 5px">
        <span class="glyphicon glyphicon-ok"></span>
    </button>
</form:form>