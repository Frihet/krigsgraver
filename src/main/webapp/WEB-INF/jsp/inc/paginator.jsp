<%@ include file="../includes.jsp"%>

<c:if test="${paginator.hasNext || paginator.hasPrevious}">
    <div class="paginator">&lt;
        <c:choose>
            <c:when test="${paginator.hasPrevious}">
                <a href="?page=${paginator.previousPage}">
                    <fmt:message key='global.previous_page'/>
                </a>
            </c:when>
            <c:otherwise>
               <fmt:message key='global.previous_page'/>
            </c:otherwise>
        </c:choose>
    
        <c:out value="|" />
    
        <c:choose>
            <c:when test="${paginator.hasNext}">
                <a href="?page=${paginator.nextPage}">
                    <fmt:message key='global.next_page'/>
                </a>
            </c:when>
            <c:otherwise>
               <fmt:message key='global.next_page'/>
            </c:otherwise>
        </c:choose>
        &gt;
    </div>
</c:if>