<%@ page isErrorPage='true' %>

<%@ include file="../includes.jsp"%>
<%@ include file="../inc/simpleHeader.jsp"%>

<div class="standardError">
    <div class="heading">
        <fmt:message key='global.error'/>
    </div>
    <div class="content">
        <p>
            <fmt:message key="errors.access_denied"/>
        </p>
    </div>
</div>

<div style="float: right">
    <a class="jsaction" onclick="javascript:history.go(-1)"><fmt:message key="errors.click_to_go_back"/></a>
</div>

<div class="clearRow"></div>

<%@ include file='../footer.jsp' %>
