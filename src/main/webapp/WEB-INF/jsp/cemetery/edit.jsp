<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<form:form modelAttribute="cemetery" method="post">
    <form:hidden path="id" />

    <fieldset>
        <legend><fmt:message key="cemetery.title" /></legend>

        <table>
            <tr>
                <th><form:label for="name" path="name" cssErrorClass="error"><fmt:message key="cemetery.name" /></form:label></th>
                <td><form:input path="name" /></td>
                <form:errors element="td" cssClass="error" path="name" />
            </tr>

            <tr>
                <th><form:label for="address" path="address" cssErrorClass="error"><fmt:message key="cemetery.address" /></form:label></th>
                <td><form:input path="address" /></td>
                <form:errors element="td" cssClass="error" path="address" />
            </tr>

            <tr>
                <th><form:label for="postalDistrict" path="postalDistrict" cssErrorClass="error"><fmt:message key="cemetery.postalDistrict" /></form:label></th>
                <td><form:input path="postalDistrict" /></td>
                <form:errors element="td" cssClass="error" path="postalDistrict" />
            </tr>

            <tr>
                <th><form:label for="postcode" path="postcode" cssErrorClass="error"><fmt:message key="cemetery.postcode" /></form:label></th>
                <td><form:input path="postcode" /></td>
                <form:errors element="td" cssClass="error" path="postcode" />
            </tr>
        </table>

        <p>
            <button type="submit"><fmt:message key="button.save" /></button>
        </p>
    </fieldset>
</form:form>

<%@ include file="../footer.jsp" %>