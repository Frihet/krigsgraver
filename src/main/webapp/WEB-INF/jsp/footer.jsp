<%@ include file="includes.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="text-align: center; padding: 2em 0 1em;">
<a href="?locale=en_gb">English</a> | <a href="?locale=no_nb">Norsk</a> | <a href="?locale=ru"><fmt:message key="language.russian" /></a>
</div>

<div style="text-align: center; padding: 2em 0 1em;">
<fmt:message key="footer.contact"/>
</div>

<script type="text/javascript">
<!--
$(function(){
    $('.button').hover(
        function() { $(this).addClass('ui-state-hover'); }, 
        function() { $(this).removeClass('ui-state-hover'); }
    );
});
//-->
</script>

<c:out escapeXml="false" value="</div></td></tr></table></body></html>" />
