<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="div-ricerca-azienda" class="divConRicerca-azienda">

	<form class="barra-ricerca" action="<%= request.getContextPath() + "/RicercaAnnunciAziendaServlet" %>" method="post">
		
		<div>
			<label class="label-ricerca" for="">Professione</label><br>
			<input class="input-ricerca" type="text" name="professione-ricerca" placeholder="Es.: Operaio">
		</div>
		
		<div>
			<label class="label-ricerca" for="">Città</label><br>
			<input class="input-ricerca2" type="text" name="citta-ricerca" placeholder="Es.: Bologna">
		</div>
		
		<input type="hidden" name="ricercaEffettuata" value="TRUE">
		
		<input id="bottone-ricerca" class="bottone-ricerca" type="submit" value="Cerca">
		
	</form>
	
</div>