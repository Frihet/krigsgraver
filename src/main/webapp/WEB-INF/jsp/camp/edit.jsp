<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<spring:hasBindErrors name="camp">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="menu.elements.camps"/></h1>

<c:set var="currentItem" value="${camp}" />
<c:set var="items" value="${camps}" />
<c:set var="editUrl"><c:url value="/camp/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/camp/delete"/></c:set>
<c:set var="createUrl"><c:url value="/camp/create"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>

<form:form id="campForm" modelAttribute="camp" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="camp.title" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="type.name" /></form:label></th>
                <td><form:input path="name" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

<%@ include file="../footer.jsp" %>