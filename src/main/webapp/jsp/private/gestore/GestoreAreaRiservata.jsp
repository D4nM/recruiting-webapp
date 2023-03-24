<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@page import="com.aziendaRecruiting.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Utente loggedUser = (Utente) session.getAttribute(CostantiServlet.LOGGED_USER);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RecruitIng | Area riservata</title>
	<jsp:include page="/jsp/forLinking/link-head.jsp"></jsp:include>
</head>
<body id="robbydabest">
	<jsp:include page="/jsp/header.jsp"></jsp:include>

	<div id="sfondo-inizio-gestore">
	
		<div class="benvenuto-gestore">
			<%
			if(loggedUser != null && loggedUser instanceof Gestore){		
				Gestore gestore = (Gestore) loggedUser;
			%>
			<div class="benvenuto">
				<div id="benvenuto">
					<p id="area-gestore">
						Benvenuto, <span id="riservata-gestore"><%= gestore.getNome() + "!"%></span>
					</p>
				</div>
			</div>
			<%
			}
			%>
		</div>
		
		<jsp:include page="SearchFormGestore.jsp"></jsp:include>
		
	</div>
	
<%
if(request.getAttribute(CostantiServlet.SEARCH_HAPPENED) != null) {
%>
	<jsp:include page="/jsp/SearchResults.jsp"></jsp:include>
<%
}
%>	
	<jsp:include page="/jsp/ActionNotification.jsp"></jsp:include>
	
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/forLinking/link-script.jsp"></jsp:include>
	
</body>
</html>