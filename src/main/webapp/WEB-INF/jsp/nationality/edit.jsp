<%@ include file="../includes.jsp"%>
<fmt:message key="menu.elements.nationalities" var="pageHeader" />
<%@ include file="../header.jsp"%>

<div class="subContent">

<spring:hasBindErrors name="nationality">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<c:set var="currentItem" value="${nationality}" />
<c:set var="items" value="${nationalities}" />
<c:set var="editUrl"><c:url value="/nationality/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/nationality/delete"/></c:set>
<c:set var="createUrl"><c:url value="/nationality/create"/></c:set>
<c:set var="mergeUrl"><c:url value="/nationality/merge"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>


<form:form id="nationalityForm" modelAttribute="nationality" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="nationality.title" /></legend>

        <table class="withMargin">
<%--
            <tr>
                <th><form:label for="countryCode" path="countryCode" cssErrorClass="ui-state-error-text"><fmt:message key="nationality.countryCode" /></form:label></th>
                <td><form:input path="countryCode" size="2" maxlength="2" cssStyle="width: 2em;" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="countryCode" />
            </tr>
 --%>

            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="nationality.name" /></form:label></th>
                <td><form:input path="name" cssClass="long ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

</div>

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