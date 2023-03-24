<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="div-ricerca-candidato" class="divConRicerca-candidato">

	<form class="barra-ricerca-candidato" action="<%= request.getContextPath() + "/RicercaAnnunciCandidatoServlet" %>" method="post">
		
		<div>
			<label class="label-ricerca-candidato" for="">Professione</label><br>
			<input class="input-ricerca-candidato" type="text" name="professione-ricerca" placeholder="Es.: Operaio">
		</div>
		
		<div>
			<label class="label-ricerca-candidato" for="">Città</label><br>
			<input class="input-ricerca-candidato2" type="text" name="citta-ricerca" placeholder="Es.: Bologna">
		</div>
				
		<input type="hidden" name="ricercaEffettuata" value="TRUE">
		
		<input id="bottone-ricerca" class="bottone-ricerca-candidato" type="submit" value="Cerca">
	</form>
	
	
	<div class="candidature">
		<form action="<%= request.getContextPath() + "/OttieniAnnunciDiCandidatoServlet" %>" method="post">
			<input type="hidden" name="ricercaEffettuata" value="TRUE">
			<input type="submit" class="bottone-candidature" value="Le mie candidature"></input>
		</form>
	</div>
</div>