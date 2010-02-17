<%-- <%@ include file="../header.jsp"%> --%>
<%@ include file="../includes.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <style>img{ height: 100px; float: left; }</style>
<!--  <script src="http://code.jquery.com/jquery-latest.js"></script>-->
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <div id="images">

</div>


<script>
    $.getJSON('http://localhost:8080/krigsgraver/grave/1', function(data) {
          $('<div/>').html(data.graveField);
    });

/*
	$.getJSON(
					"http://api.flickr.com/services/feeds/photos_public.gne?tags=cat&tagmode=any&format=json&jsoncallback=?",
					function(data) {
						$.each(data.items, function(i, item) {
							$("<img/>").attr("src", item.media.m).appendTo(
									"#images");
							if (i == 3)
								return false;
						});
					});
*/
</script>

<form:form modelAttribute="grave" method="post">
    <form:hidden path="id" />

    <fieldset>
        <legend><fmt:message key="grave.title" /></legend>

        <table>
            <tr>
                <th><form:label for="graveField" path="graveField" cssErrorClass="error"><fmt:message key="grave.graveField" /></form:label></th>
                <td><form:input path="graveField" /></td>
                <form:errors element="td" cssClass="error" path="graveField" />
            </tr>

            <tr>
                <th><form:label for="graveRow" path="graveRow" cssErrorClass="error"><fmt:message key="grave.graveRow" /></form:label></th>
                <td><form:input path="graveRow" /></td>
                <form:errors element="td" cssClass="error" path="graveRow" />
            </tr>

            <tr>
                <th><form:label for="graveNumber" path="graveNumber" cssErrorClass="error"><fmt:message key="grave.graveNumber" /></form:label></th>
                <td><form:input path="graveNumber" /></td>
                <form:errors element="td" cssClass="error" path="graveNumber" />
            </tr>
        </table>

        <p>
            <button type="submit"><fmt:message key="button.save" /></button>
        </p>
    </fieldset>
</form:form>

<%@ include file="../footer.jsp" %>