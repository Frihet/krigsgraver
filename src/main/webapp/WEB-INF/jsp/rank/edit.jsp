<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<spring:hasBindErrors name="rank">
    <%@ include file="../inc/showBindErrors.jsp" %>
</spring:hasBindErrors>

<form:form id="rankDeleteForm" method="delete" modelAttribute="rank" />

<form:form id="rankForm" modelAttribute="rank" method="post">
    <form:hidden id="hiddenId" path="id" />

    <fieldset class="main ui-corner-all">
        <legend><fmt:message key="rank.title" /></legend>

        <table style="width: 100%;">
            <tr>
                <td>
                    <select id="rankSelector" class="ui-widget-content ui-corner-all">
                        <option value="">&lt;<fmt:message key="value.createNew"/>&gt;</option>

                        <c:forEach items="${ranks}" var="r">
                            <c:choose>
                                <c:when test="${r.id == rank.id}">
                                    <option value="${r.id}" selected="selected">${r.name}</option>
                                </c:when> <c:otherwise>
                                    <option value="${r.id}">${r.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>

                <td>
                    <table class="withMargin">
                        <tr>
                            <th><form:label for="name" path="name" cssErrorClass="ui-state-error-text"><fmt:message key="rank.name" /></form:label></th>
                            <td><form:input id="rankName" path="name" cssClass="ui-widget-content ui-corner-all" cssErrorClass="ui-widget-content ui-corner-all ui-state-error"/></td>
                            <form:errors element="td" cssClass="ui-state-error-text" path="name" />
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

        <p style="text-align: right; padding-top: 1em; padding-right: 2px;">
            <a class="button ui-state-error-text ui-state-default ui-corner-all" id="delete_button" href="#" onclick="$('#rankDeleteForm').submit()" style="display: none;">
                <span class="ui-icon ui-icon-trash"></span><fmt:message key="button.deleteEntry"/>
            </a>

            &nbsp;

            <a class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button" href="#" onclick="$('#rankForm').submit()">
                <span class="ui-icon ui-icon-disk"></span><fmt:message key="button.save"/>
            </a>
            <input type="submit" style="display: none;" />
        </p>

<!-- onclick="return confirm('<fmt:message key='message.confirmDelete'/>');" -->
    </fieldset>

</form:form>

<script type="text/javascript">
<!--
    $('#rankSelector').change(function() {
    	var selector = $('#rankSelector');
        $('#hiddenId').val(selector.val());

        if (selector.val() != "") {
            // Editing old
        	$('#rankName').val($('#rankSelector :selected').text());
            $('#delete_button').css('display', '');

        } else {
            // Creating new
        	$('#rankName').val('');
        	$('#delete_button').css('display', 'none');
        }
    });

    $(function(){
        $('#save_button, #delete_button').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });

    // Process rank selector on load.
    $('#rankSelector').change();

    $('input:text:visible:first').focus();
    
</script>
<%--
    populateSelectList('<c:url value="/rank/list" />', 'rank', 'name', '${rank.id}', true, '<fmt:message key="value.notSet"/>');
 --%>

<%@ include file="../footer.jsp" %>