<%@ include file="includes.jsp"%>

<div class="menuArea">
<div class="menuTitle">
    <fmt:message key="menu.search"/>
</div>

<div>
    <a tabindex="-1" href='<c:url value='/home'/>'><fmt:message key="menu.home"/></a><br />
</div>

<div>
    <a tabindex="-1" href='<c:url value='/search'/>'><fmt:message key="menu.search.simple"/></a><br />
</div>

<div>
    <a tabindex="-1" href='<c:url value='/queryBuilder'/>'><fmt:message key="menu.search.advanced"/></a>
</div>

<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_EDITOR">
    <div class="menuTitle">
        <fmt:message key="menu.people"/>
    </div>

    <a tabindex="-1" href='<c:url value="/person/create" />'><fmt:message key="menu.people.add"/></a>

    <div class="menuTitle">
        <fmt:message key="menu.elements"/>
    </div>

    <a tabindex="-1" href='<c:url value="/category/create" />'><fmt:message key="menu.elements.categories"/></a><br/>
    <a tabindex="-1" href='<c:url value="/nationality/create" />'><fmt:message key="menu.elements.nationalities"/></a><br/>
    <a tabindex="-1" href='<c:url value="/rank/create" />'><fmt:message key="menu.elements.ranks"/></a><br/>
    <a tabindex="-1" href='<c:url value="/stalag/create" />'><fmt:message key="menu.elements.stalags"/></a><br/>
    <a tabindex="-1" href='<c:url value="/camp/create" />'><fmt:message key="menu.elements.camps"/></a><br/>
    <a tabindex="-1" href='<c:url value="/cemetery/create" />'><fmt:message key="menu.elements.cemeteries"/></a><br/>
    <a tabindex="-1" href='<c:url value="/causeOfDeath/create" />'><fmt:message key="menu.elements.causesOfDeath"/></a><br/>
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <div class="menuTitle">
        <fmt:message key="menu.admin"/>
    </div>

    <a tabindex="-1" href='<c:url value="/admin/user" />'><fmt:message key="menu.admin.users"/></a><br/>
    <a tabindex="-1" href='<c:url value="/admin/various" />'><fmt:message key="menu.admin.various"/></a>
</sec:authorize>
</div>