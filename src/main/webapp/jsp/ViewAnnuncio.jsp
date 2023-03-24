<%@page import="com.aziendaRecruiting.utils.strings.StringUtility"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.aziendaRecruiting.model.AnnuncioCandidato"%>
<%@page import="java.util.List"%>
<%@page import="com.aziendaRecruiting.utils.annuncio.StatoVisibilita"%>
<%@page import="com.aziendaRecruiting.model.Azienda"%>
<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.model.Candidato"%>
<%@page import="com.aziendaRecruiting.model.Annuncio"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@page import="com.aziendaRecruiting.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
private Integer infoBoxCounter = 1;
private final String infoBox = "infoBox";

private String putInfoBoxId() {
	return infoBox + "-" + (infoBoxCounter++);
}


// function that generates the box containing the information
private String putFieldHtml(String scriptletExprContent, String labelText) {
	
	String str = "";
	
	// La chiamata di putInfoBoxId() va assolutamente lasciata fuori
	// dall' if() sottostante
	String currentIdValue = putInfoBoxId();
	
	
	/* Se si passa una stringa "non valida" come contenuto, verrà restituita una
	 * stringa vuota, dunque un <div> vuoto che in teoria non dovrebbe neanche
	 * comparire negli elementi html
	 */
	if(StringUtility.isStringValid(scriptletExprContent)) {
		
		str += "<div class='" + infoBox + "'>";
		
		if(StringUtility.isStringValid(labelText)) {
			str += "<label for='" + currentIdValue + "'>" 
					+ labelText 
					+ "</label>";
		}
		
		str += "<div id='" + currentIdValue + "'>"
				+ scriptletExprContent 
				+ "</div>";
		
		str += "</div>";
	}
	
	return str;
}


private String putDisabledButton(String buttonText, String tooltipText) {
	String str = "";
	
	if(StringUtility.isStringValid(buttonText)) {
		str = "<button class='button-disabled' title='" + tooltipText + "' disabled>"
				+ buttonText + "</button>";
	}
	
	return str;
}


%>

<%
Utente loggedUser = (Utente) request.getSession().getAttribute(CostantiServlet.LOGGED_USER);
Annuncio annuncio = (Annuncio) request.getAttribute(CostantiServlet.ANNUNCIO_IN_VIEW);
List<AnnuncioCandidato> candidature = (List<AnnuncioCandidato>) request.getAttribute(CostantiServlet.CANDIDATURE);


String titoloAnnuncio = StringUtility.checkString(annuncio.getTitolo());

String nomeAzienda = "";
if(annuncio.getAzienda() != null) {
	nomeAzienda = StringUtility.checkString(annuncio.getAzienda().getRagioneSociale());
}

String dataPubblicazione = StringUtility.checkString(annuncio.getFormattedDataPubblicazione());

String dataScadenza = "";
if(loggedUser instanceof Azienda || loggedUser instanceof Gestore) {
	dataScadenza = StringUtility.checkString(annuncio.getFormattedDataScadenza());
}

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

String citta = StringUtility.checkString(annuncio.getCitta());
	
String luogoDiLavoro = "";
if(annuncio.getLuogoDiLavoro() != null) {
	luogoDiLavoro = StringUtility.checkString(annuncio.getLuogoDiLavoro().printPrettyLabel());
}

String numPostiDisponibili = "";
if(annuncio.getNumPostiDisponibili() != null) {
	numPostiDisponibili = StringUtility.checkString(annuncio.getNumPostiDisponibili().toString());
}

String ral = "";
if(annuncio.getRal() != null) {
	ral = StringUtility.checkString(annuncio.getRal().toString());
}

String testo = StringUtility.checkString(annuncio.getTesto());

String figProfessionale = StringUtility.checkString(annuncio.getFigProfessionale());

String esperienza = "";
if(annuncio.getEsperienza() != null) {
	esperienza = StringUtility.checkString(annuncio.getEsperienza().printPrettyLabel());
}

