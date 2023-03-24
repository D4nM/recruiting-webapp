<%@page import="com.aziendaRecruiting.utils.gestore.TipoRicerca"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="div-ricerca-gestore" class="divConRicerca-gestore">

	<form class="barra-ricerca-gestore" action="<%= request.getContextPath() + "/RicercaAnnunciGestoreServlet" %>" method="post">
		
		<div>
			<label class="label-ricerca-gestore" for="">Professione</label><br>
			<input class="input-ricerca-gestore" type="text" name="professione-ricerca" placeholder="Es.: Operaio">
		</div>
		
		<div>
			<label class="label-ricerca-gestore" for="">Città</label><br>
			<input class="input-ricerca-gestore2" type="text" name="citta-ricerca" placeholder="Es.: Bologna">
		</div>
				
		<!-- select per il tipo di ricerca da effettuare, cioè con quali restrizioni -->
		<div>
			<label class="label-ricerca-gestore" for="">Cerca in: </label><br>
			<select class="input-ricerca-gestore3" name="ambitoGestore-ricerca" required>
<%
				for(TipoRicerca item : TipoRicerca.values()) {
%>
				<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
<%
				}
%>				
			</select>
		</div>
		
		<input type="hidden" name="ricercaEffettuata" value="TRUE">
		
		<input class="bottone-ricerca-gestore" type="submit" value="Cerca">
	</form>
	
</div>