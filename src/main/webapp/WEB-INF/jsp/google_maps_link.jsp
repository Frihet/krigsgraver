<%@ include file="includes.jsp" %>

<c:if test="${geolocational.latitude != Null && geolocational.longitude != Null}">
    <a href="http://maps.google.com/?q=${geolocational.latitude},${geolocational.longitude}">
        <img class="imgLink" src="<c:url value='/inc/img/ambox_globe.png'/>" title="<fmt:message key='maps.link.description'/>" />
    </a>
</c:if>
