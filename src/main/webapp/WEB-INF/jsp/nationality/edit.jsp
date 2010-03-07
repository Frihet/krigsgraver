<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<spring:hasBindErrors name="nationality">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="nationality.title"/></h1>

<c:set var="currentItem" value="${nationality}" />
<c:set var="items" value="${nationalities}" />
<c:set var="editUrl"><c:url value="/nationality/edit"/></c:set>
<c:set var="deleteUrl"><c:url value="/nationality/delete"/></c:set>
<c:set var="createUrl"><c:url value="/nationality/create"/></c:set>
<%@ include file="../inc/itemSelector.jsp" %>


<form:form id="nationalityForm" modelAttribute="nationality" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="nationality.title" /></legend>

        <table class="withMargin">
            <tr>
                <th><form:label for="countryCode" path="countryCode" cssErrorClass="ui-state-error-text"><fmt:message key="nationality.countryCode" /></form:label></th>
                <td><form:input path="countryCode" size="2" maxlength="2" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="countryCode" />
            </tr>

            <tr>
                <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="nationality.name" /></form:label></th>
                <td><form:input path="name" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="name" />
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

<%--
<form:form id="nationalityForm" modelAttribute="nationality" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="nationality.title" /></legend>

        <table style="width: 100%;">
            <tr>
                <td>
                    <select id="nationalitySelector" class="ui-widget-content ui-corner-all">
                        <option value="">&lt;<fmt:message key="value.createNew"/>&gt;</option>

                        <c:forEach items="${nationalities}" var="r">
                            <c:choose>
                                <c:when test="${r.id == nationality.id}">
                                    <option value="${r.id}" selected="selected">${r.name}</option>
                                </c:when> <c:otherwise>
                                    <option value="${r.id}">${r.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>

                <td>
                    <table class="withMargin">
                        <tr>
                            <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="nationality.name" /></form:label></th>
                            <td><form:input id="nationalityName" path="name" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                            <form:errors element="td" cssClass="ui-state-error-text" path="name" />
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

        <p style="text-align: right; padding-top: 1em; padding-right: 2px;">
            <a class="button ui-state-error-text ui-state-default ui-corner-all" id="delete_button" href="#" onclick="$('#nationalityDeleteForm').submit()" style="display: none;">
                <span class="ui-icon ui-icon-trash"></span><fmt:message key="button.deleteEntry"/>
            </a>

            &nbsp;

            <a class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button" href="#" onclick="$('#nationalityForm').submit()">
                <span class="ui-icon ui-icon-disk"></span><fmt:message key="button.save"/>
            </a>
            <input type="submit" style="display: none;" />
        </p>

<!-- onclick="return confirm('<fmt:message key='message.confirmDelete'/>');" -->
    </fieldset>

</form:form>

<script type="text/javascript">
<!--
    $('#nationalitySelector').change(function() {
    	var selector = $('#nationalitySelector');
        $('#hiddenId').val(selector.val());

        if (selector.val() != "") {
            // Editing old
        	$('#nationalityName').val($('#nationalitySelector :selected').text());
            $('#delete_button').css('display', '');

        } else {
            // Creating new
        	$('#nationalityName').val('');
        	$('#delete_button').css('display', 'none');
        }
    });

    $(function(){
        $('#save_button, #delete_button').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });

    // Process nationality selector on load.
    $('#nationalitySelector').change();

    $('input:text:visible:first').focus();
    
</script>
 --%>

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