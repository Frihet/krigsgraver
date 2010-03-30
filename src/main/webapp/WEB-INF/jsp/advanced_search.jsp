<%@ include file="includes.jsp" %>
<fmt:message key="menu.search.advanced" var="pageTitle" />
<%@ include file="header.jsp" %>
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
                <input id="fullName" type="checkbox" name="fuzzyFields" value="fullName" checked="checked" />
                <label for="fullName"><fmt:message key="search.findSimilarWords" /></label>
            </td>
        </tr>

        <tr><td>&nbsp;</td></tr>
 
        <tr>
            <th> <fmt:message key="person.firstName"/> </th>
            <td>
                <input name="firstName" class="ui-widget-content ui-corner-all" />
                <input id="firstNameFuzzy" type="checkbox" name="fuzzyFields" value="firstName" checked="checked" />
                <label for="firstNameFuzzy"><fmt:message key="search.findSimilarWords" /></label>

                <input id="firstNameBeginsWith" type="checkbox" name="beginsWithFields" value="firstName" />
                <label for="firstNameBeginsWith"><fmt:message key="search.findBeginsWith" /></label>
            </td>
        </tr>
        
        <tr>
            <th> <fmt:message key="person.nameOfFather"/> </th>
            <td>
                <input name="nameOfFather" class="ui-widget-content ui-corner-all" />
                <input id="nameOfFatherFuzzy" type="checkbox" name="fuzzyFields" value="nameOfFather" checked="checked" />
                <label for="nameOfFatherFuzzy"><fmt:message key="search.findSimilarWords" /></label>

                <input id="nameOfFatherBeginsWith" type="checkbox" name="beginsWithFields" value="nameOfFather" />
                <label for="nameOfFatherBeginsWith"><fmt:message key="search.findBeginsWith" /></label>
            </td>
        </tr>

        <tr>
            <th> <fmt:message key="person.lastName"/> </th>
            <td>
                <input name="lastName" class="ui-widget-content ui-corner-all" />
                <input id="lastNameFuzzy" type="checkbox" name="fuzzyFields" value="lastName" checked="checked" />
                <label for="lastNameFuzzy"><fmt:message key="search.findSimilarWords" /></label>
                
                <input id="lastNameBeginsWith" type="checkbox" name="beginsWithFields" value="lastName" />
                <label for="lastNameBeginsWith"><fmt:message key="search.findBeginsWith" /></label>
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
                <input id="checkCamp" type="checkbox" name="fuzzyFields" value="camp" checked="checked" />
                <label for="checkCamp"><fmt:message key="search.findSimilarWords" /></label>
            </td>
        </tr>

        <tr><td>&nbsp;</td></tr>

        <tr>
            <th> <fmt:message key="person.placeOfBirth"/> </th>
            <td>
                <input name="placeOfBirth" class="ui-widget-content ui-corner-all" />
                <input id="checkPlaceOfBirth" type="checkbox" name="fuzzyFields" value="placeOfBirth" checked="checked" />
                <label for="checkPlaceOfBirth"><fmt:message key="search.findSimilarWords" /></label>
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
                <input id="checkPlaceOfDeath" type="checkbox" name="fuzzyFields" value="placeOfDeath" checked="checked" />
                <label for="checkPlaceOfDeath"><fmt:message key="search.findSimilarWords" /></label>
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
                <input id="checkCemetery" type="checkbox" name="fuzzyFields" value="cemetery" checked="checked" />
                <label for="checkCemetery"><fmt:message key="search.findSimilarWords" /></label>
            </td>
        </tr>

        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
        <tr>
            <th> <fmt:message key="person.causeOfDeath"/> </th>
            <td>
                <input name="causeOfDeath" class="ui-widget-content ui-corner-all" />
                <input id="checkCauseOfDeath" type="checkbox" name="fuzzyFields" value="causeOfDeath" checked="checked" />
                <label for="checkCauseOfDeath"><fmt:message key="search.findSimilarWords" /></label>
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
    function mutexCheckbox(fuzzy, beginsWith) {
        $(fuzzy).change(function() {
            if ($(this).is(':checked')) {
                $(beginsWith).attr('checked', false);
            }
        });
    
        $(beginsWith).change(function() {
            if ($(this).is(':checked')) {
                $(fuzzy).attr('checked', false);
            }
        });
    };

    $(function() {
    	mutexCheckbox('#firstNameFuzzy', '#firstNameBeginsWith');
        mutexCheckbox('#nameOfFatherFuzzy', '#nameOfFatherBeginsWith');
        mutexCheckbox('#lastNameFuzzy', '#lastNameBeginsWith');
        
        $('#search').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });



    
 //-->
</script>

<%@ include file="footer.jsp" %>