<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="includes.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.w3.org/MarkUp/SCHEMA/xhtml11.xsd" xml:lang="en">
<head>
    <title><fmt:message key="global.title"/><c:if test="${!empty pageTitle}"> - ${pageTitle}</c:if></title>
    <link rel="stylesheet" href='<c:url value="/inc/styles/main.css?version=1"/>' type="text/css" media="screen,projection" />
<!--    <link rel="stylesheet" href="<c:url value="/inc/styles/print.css?version=1"/>" type="text/css" media="print" />-->
<!--    <link rel="stylesheet" href="<c:url value="/inc/styles/menus.css?version=1"/>" type="text/css" media="screen,projection" />-->

    <link type="text/css" href="<c:url value='/inc/jquery/css/custom-theme/jquery-ui-1.7.2.custom.css'/>" rel="stylesheet" />    
    <script type="text/javascript" src="<c:url value='/inc/jquery/js/jquery-1.4.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/inc/jquery/js/jquery-ui-1.7.2.custom.min.js'/>"></script>
<!--    <script type="text/javascript" src="<c:url value='http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/jquery-ui.min.js'/>"></script>-->
    <script type="text/javascript" src="<c:url value='/inc/scripts/krigsgraver.js'/>"></script>

    <link rel="icon" href="<c:url value="/inc/img/favicon.ico" />" type="image/x-icon" />
    <link rel="shortcut icon" href="<c:url value="/inc/img/favicon.ico" />" type="image/x-icon" />
</head>

<body>
<table id="wholePage">
<tr id="topHeader">
    <td id="headerLogo" style='background-image: url(<c:url value="/inc/img/falstadlogo_bgblack.jpg" />);'></td>
    <td id="rightHeader">
        <div id="loginData">
            <sec:authorize ifNotGranted="ROLE_ADMIN,ROLE_EDITOR,ROLE_PARTNER">
                <a id="loginLink" tabindex="-1" href='<c:url value="/login" />'><fmt:message key="link.login"/></a>
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR,ROLE_PARTNER">
                    <fmt:message key="login.loggedInAs">
                        <fmt:param><sec:authentication property="principal.name" /></fmt:param>
                    </fmt:message>
                    <br />
                    <a id="loginLink" tabindex="-1" href='<c:url value="/user/editProfile" />'><fmt:message key="login.editProfile"/></a> |
                    <a id="loginLink" tabindex="-1" href='<c:url value="/j_spring_security_logout" />'><fmt:message key="link.logout"/></a>
            </sec:authorize>
        </div>
    </td>
</tr>
<tr>
    <td id="menuArea">
        <%@ include file="menu.jsp" %>
    </td>

    <td id="mainContentWrapper">
    <div id="mainContent">

    <c:if test="${infoPageName != Null}">
        <c:if test="${infoPage != Null}">
            <a style="float: right;" href="<c:url value='/info/${infoPageName}'/>">
                <img class="imgLink" src="<c:url value='/inc/img/help_smaller.png'/>" title="<fmt:message key='infoPage.linkTitle'/>" />
            </a>
        </c:if>

        <sec:authorize ifAnyGranted="ROLE_ADMIN">
            <c:if test="${infoPage == Null}">
                <a style="float: right;" href="<c:url value='/info/${infoPageName}'/>">
                    <img class="imgLink" src="<c:url value='/inc/img/help_smaller_disabled.png'/>" title="<fmt:message key='infoPage.linkTitleCreateNew'/>" />
                </a>
            </c:if>
        </sec:authorize>
    </c:if>

    <%-- If a standardInfo/standardError String is present in the model,
         draw it at the top of the page. --%>
    <c:if test="${standardInfo != Null}">
        <div class="info ui-widget">
            <div style="margin-top: 20px; padding: 0pt 0.7em;" class="ui-state-highlight ui-corner-all"> 
                <p><span style="float: left; margin-right: 0.3em;" class="ui-icon ui-icon-info"></span>
                <strong><fmt:message key="info.title"/>:</strong> <c:out value="${standardInfo}" /></p>
            </div>
        </div>
        <c:remove var="standardInfo" />
    </c:if>

    <c:if test="${standardError != Null}">
        <div class="error ui-widget">
            <div style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all"> 
                <p><span style="float: left; margin-right: 0.3em;" class="ui-icon ui-icon-alert"></span> 
                <strong><fmt:message key="error.title"/>:</strong> <c:out value="${standardError}" /></p>
            </div>
        </div>
        <c:remove var="standardError" />
    </c:if>

    <c:if test="${pageHeader != Null}">
        <h1>${pageHeader}</h1>
    </c:if>
