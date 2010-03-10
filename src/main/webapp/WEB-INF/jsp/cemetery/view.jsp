<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>
<%@ page session="false" %>

<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
    <div class="rightLink">
        <a href="edit"><fmt:message key="edit"/></a>
    </div>
</sec:authorize>

<h1><fmt:message key="cemetery.title"/></h1>

<table>
    <c:if test="${!empty cemetery.name}">
        <tr>
            <th> <fmt:message key="cemetery.name"/> </th>
            <td> ${cemetery.name} </td>
        </tr>
    </c:if>

    <c:if test="${!empty cemetery.address}">
        <tr>
            <th> <fmt:message key="cemetery.address"/> </th>
            <td> ${cemetery.name} </td>
        </tr>
        <tr>
            <th> </th>
            <td> ${cemetery.postalDistrict.postcode} ${cemetery.postalDistrict.name} </td>
        </tr>
        <tr>
            <th> <fmt:message key="cemetery.county"/> </th>
            <td> ${cemetery.postalDistrict.county} </td>
        </tr>
    </c:if>
</table>

<%@ include file="../footer.jsp" %>