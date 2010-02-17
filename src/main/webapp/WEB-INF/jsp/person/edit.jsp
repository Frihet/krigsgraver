<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<style type="text/css">
<%--
        label, input { display:block; }
        input.text { margin-bottom:12px; width:95%; padding: .4em; }
        fieldset { padding:0; border:0; margin-top:25px; }
        body { font-size: 62.5%; }
        h1 { font-size: 1.2em; margin: .6em 0; }
        div#users-contain {  width: 350px; margin: 20px 0; }
        div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
        div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
 --%>
        .ui-button { outline: 0; margin:0; padding: .4em 1em .5em; text-decoration:none;  !important; cursor:pointer; position: relative; text-align: center; }
        .ui-dialog .ui-state-highlight, .ui-dialog .ui-state-error { padding: .3em;  }
</style>


<div class="container">
    <div>
        <form:form method="post">
            <form:hidden path="person.id"/>

            <fieldset>
                <legend><fmt:message key="person.title"/></legend>

                <table>
                    <tr>
                        <th><form:label for="person.westernDetails.firstName" path="person.westernDetails.firstName" cssErrorClass="error"><fmt:message key="person.firstName"/></form:label></th>
                        <td><form:input path="person.westernDetails.firstName" /> </td>

                        <th><form:label for="person.westernDetails.nameOfFather" path="person.westernDetails.nameOfFather" cssErrorClass="error"><fmt:message key="person.nameOfFather"/></form:label></th>
                        <td><form:input path="person.westernDetails.nameOfFather" /> </td>

                        <th><form:label for="person.westernDetails.lastName" path="person.westernDetails.lastName" cssErrorClass="error"><fmt:message key="person.lastName"/></form:label></th>
                        <td><form:input path="person.westernDetails.lastName" /> </td>

                        <th><form:label for="person.westernDetails.placeOfBirth" path="person.westernDetails.placeOfBirth" cssErrorClass="error"><fmt:message key="person.placeOfBirth"/></form:label></th>
                        <td><form:input path="person.westernDetails.placeOfBirth" /> </td>

                        <form:errors element="td" cssClass="error" path="person.westernDetails.*" />
                    </tr>

                    <tr>
                        <th><form:label for="person.cyrillicDetails.firstName" path="person.cyrillicDetails.firstName" cssErrorClass="error"><fmt:message key="person.firstName"/></form:label></th>
                        <td><form:input path="person.cyrillicDetails.firstName" /> </td>

                        <th><form:label for="person.cyrillicDetails.nameOfFather" path="person.cyrillicDetails.nameOfFather" cssErrorClass="error"><fmt:message key="person.nameOfFather"/></form:label></th>
                        <td><form:input path="person.cyrillicDetails.nameOfFather" /> </td>

                        <th><form:label for="person.cyrillicDetails.lastName" path="person.cyrillicDetails.lastName" cssErrorClass="error"><fmt:message key="person.lastName"/></form:label></th>
                        <td><form:input path="person.cyrillicDetails.lastName" /> </td>

                        <th><form:label for="person.cyrillicDetails.placeOfBirth" path="person.cyrillicDetails.placeOfBirth" cssErrorClass="error"><fmt:message key="person.placeOfBirth"/></form:label></th>
                        <td><form:input path="person.cyrillicDetails.placeOfBirth" /> </td>

                        <form:errors element="td" cssClass="error" path="person.cyrillicDetails.*" />
                    </tr>

                    <tr>
                        <th><form:label for="person.dateOfBirth" path="person.dateOfBirth" cssErrorClass="error"><fmt:message key="person.dateOfBirth"/></form:label></th>
                        <td>
                            <form:input path="person.dateOfBirth.day" size="1" maxlength="2" />
                            <form:input path="person.dateOfBirth.month" size="1" maxlength="2" />
                            <form:input path="person.dateOfBirth.year" size="4" maxlength="4" />
                        </td>
                        <form:errors element="td" cssClass="error" path="person.dateOfBirth.*" />
                    </tr>

                    <tr>
                        <th><form:label for="person.nationality" path="person.nationality" cssErrorClass="error"><fmt:message key="person.nationality"/></form:label></th>
                        <td><form:input path="person.nationality" /> </td>
                        <form:errors element="td" cssClass="error" path="person.nationality" />
                    </tr>

                    <tr>
                        <th><form:label for="person.prisonerNumber" path="person.prisonerNumber" cssErrorClass="error"><fmt:message key="person.prisonerNumber"/></form:label></th>
                        <td><form:input path="person.prisonerNumber" /></td>
                        <form:errors element="td" cssClass="error" path="person.prisonerNumber" />
                    </tr>

                    <tr>
                        <th><form:label for="person.rank" path="person.rank" cssErrorClass="error"><fmt:message key="person.rank"/></form:label></th>
                        <td><form:input path="person.rank" /></td>
                        <form:errors element="td" cssClass="error" path="person.rank" />
                    </tr>

                    <tr>
                        <th><form:label for="person.obdNumber" path="person.obdNumber" cssErrorClass="error"><fmt:message key="person.obdNumber"/></form:label></th>
                        <td><form:input path="person.obdNumber" /></td>
                        <form:errors element="td" cssClass="error" path="person.obdNumber" />
                    </tr>



                    <tr>
                        <th><form:label for="person.remarks" path="person.remarks" cssErrorClass="error"><fmt:message key="person.remarks"/></form:label></th>
                        <td><form:textarea path="person.remarks" /></td>
                        <form:errors element="td" cssClass="error" path="person.remarks" />
                    </tr>
                </table>

