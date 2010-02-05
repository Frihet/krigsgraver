<%@ include file="../includes.jsp"%>

<c:choose>
    <c:when test="${!empty types}">
        <table class="standard">
            <tr>
                <th width="30%"><fmt:message key='transports.type'/></th>
                <th width="10%"><fmt:message key='transports.unit_of_measurement'/></th>
                <th width="10%"><fmt:message key='transports.amount'/></th>
                <th width="50%"><fmt:message key='transports.comment'/></th>
            </tr>
        
            <c:forEach items="${types}" var="type" varStatus="status">
                <tr>
                    <td>${type.name}</td>
                    <td><fmt:message key='${unitOfMeasurementKey}'/></td>
                    <td>
                        ${delivery.elements[type.id].amount}
                    </td>
                    <td>
                        <div class="internalTextField">
                            ${delivery.elements[type.id].comment}
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div style="font-style: italic;">
            <fmt:message key='transports.none'/>
        </div>
    </c:otherwise>
</c:choose>