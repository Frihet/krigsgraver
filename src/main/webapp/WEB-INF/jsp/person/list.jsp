<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

Følgende personer er foreløpig lagt til:

<c:forEach items="${persons}" var="person">
    <p>
        <a href="${person.id}/edit">${person.westernName}</a>
    </p>
</c:forEach>

<%@ include file="../footer.jsp" %>