String durataContratto = "";
if(annuncio.getDurataContratto() != null) {
	durataContratto = StringUtility.checkString(annuncio.getDurataContratto().printPrettyLabel());
}

String tempoLavorativo = "";
if(annuncio.getTempoLavorativo() != null) {
	tempoLavorativo = StringUtility.checkString(annuncio.getTempoLavorativo().printPrettyLabel());
}

String titoloStudio = "";
if(annuncio.getTitoloStudio() != null) {
	titoloStudio = StringUtility.checkString(annuncio.getTitoloStudio().printPrettyLabel());
}
	
%>

<!-- VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><%= annuncio.getTitolo() %></title>
	<jsp:include page="/jsp/forLinking/link-head.jsp"></jsp:include>
</head>
<body id="corpo-annuncio">
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	
	<!-- <jsp:include page="GoBackButton.jsp"></jsp:include> -->
	
	<div class="spazio"></div>
	
	<div class="annuncio-candidati">
	
		<div class="upper-annuncio"> 
			
			<div class="info-importanti">
				<!-- Titolo dell'annuncio -->
				<h4><%= putFieldHtml(titoloAnnuncio, "") %></h4>
				
				<!-- Azienda -->
				<%= putFieldHtml(nomeAzienda, "Inserzione di: ") %>
				
				<!-- Città -->
				<%= putFieldHtml(citta, "Città di riferimento: ") %>
				
				<!-- Figura professionale ricercata -->
				<%= putFieldHtml(figProfessionale, "Figura professionale richiesta: ") %>
				
				<!-- Data di pubblicazione -->
				<%= putFieldHtml(dataPubblicazione, "Pubblicato in data:") %>
				
				<!-- Data di scadenza (visibile solo dal gestore e dall'azienda) -->
				<%= putFieldHtml(dataScadenza, "Scade in data:") %>
				
				<%
				if(loggedUser != null && loggedUser instanceof Gestore && !dataScadenza.equalsIgnoreCase("NON ANCORA PUBBLICATO")) {
				%>
				<!-- PULSANTE per PROROGARE la DATA DI SCADENZA dell'annuncio -->
				<form id="form-proroga-dataScadenza" action="<%= request.getContextPath() + "/ProrogaDataScadenzaServlet" %>" method="post"> 
					<input type="hidden" name="idAnnuncio-proroga" value="<%= annuncio.getId() %>">
					
					<label for="">Proroga la data di scadenza al:</label>
					<input type="date" name="dataScadenza-nuova" min="<%= annuncio.getDataScadenza() %>" max="<%= annuncio.getDataScadenza().plusDays(180) %>" required>
					<input type="submit" value="Effettua proroga">
				</form>
				<%
				}
				%>
				
				<br>
				
				<!-- Gestore di riferimento -->	
				<%= putFieldHtml(nominativoGestore, "Gestore di riferimento:") %>			
			
			</div>
			
			<div class="requisiti-minimi">
				<h4>Requisiti minimi</h4>
				
				<!-- Titolo di studio richiesto -->
				<%= putFieldHtml(titoloStudio, "Titolo di studio: ") %>
					
				<!-- Esperienza richiesta -->
				<%= putFieldHtml(esperienza, "Esperienza: ") %>
			</div>
			
			<div class="desc-dettagli">
				<div class="descrizione">
					<h4>Descrizione</h4>
					
					<!-- Testo -->
					<%= putFieldHtml(testo, "") %>
				</div>
				
				<div class="dettagli-annuncio">
					<h4>Dettagli dell'offerta</h4>
					
					<!-- Numero di posti disponibili rimanenti -->
					<%= putFieldHtml(numPostiDisponibili, "Posizioni rimanenti: ") %>
					
					<!-- Luogo di lavoro -->
					<%= putFieldHtml(luogoDiLavoro, "Luogo di lavoro: ") %>
					
					<!-- RAL -->
					<%= putFieldHtml(ral, "RAL (&euro;): ") %>
			
					<!-- Durata/tipologia del contratto -->
					<%= putFieldHtml(durataContratto, "Durata del contratto: ") %>
					
					<!-- Tipologia di orario lavorativo -->
					<%= putFieldHtml(tempoLavorativo, "Orario lavorativo: ") %>
				</div>
			</div>
			
			<% infoBoxCounter = 1; %> <!-- Reset del contatore -->
			
		</div>
	
		
		
	
	<%
	if(loggedUser == null) { // -------------------------------------------------------------------------------Pubblico------------------------------------------------
	%>
		
		<div class="lower-annuncio">
			<a href="<%= request.getContextPath() + "/jsp/Login.jsp" %>">
				<button>Candidati</button>
			</a>
		</div>
		
		<div class="spazio-2"></div>
		
	<%
	}
	else if(loggedUser instanceof Candidato) { // ---------------------------------------------------------------Candidato------------------------------------------------------------------
		Candidato candidato = (Candidato) loggedUser;
		%>
		
		<div class="lower-annuncio">
		
		<%
		Boolean hasAlreadyApplied = (Boolean) request.getAttribute(CostantiServlet.CANDIDATO_HAS_ALREADY_APPLIED);
	
		if(hasAlreadyApplied.equals(true)) {
			// l'utente si è già candidato a questo annuncio
		%>
		
		<%= putDisabledButton("Candidati", "Candidatura già inviata") %>
		
		<%
		}
		else {
			// l'utente non si è candidato a questo annuncio
		%>
		<!-- Form di candidatura -->
		<form action="<%= request.getContextPath() + "/CandidaturaServlet" %>" method="post"> 
			<input type="hidden" name="idAnnuncio-candidatura" value="<%= annuncio.getId() %>">
			<input type="submit" value="Candidati">
		</form>
		<%
		}
		%>
		</div>
		
		<div class="spazio-2"></div>
		
	<%
	}
	else if(loggedUser instanceof Azienda) { // ----------------------------------------------------------------Azienda--------------------------------------------------------------
		Azienda azienda = (Azienda) loggedUser;
		%>
		
		<!-- TODO pulsante (di richiesta) di modifica dell'annuncio QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ -->
		<!-- temporaneo 
		<div class="lower-annuncio-mod">
			<button>
				Modifica l'annuncio
			</button>
		</div> 
		-->
		
		<%
		if(candidature != null && candidature.size() > 0) {
		%>
		
		<div class="lower-annuncio"></div>
		
		<div class="lista-candidati">
		
			<h2>Candidati proposti</h2>
		
			<%
			for(AnnuncioCandidato annCand : candidature) {
				Candidato candidato = annCand.getCandidato();
			%>
			<div class="candidato">
			
				<div class="info-candidato-cv">
					<div class="info-candidato">
							<div class="dati-candidato">
								<div id="nominativo-candidato"><%= candidato.getNome() %> <%= candidato.getCognome() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">Data di nascita: </label>
								<div><%= candidato.getFormattedDataNascita() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">Tel.: </label>
								<div><%= candidato.getRecapitoTelefonico() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">E-mail: </label>
								<div><%= candidato.getEmail() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">Comune di residenza: </label>
								<div><%= StringUtility.returnNotSpecifiedIfSo(candidato.getComuneResidenza()) %></div>
							</div>
						</div>	
					
					
					
						<!--  
						<jsp:include page="./private/DownloadCvButton.jsp">
							<jsp:param value="</%= candidato.getId() %/>" name="idCandidato"/>
						</jsp:include>
						-->
						
						<div>
							<form action="<%= request.getContextPath() + "/ScaricaCurriculumServlet"%>" method="post">
								<input type="hidden" name="idCandidato" value="<%= candidato.getId() %>">
								<input type="submit" id="scarica-cv-btn" value="Scarica CV">
							</form>
						</div>
					
					
				</div>
					
				<!-- blocco approva/scarta -->
				<div class="btn-azioni-azienda">
					<label class="azioni" for="">Azioni disponibili:</label>
					<%
					if(annCand.getIsApprovatoAzienda() == null) {
					%>
					
					<jsp:include page="./private/ApprovaCandidatoForm.jsp">
						<jsp:param value="<%= annuncio.getId() %>" name="idAnnuncio"/>
						<jsp:param value="<%= candidato.getId() %>" name="idCandidato"/>
					</jsp:include>

					<jsp:include page="./private/ScartaCandidatoForm.jsp">
						<jsp:param value="<%= annuncio.getId() %>" name="idAnnuncio"/>
						<jsp:param value="<%= candidato.getId() %>" name="idCandidato"/>
					</jsp:include>
					
					<%
					}
					else {
						String tooltip = "";
						
						if(annCand.getIsApprovatoAzienda().equals(true)) {
							tooltip = "Candidato già approvato";
						}
						else if(annCand.getIsApprovatoAzienda().equals(false)) {
							tooltip = "Candidato già scartato";
						}
					%>
						
					<!-- [PULSANTE APPROVAZIONE -come sopra- FASULLO] -->
					<%= putDisabledButton("Approva candidato", tooltip) %>
					
					<!-- [PULSANTE SCARTAMENTO -come sopra- FASULLO] -->
					<%= putDisabledButton("Scarta candidato", tooltip) %>
						
					<%
					}
					%>
				</div>
				
			</div>	
			<%
			} // chiusura for()
			%>
		
		</div> <!-- chiusura "list-candidati" -->
		
		<%
		}
		else {
		%>
		<div class="lower-annuncio">
			<%= putDisabledButton("Visualizza candidati", "Nessun candidato presente") %>
		</div>
		<%
		}
		%>
	
	<%
	}
	else if(loggedUser instanceof Gestore) { // --------------------------------------------------------------Gestore-------------------------------------------------------------------
		Gestore gestore = (Gestore) loggedUser;
		%>
		
		<div class="lower-annuncio">
			
			<!-- da fare la servlet QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ -->
			<!-- Modifica dell'annuncio 
			<form action="</%= request.getContextPath() + "/ModificaAnnuncioServlet" %/>" method="post"> 
				<input type="hidden" name="idAnnuncio-modifica" value="</%= annuncio.getId() %/>">
				<input type="submit" value="Modifica annuncio e prenditene carico">
			</form>
			-->
			
			<%
			if(annuncio.getStatoVisibilita() == StatoVisibilita.CREATO || annuncio.getStatoVisibilita() == StatoVisibilita.CHIUSO) {
			%>
				<form action="<%= request.getContextPath() + "/EliminaAnnuncioServlet" %>" method="post">
					<input type="hidden" name="idAnnuncio-elimina" value="<%= annuncio.getId() %>">
					<input type="submit" value="Elimina l'annuncio">
				</form>
			<%
			}
			%>
			
			<%
			if(annuncio.getStatoVisibilita() == StatoVisibilita.CREATO) {
			%>
		
			<!-- Pubblicazione dell'annuncio -->
			<form action="<%= request.getContextPath() + "/PubblicaAnnuncioServlet" %>" method="post"> 
				<input type="hidden" name="idAnnuncio-pubblicazione" value="<%= annuncio.getId() %>">
				<input type="submit" value="Pubblica annuncio e prenditene carico">
			</form>
		
			<%
			}
			else if(annuncio.getStatoVisibilita() == StatoVisibilita.PUBBLICATO) {
			%>
			
			<!-- PULSANTE per CHIUDERE l'annuncio -->
			<form action="<%= request.getContextPath() + "/ChiudiAnnuncioServlet" %>" method="post"> 
				<input type="hidden" name="idAnnuncio-chiusura" value="<%= annuncio.getId() %>">
				<input type="submit" value="Chiudi l'annuncio">
			</form>
					
		
		<!-- </div> -->
			
			<%	
			if(candidature != null && candidature.size() > 0) {
			%>
		
			</div> <!-- chiusura "lower-annuncio" -->
		
			<div class="lista-candidati">
		
				<h2 class="titolo-candidati">Candidati</h2>
			
				<%
				for(AnnuncioCandidato annCand : candidature) {
					Candidato candidato = annCand.getCandidato();
				%>
				<div class="candidato">
				
					<div class="info-candidato-cv">
						<div class="info-candidato">
							<div class="dati-candidato">
								<div id="nominativo-candidato"><%= candidato.getNome() %> <%= candidato.getCognome() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">Data di nascita: </label>
								<div><%= candidato.getFormattedDataNascita() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">Tel.: </label>
								<div><%= candidato.getRecapitoTelefonico() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">E-mail: </label>
								<div><%= candidato.getEmail() %></div>
							</div>
							<div class="dati-candidato">
								<label for="">Comune di residenza: </label>
								<div><%= StringUtility.returnNotSpecifiedIfSo(candidato.getComuneResidenza()) %></div>
							</div>
						</div>	
							
							
						<!--  
						<jsp:include page="./private/DownloadCvButton.jsp">
							<jsp:param value="</%= candidato.getId() %/>" name="idCandidato"/>
						</jsp:include>
						-->
						
						<div>
							<form action="<%= request.getContextPath() + "/ScaricaCurriculumServlet"%>" method="post">
								<input type="hidden" name="idCandidato" value="<%= candidato.getId() %>">
								<input type="submit" id="scarica-cv-btn" value="Scarica CV">
							</form>
						</div>
						
						
					</div>
					
					
					<!-- blocco approva/scarta -->
					<div class="btn-azioni-gestore">
						<label class="azioni" for="">Azioni disponibili come gestore:</label>
						<%
						if(annCand.getIsApprovatoGestore() == null) {
						%>
						
						<jsp:include page="./private/ApprovaCandidatoForm.jsp">
							<jsp:param value="<%= annuncio.getId() %>" name="idAnnuncio"/>
							<jsp:param value="<%= candidato.getId() %>" name="idCandidato"/>
						</jsp:include>
	
						<jsp:include page="./private/ScartaCandidatoForm.jsp">
							<jsp:param value="<%= annuncio.getId() %>" name="idAnnuncio"/>
							<jsp:param value="<%= candidato.getId() %>" name="idCandidato"/>
						</jsp:include>
						
						<%
						}
						else {
							String tooltip = "";
							
							if(annCand.getIsApprovatoGestore().equals(true)) {
								tooltip = "Candidato già approvato";
							}
							else if(annCand.getIsApprovatoGestore().equals(false)) {
								tooltip = "Candidato già scartato";
							}
						%>
						
						<!-- [PULSANTE APPROVAZIONE FASULLO] -->
						<%= putDisabledButton("Approva candidato", tooltip) %>
						
						<!-- [PULSANTE SCARTAMENTO FASULLO] -->
						<%= putDisabledButton("Scarta candidato", tooltip) %>
						
						<%
						}
						%>
					</div>
					
				</div>
				<%
				} // chiusura for()
				%>
				
			</div> <!-- chiusura "lista-candidati" -->
			
			<%
			}
			else {
			%>
			
			<%= putDisabledButton("Visualizza candidati", "Nessun candidato presente") %>
			
			</div> <!-- chiusura "lower-annuncio" -->
			<%
			}
			%>
				
		<%
		} // fine blocco if(PUBBLICATO)
		%>
	
	<%
	} // fine blocco if() di Gestore
	
	%>
	
	</div> <!-- fine "annuncio-candidati" -->
	
	

	
	
	<jsp:include page="/jsp/ActionNotification.jsp"></jsp:include>
	
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/forLinking/link-script.jsp"></jsp:include>
</body>
</html>