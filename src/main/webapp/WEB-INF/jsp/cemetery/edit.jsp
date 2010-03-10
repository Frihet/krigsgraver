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
                <th><form:label for="postalDistrict" path="postalDistrict" cssErrorClass="ui-state-error-text"><fmt:message key="cemetery.postcode" /></form:label></th>
                <td><form:input id="postcode" onchange="javascript:reloadPostalDistrict();" cssStyle="width: 4em;" maxlength="4" path="postalDistrict" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                <form:errors element="td" cssClass="ui-state-error-text" path="postalDistrict" />
            </tr>

            <fmt:message key="cemetery.automaticFill" var="autoFillMessage" />
            <tr>
                <th> <fmt:message key="cemetery.postalDistrict"/> </th>
                <td id="postalDistrict"> <c:out escapeXml="false" value="${cemetery.postalDistrict.name}" default="<span class='soft'>${autoFillMessage}</span>" /> </td>
            </tr>

            <tr>
                <th> <fmt:message key="cemetery.county"/> </th>
                <td id="county"> <c:out escapeXml="false" value="${cemetery.postalDistrict.county}" default="<span class='soft'>${autoFillMessage}</span>" /> </td>
            </tr>

            <tr>
                <th><form:label for="latitude" path="latitude" cssErrorClass="ui-state-error-text"><fmt:message key="latitude" /></form:label></th>
                <td>
                    <form:input cssStyle="width: 15em;" path="latitude" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/>
                    <span class="soft">(<fmt:message key="latitude.example"/>)</span>
                </td>
                <form:errors element="td" cssClass="ui-state-error-text" path="latitude" />
            </tr>

            <tr>
                <th><form:label for="longitude" path="longitude" cssErrorClass="ui-state-error-text"><fmt:message key="longitude" /></form:label></th>
                <td>
                    <form:input cssStyle="width: 15em;" path="longitude" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/>
                    <span class="soft">(<fmt:message key="longitude.example"/>)</span>
                </td>
                <form:errors element="td" cssClass="ui-state-error-text" path="longitude" />
            </tr>
        </table>
    </fieldset>

	<button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
</form:form>

<script type="text/javascript">
<!--
    /* Load the current postal district based on what's in the 'postcode' field. */
    function reloadPostalDistrict() {
        $('#postalDistrict').html("");
        $('#county').html("");

    	$.getJSON('<c:url value="/postalDistrict/" />' + $('#postcode').val(), function(data) {
            
    		$('#postalDistrict').html(data.name);
            $('#county').html(data.county);
        });
    }
//-->
</script>


<%@ include file="../footer.jsp" %>