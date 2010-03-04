<%@ include file="header.jsp" %>
<%@ include file="includes.jsp" %>
<%@ page session="false" %>

<style type="text/css">
    #mainContent {
        max-width: 200em;
    }
</style>

<h1><fmt:message key="menu.search.advanced"/></h1>

<form method="post">
    <table>
        <tr>
            <th> <fmt:message key="person.name"/> </th>
            <td>
                <input name="fullName" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyName" value="true" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>
        <tr>
            <th> <fmt:message key="person.placeOfBirth"/> </th>
            <td>
                <input name="placeOfBirth" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyPlaceOfBirth" value="true" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <tr>
            <th> <fmt:message key="person.dateOfBirth"/> </th>
            <td>
                <fmt:message key="date.from"/>
                <input class="ui-widget-content ui-corner-all" size="4" maxlength="4" name="dateOfBirthFromYear"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthToMonth"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthToDay"/>
                &nbsp;&nbsp;
                <fmt:message key="date.to"/>
                <input class="ui-widget-content ui-corner-all" size="4" maxlength="4" name="dateOfBirthToYear"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthToMonth"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthToDay"/>
            </td>
        </tr>
    
        <tr>
            <th> <fmt:message key="person.nationality"/> </th>
    <%--

     --%>
            <td>
                <select name="countryCode" class="ui-widget-content ui-corner-all">
                    <option value="">&lt;<fmt:message key="value.notSet"/>&gt;</option>

                    <c:forEach items="${nationalities}" var="nationality">
                        <option value="${nationality.countryCode}">${nationality.countryCode}</option>
                    </c:forEach>
                </select>

<!--                <input name="nationality" value="${nationality}"/>-->
                
            </td>
        </tr>
    </table>

<!-- 
                "dateOfBirth.year", "nationality.countryCode", "prisonerNumber", "obdNumber",
                "rank.name", "camp.name", "stalag.name",
                "placeOfDeath",
                "causeOfDeathDescription", "remarks",
                "causesOfDeath.cause", "causesOfDeath.causeGroup", "graves.cemetery.name"
 -->

    <br></br>
    <button id="search" type="submit" class="ui-priority-primary ui-state-default ui-corner-all"><fmt:message key="button.search"/></button>
</form>

<script type="text/javascript">
<!--
    $(function() {
        // $('#searchField').focus();

        $('#search').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });
//-->
</script>

<%@ include file="footer.jsp" %>