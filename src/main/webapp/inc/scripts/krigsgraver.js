/* Populate a select list asynchronously using JSON. */
function populateSelectList(source, type, field, currentId, allowNull, notSetString) {
    $.getJSON(source, function(data) {
        var html = '';
        if (allowNull) {
            html += '<option value="null">&lt;' + notSetString + '&gt;</option>';
        }
        
        var len = data.length;

        $.each(data, function(i, item) {
            html += '<option value=' + item.id + '>' + eval('item.' + field) + '</option>';
        });
        $('#' + type + 'Selector').html(html);
        $('#' + type + 'Selector').val(currentId);
    });
}