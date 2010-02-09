<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<div class="container">
    <h1>
        TEST
    </h1>

    <p>
        <fmt:message key="person.westernName"/>: ${person.westernName}
    </p>
    <p>
        <fmt:message key="person.cyrillicName"/>: ${person.cyrillicName}
    </p>
    <p>
        <fmt:message key="person.nationality"/>: <fmt:message key="person.nationality"/>
    </p>
</div>

<%@ include file="../footer.jsp" %>