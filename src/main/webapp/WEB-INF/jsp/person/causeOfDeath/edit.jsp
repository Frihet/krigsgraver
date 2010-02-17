<%@ include file="../../includes.jsp"%>

<form:form modelAttribute="causeOfDeath" id="causeOfDeathForm" method="post" onsubmit="return false">
<%--    <form:hidden path="id" /> --%>

    <fieldset>
        <form:label for="cause" path="cause" cssErrorClass="error"><fmt:message key="person.causeOfDeath.cause" /></form:label>
        <form:input path="cause" cssClass="text ui-widget-content ui-corner-all" />
        <form:errors element="span" cssClass="error" path="cause" />

        <form:label for="causeGroup" path="causeGroup" cssErrorClass="error"><fmt:message key="person.causeOfDeath.causeGroup" /></form:label>
        <form:input path="causeGroup" cssClass="text ui-widget-content ui-corner-all" />
        <form:errors element="span" cssClass="error" path="causeGroup" />

        <form:label for="description" path="description" cssErrorClass="error"><fmt:message key="person.causeOfDeath.description" /></form:label>
        <form:input path="description" cssClass="text ui-widget-content ui-corner-all" />
        <form:errors element="span" cssClass="error" path="description" />
    </fieldset>

<%--
    <table>
        <tr>
            <th><form:label for="cause" path="cause" cssErrorClass="error"><fmt:message key="person.causeOfDeath.cause" /></form:label></th>
            <td><form:input path="cause" /></td>
            <form:errors element="td" cssClass="error" path="cause" />
        </tr>

        <tr>
            <th><form:label for="causeGroup" path="causeGroup" cssErrorClass="error"><fmt:message key="person.causeOfDeath.causeGroup" /></form:label></th>
            <td><form:input path="causeGroup" /></td>
            <form:errors element="td" cssClass="error" path="causeGroup" />
        </tr>

        <tr>
            <th><form:label for="description" path="description" cssErrorClass="error"><fmt:message key="person.causeOfDeath.description" /></form:label></th>
            <td><form:input path="description" /></td>
            <form:errors element="td" cssClass="error" path="description" />
        </tr>
    </table>
 --%>

<!--    <input type="submit" style="display:none" />-->

<!-- 
    <p>
        <button type="submit"><fmt:message key="button.save" /></button>
    </p>
 -->    
</form:form>
