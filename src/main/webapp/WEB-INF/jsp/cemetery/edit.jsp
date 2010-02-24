<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<form:form id="cemeteryForm" modelAttribute="cemetery" method="post">
    <form:hidden path="id" />

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

    <p style="text-align: right; padding-top: 1em; padding-right: 2px;">
        <a class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button" href="#" onclick="$('#cemeteryForm').submit()">
            <span class="ui-icon ui-icon-disk"></span><fmt:message key="button.save"/>
        </a>
        <input type="submit" style="display: none;" />
    </p>
</form:form>

<script type="text/javascript">
<!--
    $('input:text:visible:first').focus();
//-->
</script>

<%@ include file="../footer.jsp" %>