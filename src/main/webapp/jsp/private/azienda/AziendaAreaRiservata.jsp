<%@page import="com.aziendaRecruiting.model.Azienda"%>
<%@page import="com.aziendaRecruiting.model.Utente"%>
<%@page import="com.aziendaRecruiting.utils.strings.StringUtility"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Utente loggedUser = (Utente) session.getAttribute(CostantiServlet.LOGGED_USER);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Recruiting | Area riservata</title>
	<jsp:include page="/jsp/forLinking/link-head.jsp"></jsp:include>
</head>
<body id="robbybestprof">
	<jsp:include page="/jsp/header.jsp"></jsp:include>	
	
	<div id="sfondo-inizio-azienda">
		
		<div class="benvenuto-azienda">
			<%
			if(loggedUser != null && loggedUser instanceof Azienda){		
				Azienda azienda = (Azienda) loggedUser;
			%>
			<div class="benvenuto">
				<div id="benvenuto">
					<p id="area">
						Benvenuto, <span id="riservata"><%= azienda.getRagioneSociale() + "!"%></span>
					</p>
				</div>
			</div>
			<%
			}
			%>
			
			<div id="apri-azienda" class="crea-annuncio">
				<label>Crea un annuncio</label>
				<button id="bottone-apri" class="material-symbols-outlined bottone-crea-annuncio">add</button>
			</div>
			
		</div>
		
		<jsp:include page="SearchFormAzienda.jsp"></jsp:include>
		
		<div id="spaziatore-ricerca-azienda"></div>
		
	</div>
	
<%
if(request.getAttribute(CostantiServlet.SEARCH_HAPPENED) != null) {
%>
	<jsp:include page="/jsp/SearchResults.jsp"></jsp:include>
<%
}
%>	
	
	<jsp:include page="CreaAnnuncioForm.jsp"></jsp:include>
	

	<jsp:include page="/jsp/ActionNotification.jsp"></jsp:include>
	
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/forLinking/link-script.jsp"></jsp:include>
	
	<!-- 
	
                ;'-. 
    `;-._        )  '---.._
      >  `-.__.-'          `'.__
     /_.-'-._         _,   ^ ---)
     `       `'------/_.'----```
                     `
	 -->
</body>
</html>