<%@ include file="includes.jsp"%>

<div class="menuTitle">
    <fmt:message key="menu.search"/>
</div>

<div>
    <a href='<c:url value='/'/>'><fmt:message key="menu.search.simple"/></a><br />
</div>

<div>
    <a href='<c:url value='/queryBuilder'/>'><fmt:message key="menu.search.advanced"/></a>
</div>

<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
    <div class="menuTitle">
        <fmt:message key="menu.people"/>
    </div>

    <a href='<c:url value="/person/create" />'><fmt:message key="menu.people.add"/></a>

    <div class="menuTitle">
        <fmt:message key="menu.elements"/>
    </div>

    <a href='<c:url value="/nationality/create" />'><fmt:message key="menu.elements.nationalities"/></a><br/>
    <a href='<c:url value="/rank/create" />'><fmt:message key="menu.elements.ranks"/></a><br/>
    <a href='<c:url value="/stalag/create" />'><fmt:message key="menu.elements.stalags"/></a><br/>
    <a href='<c:url value="/camp/create" />'><fmt:message key="menu.elements.camps"/></a><br/>
    <a href='<c:url value="/cemetery/create" />'><fmt:message key="menu.elements.cemeteries"/></a><br/>
    <a href='<c:url value="/causeOfDeath/create" />'><fmt:message key="menu.elements.causesOfDeath"/></a><br/>
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <div class="menuTitle">
        <fmt:message key="menu.admin"/>
    </div>
    
    <a href='<c:url value="/admin/users" />'><fmt:message key="menu.admin.users"/></a> (kommer snart)
</sec:authorize>