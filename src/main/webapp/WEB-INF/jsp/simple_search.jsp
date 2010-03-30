<%@ include file="includes.jsp" %>
<fmt:message key="menu.search.simple" var="pageTitle" />
<%@ include file="header.jsp" %>
<%@ page session="false" %>

<c:set var="simple" value="${true}" />

<style type="text/css">
<!--
    #mainContent { max-width: 200em; }
-->
</style>

<h1><fmt:message key="menu.search.simple"/></h1>

<p>
    <c:url value="/queryBuilder" var="advanced" />
    <fmt:message key="search.simple.instructions">
        <fmt:param value="<a href='${advanced}'>" />
        <fmt:param value="</a>" />
    </fmt:message>
</p>

<div class="searchContainer ui-widget">
    <form style="text-align: center;">
        <%@ include file="simple_search_bar.jsp"%>
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