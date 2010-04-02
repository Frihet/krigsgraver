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

        <a id="editItem" tabindex="0" href="javascript:void(0)" class="button ui-state-default ui-priority-primary ui-corner-all">
            <fmt:message key="link.edit" />
        </a>

        <c:if test="${!empty mergeUrl}">
            <a id="mergeItem" tabindex="0" href="javascript:void(0)" class="button ui-state-default ui-corner-all">
                <fmt:message key="button.merge"/>
            </a>
        </c:if>

        <a id="deleteItem" tabindex="0" href="javascript:void(0)" class="button ui-state-default ui-corner-all">
            <fmt:message key="link.delete"/>
        </a>

<%--
        &nbsp;-&nbsp;
 --%>

        <a href="${createUrl}" tabindex="0" class="button ui-state-default ui-corner-all" style="float: right;">
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

<c:if test="${!empty mergeUrl}">
    <div id="mergeDialog" style="display: none;" class="dialog">
        <form id="mergeForm" action="${mergeUrl}">
            <div>
                <fmt:message key="merge.merge"/>
                <fmt:message key="merge.with"/>
                <input type="hidden" id="fromId" name="fromId" />

                <select id="toIdSelect" name="toId" class="medium ui-widget-content ui-corner-all">
                    <c:forEach items="${items}" var="item">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>

                <p><fmt:message key="merge.info"/> &quot;<span id="fromIdSpan"></span>&quot;.</p>
            </div>
        </form>
    </div>
    
    <script type="text/javascript">
    <!--
        $("#mergeDialog").dialog({
            title: '<fmt:message key="merge.title"/>',
            modal: true,
            resizable: true,
            closeOnEscape: true,
            width: 500,
    		buttons: {
                '<fmt:message key="button.merge"/>': function() {
                	$('#mergeForm').submit();
    			},
    			'<fmt:message key="button.cancel"/>': function() {
    				$(this).dialog('close');
    			}
    		},
            open: function() {
    			$('#toIdSelect').focus();
    		},
            autoOpen: false
        });
    //-->
    </script> 
</c:if>

<script type="text/javascript">
<!--
    $('#itemSelector').keyup(function(e) {
        if (e.keyCode == 13) {
            // Enter key was pressed
        	$('#editItem').click();
        }
    });

    $('a.button').focus(function() {
    	   $(this).addClass('ui-state-focus');
    }).blur(function() {
           $(this).removeClass('ui-state-focus');
    })

    $('#itemSelector').change(function() {
    	var selected = $('#itemSelector option:selected');
    	var selectedValue = selected.val();
        var selectedText = selected.text();
        
        if (selectedValue == "null") {
        	$('#editItem, #deleteItem, #mergeItem')
                .addClass('ui-state-disabled')
                .unbind('click');

        } else {
            $('#deleteItem, #editItem, #mergeItem').removeClass('ui-state-disabled');

            $('#deleteItem').click(function() {
                var form = $('#selectorForm');

                if (confirm('<fmt:message key="message.confirmDelete"/>')) {
                    form.attr('action', '${deleteUrl}');
                    form.submit();
                } else {
                    return false;
                } 
            });

            $('#editItem').click(function() {
                var form = $('#selectorForm');
                form.attr('action', '${editUrl}');
                form.submit();
            });

            $('#mergeItem').click(function() {
            	$('#fromId').val(selectedValue);
            	$('#fromIdSpan').html(selectedText);
            	$('#mergeDialog').dialog('open');
            });
        }
    });

    $('#itemSelector').keypress(function() {
        $('#itemSelector').change();
    });

    $(function() {
    	$('#itemSelector').change();
    });
//-->
</script>
