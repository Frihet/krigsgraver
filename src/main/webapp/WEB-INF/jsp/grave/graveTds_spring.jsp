<%@ include file="../includes.jsp"%>
<td>
    <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.day" size="1" maxlength="2" />
</td>
<td>
    <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.month" size="1" maxlength="2" />
</td>
<td>
    <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.year" size="4" maxlength="4" />
</td>
<td>
    <form:checkbox path="lazyGraves[${index}].approximateDate" />
</td>
<td>
    <%--
    <select id="lazyGraves${index}.cemetery" name="lazyGraves[${index}].cemetery">
        TODO...
    </select>
    --%>
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