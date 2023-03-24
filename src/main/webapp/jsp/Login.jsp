<%@page import="com.aziendaRecruiting.model.Candidato"%>
<%@page import="com.aziendaRecruiting.model.Azienda"%>
<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.model.Utente"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RecruitIng | Login</title>
	<jsp:include page="/jsp/forLinking/link-head.jsp"></jsp:include>
</head>
<body id="corpo">
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	
	<div id="wrapper">
	
<%
		Utente loggedUser = (Utente) session.getAttribute(CostantiServlet.LOGGED_USER);
		
		if(loggedUser != null) {
%>
		<div id="loginGiaEffettuato-container">
			<h1 id="login-ge">Login già effettuato</h1>
<%
			//String baseLink = "./private/utente/UtenteAreaRiservata.jsp";
			String str = "";
			
			if(loggedUser instanceof Gestore) {
				str = "Gestore";
			}
			else if(loggedUser instanceof Azienda) {
				str = "Azienda";
			}
			else if(loggedUser instanceof Candidato) {
				str = "Candidato";
			}
			
			String link = request.getContextPath() +  "/jsp/private/" + str.toLowerCase() + "/" + str + "AreaRiservata.jsp";
%>
			<div id="ar-pulsanti">
				<a href="<%= link %>">
					<button id="ar-submit" type="button">Torna all'area riservata</button>
				</a>
				
				<jsp:include page="private/LogoutButton.jsp"></jsp:include>
			</div>
		</div>
<%			
		}
		else {
%>
	
		<div id="login-container">

<%
		String errMsg = (String) request.getAttribute(CostantiServlet.ERROR_MSG);
		
		if(errMsg != null) {
%>
			<!-- bisogna mettere da qualche parte il messaggio di errore -->
			<div style="color: red;">
				<h3><%= errMsg %></h3>
			</div>
<%
		}
%>
			
			<h2 id="login-title">Login</h2>
			
			<div id="login">
				<form id="form-login" action="<%= request.getContextPath() + "/LoginServlet" %>" method="post">
					
					<div id="input-group" class="input-group">
						<input class="input-login" name="email-login" type="email" placeholder=" " required>
						<label class="placeholder2">E-mail:</label>
					</div>
					
					<div id="input-group" class="input-group">
						<input class="input-login" name="password-login" type="password" placeholder=" " required>
						<label class="placeholder2">Password:</label>
					</div>
					
					<div>
						<input class="login-submit" type="submit" value="Login">
					</div>
				
				</form>
			</div>
			
			<div>
				<p class="p-signup">Non hai un account?<a class="link-signup" href="<%= request.getContextPath() + "/jsp/Signup.jsp" %>">   Registrati</a></p>
			</div>
			
		
		</div>
		
		
<%			
		}
%>
	
	</div>
	
	<!-- <jsp:include page="FooterTest.jsp"></jsp:include> -->
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/forLinking/link-script.jsp"></jsp:include>

	<!-- 
     _.-- ,.--.
   .'   .'    /
   | @       |'..--------._
  /      \._/              '.
 /  .-.-                     \
(  /    \                     \
 \\      '.                  | #
  \\       \   -.           /
   :\       |    )._____.'   \
    "       |   /  \  |  \    )
            |   |./'  :__ \.-'
            '--'	
	 -->

</body>
</html>