<%-- <%@ include file="../header.jsp"%> --%>
<%@ include file="../includes.jsp"%>

<div class="subContent">

<form:form modelAttribute="grave" method="post">
    <form:hidden path="id" />

    <fieldset>
        <legend><fmt:message key="grave.title" /></legend>

        <table>
            <tr>
                <th><form:label for="graveField" path="graveField" cssErrorClass="error"><fmt:message key="grave.graveField" /></form:label></th>
                <td><form:input path="graveField" /></td>
                <form:errors element="td" cssClass="error" path="graveField" />
            </tr>

            <tr>
                <th><form:label for="graveRow" path="graveRow" cssErrorClass="error"><fmt:message key="grave.graveRow" /></form:label></th>
                <td><form:input path="graveRow" /></td>
                <form:errors element="td" cssClass="error" path="graveRow" />
            </tr>

            <tr>
                <th><form:label for="graveNumber" path="graveNumber" cssErrorClass="error"><fmt:message key="grave.graveNumber" /></form:label></th>
                <td><form:input path="graveNumber" /></td>
                <form:errors element="td" cssClass="error" path="graveNumber" />
            </tr>
        </table>

        <p>
            <button type="submit"><fmt:message key="button.save" /></button>
        </p>
    </fieldset>
</form:form>

</div>

<%@ include file="../footer.jsp" %>