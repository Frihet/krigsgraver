<%@ include file="../includes.jsp"%>

<div class="error ui-widget">
    <div style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all"> 
        <p>
            <span style="float: left; margin-right: 0.3em;" class="ui-icon ui-icon-alert"></span>
            <strong><fmt:message key="errors.thereWereErrors"/>:</strong>
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
            <fmt:message key="errors.appropriateCorrections"/>
        </p>
    </div>
</div>


