<%@ include file="includes.jsp" %>
<fmt:message var="pageTitle" key="menu.search.simple" />
<c:set var="helpPage" value="search" />
<%@ include file="header.jsp" %>
<%@ page session="false" %>

<c:set var="simple" value="${true}" />

<style type="text/css">
<!--
    #mainContent { max-width: 200em; }
-->
</style>
<p>
<table>
<tr>
<td style='text-align:left; width:10%;'>
<a href="home?locale=no_nb">Norsk</a>

</td>
<td style='width:50%;'>
<a href="home?locale=no_nb">
  <h1 style='border-bottom:none;'>Utenlandske krigsgraver i Norge</h1>
</a>
</td>
<td rowspan='5'>
<img src='/krigsgraver/inc/img/welcome.jpg'>
<p>
Krigsgrav i Falstadskogen. Fotograf: Oskar A. Johansen (1945).
<p>
War grave in The Falstad forest. Photo: Oskar A. Johansen (1945).
<p>
<fmt:message key="language.russianPhotoCaption" />

</td>
</tr>

<tr>
<td style='text-align:left;'>
<a href="home?locale=en_gb">
  English
</a>

</td>
<td>
<a href="home?locale=en_gb">
  <h1 style='border-bottom:none;'>Foreign war-graves in Norway</h1>
</a>
</td>
</tr>

<tr>
<td style='text-align:left;'>
<a href="home?locale=ru">
  <fmt:message key="language.russian" />
</a>
</td>
<td>
<a href="home?locale=ru">
  <h1 style='border-bottom:none;'><fmt:message key="language.russianHeader" /></h1>
</a>
</td>
</tr>

<tr><td><p></td><td></td></tr>

<tr><td><p></td><td></td></tr>

</table>


<script type="text/javascript">
<!--
    $(function() {
        $('#searchField').focus();

        $('#search').hover(
            function() { $(this).addClass('ui-state-hover'); }, 
            function() { $(this).removeClass('ui-state-hover'); }
        );
    });

var sc_project=6742443;
var sc_invisible=1;
var sc_security="2967312c";

//-->
</script>

<script type="text/javascript" src="http://www.statcounter.com/counter/counter.js"></script>
<noscript>
<div class="statcounter"><a title="vBulletin analytics" href="http://statcounter.com/vbulletin/" target="_blank"><img class="statcounter" src="http://c.statcounter.com/6742443/0/2967312c/1/" alt="vBulletin analytics" ></a></div>
</noscript>



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
