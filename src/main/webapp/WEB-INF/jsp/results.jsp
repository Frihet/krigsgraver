<%@ include file="header.jsp"%>
<%@ include file="includes.jsp"%>
<%@ page session="false"%>

<style type="text/css"><!--
    table.results { width: 100%; border-collapse: collapse; }
    table.results th, table.results td { padding-left: 1em; padding-right: 0.5em; }
    table.results th { background-color: black; color: white; padding-top: 1em; padding-bottom: 1em; }
    table.results td { padding-top: 0.5em; padding-bottom: 1.5em; }
    table.results tr.tr0 { background-color: #dadab5; }
    table.results tr.tr1 { }
    table.results td { vertical-align: top; }
    div.paginator { text-align: center; color: #444; font-size: 80%; }
    div.paginator a { text-decoration: none; color: white; }
    .hover { color: white !important; background-color: #919152 !important; cursor: pointer; }
--></style>

<h1><fmt:message key="search.title"/></h1>

<div style="margin: 0 auto; padding-bottom: 1em; width: 50%;">
    <%@ include file="simple_search_bar.jsp"%>
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
        <th class="ui-corner-tr"><fmt:message key="person.dateOfBirth" /></th>
    </tr>

    <c:if test="${empty persons}">
        <tr class="line tr0">
            <td colspan="3">
                <div style="text-align: center; font-style: italic; padding-top: 1em;">
                    <fmt:message key="search.noResults" />
                </div>
            </td>
        </tr>
    </c:if>

    <c:forEach items="${persons}" var="person" varStatus="status">
        <tr class="line tr${status.index % 2}">
            <td><a href='<c:url value="/person/${person.id}/view"/>'>${person.westernDetails}<br></br>${person.cyrillicDetails}&nbsp;</a></td>
            <td>${person.nationality}</td>
            <td>${person.dateOfBirth}</td>
        </tr>
    </c:forEach>
    <tr>
        <th colspan="3" class="ui-corner-bottom">
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