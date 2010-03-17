<%--
<%@ page isErrorPage='true' %>
 --%>

<%@ include file="../includes.jsp"%>
<%@ include file="../header.jsp"%>

<h1>
    <fmt:message key='error.title'/>
</h1>

<div class="content">
    <p>
        <fmt:message key="errors.unableToProcess"/>
    </p>
</div>

<%@ include file='../footer.jsp' %>
