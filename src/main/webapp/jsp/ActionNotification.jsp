<%@page import="com.aziendaRecruiting.utils.strings.StringUtility"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String actionMsg = (String) session.getAttribute(CostantiServlet.ACTION_MSG);

if(StringUtility.isStringValid(actionMsg)) {
%>
	<div id="container-notifica">
		<div class="rettangolo">
			<div class="testo-notifica">
			
				<!--  
				<button id="closeButton-notifica">
					<span class="material-symbols-outlined">close</span>
				</button>
				-->
				
				
				<span id="closeButton-notifica" class="material-symbols-outlined">close</span>
				
				<span id="text-notifica"><%= actionMsg %></span>
			</div>
		</div>
	</div>
<%
}
session.removeAttribute(CostantiServlet.ACTION_MSG);
%>
