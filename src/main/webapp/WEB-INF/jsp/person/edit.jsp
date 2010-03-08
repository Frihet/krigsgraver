<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<style type="text/css">
    input.text { margin-bottom:12px; width:100%; }
    input { vertical-align: middle; }
    select { width: 20em; }
    th { font-weight: normal; }
    th,td { padding-bottom: 0.5em; }
</style>

<spring:hasBindErrors name="command">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<h1>
    <c:choose>
        <c:when test="${command.person.id == Null}">
            <fmt:message key="person.createNew"/>
        </c:when>
        <c:otherwise>
            <fmt:message key="person.editExisting">
                <fmt:param value="${command.person.fullName}" />
            </fmt:message>
        </c:otherwise>
    </c:choose>
</h1>

<div class="ui-widget">
    <div>
        <form:form method="post" id="personForm">
            <form:hidden path="person.id"/>

            <fieldset class="main ui-corner-all">
                <legend><fmt:message key="person.title"/></legend>

                <table class="fixedWidth">
                    <tr>
                        <td>
                            <fieldset class="sub ui-corner-all">
                                <legend><fmt:message key="charset.western"/></legend>

                                <table>
                                    <tr>
                                        <th><form:label for="person.westernDetails.firstName" path="person.westernDetails.firstName" cssErrorClass="ui-state-error-text"><fmt:message key="person.firstName"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.westernDetails.firstName" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.westernDetails.nameOfFather" path="person.westernDetails.nameOfFather" cssErrorClass="ui-state-error-text"><fmt:message key="person.nameOfFather"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-allui-state-error" path="person.westernDetails.nameOfFather" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.westernDetails.lastName" path="person.westernDetails.lastName" cssErrorClass="ui-state-error-text"><fmt:message key="person.lastName"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.westernDetails.lastName" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.westernDetails.placeOfBirth" path="person.westernDetails.placeOfBirth" cssErrorClass="ui-state-error-text"><fmt:message key="person.placeOfBirth"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.westernDetails.placeOfBirth" /> </td>
                                    </tr>
                                </table>
                                <form:errors element="p" cssClass="ui-state-error-text" cssStyle="text-align: center;" path="person.westernDetails.*" />
                            </fieldset>
                        </td>

                        <td>
                            <fieldset class="sub ui-corner-all">
                                <legend><fmt:message key="charset.cyrillic"/></legend>
                                
                                <table>
                                    <tr>
                                        <th><form:label for="person.cyrillicDetails.firstName" path="person.cyrillicDetails.firstName" cssErrorClass="error"><fmt:message key="person.firstName"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.cyrillicDetails.firstName" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.cyrillicDetails.nameOfFather" path="person.cyrillicDetails.nameOfFather" cssErrorClass="error"><fmt:message key="person.nameOfFather"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.cyrillicDetails.nameOfFather" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.cyrillicDetails.lastName" path="person.cyrillicDetails.lastName" cssErrorClass="error"><fmt:message key="person.lastName"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.cyrillicDetails.lastName" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.cyrillicDetails.placeOfBirth" path="person.cyrillicDetails.placeOfBirth" cssErrorClass="error"><fmt:message key="person.placeOfBirth"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.cyrillicDetails.placeOfBirth" /> </td>
                                    </tr>
                                </table>
                                <form:errors element="p" cssClass="ui-state-error-text" cssStyle="text-align: center;" path="person.cyrillicDetails.*" />
                            </fieldset>
                        </td>
                    </tr>
                </table>

                <table class="fixedWidth withMargin ui-corner-all">
                    <tr>
                        <th><form:label for="person.dateOfBirth" path="person.dateOfBirth" cssErrorClass="ui-state-error-text"><fmt:message key="person.dateOfBirth"/></form:label></th>
                        <td>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfBirth.year" size="4" maxlength="4" />
                            <span class="soft">-</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfBirth.month" size="1" maxlength="2" />
                            <span class="soft">-</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfBirth.day" size="1" maxlength="2" />
                            <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span>
                            
                            <span style="padding-right: 2em;"></span>
                            <fmt:message key="grave.approximateDate"/>
                            <form:checkbox path="person.dateOfBirth.approximate" />
                        </td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.dateOfBirth.*" />
                    </tr>

                    <tr>
                        <th><form:label for="person.nationality" path="person.nationality" cssErrorClass="ui-state-error-text"><fmt:message key="person.nationality"/></form:label></th>
                        <td>
                            <form:select path="person.nationality" cssClass="ui-widget-content ui-corner-all">
                                <option value="null">&lt;<fmt:message key="value.notSet"/>&gt;</option>

                                <c:forEach items="${nationalities}" var="nationality">
                                    <c:choose>
                                        <c:when test="${nationality.id == command.person.nationality.id}">
                                            <option value="${nationality.id}" selected="selected">${nationality.name}</option>
                                        </c:when> <c:otherwise>
                                            <option value="${nationality.id}">${nationality.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                        </td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.nationality" />
                    </tr>

                    <tr>
                        <th><form:label for="person.rank" path="person.rank" cssErrorClass="ui-state-error-text"><fmt:message key="person.rank"/></form:label></th>
                        <td>
                            <form:select id="rankSelector" path="person.rank" cssClass="ui-widget-content ui-corner-all">
                                <option value="not_loaded"><fmt:message key="status.loading"/></option>
                            </form:select>
                        </td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.rank" />
                    </tr>

                    <tr>
                        <th>
                            <form:label for="person.stalag" path="person.stalag" cssErrorClass="ui-state-error-text"><fmt:message key="stalag.title"/></form:label>
                        </th>

                        <td>
                            <form:select id="stalagSelector" path="person.stalag" cssClass="ui-widget-content ui-corner-all">
                                <option value="not_loaded"><fmt:message key="status.loading"/></option>
                            </form:select>
                        </td>
                        
                        <td>
                            <form:label for="person.prisonerNumber" path="person.prisonerNumber" cssErrorClass="error"><fmt:message key="person.prisonerNumber"/></form:label>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.prisonerNumber" />
                        </td>
                            <form:errors element="td" cssClass="ui-state-error-text" path="person.prisonerNumber" />
                    </tr>

                    <tr>
                        <th><form:label for="person.camp" path="person.camp" cssErrorClass="ui-state-error-text"><fmt:message key="camp.title"/></form:label></th>
                        <td>
                            <form:select id="campSelector" path="person.camp" cssClass="ui-widget-content ui-corner-all">
                                <option value="not_loaded"><fmt:message key="status.loading"/></option>
                            </form:select>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <fieldset class="main ui-corner-all">
                <legend><fmt:message key="person.death.title"/></legend>

                <table class="fixedWidth withMargin">
                    <tr>
                        <th><form:label for="person.dateOfDeath" path="person.dateOfDeath" cssErrorClass="ui-state-error-text"><fmt:message key="person.dateOfDeath"/></form:label></th>
                        <td>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfDeath.year" size="4" maxlength="4" />
                            <span class="soft">-</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfDeath.month" size="1" maxlength="2" />
                            <span class="soft">-</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfDeath.day" size="1" maxlength="2" />
                            <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span>
                            <span style="padding-right: 2em;"></span>
                            <fmt:message key="grave.approximateDate"/>
                            <form:checkbox path="person.dateOfDeath.approximate" />
                        </td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.dateOfDeath.*" />
                    </tr>

                    <tr>
                        <th><form:label for="person.placeOfDeath" path="person.placeOfDeath" cssErrorClass="ui-state-error-text"><fmt:message key="person.placeOfDeath"/></form:label></th>
                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.placeOfDeath" /></td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.placeOfDeath" />
                    </tr>

                    <tr style="vertical-align: top;">
                        <th><fmt:message key="person.causesOfDeath"/></th>
                        <td style="padding-right: 1em;">
                            <div id="causeOfDeathSection">
                                <c:forEach items="${command.lazyCausesOfDeath}" var="causeOfDeath" varStatus="status">
                                    <div>
                                        <select id="causeOfDeath${status.index}Selector" name="lazyCausesOfDeath[${status.index}]" class="ui-widget-content ui-corner-all">
                                            <option value="not_loaded"><fmt:message key="status.loading"/></option>
                                        </select>
                                        <script type="text/javascript">
                                            populateSelectList('<c:url value="/causeOfDeath/list" />', 'causeOfDeath${status.index}', 'name', '${causeOfDeath.id}', true, '<fmt:message key="value.notSet"/>');
                                        </script>
                                    </div>
                                </c:forEach>
                            </div>
                        
                            <div>
                                <a class="clickie" onclick="javascript:addCauseOfDeath();"><fmt:message key="person.causeOfDeath.add"/></a>
                            </div>
                        </td>
                        <td><fmt:message key="person.causeOfDeathDescription"/></td>
                        <td>
                            <form:textarea path="person.causeOfDeathDescription" cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" rows="4" cols="30" />
                        </td>
                    </tr>

<%--
                    <c:forEach items="${command.person.causesOfDeath}" var="causeOfDeath" varStatus="status" >
                        <tr>
                            <th><fmt:message key="person.causeOfDeathDescription"/></th>
                        </tr>
                    </c:forEach>
 --%>

                    <tr>
                        <th><form:label for="person.obdNumber" path="person.obdNumber" cssErrorClass="ui-state-error-text"><fmt:message key="person.obdNumber"/></form:label></th>
                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.obdNumber" /></td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.obdNumber" />
                    </tr>
                </table>
            </fieldset>

            <fieldset class="main ui-corner-all">
                <legend><fmt:message key="person.graves.title"/></legend>

                <form:errors element="div" cssClass="ui-state-error-text" cssStyle="text-align: center;" path="lazyGraves*" />

                <table id="graveTable" class="withMargin" style="width: 100%;">
                    <tr>
                        <th width="25%"><fmt:message key="type.date"/> <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span></th>
                        <th width="5%">(<fmt:message key="grave.approximateDate"/>)</th>
                        <th width="25%"><fmt:message key="cemetery.title"/></th>
                        <th width="5%"><fmt:message key="grave.graveField"/></th>
                        <th width="5%"><fmt:message key="grave.graveRow"/></th>
                        <th width="5%"><fmt:message key="grave.graveNumber"/></th>
                        <th width="10%"><fmt:message key="grave.massGrave"/></th>
                        <th width="10%"><fmt:message key="grave.moved"/></th>
                        <th width="10%"><fmt:message key="button.delete"/></th>
                    </tr>

                    <c:forEach items="${command.lazyGraves}" var="grave" varStatus="status">
                        <c:set var="index" value="${status.index}" />
                        <%@ include file="../grave/graveTds_spring.jsp"%>
                    </c:forEach>
                </table>
                
                <div style="text-align: center; padding-top: 1em;">
                    <a class="clickie" onclick="javascript:addGrave();"><fmt:message key="grave.addGrave"/></a>
                </div>
            </fieldset>

            <fieldset class="main ui-corner-all">
                <legend><fmt:message key="person.various.title"/></legend>

                <table class="fixedWidth withMargin">
                    <tr>
                        <th style="vertical-align: top;"><form:label for="person.remarks" path="person.remarks"><fmt:message key="person.remarks"/></form:label></th>
                        <td>
                            <div style="padding-right: 1.5em;">
                                <form:textarea cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.remarks" rows="4" cols="50" cssStyle="width: 100%;" />
                            </div>
                        </td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.remarks" />
                    </tr>
                </table>
            </fieldset>

            <p style="text-align: right; padding-top: 1em; padding-right: 2px;">
                <button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="person.button.save"/></button>
<%--
                <a class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button" href="#" onclick="$('#personForm').submit()">
                    <fmt:message key="person.button.save"/>
                </a>
 --%>

                <input type="submit" style="display: none;" />
            </p>
        </form:form>
    </div>
</div>



<c:set var="index" value="NNN" />
<script type="text/javascript">
<!--
    $(function(){
        $('#save_button, select').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });

    $('#personForm input:text:visible:first').focus();

    /* Reload the causeOfDeath select box with JSON data. */
    function reloadCauseOfDeathSelector() {
        $.getJSON('<c:url value="/causeOfDeath/list" />', function(data) {
            var html = '<option value="null">&lt;<fmt:message key="value.notSet"/>&gt;</option>';
            var len = data.length;
            $.each(data, function(i, item) {
                html += '<option value=' + item.id + '>' + item.name + '</option>';
            });
            $('#causeOfDeathSelector').html(html);
        });
    }

    populateSelectList('<c:url value="/rank/list" />', 'rank', 'name', '${command.person.rank.id}', true, '<fmt:message key="value.notSet"/>');
    populateSelectList('<c:url value="/stalag/list" />', 'stalag', 'name', '${command.person.stalag.id}', true, '<fmt:message key="value.notSet"/>');
    populateSelectList('<c:url value="/camp/list" />', 'camp', 'name', '${command.person.camp.id}', true, '<fmt:message key="value.notSet"/>');
    reloadCauseOfDeathSelector();
    
    /* Make an AJAX call to load the data in the causeOfDeath dialog. */
/*
    function loadCauseOfDeathForm() {
        $.get('<c:url value="/person/causeOfDeath/create" />', function(data) {
            $('#causeOfDeathDialog').html(data);
            $('#causeOfDeathForm input:text:visible:first').focus();
        });
    }
*/

    /* POST the cause of death form. */
/*     $('#dialog').bind('submit', function() {
    	$.post("causeOfDeath/create", $("#causeOfDeathForm").serialize(), function(data) {
            $('#dialog').html(data);
            // alert("Data Loaded: " + data);
        });
    });
*/

    /* Add a new grave to the list. */
    function addGrave() {
    	var graveTable = document.getElementById('graveTable');
        var graveRows = graveTable.getElementsByTagName('tr');
        var rowId = graveRows.length - 1;

        var graveTds = document.getElementById('graveTrNNN').innerHTML.replace(/NNN/gi, rowId);
        $('#graveTable').append('<tr id="graveTr' + rowId + '">' + graveTds + '</tr>');

        populateSelectList('<c:url value="/cemetery/list" />', 'lazyGraves' + rowId + 'Cemetery', 'name', 'null', true, '<fmt:message key="value.notSet"/>');
    };

    /* Add another "cause of death" select box. */
    function addCauseOfDeath() {
        var causeOfDeathSection = document.getElementById('causeOfDeathSection');
        var elements = causeOfDeathSection.getElementsByTagName('div');
        var n = elements.length;
    	$('#causeOfDeathSection').append('<div><select id="causeOfDeath' + n + 'Selector" name="lazyCausesOfDeath[' + n + 
                ']" class="ui-widget-content ui-corner-all">' + 
                '<option value="not_loaded"><fmt:message key="status.loading"/></option></select></div>');
        
        populateSelectList('<c:url value="/causeOfDeath/list" />', 'causeOfDeath' + n, 'name', 'null', true, '<fmt:message key="value.notSet"/>');
    }

    /* Gray out the row if 'delete' is checked.  */
    function toggleDeleteGrave(checkbox, rowSelector) {
    	if (checkbox.checked) {
            $(rowSelector).addClass('ui-state-disabled');
        } else {
            $(rowSelector).removeClass('ui-state-disabled');
        }
    }
//-->
</script>

<%--
<script type="text/javascript">
<!--
/* Prepare the causeOfDeath dialog box. */
$(function() {
    var allFields = $([])
            .add($("#cause"))
            .add($("#causeGroup"))
            .add($("#description"));

    $("#causeOfDeathDialog").dialog({
        bgiframe: true,
        autoOpen: false,
        modal: true,
        title: '<fmt:message key="person.causeOfDeath"/>',
        buttons: {
            '<fmt:message key="button.save"/>': function() {
                $.post('<c:url value="/person/causeOfDeath/create" />', $('#causeOfDeathForm').serialize(), function(data, textStatus) {
                    if (data == "SUCCESS") {
                        $("#causeOfDeathDialog").dialog('close');
                        
                    } else {
                        $('#causeOfDeathDialog').html(data);
                    }
                });
            },

            '<fmt:message key="button.cancel"/>': function() {
                $(this).dialog('close');
            }
        },
        open: function() {
            loadCauseOfDeathForm();
        },
        beforeclose: function() {
            reloadCauseOfDeathSelector();
        },
        close: function() {
            allFields.val('').removeClass('ui-state-error');
        }
    });
});
//-->
</script>

<div class="dialog" id="causeOfDeathDialog"></div>
 --%>

<table style="display: none;">
    <%@ include file="../grave/graveTds_raw.jsp"%>
</table>


<%@ include file="../footer.jsp" %>