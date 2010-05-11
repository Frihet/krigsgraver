<%@ include file="includes.jsp"%>
<fmt:message key="search.results" var="pageTitle" />
<fmt:message key="search.title" var="pageHeader" />
<%@ include file="header.jsp"%>
<%@ page session="false"%>

<c:set var="persons" value="${searchResult.persons}" />
<c:set var="paginator" value="${searchResult.paginator}" />

<div style="margin: 0 auto; padding-bottom: 1em; padding-top: 1em; width: 50%;">
    <form style="text-align: center;">
        <%@ include file="simple_search_bar.jsp"%>
    </form>
</div>

<c:if test="${!empty persons}">
    <div style="font-style: italic; padding-bottom: 0.5em;">
        <fmt:message key="search.numberOfResults">
            <fmt:param value="${paginator.numberOfResults}" />
        </fmt:message>
    </div>
</c:if>

<table class="results">
    <tr class="header">
        <th class="ui-corner-tl"><fmt:message key="person.name" /></th>
        <th><fmt:message key="person.nationality" /></th>
        <th><fmt:message key="person.dateOfBirth" /></th>
        <th><fmt:message key="person.dateOfDeath" /></th>
        <th class="ui-corner-tr"><fmt:message key="person.placeOfDeath" /></th>
    </tr>

    <c:if test="${empty persons}">
        <tr class="line tr0">
            <td colspan="5">
                <div style="text-align: center; font-style: italic; padding-top: 1em;">
                    <fmt:message key="search.noResults" />
                </div>
            </td>
        </tr>
    </c:if>

    <fmt:message key="type.unknown" var="unknown" />
    
    <c:forEach items="${persons}" var="person" varStatus="status">
        <tr class="line tr${status.index % 2}">
<%--
            <td><a href='<c:url value="/person/${person.id}/view"/>'>${person.westernDetails}<br></br>${person.cyrillicDetails}&nbsp;</a></td>
 --%>
            <td><a href='<c:url value="/person/${person.id}/view"/>'>${person.fullName}</a></td>
            <td>${person.nationality.name}</td>
            <td>${person.dateOfBirth}</td>
            <td>${person.dateOfDeath}</td>
            <td>
                <c:choose>
                    <c:when test="${!empty person.placeOfDeath}">
                        ${person.placeOfDeath}
                    </c:when>
                    <c:otherwise>
                        <span style="font-style: italic">${unknown}</span>
                    </c:otherwise>
                </c:choose>
            </td>            
        </tr>
    </c:forEach>
    <tr>
        <th colspan="5" class="ui-corner-bottom">
            <%@ include file="paginator.jsp"%>
        </th>
    </tr>
</table>


<%--
<script type="text/javascript">
<!--
    $(function(){
        $('.line').hover(
            function() { $(this).addClass('hover'); }, 
            function() { $(this).removeClass('hover'); }
        );
    });
//-->
</script>
 --%>

<%@ include file="footer.jsp"%>