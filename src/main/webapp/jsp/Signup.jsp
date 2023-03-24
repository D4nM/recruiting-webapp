<%@page import="com.aziendaRecruiting.utils.strings.StringUtility"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@page import="com.aziendaRecruiting.utils.azienda.SettoreAzienda"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String errMsg = (String) request.getAttribute(CostantiServlet.ERROR_MSG);
%>

<%!

private String putMsgInLabelHtml(String text) {
	
	if(StringUtility.isStringValid(text)) {
		return "<label id='message'>" + text + "</label>";
	}
	
	return "";
}

%>



<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RecruitIng | Registrati</title>
	<jsp:include page="/jsp/forLinking/link-head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	
	<div class="wrapper">
	
		<div class="signup-container">
		
			<h1 id="signup-title">Signup</h1>
			<div class="signup-buttons">
				<button class="action aziendaButton">Azienda</button>
				<button class="action utenteButton">Utente</button>
				<button class="movingButton">Azienda</button>
			</div>
			
			<div id="signup-azienda">
				<form id="azienda" class="form aziendaForm" action="<%= request.getContextPath() + "/SignupAziendaServlet" %>" method="post">
					
					<%= putMsgInLabelHtml(errMsg) %>
					
					<div class="input-group">
						<input class="input-signup" type="email" name="email-signup" placeholder=" " required>
						<label class="placeholderLabelForm">E-mail*</label>
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="password" name="password-signup" placeholder=" " required>
						<label class="placeholderLabelForm">Password*</label>
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="text" name="ragione-sociale" placeholder=" " required>
						<label class="placeholderLabelForm">Ragione Sociale*</label>
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="text" name="sede-legale" placeholder=" " required>
						<label class="placeholderLabelForm">Sede legale*</label>
					</div>
					
					<!-- Mancante nel file Signup.jsp passatomi: aggiunte classi analoghe alla struttura degli altri input -->
					<div class="input-group">
						<input class="input-signup" type="tel" name="telefono" pattern="[0-9]{10}" placeholder=" ">
						<label class="placeholderLabelForm">Recapito telefonico</label>
					</div>
					
					<!-- Mancante nel file Signup.jsp passatomi: aggiunte classi analoghe alla struttura degli altri input -->	
					<div class="input-group">
						<select class="input-signup" name="settore">
							<option>- Non specificato -</option>
							<%
							for(SettoreAzienda item : SettoreAzienda.values()) {
							%>
							<option value="<%= item %>"><%= item.printPrettyLabel() %></option>
							<%
							}
							%>
							
						</select>
						<label class="placeholderLabelForm">Settore</label>
					</div>
					
					<div>
						<input class="signup-submit" type="submit" value="Sign up">
					</div>
					
				</form>		
			</div>
			
			
			<div id="signup-utente">
				<form id="privato" class="form" action="<%= request.getContextPath() + "/SignupCandidatoServlet" %>" 
					method="post" 
					enctype="multipart/form-data"> <!-- enctype è l'attributo da aggiungere per far caricare file -->
					
					<%= putMsgInLabelHtml(errMsg) %>
					
					<div class="input-group">
						<input class="input-signup" type="email" name="email-signup" placeholder=" " required>
						<label class="placeholderLabelForm">E-mail*</label>
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="password" name="password-signup" placeholder=" " required>
						<label class="placeholderLabelForm">Password*</label>
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="text" name="nome" placeholder=" " required>
						<label class="placeholderLabelForm">Nome*</label>
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="text" name="cognome" placeholder=" " required>
						<label class="placeholderLabelForm">Cognome*</label>
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="tel" name="telefono" pattern="[0-9]{10}" placeholder=" " required>
						<label class="placeholderLabelForm">Telefono*</label>						
					</div>
					
					<div class="input-group">
						<input class="input-signup" type="date" name="data-di-nascita" max="<%= LocalDate.now().minusYears(18) %>" placeholder=" " required>
						<label class="placeholderLabelForm">Data di nascita*</label>						
					</div>
					
					<!-- Mancante nel file Signup.jsp passatomi: aggiunte classi analoghe alla struttura degli altri input -->
					<div class="input-group">
						<input class="input-signup" type="text" name="comune-residenza" placeholder=" ">
						<label class="placeholderLabelForm">Comune di residenza</label>
					</div>
					
					<!-- Mancante nel file Signup.jsp passatomi: aggiunte classi analoghe alla struttura degli altri input -->
					<!-- CV -->
					<div class="input-group">
						<input class="input-signup" type="file" name="file-cv" accept=".pdf" required>
						<label class="placeholderLabelForm">Curriculum*</label>
					</div>
					
					<div>
						<input class="signup-submit" type="submit" value="Sign up">
					</div>
				
				</form>
			</div>
			
			<div>
				<p class="p-login">
					Hai già un account?<a class="link-login" href="<%= request.getContextPath() + "/jsp/Login.jsp" %>">   Accedi</a>
				</p>
			</div>
			
		
		</div> <!-- chiusura 'signup-container' -->
	
	</div> <!-- chiusura 'wrapper' -->
	
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
	<jsp:include page="/jsp/forLinking/link-script.jsp"></jsp:include>
	
	<!-- 
          ."`".
       .-./ _=_ \.-.
      {  (,(oYo),) }}
      {{ |   "   |} }
      { { \(---)/  }}
      {{  }'-=-'{ } }
      { { }._:_.{  }}
      {{  } -:- { } }
      {_{ }`===`{  _}
     ((((\)     (/))))	
	 
	 -->

</body>
</html>