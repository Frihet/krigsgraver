<%@ page isErrorPage='true' %>

<%@ include file="../includes.jsp"%>
<%@ include file="../header.jsp"%>

<%--
 --%>

<div class="standardError">
    <div class="heading">
        <fmt:message key='global.error'/>
    </div>
    <div class="content">
        <p>
            <fmt:message key="errors.dataAccessFailure" />
        </p>
    </div>
</div>

<%--
<div style="float: right">
    <a href=""><fmt:message key="errors.click_to_go_back"/></a>
</div>
 --%>

<div class="clearRow"></div>
 
<%@ include file='../footer.jsp' %>
