<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<c:forEach items="${persons}" var="person">
    <p>
        <a href="${person.id}/view">${person.fullName}</a>
    </p>
</c:forEach>

<%@ include file="../footer.jsp" %>