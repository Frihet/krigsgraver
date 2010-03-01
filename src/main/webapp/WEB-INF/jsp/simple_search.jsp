<%@ include file="header.jsp" %>
<%@ include file="includes.jsp" %>
<%@ page session="false" %>

<style type="text/css">
    #mainContent {
        max-width: 200em;
    }
</style>

<h1><fmt:message key="search.title"/></h1>

<div class="searchContainer ui-widget">
<%--
    <div class="searchMenu">
        <span><fmt:message key="search.simple"/></span>
        <span><a href="#"><fmt:message key="search.advanced"/></a></span>
    </div>
 --%>

    <%@ include file="simple_search_bar.jsp"%>
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