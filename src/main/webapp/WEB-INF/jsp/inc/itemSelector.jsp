<%@ include file="../includes.jsp"%>

<form id="selectorForm">
    <select id="itemSelector" name="id" class="ui-widget-content ui-corner-all">
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
</form>

<h2>
    <c:choose>
        <c:when test="${stalag.id == Null}">
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
    $('#editItem').click(function() {
        var form = $('#selectorForm');
        form.attr('action', '${editUrl}');
        form.submit();
    });

    $('#deleteItem').click(function() {
        var form = $('#selectorForm');

        if (confirm('<fmt:message key='message.confirmDelete'/>')) {
            form.attr('action', '${deleteUrl}');
            form.submit();
        } else {
            return false;
        } 
    });
//-->
</script>
