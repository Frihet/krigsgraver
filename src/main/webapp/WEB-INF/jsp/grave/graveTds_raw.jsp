<%@ include file="../includes.jsp"%>
<td>
    <input id="lazyGraves${index}.dateOfBurial.day" name="lazyGraves[${index}].dateOfBurial.day" type="text" value="" size="1" maxlength="2"/>
</td>
<td>
    <input id="lazyGraves${index}.dateOfBurial.month" name="lazyGraves[${index}].dateOfBurial.month" type="text" value="" size="1" maxlength="2"/>
</td>

<td>
    <input id="lazyGraves${index}.dateOfBurial.year" name="lazyGraves[${index}].dateOfBurial.year" type="text" value="" size="4" maxlength="4"/>
</td>
<td>
    <input id="lazyGraves[${index}].approximateDate1" name="lazyGraves[${index}].approximateDate" type="checkbox" value="true"/><input type="hidden" name="_lazyGraves[${index}].approximateDate" value="on"/>
</td>
<td>
    <%--
    <select id="lazyGraves${index}.cemetery" name="lazyGraves[${index}].cemetery">
        TODO...
    </select>
    --%>
</td>
<td>
    <input id="lazyGraves${index}.graveField" name="lazyGraves[${index}].graveField" type="text" value="" size="2"/>
</td>
<td>
    <input id="lazyGraves${index}.graveRow" name="lazyGraves[${index}].graveRow" type="text" value="" size="2"/>
</td>
<td>

    <input id="lazyGraves${index}.graveNumber" name="lazyGraves[${index}].graveNumber" type="text" value="" size="2"/>
</td>
<td>
    <input id="lazyGraves[${index}].massGrave1" name="lazyGraves[${index}].massGrave" type="checkbox" value="true"/><input type="hidden" name="_lazyGraves[${index}].massGrave" value="on"/>
</td>
<td>
    <input id="lazyGraves[${index}].moved1" name="lazyGraves[${index}].moved" type="checkbox" value="true"/><input type="hidden" name="_lazyGraves[${index}].moved" value="on"/>
</td>