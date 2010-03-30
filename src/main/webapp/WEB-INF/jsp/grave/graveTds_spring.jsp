<%@ include file="../includes.jsp"%>
<tr id="graveTr${index}">
    <td>
        <spring:bind path="lazyGraves[${index}].dateOfBurial">
            <c:choose>
                <c:when test="${!empty status.errorMessages}"><c:set var="dateOfdateOfBurialErrorClass" value="ui-state-error" /></c:when>
                <c:otherwise><c:set var="dateOfdateOfBurialErrorClass" value="" /></c:otherwise>
            </c:choose>
        </spring:bind>

        <form:input id="graveYear${index}" cssClass="ui-widget-content ui-corner-all ${dateOfdateOfBurialErrorClass}" cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.year" size="4" maxlength="4" />
        <span class="soft">-</span>
        <form:input cssClass="ui-widget-content ui-corner-all ${dateOfdateOfBurialErrorClass}"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.month" size="1" maxlength="2" />
        <span class="soft">-</span>
        <form:input cssClass="ui-widget-content ui-corner-all ${dateOfdateOfBurialErrorClass}"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.day" size="1" maxlength="2" />
    </td>
    <td>
        <form:checkbox path="lazyGraves[${index}].dateOfBurial.approximate" />
    </td>
    <td>
        <form:select id="lazyGraves${index}CemeterySelector" path="lazyGraves[${index}].cemetery" cssClass="ui-widget-content ui-corner-all">
            <option value="not_loaded"><fmt:message key="status.loading"/></option>
        </form:select>
        <script type="text/javascript">
            populateSelectList('<c:url value="/cemetery/list.json" />', 'lazyGraves${index}Cemetery', 'name', 'cemeteries', '${grave.cemetery.id}', true, '<fmt:message key="value.notSet"/>');
        </script>
    </td>
    <td>
        <form:input cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].graveField" size="2" />
    </td>
    <td>
        <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].graveRow" size="2" />
    </td>
    <td>
        <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].graveNumber" size="2" />
    </td>
    <td>
        <form:checkbox path="lazyGraves[${index}].massGrave" />
    </td>
    <td>
        <form:checkbox path="lazyGraves[${index}].moved" />
    </td>
    <td>
        <form:checkbox cssClass="deleteGrave" path="lazyGraves[${index}].delete" onclick="toggleDeleteGrave(this, '#graveTr${index} *')" />
    </td>
    <td>
<%--
        <form:errors element="td" cssClass="ui-state-error" path="lazyGraves[${status.index}].*" />
 --%>
 
<script type="text/javascript">
<!--
$('#graveYear${index}').change(function() {
    var warnings = "";
    
    var year = $('#graveYear${index}').val();
    var yearNum = parseInt(year);

    if (yearNum < 1940) {
        warnings += '${msgWarning}: ${msgYear} ${msgShouldNotBeBefore} 1940<br/>';
        $(this).addClass('ui-state-error');
    } else {
    	$(this).removeClass('ui-state-error');
    }
    
    $('#graveErrors').html(warnings);
});
//-->
</script>

    </td>
</tr>

