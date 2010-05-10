<%@ include file="../includes.jsp"%>
<c:set var="pageTitle" value="${person}"/>
<%@ include file="../header.jsp"%>
<%@ page session="false" %>

<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
    <div class="rightLink">
        <a href="edit"><fmt:message key="person.edit"/></a>
    </div>
</sec:authorize>

<div class="subContent">
<h1>${person}</h1>

<table>
    <c:if test="${!empty person.placeOfBirth}">
        <tr>
            <th> <fmt:message key="person.placeOfBirth"/> </th>
            <td> ${person.placeOfBirth} </td>
        </tr>
    </c:if>
    <c:if test="${person.dateOfBirth != '?'}">
        <tr>
            <th> <fmt:message key="person.dateOfBirth"/> </th>
            <td> <c:if test="${person.dateOfBirth.approximate}"><fmt:message key="date.approximate"/></c:if> ${person.dateOfBirth} </td>
        </tr>
    </c:if>
    <c:if test="${!empty person.nationality}">
        <tr>
            <th> <fmt:message key="person.nationality"/> </th>
            <td> ${person.nationality.name} </td>
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
            <td>${person.stalag.name}
                <c:if test="${!empty person.stalag.description}">
                    <img id="stalagDescriptionImg" class="imgLink" src="<c:url value='/inc/img/comment.gif'/>" title="<fmt:message key='type.description'/>" />
                    <div class="dialog" id="stalagDescriptionDialog" style="display: none;">${person.stalag.description}</div>
                    <script type="text/javascript">
                    <!--
                        $("#stalagDescriptionDialog").dialog({
                            title: '<fmt:message key="stalag.title"/>: ${person.stalag.name}',
                            modal: true,
                            autoOpen: false
                        });
                
                        $('#stalagDescriptionImg').click(function() {
                            $("#stalagDescriptionDialog").dialog('open');
                            $('.ui-widget-overlay').click(function() { $("#stalagDescriptionDialog").dialog("close"); });
                        });
                    //-->
                    </script>
                </c:if>

                <c:set var="geolocational" value="${person.stalag}" />
                <%@ include file="../google_maps_link.jsp" %>
            </td>
            <c:if test="${!empty person.prisonerNumber}">
                <th> <fmt:message key="person.prisonerNumber"/> </th>
                <td> ${person.prisonerNumber} </td>
            </c:if>
        </tr>
    </c:if>
    <c:if test="${!empty person.camp.name}">
        <tr>
            <th> <fmt:message key="camp.title"/> </th>
            <td>${person.camp.name}
                <c:if test="${!empty person.camp.description}">
                    <img id="campDescriptionImg" class="imgLink" src="<c:url value='/inc/img/comment.gif'/>" title="<fmt:message key='type.description'/>" />
                    <div class="dialog" id="campDescriptionDialog" style="display: none;">${person.camp.description}</div>
                    <script type="text/javascript">
                    <!--
                        $("#campDescriptionDialog").dialog({
                            title: '<fmt:message key="camp.title"/>: ${person.camp.name}',
                            modal: true,
                            autoOpen: false
                        });
                
                        $('#campDescriptionImg').click(function() {
                            $("#campDescriptionDialog").dialog('open');
                            $('.ui-widget-overlay').click(function() { $("#campDescriptionDialog").dialog("close"); });
                        });
                    //-->
                    </script>
                </c:if>

                <c:set var="geolocational" value="${person.camp}" />
                <%@ include file="../google_maps_link.jsp" %>
            </td>
        </tr>
    </c:if>
</table>

<h2><fmt:message key="person.death.title"/></h2>
<table>
    <c:if test="${person.dateOfDeath != '?'}">
        <tr>
            <th> <fmt:message key="person.dateOfDeath"/> </th>
            <td> <c:if test="${person.dateOfDeath.approximate}"><fmt:message key="date.approximate"/></c:if> ${person.dateOfDeath} </td>
        </tr>
    </c:if>
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
                <c:forEach items="${person.causesOfDeath}" var="causeOfDeath" varStatus="status"><c:if test="${status.index > 0}">; </c:if>${causeOfDeath}</c:forEach>
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
<c:choose>
    <c:when test="${empty person.graves}">
        <p style="font-style: italic;"><fmt:message key="info.noData"/></p>
    </c:when>
    <c:otherwise>
        <c:forEach items="${person.graves}" var="grave" varStatus="status">

            <%-- Separate the graves with a horizontal line. --%>
            <c:if test="${status.index > 0}">
                <div class="ruler"></div>
            </c:if>
        
            <table>
                <c:if test="${grave.dateOfBurial != '?'}">
                    <tr>
                        <th> <fmt:message key="type.date"/> </th>
                        <td> <c:if test="${grave.dateOfBurial.approximate}"><fmt:message key="date.approximate"/></c:if> ${grave.dateOfBurial} </td>
                    </tr>
                </c:if>

                <c:if test="${!empty grave.cemetery}">
                    <tr>
                        <th> <fmt:message key="cemetery.title"/> </th>
                        <td> ${grave.cemetery.name}
                        
                            <c:set var="geolocational" value="${grave.cemetery}" />
                            <%@ include file="../google_maps_link.jsp" %>
                        </td>
                    </tr>
                </c:if>
        
                <c:if test="${!empty grave.cemetery.address}">
                    <tr>
                        <th> <fmt:message key="cemetery.address"/> </th>
                        <td> ${grave.cemetery.address} </td>
                    </tr>
                </c:if>
        
                <c:if test="${!empty grave.cemetery.postalDistrict.postcode}">
                    <tr>
                        <th> <fmt:message key="cemetery.postalDistrict"/> </th>
                        <td> ${grave.cemetery.postalDistrict.postcode} ${grave.cemetery.postalDistrict.name} </td>
                    </tr>
                </c:if>
        
                <c:if test="${!empty grave.cemetery.postalDistrict.county}">
                    <tr>
                        <th> <fmt:message key="cemetery.county"/> </th>
                        <td> ${grave.cemetery.postalDistrict.county} </td>
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
    </c:otherwise>
</c:choose>

<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
    <c:if test="${!empty person.remarks}">
        <h2><fmt:message key="person.remarks"/></h2>
        <p>
            ${person.remarks}
        </p>
    </c:if>
</sec:authorize>

</div>


<%@ include file="../footer.jsp" %>