<%@ tag body-content="empty" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="model" required="false" type="java.lang.String" %>
<%@ attribute name="action" required="false" type="java.lang.String" %>
<%@ attribute name="style" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>

<form:form method="post" modelAttribute="${model}" action="/add-${action}" autocomplete="off">
    <fieldset>
        <form:input class="${style}" path="name" placeholder="Add title" type="text"/>
    </fieldset>
    <button type="submit" class="btn btn-success">Save</button>
    <button type="button"
            onclick="changeDisplayForTwoElements('new-${id}', 'save-${id}')"
            class="close" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</form:form>