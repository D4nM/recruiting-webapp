<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@page import="com.aziendaRecruiting.model.Annuncio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RecruitIng | Home</title>
	<jsp:include page="/jsp/forLinking/link-head.jsp"></jsp:include>
</head>
<body id="home-body">
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	
	<div id="immaginejob">
		<img alt="" src="<%= request.getContextPath() + "/img/immaginerecruiting19.png" %>" width="60%" height="30%">
	</div>
	
	<jsp:include page="/jsp/SearchFormPubblica.jsp"></jsp:include>
	
	
<%
if(request.getAttribute(CostantiServlet.SEARCH_HAPPENED) != null) {
%>
	<jsp:include page="/jsp/SearchResults.jsp"></jsp:include>
<%
}
%>	
	<%
	String searchHappened = (String) request.getAttribute(CostantiServlet.SEARCH_HAPPENED);
	if(searchHappened == null){
	%>	
	<div id="first-page">
	
		<div class="paragrafo1 hidden-container">
		
			<div id="paragrafo1">
				<p>La ricerca del personale e la successiva fase di onboarding
					giocano un ruolo cruciale all’interno di ogni organizzazione
					indipendentemente dalla grandezza. Scegliere il giusto candidato
					significa beneficiare di una vera risorsa negli anni avvenire,
					sbagliare la scelta, diversamente, causa danni economici e perdite
					di tempo che certamente vuoi evitare. I software di reclutamento
					sono strumenti sempre più utilizzati. Li utilizzano in maniera
					trasversale tutte le tipologie di imprese, dalle piccole alle medio
					grandi.
				</p>
			</div>
			
			<div id="img-par">
				<img alt="" src="<%= request.getContextPath() + "/img/immaginerecruiting18.png" %>">
			</div>
		</div>
	
		<div class="paragrafo2 hidden-container">
		
			<div id="img-par2">
				<img alt="" src="<%= request.getContextPath() + "/img/immaginerecruiting17.png" %>">
			</div>
			
			<div id="paragrafo2">
				<p>Un recruiting software ti aiuta a seguire l’intero processo
					di selezione del personale. Soprattutto quando hai necessità di
					coprire diverse posizioni lavorative il rischio di perdersi alcune
					candidature è reale. Quello che proprio vuoi evitare è non dare
					tempestiva risposta ai migliori profili. Questo è un vantaggio per
					te ma anche per il futuro lavoratore, il quale, sin da subito ha la
					possibilità di accedere alle informazioni e agli strumenti di cui
					ha bisogno per inserirsi in azienda. Migliore è l’esperienza del
					candidato più sono le possibilità che accetti la tua offerta di
					lavoro. La comunicazione tra te e gli aspiranti lavoratori diventa
					così immediata e questa funzionalità riduce il rischio che il
					talento venga assunto da una più rapida ed efficiente azienda a te
					concorrente.
				</p>
				
			</div>
		</div>
			
		<div class="paragrafo3 hidden-container">
		
			<div id="paragrafo3">
				<p>Le fasi del processo di selezione, o recruiting, sono tutte ugualmente importanti per la buona riuscita dell’operazione. <br>
				-Analisi (job analysis) e pianificazione.<br>
				-Stesura della job description (annuncio di lavoro).<br>
				-Ricerca dei candidati.<br>
				-Valutazione dei candidati e colloqui.<br>
				-Selezione e inserimento.<br>
				Una volta scelto il candidato o i candidati più adatti, ci possono essere prove e colloqui ulteriori per approfondire la conoscenza delle persone individuate.
				
			</div>
			
			<div id="img-par3">
				<img alt="" src="<%= request.getContextPath() + "/img/immaginerecruiting16.png" %>">
			</div>
			
		</div>
	
	
		<div id="citta">
			<div>
				<h3>Le città in cui lavoriamo </h3>
			</div>
			<div id="immagini" class="container">
				<div class="card">
					<img src="<%= request.getContextPath() + "/img/fotocitta.jpg" %>" alt="...">
				</div>
				<div class="card">
					<img src="<%= request.getContextPath() + "/img/fotocitta1.jpg" %>" alt="...">
				</div>
				<div class="card">
					<img src="<%= request.getContextPath() + "/img/fotocitta2.jpg" %>" alt="...">
				</div>
				<div class="card">
					<img src="<%= request.getContextPath() + "/img/fotocitta3.jpg" %>" alt="...">
				</div>
			</div>
		</div>
	
	</div>
	<%
	}
	%>
	
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/forLinking/link-script.jsp"></jsp:include>
	
	<!-- 
	                             |\    /|
                              ___| \,,/_/
                           ---__/ \/    \
                          __--/     (D)  \
                          _ -/    (_      \
                         // /       \_ /  -\
   __-------_____--___--/           / \_ O o)
  /                                 /   \__/
 /                                 /
||          )                   \_/\
||         /              _      /  |
| |      /--______      ___\    /\  :
| /   __-  - _/   ------    |  |   \ \
 |   -  -   /                | |     \ )
 |  |   -  |                 | )     | |
  | |    | |                 | |    | |
  | |    < |                 | |   |_/
  < |    /__\                <  \
  /__\                       /___\
	
	 -->
</body>
</html>