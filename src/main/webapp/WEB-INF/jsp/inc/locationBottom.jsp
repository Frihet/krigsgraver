<%@ include file="../includes.jsp"%>

<div style="font-size: xx-small; font-style: italic;">
    <input type="hidden" name="locationId" value="${location.id}" />
    <fmt:message key='admin.locations.required_info'/>
</div>

<div class="buttonRow">
    <table class="buttons">
        <tr>
            <td>
                <button class="image" type="submit">
                    <div style="background-image: url(<c:url value='/inc/img/icons/table_save.png'/>);">
                        <fmt:message key='global.save'/>
                    </div>
                </button>
            </td>
        </tr>
    </table>
</div>
