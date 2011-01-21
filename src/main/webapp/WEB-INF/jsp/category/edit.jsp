<%@ include file="../includes.jsp"%>
<fmt:message key="menu.elements.categories" var="pageHeader" />
<%@ include file="../header.jsp"%>

<div class="subContent">

<spring:hasBindErrors name="category">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<c:set var="currentItem" value="${category}" />
<c:set var="items" value="${categories}" />
<c:set var="editUrl"><c:url value="/category/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/category/delete"/></c:set>
<c:set var="createUrl"><c:url value="/category/create"/></c:set>
<c:set var="mergeUrl"><c:url value="/category/merge"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>

<form:form id="categoryForm" modelAttribute="category" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="category.title" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="type.name" /></form:label></th>
                <td><form:input path="name" cssClass="long ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>

            <tr>
                <th><form:label for="description" path="description" cssErrorClass="ui-state-error-text"><fmt:message key="type.description" /></form:label></th>
                <td>
                    <div style="padding-right: 1.5em;">
                        <form:textarea cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="description" rows="4" cols="50" cssStyle="width: 100%;" />
                    </div>
                </td>
                <form:errors element="td" cssClass="ui-state-error-text" path="description" />
            </tr>

        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

</div>

<%@ include file="../footer.jsp" %>