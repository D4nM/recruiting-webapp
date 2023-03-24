<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.utils.strings.StringUtility"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@page import="com.aziendaRecruiting.model.Annuncio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
private final Integer TEXT_LENGTH = 600; // per impostare la lunghezza del testo dell'annuncio nella preview
%>


<div id="risultati-ricerca">
<%

	List<Annuncio> risultatiRicerca = (List<Annuncio>)request.getAttribute(CostantiServlet.RESULT_ANNUNCI);
	
	if(risultatiRicerca != null && risultatiRicerca.size() > 0){
				
		for(Annuncio annuncio: risultatiRicerca) {
			
			String nominativoGestore = "";
			if(annuncio.getGestore() != null) {
				Gestore gestore = annuncio.getGestore();
				if(gestore.getNome() != null) {
					nominativoGestore += gestore.getNome() + " ";
				}
				if(gestore.getCognome() != null) {
					nominativoGestore += gestore.getCognome();
				}
			}
%>

	<div class="container-anteprima hidden-container">
	
		<div class="colonna-sinistra col-lg-8">
			<h1 class="titolo-anteprima"><%= annuncio.getTitolo() %></h1>
			<p class="testo-anteprima"><%= StringUtility.printShortVersion(annuncio.getTesto(), TEXT_LENGTH) %></p>
		</div>
		
		<div class="colonna-destra col-lg">
			<p class="postid-anteprima">
				<img alt="" src="<%=request.getContextPath()+ "/img/icona_posti.png" %>" width="30px" height="30px">
			</p>
			
			<p class="postid-anteprima"><%= annuncio.getNumPostiDisponibili() %></p>
			
			<p class="datap-anteprima">Pubblicato il:</p>
			<p class="datap-anteprima-inner"><%= annuncio.getFormattedDataPubblicazione() %></p>
			
			<p class="figprof-anteprima">Figura ricercata:</p>
			<p class="figprof-anteprima-inner"><%= annuncio.getFigProfessionale() %></p>
			
			<p class="citta-anteprima">Città:</p>
			<p class="citta-anteprima-inner"><%= annuncio.getCitta() %></p>
			
			<br>
			
			<p class="gestore-anteprima">Gestore di riferimento:</p>
			<p class="gestore-anteprima-inner"><%= StringUtility.returnNotSpecifiedIfSo(nominativoGestore) %></p>
		</div>
		
		
		<form id="form-bottone" action="<%= request.getContextPath() + "/ViewAnnuncioServlet" %>" method="get">
			<input type="hidden" name="idAnnuncio" value="<%= annuncio.getId() %>">
		</form>

	</div>
	
<%
		}
	}
	else if(request.getAttribute(CostantiServlet.SEARCH_HAPPENED) != null) {
%>
	<div id="noresult-container">
		<p id="noresult-text">Nessun risultato trovato.</p>
		
		
		<div id="noresult-message-custom">
			<div id="noresult-animated-image">
				<lottie-player src="https://assets1.lottiefiles.com/packages/lf20_l3j1mflq.json" background="rgba(0,0,0,0)" speed="1" loop autoplay></lottie-player>
			</div>	
			<div id="suggerimenti-ricerca">
				<p id="prova-a">Prova a:</p>
				<ul type="square">
					<li>Utilizzare parole chiave più generiche</li>
					<li>Non usare abbreviazioni</li>
					<li>Controllare l'ortografia</li>
				</ul>
			</div>
		</div>
		
		
	</div>
	
<%
	}
%>
	
</div>


