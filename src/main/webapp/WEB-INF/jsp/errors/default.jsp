<%@ page isErrorPage='true' %>

<%@ include file="../includes.jsp"%>
<%@ include file="../header.jsp"%>

<h1>
    <fmt:message key='error.title'/>
</h1>
<div class="content">
    <p>
        <fmt:message key="errors.sorry_message"/>
    </p>
    
    <p style="font-style: italic; text-indent: 2em;">
        <% out.print(exception.getLocalizedMessage()); %>
    </p>
    
    <p>
        <fmt:message key="errors.appreciate_report"/>
    </p>
</div>

<%@ include file='../footer.jsp' %>
