<%@page import="com.aziendaRecruiting.model.Utente"%>
<%@page import="com.aziendaRecruiting.model.Candidato"%>
<%@page import="java.util.List"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Visualizzazione della lista dei candidati -->
<div>
	<form action="<%= request.getContextPath() + "/ViewCandidatiFromAnnuncioServlet" %>"> 
		<input type="hidden" name="idAnnuncio-visualizzaCandidati" value="<%= request.getParameter("idAnnuncio-paramPassato") %>">
		<input type="submit" value="Visualizza i candidati">
	</form>
</div>
	