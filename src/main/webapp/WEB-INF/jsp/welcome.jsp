<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<%-- Redirect the user to the correct welcome page. --%>

Test... Welcome!

<%--

<%@page import="no.freecode.gcl.model.User"%>
<%@page import="org.springframework.security.Authentication"%>
<%@page import="org.springframework.security.context.SecurityContextHolder"%>

<%
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    
    switch (user.getRole()) {
    case ROLE_SENDER:
        // response.sendRedirect("transports/edit.do?siteLanguage=en");
        response.sendRedirect("transports/edit.do");
        break;

    case ROLE_RECEIVER:
        // response.sendRedirect("deliveries/showIncoming.do?siteLanguage=en");
        response.sendRedirect("deliveries/showIncoming.do");
        break;

    case ROLE_ADMIN:
        // response.sendRedirect("reports/show.do?siteLanguage=en");
        response.sendRedirect("reports/show.do");
        break;
    }
    
%>

--%>