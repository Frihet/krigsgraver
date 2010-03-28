<%@ include file="../header.jsp" %>
<%@ include file="../includes.jsp" %>

<h1><fmt:message key="menu.admin.various"/></h1>

<h3><fmt:message key="menu.admin.maintenance"/></h3>

<p>
    <a id="indexDataLink" href="indexData"><fmt:message key="admin.various.updateIndexLink"/></a>.
    <fmt:message key="admin.various.updateIndexLinkComment"/>
</p>

<h3 style="margin-top: 2em;"><fmt:message key="menu.admin.dataImport"/></h3>
<p>
    <fmt:message key="admin.various.dataImport.comment"/>
</p>

<div>
    <table>
        <tr>
            <th><fmt:message key="person.plural"/></th>
            <td>
                <form method="post" enctype="multipart/form-data" action="<c:url value='/person/upload'/>">
                    <input type="file" name="file" />
                    <button id="uploadPeople" type="submit"><fmt:message key="admin.various.uploadCsv"/></button>
                </form>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="admin.various.uploadCsv.postalDistricts"/></th>
            <td>
                <form method="post" enctype="multipart/form-data" action="<c:url value='/postalDistrict/upload'/>">
                    <input type="file" name="file" />
                    <button id="uploadPostalDistricts" type="submit"><fmt:message key="admin.various.uploadCsv"/></button>
                </form>
            </td>
        </tr>
    </table>
</div>


<div class="dialog" id="waitDialog" style="display: none; text-align: left; padding: 1.5em;">
    <table>
        <tr>
            <td style="vertical-align: middle;">
                <img src="<c:url value='/inc/img/busy.gif'/>" />
            </td>

            <td style="padding-left: 1.5em; vertical-align: middle;">
                <fmt:message key="info.pleaseWait"/>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
<!--
    $('#indexDataLink, #uploadPeople, #uploadPostalDistricts').click(function() {
        $("#waitDialog").dialog({
            title: '<fmt:message key="info.title"/>',
            bgiframe: true,
            modal: true,
            resizable: false,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); }
        });
    });
//-->
</script>

<%@ include file="../footer.jsp" %>