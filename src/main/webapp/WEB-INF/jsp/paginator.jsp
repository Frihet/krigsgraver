<%@ include file="includes.jsp"%>
<c:if test="${paginator.hasNext || paginator.hasPrevious}">
    <div class="paginator">
        <c:choose>
            <c:when test="${paginator.hasPrevious}">
                <a href="?q=<c:out value="${q}"/>&page=${paginator.previousPage}">
                    <fmt:message key='search.previous'/>
                </a>
            </c:when>
            <c:otherwise>
               <fmt:message key='search.previous'/>
            </c:otherwise>
        </c:choose>

        <c:out value="|" />

        <c:choose>
            <c:when test="${paginator.hasNext}">
                <a href="?q=<c:out value="${q}"/>&page=${paginator.nextPage}">
                    <fmt:message key='search.next'/>
                </a>
            </c:when>
            <c:otherwise>
               <fmt:message key='search.next'/>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>
