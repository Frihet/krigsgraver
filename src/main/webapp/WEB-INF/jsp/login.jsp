<%@ include file="header.jsp"%>
<%@ include file="includes.jsp"%>

<%@page import="org.springframework.security.core.AuthenticationException"%>
<%@page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>

<style>
<!--
    #mainContent {
        max-width: 200em;
    }

    div.loginBox {
        width: 30em;
        margin: 5em auto 3em;
        padding: 1.5em;
        background-color: #DADAB5;
        /* background-color: #f5f5cb; */
        border: 1px solid black;
    }
-->
</style>

<div style="text-align: center;">
    <div style="text-align: left;" class="loginBox ui-widget ui-corner-all">
        <form id="loginform" class="loginform" action="<c:url value='/j_spring_security_check'/>" method="post">
            <div class="loginContent" style="font-weight: bold;"><fmt:message key='login.enter_details'/></div>
    
            <c:if test="${not empty param.login_error}">
                <div class="loginContent" style="color: red; padding: 1em;" title="Reason: <%= ((AuthenticationException) session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>.">
                    <fmt:message key="login.invalid" />

    <%-- Uncomment to get an error description (e.g "Reason: Bad credidentials").
            Reason: <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
     --%>
                    
                </div>
            </c:if>
    
            <div style="text-align: center; padding: 1.5em 0;">
                <input type="hidden" name="_spring_security_remember_me" value="true" />

                <table width="50%" style="text-align: right; margin: auto;">
                    <tr>
                        <td class="loginContent"><fmt:message key='login.username'/>:</td>
                        <td style="width: 20%"><input id="username" class="loginText text ui-widget-content ui-corner-all" type='text' size="10" name='j_username' <c:if test="${not empty param.login_error}">value='<c:out value="${ACEGI_SECURITY_LAST_USERNAME}"/>'</c:if>/></td>
                    </tr>
                    <tr>
                        <td class="loginContent"><fmt:message key='login.password'/>:</td>
                        <td style="width: 20%"><input class="loginText text ui-widget-content ui-corner-all" type='password' size="10" name='j_password' /></td>
                    </tr>
                </table>
            </div>
    
            <div style="text-align: center">
                <button id="login" type="submit" class="ui-priority-primary ui-state-default ui-corner-all"><fmt:message key="login.login"/></button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
<!--
    $(function() {
        $('#username').focus();

        $('#login').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });

//-->
</script>


<%@ include file="footer.jsp"%>