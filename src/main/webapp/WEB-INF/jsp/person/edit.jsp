<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<style type="text/css">
    input.text { margin-bottom:12px; width:100%; }
    input { vertical-align: middle; }
    select { width: 20em; }
    div.dialog fieldset { padding:0; border:0; margin-top:25px; }
    fieldset.main { border-color: #003366; margin-bottom: 1em; }
    fieldset.main legend { color: #003366; font-size: 110%; }
    fieldset.sub { border: solid 1px #cccccc; margin-bottom: 0.5em; }
    fieldset.sub legend { color: #000000; font-size: 80%; }
    table.fixedWidth th { width: 12em; }
    table.withMargin { margin: 0 1em; }
    th { font-weight: normal; }
</style>

<script type="text/javascript">
    $(function(){
        //hover states on the static widgets
        $('#save_button, #causeOfDeathSelector').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });
</script>

<div class="container">
    <div>
        <form:form method="post" id="personForm">
            <form:hidden path="person.id"/>

            <fieldset class="main">
                <legend><fmt:message key="person.title"/></legend>

                <table class="fixedWidth">
                    <tr>
                        <td>
                            <fieldset class="sub">
                                <legend><fmt:message key="charset.western"/></legend>

                                <table>
                                    <tr>
                                        <th><form:label for="person.westernDetails.firstName" path="person.westernDetails.firstName" cssErrorClass="ui-state-error-text"><fmt:message key="person.firstName"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.westernDetails.firstName" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.westernDetails.nameOfFather" path="person.westernDetails.nameOfFather" cssErrorClass="ui-state-error-text"><fmt:message key="person.nameOfFather"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-allui-state-error" path="person.westernDetails.nameOfFather" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.westernDetails.lastName" path="person.westernDetails.lastName" cssErrorClass="ui-state-error-text"><fmt:message key="person.lastName"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.westernDetails.lastName" /> </td>
                                    </tr>
                                    <tr>
                                        <th><form:label for="person.westernDetails.placeOfBirth" path="person.westernDetails.placeOfBirth" cssErrorClass="ui-state-error-text"><fmt:message key="person.placeOfBirth"/></form:label></th>
                                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.westernDetails.placeOfBirth" /> </td>
                                    </tr>
                                </table>
                                <form:errors element="p" cssClass="ui-state-error-text" cssStyle="text-align: center;" path="person.westernDetails.*" />
                            </fieldset>
                        </td>

                        <td>
                            <fieldset class="sub">
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

                <table class="fixedWidth withMargin">
                    <tr>
                        <th><form:label for="person.dateOfBirth" path="person.dateOfBirth" cssErrorClass="ui-state-error-text"><fmt:message key="person.dateOfBirth"/></form:label></th>
                        <td>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfBirth.day" size="1" maxlength="2" />
                            <span class="soft">/</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfBirth.month" size="1" maxlength="2" />
                            <span class="soft">/</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfBirth.year" size="4" maxlength="4" />
                            <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span>
                            
                            <span style="padding-right: 2em;"></span>
                            <fmt:message key="grave.approximateDate"/>
                            <form:checkbox path="person.dateOfBirth.approximate" />
                        </td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.dateOfBirth.*" />
                    </tr>

                    <tr>
                        <th><form:label for="person.nationality" path="person.nationality" cssErrorClass="ui-state-error-text"><fmt:message key="person.nationality"/></form:label></th>
                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.nationality" /> </td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.nationality" />
                    </tr>

                    <tr>
                        <th><form:label for="person.rank" path="person.rank" cssErrorClass="ui-state-error-text"><fmt:message key="person.rank"/></form:label></th>
                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.rank" /></td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.rank" />
                    </tr>

                    <tr>
                        <th>
                            <form:label for="person.stalag" path="person.stalag" cssErrorClass="ui-state-error-text"><fmt:message key="stalag.title"/></form:label>
                        </th>

                        <td>
                            <form:select path="person.stalag" cssClass="ui-widget-content ui-corner-all">
                                <option value="null"></option>
                            
                                <c:forEach items="${stalags}" var="stalag">
                                    <c:choose>
                                        <c:when test="${stalag.id == command.person.stalag.id}">
                                            <option value="${stalag.id}" selected="selected">${stalag.name}</option>
                                        </c:when> <c:otherwise>
                                            <option value="${stalag.id}">${stalag.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                        </td>
                        
                        <th><form:label for="person.prisonerNumber" path="person.prisonerNumber" cssErrorClass="error"><fmt:message key="person.prisonerNumber"/></form:label></th>
                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.prisonerNumber" /></td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.prisonerNumber" />
                    </tr>

                    <tr>
                        <th><form:label for="person.camp" path="person.camp" cssErrorClass="ui-state-error-text"><fmt:message key="camp.title"/></form:label></th>
                        <td>
                            <form:select path="person.camp" cssClass="ui-widget-content ui-corner-all">
                                <option value="null"></option>
                                
                                <c:forEach items="${camps}" var="camp">
                                    <c:choose>
                                        <c:when test="${camp.id == command.person.camp.id}">
                                            <option value="${camp.id}" selected="selected">${camp.name}</option>
                                        </c:when> <c:otherwise>
                                            <option value="${camp.id}">${camp.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                            
                            
                            
                        </td>
                    </tr>
                </table>
            </fieldset>

            <fieldset class="main">
                <legend><fmt:message key="person.death.title"/></legend>

                <table class="fixedWidth withMargin">
                    <tr>
                        <th><form:label for="person.dateOfDeath" path="person.dateOfDeath" cssErrorClass="ui-state-error-text"><fmt:message key="person.dateOfDeath"/></form:label></th>
                        <td>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfDeath.day" size="1" maxlength="2" />
                            <span class="soft">/</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfDeath.month" size="1" maxlength="2" />
                            <span class="soft">/</span>
                            <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.dateOfDeath.year" size="4" maxlength="4" />
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

                    <tr>
                        <th><fmt:message key="person.causesOfDeath"/></th>
                        <td>
                            <select id="causeOfDeathSelector" class="ui-widget-content ui-corner-all">
                                <c:forEach items="${causesOfDeath}" var="causeOfDeath">
                                    <option value="${causeOfDeath.id}">${causeOfDeath.cause}</option>
                                </c:forEach>
                            </select>
                            <a onclick="$('#causeOfDeathDialog').dialog('open');" class="clickie"><fmt:message key="button.add"/></a>
                        </td>
                    </tr>

                    <c:forEach items="${command.person.causesOfDeath}" var="causeOfDeath" varStatus="status" >
                        <tr>
                            <th><fmt:message key="person.causeOfDeath"/></th>
                        </tr>
                    </c:forEach>

                    <tr>
                        <th><form:label for="person.obdNumber" path="person.obdNumber" cssErrorClass="ui-state-error-text"><fmt:message key="person.obdNumber"/></form:label></th>
                        <td><form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.obdNumber" /></td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.obdNumber" />
                    </tr>
                </table>
            </fieldset>

            <fieldset class="main">
                <legend><fmt:message key="person.graves.title"/></legend>

                <table id="graveTable" class="withMargin" style="width: 100%;">
                    <tr>
                        <th width="20%"><fmt:message key="type.date"/> <span class="soft">(<fmt:message key="type.date.formatDescription"/>)</span></th>
                        <th width="10%">(<fmt:message key="grave.approximateDate"/>)</th>
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

            <fieldset class="main">
                <legend><fmt:message key="person.various.title"/></legend>

                <table class="fixedWidth withMargin">
                    <tr>
                        <th style="vertical-align: top;"><form:label for="person.remarks" path="person.remarks"><fmt:message key="person.remarks"/></form:label></th>
                        <td><form:textarea cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-corner-all ui-state-error" path="person.remarks" rows="4" cols="100%" cssStyle="width: 100%;" /></td>
                        <form:errors element="td" cssClass="ui-state-error-text" path="person.remarks" />
                    </tr>
                </table>
            </fieldset>

            <p style="text-align: right; padding-top: 1em; padding-right: 2px;">
                <a class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button" href="#" onclick="$('#personForm').submit()">
                    <span class="ui-icon ui-icon-disk"></span><fmt:message key="person.button.save"/>
                </a>

                <input type="submit" style="display: none;" />
            </p>
        </form:form>
    </div>
</div>

<c:set var="index" value="NNN" />
<script type="text/javascript">
<!--
    $('#personForm input:text:visible:first').focus();

    /* Reload the causeOfDeath select box with JSON data. */
    function reloadCauseOfDeathSelector() {
        $.getJSON('<c:url value="/person/causeOfDeath/list" />', function(data) {
            var html = '';
            var len = data.length;
            $.each(data, function(i, item) {
                html += '<option value=' + item.id + '>' + item.cause + '</option>';
            });
            $('#causeOfDeathSelector').html(html);
        });
    }

    /* Make an AJAX call to load the data in the causeOfDeath dialog. */
    function loadCauseOfDeathForm() {
        $.get('<c:url value="/person/causeOfDeath/create" />', function(data) {
            $('#causeOfDeathDialog').html(data);
            $('#causeOfDeathForm input:text:visible:first').focus();
        });
    }

    /* POST the cause of death form. */
/*     $('#dialog').bind('submit', function() {
    	$.post("causeOfDeath/create", $("#causeOfDeathForm").serialize(), function(data) {
            $('#dialog').html(data);
            // alert("Data Loaded: " + data);
        });
    });
*/

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
            	// $("#causeOfDeathForm input:text:visible:first").focus();
            	/* $('#causeOfDeathDefaultField').focus(); */
            },
            beforeclose: function() {
                reloadCauseOfDeathSelector();
            },
            close: function() {
                allFields.val('').removeClass('ui-state-error');
            }
        });
    });

    /* Add a new grave to the list. */
    function addGrave() {
    	var graveTable = document.getElementById('graveTable');
        var graveRows = graveTable.getElementsByTagName('tr');
        var rowId = graveRows.length - 1;

        var graveTds = document.getElementById('graveTrNNN').innerHTML.replace(/NNN/gi, rowId);
        $('#graveTable').append('<tr id="graveTr' + rowId + '">' + graveTds + '</tr>');
    };

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

<div class="dialog" id="causeOfDeathDialog"></div>

<table style="display: none;">
    <%@ include file="../grave/graveTds_raw.jsp"%>
</table>


<%@ include file="../footer.jsp" %>