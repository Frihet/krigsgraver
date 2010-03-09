<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<spring:hasBindErrors name="stalag">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="menu.elements.stalags"/></h1>

<c:set var="currentItem" value="${stalag}" />
<c:set var="items" value="${stalags}" />
<c:set var="editUrl"><c:url value="/stalag/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/stalag/delete"/></c:set>
<c:set var="createUrl"><c:url value="/stalag/create"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>


<form:form id="stalagForm" modelAttribute="stalag" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="stalag.title" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="stalag.name" /></form:label></th>
                <td><form:input path="name" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>

            <tr>
                <th><form:label for="description" path="description" cssErrorClass="ui-state-error-text"><fmt:message key="stalag.description" /></form:label></th>
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

<script type="text/javascript">
<!--
$(function(){
    $('.button').hover(
        function() { $(this).addClass('ui-state-hover'); }, 
        function() { $(this).removeClass('ui-state-hover'); }
    );
});
//-->
</script>

<%@ include file="../footer.jsp" %>