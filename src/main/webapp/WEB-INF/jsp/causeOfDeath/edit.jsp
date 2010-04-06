<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<div class="subContent">

<spring:hasBindErrors name="causeOfDeath">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="menu.elements.causesOfDeath"/></h1>

<c:set var="currentItem" value="${causeOfDeath}" />
<c:set var="items" value="${causeOfDeaths}" />
<c:set var="editUrl"><c:url value="/causeOfDeath/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/causeOfDeath/delete"/></c:set>
<c:set var="createUrl"><c:url value="/causeOfDeath/create"/></c:set>
<%--
 --%>
<c:set var="mergeUrl"><c:url value="/causeOfDeath/merge"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>

<form:form id="causeOfDeathForm" modelAttribute="causeOfDeath" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="causeOfDeath.title" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="type.name" /></form:label></th>
                <td><form:input path="name" cssClass="long ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>
            
            <tr>
                <th><form:label for="causeGroup" path="causeGroup" cssErrorClass="ui-state-error-text"><fmt:message key="causeOfDeath.causeGroup" /></form:label></th>
                <td><form:input path="causeGroup" cssClass="long ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="causeGroup" />
            </tr>

            <tr>
                <th><form:label for="description" path="description" cssErrorClass="ui-state-error-text"><fmt:message key="type.description" /></form:label></th>
                <td><form:input path="description" cssClass="long ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="description" />
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

</div>

<%@ include file="../footer.jsp" %>