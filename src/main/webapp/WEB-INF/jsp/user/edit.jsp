<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<spring:hasBindErrors name="user">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="menu.admin.users"/></h1>

<c:set var="currentItem" value="${user}" />
<c:set var="items" value="${users}" />
<c:set var="editUrl"><c:url value="/admin/user/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/admin/user/delete"/></c:set>
<c:set var="createUrl"><c:url value="/admin/user/create"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>

<form:form id="userForm" modelAttribute="user" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="user" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="username" path="username" cssErrorClass="ui-state-error-text"><fmt:message key="user.username" /></form:label></th>
                <td><form:input path="username" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="username" />
            </tr>

            <tr>
                <th><form:label for="password" path="password" cssErrorClass="ui-state-error-text"><fmt:message key="user.password" /></form:label></th>
                <td><form:password path="password" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="password" />
            </tr>

            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="user.name" /></form:label></th>
                <td><form:input path="name" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>

            <tr>
                <th><form:label for="role" path="role" cssErrorClass="ui-state-error-text"><fmt:message key="user.role" /></form:label></th>
                <td>
                    <form:select path="role" id="role" cssClass="ui-widget-content ui-corner-all" cssStyle="width: 20em;">
                        <c:forEach items="${roles}" var="r" >
                            <form:option value="${r}"><fmt:message key="${r.descriptionId}"/></form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

<%@ include file="../footer.jsp" %>