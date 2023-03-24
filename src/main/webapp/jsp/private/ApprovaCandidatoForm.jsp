<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
	<form action="<%= request.getContextPath() + "/ApprovaCandidatoServlet"%>" method="post">
		<input type="hidden" name="idAnnuncio" value="<%= request.getParameter("idAnnuncio") %>">
		<input type="hidden" name="idCandidato-approvato" value="<%= request.getParameter("idCandidato") %>">		
		<input type="submit" value="Approva candidato">
	</form>
</div>