<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="box-search">
	<form id="box-search" action="<%=request.getContextPath() + "/RicercaAnnunciCandidatoServlet" %>" method="post">

		<div id="search-prof">
			<label class="form-label">Professione</label>
			<input id="search1" type="text" name="professione-ricerca" placeholder="Es. Developer"> 
		</div>
		
		<div id="search-citta">
			<label class="form-label">Città</label>
			<input id="search2" type="text" name="citta-ricerca" placeholder="Es. Torino">
		</div>
		
		<input type="hidden" name="ricercaEffettuata" value="TRUE">
		<input type="hidden" name="ricercaPubblica" value="TRUE">
		
		<div  class="search-button">
			<input id="search-submit" type="submit" value="Cerca" >
		</div>

	</form>
</div>
