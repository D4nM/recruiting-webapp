<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
	<form action="<%= request.getContextPath() + "/ScartaCandidatoServlet"%>" method="post">
		<input type="hidden" name="idAnnuncio" value="<%= request.getParameter("idAnnuncio") %>">
		<input type="hidden" name="idCandidato-scartato" value="<%= request.getParameter("idCandidato") %>">
		<input type="submit" value="Scarta candidato">
	</form>
</div>