<%--
    <form:select path="person.receiver" cssStyle="width: 25em;" items="${receivers}" itemLabel="name" />
 --%>

            </fieldset>

            <fieldset>
                <legend><fmt:message key="person.death.title"/></legend>

                <table>
                    <tr>
                        <th><form:label for="person.dateOfDeath" path="person.dateOfDeath" cssErrorClass="error"><fmt:message key="person.dateOfDeath"/></form:label></th>
                        <td>
                            <form:input path="person.dateOfDeath.day" size="1" maxlength="2" />
                            <form:input path="person.dateOfDeath.month" size="1" maxlength="2" />
                            <form:input path="person.dateOfDeath.year" size="4" maxlength="4" />
                        </td>
                        <form:errors element="td" cssClass="error" path="person.dateOfDeath.*" />
                    </tr>

                    <tr>
                        <th><form:label for="person.placeOfDeath" path="person.placeOfDeath" cssErrorClass="error"><fmt:message key="person.placeOfDeath"/></form:label></th>
                        <td><form:input path="person.placeOfDeath" /></td>
                        <form:errors element="td" cssClass="error" path="person.placeOfDeath" />
                    </tr>

                    <tr>
                        <th><fmt:message key="person.causeOfDeath"/></th>
                        <td>
                            <select id="causeOfDeathSelector">
                                <c:forEach items="${causesOfDeath}" var="causeOfDeath">
                                    <option value="${causeOfDeath.id}">${causeOfDeath.cause}</option>
                                </c:forEach>
                            </select>
                            <a onclick="$('#causeOfDeathDialog').dialog('open');"><fmt:message key="button.add"/></a>
                        </td>
                    </tr>

                    <c:forEach items="${command.person.causesOfDeath}" var="causeOfDeath" varStatus="status" >
                        <tr>
                            <th><fmt:message key="person.causeOfDeath"/></th>
<!--                            <td>${causeOfDeath.cause}</td>-->
                        </tr>
                    </c:forEach>
                </table>
            </fieldset>

            <fieldset>
                <legend><fmt:message key="person.graves.title"/></legend>

                <table id="graveTable">
                    <tr>
                        <th colspan="3"><fmt:message key="type.date"/></th>
                        <th>(<fmt:message key="grave.approximateDate"/>)</th>
                        <th><fmt:message key="cemetery.title"/></th>
                        <th><fmt:message key="grave.graveField"/></th>
                        <th><fmt:message key="grave.graveRow"/></th>
                        <th><fmt:message key="grave.graveNumber"/></th>
                        <th><fmt:message key="grave.massGrave"/></th>
                        <th><fmt:message key="grave.moved"/></th>
                    </tr>

                    <c:forEach items="${command.lazyGraves}" var="grave" varStatus="status">
                        <tr>
                            <c:set var="index" value="${status.index}" />
                            <%@ include file="../grave/graveTds_spring.jsp"%>

                            <td>
                                <form:errors element="td" cssClass="error" path="lazyGraves[${status.index}].*" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                
                <a onclick="javascript:addGrave();">Add entry</a>
            </fieldset>
            
            <p>
                <button type="submit"><fmt:message key="button.save"/></button>
            </p>
        </form:form>
    </div>
</div>

<c:set var="index" value="NNN" />
<script type="text/javascript">
<!--
    /* Reload the causeOfDeath select box with JSON data. */
    function reloadCauseOfDeathSelector() {
        $.getJSON('causeOfDeath/list', function(data) {
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
        $.get('causeOfDeath/create', function(data) {
            $('#causeOfDeathDialog').html(data);
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
                    $.post("causeOfDeath/create", $("#causeOfDeathForm").serialize(), function(data, textStatus) {
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

    function addGrave() {
    	var graveTable = document.getElementById('graveTable');
        var graveRows = graveTable.getElementsByTagName('tr');

        var graveTds = document.getElementById('graveTds').innerHTML.replace(/NNN/gi, graveRows.length-1);
        $('#graveTable').append('<tr>' + graveTds + '</tr>');
    }

//-->
</script>

<div id="causeOfDeathDialog"></div>

<table style="display: none;">
  <tr id="graveTds">
    <%@ include file="../grave/graveTds_raw.jsp"%>
  </tr>
</table>


<%@ include file="../footer.jsp" %>