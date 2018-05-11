<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ tag body-content="empty" %>
<%@ attribute name="model" required="false" type="java.lang.String" %>

<form:form class="form-edit" method="post" modelAttribute="${model}" autocomplete="off">
        <form:input class="edit-input" path="name" placeholder="Edit board " type="text"/>
    <button type="submit" class="btn btn-success" style="margin-right: 11px">Save</button>
    <a type="button" class="btn btn-info" onclick="changeDisplayForTwoElements('menu', 'edit')">No thanks</a>
</form:form>