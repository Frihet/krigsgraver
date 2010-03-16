/* Populate a select list asynchronously using JSON. */
function populateSelectList(source, type, field, listObject, currentId, allowNull, notSetString) {
    $.getJSON(source, function(data) {
        var html = '';
        if (allowNull) {
            html += '<option value="null">&lt;' + notSetString + '&gt;</option>';
        }

        $.each(data[listObject], function(i, item) {
            html += '<option value=' + item.id + '>' + eval('item.' + field) + '</option>';
        });

        var selector = $('#' + type + 'Selector');
        selector.html(html);
        selector.val(currentId);
    });
}