<%@page import="com.aziendaRecruiting.utils.annuncio.TitoloDiStudio"%>
<%@page import="com.aziendaRecruiting.utils.annuncio.TempoLavorativo"%>
<%@page import="com.aziendaRecruiting.utils.annuncio.DurataContratto"%>
<%@page import="com.aziendaRecruiting.utils.annuncio.Esperienza"%>
<%@page import="com.aziendaRecruiting.utils.annuncio.FiguraProfessionale"%>
<%@page import="com.aziendaRecruiting.utils.annuncio.LuogoDiLavoro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="create-annuncio" class="hidden-box">
	
	<div id="box-create">
	
		<div id="contenitore-esterno">
		
			<div id="spaziatore"></div>
			
			<div id="tavola">
				
				<div id="foglio">
					
					<div id="contenuto">
						
						<div id="chiudi-azienda" class="crea-annuncio">
							<label>Chiudi</label>
							<button id="bottone-chiudi" class="material-symbols-outlined bottone-chiudi-annuncio" type="button">
								close
							</button>
						</div>
						
						<div class="titolo-creazione">
							<h1>Creazione Annuncio</h1>
						</div>
						
						<form id="form-creazione-annuncio" action="<%= request.getContextPath() + "/CreateAnnuncioServlet"%>" method="post">
							
							<div id="primapagina">
								
								<!-- RIGA 1 -->
								<div class="riga-input">
									<div class="input-creazione">
										<label for="">Titolo</label><br>
										<input type="text" name="titolo-annuncio" required>
									</div>
									
									<div class="input-creazione">
										<label for="">Città</label><br>
										<input type="text" name="citta-annuncio">
									</div>
								</div>
								
								
								<!-- RIGA 2 -->
								<div class="riga-input">
									<div class="input-creazione">
										<label for="">Numero di posti offerti</label><br>
										<input type="number" min="1" step="1" name="numero-posti-offerti" required>
									</div>
									
									<div class="input-creazione">
										<label for="">Reddito Annuo Lordo (RAL) (&euro;)</label><br>
										<input type="number" min="0" name="ral-annuncio">
									</div>
								</div>
								
								
								<!-- RIGA 3 -->
								<div class="riga-input">
									<!-- select luogo di lavoro -->
									<div class="input-creazione">
										<label for="">Luogo di lavoro</label><br>
										<select id="" name="luogo-lavoro" required>
											<option>- Non selezionato -</option>
										<%
										for(LuogoDiLavoro item : LuogoDiLavoro.values()) {
										%>
											<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
										<%
										}
										%>
										</select>
									</div>
					
									<!-- datalist figura professionale -->
									<!-- https://stackoverflow.com/questions/25616625/
										how-to-display-only-the-text-in-datalist-html5-and-not-the-value -->
									<div class="input-creazione">
										<label for="">Figura professionale ricercata</label><br>
										<input list="figura-professionista" name="professione-annuncio" required>
										<datalist id="figura-professionista">
										<%
										for(FiguraProfessionale item : FiguraProfessionale.values()) {
										%>
											<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
										<%
										}
										%>				
										</datalist>
									</div>
								</div>
								
								<!-- RIGA 4 -->
								<div class="riga-input">
									<!-- select esperienza richiesta -->
									<div class="input-creazione">
										<label for="">Esperienza minima richiesta</label><br>
										<select name="esperienza-annuncio" required>
											<option>- Non selezionato -</option>
										<%
										for(Esperienza item : Esperienza.values()) {
										%>
											<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
										<%
										}
										%>				
										</select>
									</div>
					
									<!-- select durata del contratto -->
									<div class="input-creazione">
										<label for="">Durata/tipologia del contratto</label><br>
										<select name="durata-contratto-annuncio">
											<option>- Non selezionato -</option>
										<%
										for(DurataContratto item : DurataContratto.values()) {
										%>
											<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
										<%
										}
										%>				
										</select>
									</div>
								</div>
				
								<!-- RIGA 5 -->
								<div class="riga-input">
									<!-- select tempo lavorativo -->
									<div class="input-creazione">
										<label for="">Tempo lavorativo</label><br>
										<select name="tempo-lavorativo-annuncio">
											<option>- Non selezionato -</option>
										<%
										for(TempoLavorativo item : TempoLavorativo.values()) {
										%>
											<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
										<%
										}
										%>
										</select>
									</div>
									
									<!-- select titolo di studio richiesto -->
									<div class="input-creazione">
										<label for="">Titolo di studio richiesto</label><br>
										<select name="titolo-studio-annuncio">
											<option>- Non selezionato -</option>
										<%
										for(TitoloDiStudio item : TitoloDiStudio.values()) {
										%>
											<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
										<%
										}
										%>
										</select>
									</div>
								</div>
								
								<div class="input-creazione-submit">
									<button id="bottone-next" type="button">Next</button>
								</div>
										
							</div> <!-- fine "prima pagina" -->
							
							
							<div id="seconda-pagina" class="hidden-box">
								
								<div id="area-testo" class="riga-input">
									<div class="input-creazione-area">
										<label for="">Testo</label><br>
										<textarea name="testo-annuncio" rows="20" cols="50" required></textarea>
									</div>
								</div>
								
								<div id="btn-btn">
									<div class="input-creazione-submit2" id="back">
										<button id="bottone-back" type="button">Back</button>
									</div>
									<div class="input-creazione-submit2 ">
										<input id="bottone-crea-annuncio" type="submit" value="Crea">
									</div>
								</div>
								
							</div> <!-- fine "seconda pagina" -->
							
						</form>
												
					</div>
					
				</div>
				
			</div>
		
			<div id="clip-container">
				<div id="clip" class="clip-row">
					<div id="semicircle" class="clip"></div>
					<div id="clamp" class="clip"></div>
				</div>
			</div>
		
		</div> <!-- fine "contenitore-esterno" -->
	

	</div> <!-- fine "box-create" -->
</div>
