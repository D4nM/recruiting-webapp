<%@page import="com.aziendaRecruiting.utils.strings.StringUtility"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String errMsg = (String) request.getAttribute(CostantiServlet.ERROR_MSG);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Errore imprevisto</title>
	<jsp:include page="/jsp/forLinking/link-head.jsp"></jsp:include>
</head>
<body id="corpo-errorPage">
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	
	<img class="error-img" src="<%= request.getContextPath() + "/img/immagine_errore4.png" %>">
	
	<div class="testo-errorPage">
		<h1 class="titolo-errorPage">Qualcosa è andato storto :\</h1>
		<%
		if(errMsg != null) {
		%>
		<h3><%= errMsg %></h3>
		<%
		}
		%>
	</div>		
	
	<jsp:include page="/jsp/GoBackButton.jsp"></jsp:include>
	
	
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/forLinking/link-script.jsp"></jsp:include>
	
	<!-- 
     (()__(()
     /       \ 
    ( /    \  \
     \ o o    /
     (_()_)__/ \             
    / _,==.____ \
   (   |--|      )
   /\_.|__|'-.__/\_
  / (        /     \ 
  \  \      (      /
   )  '._____)    /    
(((____.--(((____/	
	 -->
</body>
</html>