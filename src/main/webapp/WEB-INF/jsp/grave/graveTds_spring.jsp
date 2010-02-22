<%@ include file="../includes.jsp"%>
<tr id="graveTr${index}">
    <td>
        <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.day" size="1" maxlength="2" />
        <span class="soft">/</span>
        <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.month" size="1" maxlength="2" />
        <span class="soft">/</span>
        <form:input cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].dateOfBurial.year" size="4" maxlength="4" />
    </td>
    <td>
        <form:checkbox path="lazyGraves[${index}].dateOfBurial.approximate" />
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

    <td>
<%--
        <form:checkbox cssClass="deleteGrave" path="lazyGraves[${index}].delete" onchange="if (this.checked) $('#graveTr${index}').addClass('ui-state-disabled');" />
 --%>
        <form:checkbox cssClass="deleteGrave" path="lazyGraves[${index}].delete" onchange="toggleDeleteGrave(this, '#graveTr${index}')" />
    </td>

    <td>
        <form:errors element="td" cssClass="ui-state-error" path="lazyGraves[${status.index}].*" />
    </td>
</tr>
<%--
<tr>
    <td colspan="9">
        <form:textarea cssClass="ui-widget-content ui-corner-all"  cssErrorClass="ui-widget-content ui-state-error" path="lazyGraves[${index}].reference" />
    </td>
</tr>
 --%>
