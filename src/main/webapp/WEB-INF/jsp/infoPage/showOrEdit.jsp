<%@ include file="../includes.jsp"%>
<%@ include file="../header.jsp"%>

<sec:authorize ifNotGranted="ROLE_ADMIN">
    ${infoPage.html}
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <script language="javascript" type="text/javascript" src="<c:url value='/inc/tiny_mce/tiny_mce.js'/>"></script>
    <script language="javascript" type="text/javascript">
        tinyMCE.init({
                mode : "exact",
                elements : "pageBody",
                gecko_spellcheck : true,
                theme : "advanced",
                theme_advanced_toolbar_location : "top",
                theme_advanced_buttons1 : "cut,copy,paste,separator,fontselect,fontsizeselect,separator,bold,italic,underline,separator,justifyleft,justifycenter,justifyright,justifyfull,separator,bullist,numlist,separator,outdent,indent,separator,undo,redo,separator,hr,link,unlink,cleanup,separator,help",
                theme_advanced_buttons2 : "",
                theme_advanced_buttons3 : ""
        });
    </script>

    <form action="" method="post">
        <textarea id="pageBody" name="pageBody" rows="30" cols="120">${infoPage.html}</textarea>
        <button type="submit" class="button ui-priority-primary ui-state-default ui-corner-all" id="save_button"><fmt:message key="button.save"/></button>
    </form>

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
</sec:authorize>

<%@ include file="../footer.jsp" %>