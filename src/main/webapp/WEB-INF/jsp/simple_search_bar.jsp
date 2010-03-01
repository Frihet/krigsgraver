<%@ include file="includes.jsp" %>

<form style="text-align: center;">
    <table>
        <tr>
            <td style="width: 100%; padding: 0 0.5em 0 0">
                <input id="searchField" name="q" size="50" class="text ui-widget-content ui-corner-all" style="width: 100%;" />
            </td>
            <td>
                <button id="search" type="submit" class="ui-priority-primary ui-state-default ui-corner-all"><fmt:message key="button.search"/></button>
            </td>
        </tr>
    </table>
    <form:errors element="p" cssStyle="color: red;" path="url" />
</form>

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