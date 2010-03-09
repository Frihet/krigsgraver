<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<%@ page isErrorPage='true' %>

<div class="error ui-widget">
    <div style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all"> 
        <p><span style="float: left; margin-right: 0.3em;" class="ui-icon ui-icon-alert"></span>
        <strong><fmt:message key="error.title"/>:</strong> <c:out value="${standardError}" /></p>

        <p>
<%--
            <% out.print(exception.getLocalizedMessage()); %>
 --%>
        </p>
        
    </div>
</div>
<%--
<c:remove var="standardError" scope="session" />
 --%>


<div class="standardError">
    <div class="heading">
        <fmt:message key='global.error'/>
    </div>
    <div class="content">
        <p>
            <fmt:message key="errors.illegalDatabaseOperation" />
<%--
            <% out.print(exception.getLocalizedMessage()); %>
 --%>
        </p>
    </div>
</div>

<div style="float: right">
    <a href="#" onclick="javascript:history.go(-1)"><fmt:message key="errors.click_to_go_back"/></a>
</div>

<div class="clearRow"></div>
 
<%@ include file='../footer.jsp' %>
