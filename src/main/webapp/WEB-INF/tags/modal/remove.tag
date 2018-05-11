<%@ tag body-content="empty" %>
<%@ attribute name="name" required="false" type="java.lang.String" %>

<div id="modal" class="modal-window">
    <div class="content">
        <div class="content-message">
            <h4 id="remove">Remove the ${name}?</h4>
            <p>Once you remove a ${name}, there is no going back. Please be certain.</p>
        </div>
        <a type="button" id="remove-${name}-by-id" class="btn btn-danger">Remove</a>
        <a type="button" class="btn btn-default" onclick="hideModal()">No, thanks.</a>
    </div>
</div>