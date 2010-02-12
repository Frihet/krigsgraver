<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<form>
    <p>
        Search:
        <input type="text" name="q" />
        <button type="submit">Go!</button>
    </p>
</form>

<p>
    Results:
</p>

<c:forEach items="${persons}" var="person">
    <p>
        <a href="${person.id}/edit">${person.westernDetails} (${person.cyrillicDetails})</a>
    </p>
</c:forEach>

<%@ include file="../footer.jsp" %>