<%@ include file="../includes.jsp"%>
<%@ include file="../header.jsp"%>

<div class="subContent">
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
                content_css : "<c:url value='/inc/styles/editor.css?version=1'/>",
                width: "100%",
                theme_advanced_blockformats : "p,h1,h2,h3,h4",
                theme : "advanced",
                theme_advanced_toolbar_location : "top",
                theme_advanced_buttons1 : "cut,copy,paste,separator,formatselect,separator,bold,italic,underline,separator,justifyleft,justifycenter,justifyright,separator,bullist,numlist,separator,outdent,indent,separator,undo,redo,separator,hr,link,unlink,cleanup,separator,code",
                theme_advanced_buttons2 : "",
                theme_advanced_buttons3 : ""
        });
    </script>

    <h1><fmt:message key="infoPage.editingPage" /></h1>

    <form action="" method="post">
        <textarea id="pageBody" name="pageBody" rows="30" cols="120">${infoPage.html}</textarea>
        <br />
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

</div>

<%@ include file="../footer.jsp" %>