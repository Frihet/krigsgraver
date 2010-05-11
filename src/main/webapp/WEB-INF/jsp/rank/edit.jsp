<%@ include file="../includes.jsp"%>
<fmt:message key="menu.elements.ranks" var="pageHeader" />
<%@ include file="../header.jsp"%>

<div class="subContent">

<spring:hasBindErrors name="rank">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<c:set var="currentItem" value="${rank}" />
<c:set var="items" value="${ranks}" />
<c:set var="editUrl"><c:url value="/rank/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/rank/delete"/></c:set>
<c:set var="createUrl"><c:url value="/rank/create"/></c:set>
<c:set var="mergeUrl"><c:url value="/rank/merge"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>


<form:form id="rankForm" modelAttribute="rank" method="post">
    <div>
        <form:hidden id="hiddenId" path="id" />
    
        <fieldset class="main ui-corner-all">
            <legend><fmt:message key="rank.title" /></legend>
    
            <table class="withMargin">
                <tr>
                    <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="type.name" /></form:label></th>
                    <td><form:input path="name" cssClass="long ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                    <form:errors element="td" cssClass="ui-state-error-text" path="name" />
                </tr>
            </table>
        </fieldset>
    
    	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
    </div>
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