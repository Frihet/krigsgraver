<%@ include file="../includes.jsp"%>

<form id="selectorForm" action="create">
    <div>
        <select id="itemSelector" name="id" class="long ui-widget-content ui-corner-all">
            <option value="null">&lt;<fmt:message key="value.chooseOne"/>&gt;</option>
    
            <c:forEach items="${items}" var="item">
                <c:choose>
                    <c:when test="${item.id == currentItem.id}">
                        <option value="${item.id}" selected="selected">${item.name}</option>
                    </c:when> <c:otherwise>
                        <option value="${item.id}">${item.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    
        <a id="editItem" class="button ui-state-default ui-corner-all">
            <fmt:message key="link.edit" />
        </a>
    
        <a id="deleteItem" class="button ui-state-default ui-corner-all">
            <fmt:message key="link.delete"/>
        </a>
        
        &nbsp;
    
        <a href="${createUrl}" class="button ui-state-default ui-priority-primary ui-corner-all">
            <fmt:message key="link.addNew" />
        </a>
    </div>
</form>

<h2>
    <c:choose>
        <c:when test="${currentItem.id == Null}">
            <fmt:message key="title.createNew"/>
        </c:when>
        <c:otherwise>
            <fmt:message key="title.editingElement">
                <fmt:param value="${currentItem.name}" />
            </fmt:message>
        </c:otherwise>
    </c:choose>
</h2>

<script type="text/javascript">
<!--
    $('#itemSelector').click(function() {
        if ($('#itemSelector option:selected').val() == "null") {
        	$('#editItem').addClass('ui-state-disabled');
        	$('#editItem').unbind('click'); 
            $('#deleteItem').addClass('ui-state-disabled');
            $('#deleteItem').unbind('click');

        } else {
            $('#deleteItem').removeClass('ui-state-disabled');
            $('#deleteItem').click(function() {
                var form = $('#selectorForm');

                if (confirm('<fmt:message key="message.confirmDelete"/>')) {
                    form.attr('action', '${deleteUrl}');
                    form.submit();
                } else {
                    return false;
                } 
            });

            $('#editItem').removeClass('ui-state-disabled');
            $('#editItem').click(function() {
                var form = $('#selectorForm');
                form.attr('action', '${editUrl}');
                form.submit();
            });
            
        }
    });

    $(function() {
    	$('#itemSelector').click();
    });
//-->
</script>
