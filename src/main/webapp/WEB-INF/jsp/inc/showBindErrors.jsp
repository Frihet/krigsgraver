<%@ include file="../includes.jsp"%>

<div class="standardError">
    <div class="heading">
        <fmt:message key='global.error'/>
    </div>
    
    <div class="content">
        <p>
            <c:choose>
                <c:when test="${fn:length(errors.allErrors) == 1}">
                    There was 1 error:
                </c:when>
                <c:otherwise>
                    There were ${fn:length(errors.allErrors)} errors:
                </c:otherwise>
            </c:choose>
        </p>

        <p style="font-style: italic; text-indent: 2em;">
            <c:forEach items="${errors.allErrors}" var="error">
                <ul>
                    <li>
                        <span class="error">
                            <spring:message message="${error}"/>
                        </span>
                    </li>
                </ul>
            </c:forEach>
        </p>
        
        <p>
            <fmt:message key="errors.appropriate_corrections"/>
        </p>
    </div>
</div>


