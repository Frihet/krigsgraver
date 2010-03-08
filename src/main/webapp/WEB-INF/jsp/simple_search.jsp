<%@ include file="header.jsp" %>
<%@ include file="includes.jsp" %>
<%@ page session="false" %>

<c:set var="simple" value="${true}" />


<style type="text/css">
<!--
    #mainContent { max-width: 200em; }
-->
</style>


<h1><fmt:message key="menu.search.simple"/></h1>

<div class="searchContainer ui-widget">
<%--
    <table>
        <tr>
            <td style="width: 100%; padding: 0 0.5em 0 0">
                <div class="searchMenu">
                    <span><fmt:message key="search.simple"/></span>
                    <span><a href='<c:url value='/queryBuilder'/>'><fmt:message key="search.advanced"/></a></span>
                </div>
            </td>
        </tr>
    </table>
 --%>

    <form style="text-align: center;">
        <%@ include file="simple_search_bar.jsp"%>
<%--
        <input type="checkbox" name="fuzzy" value="true" />
        <fmt:message key="search.findSimilarWords" />
 --%>
 
<%--
        <input type="checkbox" class="ui-state-default" style="vertical-align: middle;" name="fuzzy" value="true" />
        <fmt:message key="search.findSimilarWords" />
 --%>
    </form>
</div>

<script type="text/javascript">
<!--
    $(function() {
        $('#searchField').focus();

        $('#search').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });

//-->
</script>

<%@ include file="footer.jsp" %>