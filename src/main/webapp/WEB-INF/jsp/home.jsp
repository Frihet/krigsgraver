<%@ include file="includes.jsp" %>
<fmt:message var="pageTitle" key="menu.home" />
<c:set var="helpPage" value="search" />
<%@ include file="header.jsp" %>
<%@ page session="false" %>

<c:set var="simple" value="${true}" />

<style type="text/css">
<!--
    #mainContent { max-width: 200em; }
-->
</style>

<h1><fmt:message key="home.title"/></h1>

<fmt:message key="home.info"/>

<p>
    <c:url value="/queryBuilder" var="advanced" />
    <fmt:message key="search.simple.instructions">
        <fmt:param value="<a href='${advanced}'>" />
        <fmt:param value="</a>" />
    </fmt:message>
</p>
<div class="searchContainer ui-widget">
    <form action="search" style="text-align: center;">
        <%@ include file="simple_search_bar.jsp"%>
    </form>
</div>

<fmt:message key="home.help"/>


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