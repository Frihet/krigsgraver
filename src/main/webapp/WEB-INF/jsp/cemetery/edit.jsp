<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<spring:hasBindErrors name="cemetery">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="menu.elements.cemeteries"/></h1>

<c:set var="currentItem" value="${cemetery}" />
<c:set var="items" value="${cemeteries}" />
<c:set var="editUrl"><c:url value="/cemetery/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/cemetery/delete"/></c:set>
<c:set var="createUrl"><c:url value="/cemetery/create"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>

<form:form id="cemeteryForm" modelAttribute="cemetery" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="cemetery.title" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="cemetery.name" /></form:label></th>
                <td><form:input path="name" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>

            <tr>
                <th><form:label for="address" path="address" cssErrorClass="ui-state-error-text"><fmt:message key="cemetery.address" /></form:label></th>
                <td><form:input path="address" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="address" />
            </tr>

            <tr>
                <th><form:label for="postalDistrict" path="postalDistrict" cssErrorClass="ui-state-error-text"><fmt:message key="cemetery.postalDistrict" /></form:label></th>
                <td><form:input path="postalDistrict" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="postalDistrict" />
            </tr>

            <tr>
                <th><form:label for="postcode" path="postcode" cssErrorClass="ui-state-error-text"><fmt:message key="cemetery.postcode" /></form:label></th>
                <td><form:input path="postcode" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="postcode" />
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>



<%@ include file="../footer.jsp" %>