<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.model.Azienda"%>
<%@page import="com.aziendaRecruiting.model.Candidato"%>
<%@page import="com.aziendaRecruiting.model.Utente"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Utente loggedUser = (Utente) session.getAttribute(CostantiServlet.LOGGED_USER);

String pageUrl = "";
String buttonText = "";

if(loggedUser == null) {
	pageUrl = request.getContextPath() + "/jsp/Home.jsp";
	buttonText = "Torna alla Home";
}
else {
	buttonText = "Torna all'area riservata";
	
	if(loggedUser instanceof Candidato) {
		pageUrl = request.getContextPath() + "/jsp/private/candidato/CandidatoAreaRiservata.jsp";
	}
	else if(loggedUser instanceof Azienda) {
		pageUrl = request.getContextPath() + "/jsp/private/azienda/AziendaAreaRiservata.jsp";
	}
	else if(loggedUser instanceof Gestore) {
		pageUrl = request.getContextPath() + "/jsp/private/gestore/GestoreAreaRiservata.jsp";
	}
}

%>
<div>
	<a href="<%= pageUrl %>">
		<button id="goback-button"><%= buttonText %></button>
	</a>
</div>


