<%@ include file="../includes.jsp"%>

<tr>
    <th><fmt:message key='admin.locations.name'/>*:</th>
    <td>
        <form:input path="name" />
    </td>
    <form:errors cssClass="errorLine" element="td" path="name" />
</tr>

<tr>
    <th><fmt:message key='admin.locations.address'/>*:</th>
    <td>
        <form:input path="addressLine1" />
    </td>
    <form:errors cssClass="errorLine" element="td" path="addressLine1" />
</tr>

<tr>
    <th></th>
    <td>
        <form:input path="addressLine2" />
    </td>
    <form:errors cssClass="errorLine" element="td" path="addressLine2" />
</tr>

<tr>
    <th><fmt:message key='locations.postal_code'/>*:</th>
    <td>
        <form:input path="postalCode" />
    </td>
    <form:errors cssClass="errorLine" element="td" path="postalCode" />
</tr>

<tr>
    <th><fmt:message key='locations.municipality'/>*:</th>
    <td>
        <form:input path="municipality" />
    </td>
    <form:errors cssClass="errorLine" element="td" path="municipality" />
</tr>

<tr>
    <th><fmt:message key='locations.county'/>*:</th>
    <td>
        <form:input path="county" />
    </td>
    <form:errors cssClass="errorLine" element="td" path="county" />
</tr>

<tr>
    <th><fmt:message key='locations.country'/>*:</th>
    <td>
        <form:select path="country" cssStyle="width: 10em;">
            <c:forEach items="${countries}" var="c" >
                <form:option value="${c}"><fmt:message key="${c.descriptionId}"/></form:option>
            </c:forEach>
        </form:select>
    </td>
    <form:errors cssClass="errorLine" element="td" path="country" />
</tr>

<tr>
    <th><fmt:message key='locations.email_address'/>*:</th>
    <td>
        <form:input path="email" />
    </td>
    <form:errors cssClass="errorLine" element="td" path="email" />
</tr>
