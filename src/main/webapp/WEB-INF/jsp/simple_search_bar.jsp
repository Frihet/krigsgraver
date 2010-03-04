<%@ include file="includes.jsp" %>

<table>
<%--
    <tr>
        <td>
            <table class="searchMenu">
                <tr>
                    <td>
                    <td style="width: 100%; padding: 0 0.5em 0 0">
                            <span style="font-weight: bold;"><fmt:message key="search.simple"/></span>
                            <span><a href='<c:url value='/queryBuilder'/>'><fmt:message key="search.advanced"/></a></span>
                    </td>

                    <td style="font-style: italic; font-size: 80%; text-align: right;">
                        <input type="checkbox" class="ui-state-default" style="vertical-align: middle;" name="fuzzy" value="true" />
                        <fmt:message key="search.findSimilarWords" />
                    </td>
                </tr>
            </table>
        </td>
        <td></td>
    </tr>
 --%>
    <tr>
        <td style="width: 100%; padding: 0 0.5em 0 1px">
            <input id="searchField" name="q" size="50" class="text ui-widget-content ui-corner-all" style="width: 100%;" value='${q}' />
        </td>
        <td>
            <button id="search" type="submit" class="ui-priority-primary ui-state-default ui-corner-all"><fmt:message key="button.search"/></button>
        </td>
    </tr>
</table>
<form:errors element="p" cssStyle="color: red;" path="url" />

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