<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

F�lgende personer er forel�pig lagt til:

<c:forEach items="${persons}" var="person">
    <p>
        <a href="${person.id}/edit">${person.westernName}</a>
    </p>
</c:forEach>

<%@ include file="../footer.jsp" %>