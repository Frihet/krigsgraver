<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<c:forEach items="${persons}" var="person">
    <p>
        <a href="${person.id}/edit">${person.westernDetails} (${person.cyrillicDetails})</a>
    </p>
</c:forEach>

<%@ include file="../footer.jsp" %>