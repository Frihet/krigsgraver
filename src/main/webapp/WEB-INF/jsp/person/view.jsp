<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>


<style>
<!--
    #mainContent { max-width: 80em; }
    
    th { padding-left: 0; } 
    td, th { vertical-align: top; }
    
    div.rightLink { float: right; }
-->
</style>

<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
    <div class="rightLink">
        <a href="edit"><fmt:message key="person.edit"/></a>
    </div>
</sec:authorize>

<h1>${person}</h1>

<table>
    <c:if test="${!empty person.placeOfBirth}">
        <tr>
            <th> <fmt:message key="person.placeOfBirth"/> </th>
            <td> ${person.placeOfBirth} </td>
        </tr>
    </c:if>
    <tr>
        <th> <fmt:message key="person.dateOfBirth"/> </th>
        <td> <c:if test="${person.dateOfBirth.approximate}"><fmt:message key="date.approximate"/></c:if> ${person.dateOfBirth} </td>
    </tr>
    <c:if test="${!empty person.nationality}">
        <tr>
            <th> <fmt:message key="person.nationality"/> </th>
            <td> ${person.nationality} </td>
        </tr>
    </c:if>
    <c:if test="${!empty person.rank.name}">
        <tr>
            <th> <fmt:message key="person.rank"/> </th>
            <td> ${person.rank.name} </td>
        </tr>
    </c:if>
    <c:if test="${!empty person.stalag.name}">
        <tr>
            <th> <fmt:message key="stalag.title"/> </th>
            <td> ${person.stalag.name} </td>
            <c:if test="${!empty person.prisonerNumber}">
                <th> <fmt:message key="person.prisonerNumber"/> </th>
                <td> ${person.prisonerNumber} </td>
            </c:if>
        </tr>
    </c:if>
    <c:if test="${!empty person.camp.name}">
        <tr>
            <th> <fmt:message key="camp.title"/> </th>
            <td> ${person.camp.name} </td>
        </tr>
    </c:if>
</table>

<h2><fmt:message key="person.death.title"/></h2>
<table>
    <tr>
        <th> <fmt:message key="person.dateOfDeath"/> </th>
        <td> <c:if test="${person.dateOfDeath.approximate}"><fmt:message key="date.approximate"/></c:if> ${person.dateOfBirth} </td>
    </tr>
    
    <c:if test="${!empty person.placeOfDeath}">
        <tr>
            <th> <fmt:message key="person.placeOfDeath"/> </th>
            <td> ${person.placeOfDeath} </td>
        </tr>
    </c:if>
    <c:if test="${!empty person.causesOfDeath}">
        <tr>
            <th> <fmt:message key="person.causeOfDeath"/> </th>
            <td>
                <c:forEach items="${person.causesOfDeath}" var="causeOfDeath" varStatus="status">
                    ${causeOfDeath}
                </c:forEach>
            </td>
        </tr>
    </c:if>
    <c:if test="${!empty person.causeOfDeathDescription}">
        <tr>
                <th> <fmt:message key="person.causeOfDeathDescription"/> </th>
                <td> ${person.causeOfDeathDescription} </td>
        </tr>
    </c:if>
    <c:if test="${!empty person.obdNumber}">
        <tr>
            <th> <fmt:message key="person.obdNumber"/> </th>
            <td> ${person.obdNumber} </td>
        </tr>
    </c:if>
</table>

<h2><fmt:message key="person.graves.title"/></h2>
<c:forEach items="${person.graves}" var="grave" varStatus="status">

    <%-- Separate the graves with a horizontal line. --%>
    <c:if test="${status.index > 0}">
        <div class="ruler"></div>
    </c:if>

    <table>
        <tr>
            <th> <fmt:message key="type.date"/> </th>
            <td> <c:if test="${grave.dateOfBurial.approximate}"><fmt:message key="date.approximate"/></c:if> ${grave.dateOfBurial} </td>
        </tr>

        <c:if test="${!empty grave.cemetery}">
            <tr>
                <th> <fmt:message key="cemetery.title"/> </th>
                <td> ${grave.cemetery.name} </td>
            </tr>
        </c:if>

        <c:if test="${!empty grave.graveField}">
            <tr>
                <th> <fmt:message key="grave.graveField"/> </th>
                <td> ${grave.graveField} </td>
            </tr>
        </c:if>

        <c:if test="${!empty grave.graveRow}">
            <tr>
                <th> <fmt:message key="grave.graveRow"/> </th>
                <td> ${grave.graveRow} </td>
            </tr>
        </c:if>

        <c:if test="${!empty grave.graveNumber}">
            <tr>
                <th> <fmt:message key="grave.graveNumber"/> </th>
                <td> ${grave.graveNumber} </td>
            </tr>
        </c:if>

        <c:if test="${grave.massGrave}">
            <tr>
                <th> <fmt:message key="grave.type"/> </th>
                <td> <fmt:message key="grave.massGrave.fullTitle"/> </td>
            </tr>
        </c:if>

    </table>
</c:forEach>

<c:if test="${!empty person.remarks}">
    <h2><fmt:message key="person.remarks"/></h2>
    <p>
        ${person.remarks}
    </p>
</c:if>

<%@ include file="../footer.jsp" %>