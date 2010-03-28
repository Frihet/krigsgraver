<%@ include file="header.jsp" %>
<%@ include file="includes.jsp" %>
<%@ page session="false" %>

<spring:hasBindErrors name="command">
    <%@ include file="inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1><fmt:message key="menu.search.advanced"/></h1>

<p><fmt:message key="search.advanced.instructions"/></p>

<form method="post" class="advancedSearch">
    <table>
        <tr>
            <th> <fmt:message key="person.fullName"/> </th>
            <td>
                <input name="fullName" class="long ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="fullName" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <tr><td>&nbsp;</td></tr>
 
        <tr>
            <th> <fmt:message key="person.firstName"/> </th>
            <td>
                <input name="firstName" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="firstName" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>
        
        <tr>
            <th> <fmt:message key="person.nameOfFather"/> </th>
            <td>
                <input name="nameOfFather" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="nameOfFather" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <tr>
            <th> <fmt:message key="person.lastName"/> </th>
            <td>
                <input name="lastName" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="lastName" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <tr><td>&nbsp;</td></tr>

        <tr>
            <th> <fmt:message key="person.nationality"/> </th>
            <td>
                <select name="nationality" class="medium ui-widget-content ui-corner-all">
                    <option value="">&lt;<fmt:message key="value.notSet"/>&gt;</option>

                    <c:forEach items="${nationalities}" var="nationality">
                        <option value="${nationality.name}">${nationality.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <th> <fmt:message key="person.rank"/> </th>
            <td>
                <select name="rank" class="medium ui-widget-content ui-corner-all">
                    <option value="">&lt;<fmt:message key="value.notSet"/>&gt;</option>

                    <c:forEach items="${ranks}" var="rank">
                        <option value="${rank.name}">${rank.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        
        <tr>
            <th> <fmt:message key="stalag.title"/> </th>
            <td>
                <select name="stalag" class="medium ui-widget-content ui-corner-all">
                    <option value="">&lt;<fmt:message key="value.notSet"/>&gt;</option>

                    <c:forEach items="${stalags}" var="stalag">
                        <option value="${stalag.name}">${stalag.name}</option>
                    </c:forEach>
                </select>

<%--
                <input name="stalag" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="stalag" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
 --%>
                
                <span style="font-weight: bold; padding-left: 3em; padding-right: 1em;"><fmt:message key="person.prisonerNumber"/> </span><input name="prisonerNumber" class="ui-widget-content ui-corner-all" />
            </td>
        </tr>

        <tr>
            <th> <fmt:message key="camp.title"/> </th>
            <td>
                <input name="camp" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="camp" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <tr><td>&nbsp;</td></tr>

        <tr>
            <th> <fmt:message key="person.placeOfBirth"/> </th>
            <td>
                <input name="placeOfBirth" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="placeOfBirth" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <tr>
            <th style="vertical-align: text-top; padding-top: 0.5em"> <fmt:message key="person.dateOfBirth"/> </th>
            <td>
                <table>
                <tr><td><fmt:message key="date.from"/></td>
                <td>
                <input class="ui-widget-content ui-corner-all" size="4" maxlength="4" name="dateOfBirthFromYear"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthFromMonth"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthFromDay"/>
                <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span>
                </td></tr>
                <tr><td><fmt:message key="date.to"/></td>
                <td>
                <input class="ui-widget-content ui-corner-all" size="4" maxlength="4" name="dateOfBirthToYear"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthToMonth"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfBirthToDay"/>
                <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span>
                </td></tr>
                </table>
        </tr>

        <tr><td>&nbsp;</td></tr>

        <tr>
            <th> <fmt:message key="person.placeOfDeath"/> </th>
            <td>
                <input name="placeOfDeath" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="placeOfDeath" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <tr>
            <th style="vertical-align: text-top; padding-top: 0.5em"> <fmt:message key="person.dateOfDeath"/> </th>
            <td>
                <table>
                <tr><td><fmt:message key="date.from"/></td>
                <td>
                <input class="ui-widget-content ui-corner-all" size="4" maxlength="4" name="dateOfDeathFromYear"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfDeathToMonth"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfDeathToDay"/>
                <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span>
                </td></tr>
                <tr><td><fmt:message key="date.to"/></td>
                <td>
                <input class="ui-widget-content ui-corner-all" size="4" maxlength="4" name="dateOfDeathToYear"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfDeathToMonth"/> -
                <input class="ui-widget-content ui-corner-all" size="2" maxlength="2" name="dateOfDeathToDay"/>
                <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span>
                </td></tr>
                </table>
        </tr>

        <tr>
            <th> <fmt:message key="cemetery.title"/> </th>
            <td>
                <input name="cemetery" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="cemetery" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>

        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
        <tr>
            <th> <fmt:message key="person.causeOfDeath"/> </th>
            <td>
                <input name="causeOfDeath" class="ui-widget-content ui-corner-all" />
                <input type="checkbox" name="fuzzyFields" value="causeOfDeath" checked="checked" />
                <fmt:message key="search.findSimilarWords" />
            </td>
        </tr>
        </sec:authorize>
        
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