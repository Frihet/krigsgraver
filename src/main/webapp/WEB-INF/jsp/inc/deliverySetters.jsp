<%@ include file="../includes.jsp"%>

<table class="standard">
    <tr>
        <th width="30%"><fmt:message key='transports.type'/></th>
        <th width="7%"><fmt:message key='transports.unit_of_measurement'/></th>
        <th width="7%"><fmt:message key='transports.amount'/></th>
        <th width="60%"><fmt:message key='transports.comment'/></th>
    </tr>

    <c:forEach items="${types}" var="type" varStatus="status">
        <tr>
            <td>${type.name}</td>
            <td><fmt:message key='${unitOfMeasurementKey}'/></td>
            <td>
                <form:input path="elements[${type.id}].amount" cssErrorClass="error" size="5" />
            </td>
            <td>
                <div class="internalTextField">
                    <form:input path="elements[${type.id}].comment" cssErrorClass="error" cssStyle="width: 100%;" />
                </div>
            </td>

            <form:errors cssClass="errorLine" element="td" path="elements[${type.id}].*"/>
        </tr>
    </c:forEach>        
</table>