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
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <div class="menuTitle">
        <fmt:message key="menu.admin"/>
    </div>
    
    <a href='<c:url value="/admin/users" />'><fmt:message key="menu.admin.users"/></a>
</sec:authorize>