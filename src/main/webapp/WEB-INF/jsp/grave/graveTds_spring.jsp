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
        <form:select id="lazyGraves${index}CemeterySelector" path="lazyGraves[${index}].cemetery" cssClass="ui-widget-content ui-corner-all">
            <option value="not_loaded"><fmt:message key="status.loading"/></option>
        </form:select>
        <script type="text/javascript">
            populateSelectList('cemetery/list', 'lazyGraves${index}Cemetery', 'name', '${grave.cemetery.id}', true);
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
        <form:errors element="td" cssClass="ui-state-error" path="lazyGraves[${status.index}].*" />
    </td>
</tr>

