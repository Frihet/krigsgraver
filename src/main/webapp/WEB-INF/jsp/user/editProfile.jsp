<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<div class="subContent">

<spring:hasBindErrors name="user">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="editProfile"/></h1>

<form:form id="userForm" modelAttribute="user" method="post">
    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="user" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="username" path="username" cssErrorClass="ui-state-error-text"><fmt:message key="user.username" /></form:label></th>
                <td>${user.username}</td>
            </tr>

            <tr>
                <th><form:label for="password" path="password" cssErrorClass="ui-state-error-text"><fmt:message key="user.password" /></form:label></th>
                <td><form:password autocomplete="off" path="password" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="password" />
            </tr>

            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="user.name" /></form:label></th>
                <td><form:input path="name" cssClass="medium ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

</div>

<%@ include file="../footer.jsp" %>