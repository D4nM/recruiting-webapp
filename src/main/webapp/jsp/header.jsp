<%@page import="com.aziendaRecruiting.model.Gestore"%>
<%@page import="com.aziendaRecruiting.model.Azienda"%>
<%@page import="com.aziendaRecruiting.model.Candidato"%>
<%@page import="com.aziendaRecruiting.model.Utente"%>
<%@page import="org.apache.catalina.User"%>
<%@page import="com.aziendaRecruiting.utils.servlet.CostantiServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	 <header class="container-fluid">
        <div id="dios-mios" class="row d-flex justify-content-center align-items-center">
            <div id="logo-container" class="col-lg d-flex justify-content-center align-items-center">
                <a id="resto" href="<%= request.getContextPath() + "/jsp/Home.jsp"%>">Recruit</a><a id="ing" href="<%= request.getContextPath() + "/jsp/Home.jsp"%>">Ing</a>
            </div>
            <div class="col-lg-6 d-flex justify-content-center align-items-center"></div>
            <div class="col-lg d-flex justify-content-center align-items-center">
            <%
            	Utente user = (Utente)request.getSession().getAttribute(CostantiServlet.LOGGED_USER);
            	if(user != null){
           	%>
	           		<div class="dropdown">
	        <%
	        			if(user instanceof Azienda){
	        				Azienda azienda = (Azienda)request.getSession().getAttribute(CostantiServlet.LOGGED_USER);
	        %>
	           			<button class="dropdown bottone-profilo" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"><%= azienda.getRagioneSociale().toUpperCase().charAt(0)%></button>
	           			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						    <li><a class="dropdown-item" href="<%= request.getContextPath() + "/jsp/private/azienda/AziendaAreaRiservata.jsp"%>"><button id="bottone-area-riservata">Area riservata</button></a></li>
						    <li><hr class="dropdown-divider"></li>
						    <li><a class="dropdown-item" href="<%= request.getContextPath() + "/LogoutServlet"%>"><button id="log-out" class="log-out0">Log out</button></a></li>
					  	</ul>
	        <%
	        			}
	        			else if(user instanceof Candidato){
	        				Candidato candidato = (Candidato)request.getSession().getAttribute(CostantiServlet.LOGGED_USER);
	        %>
	        			<button class="dropdown bottone-profilo" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"><%= candidato.getNome().toUpperCase().charAt(0)%></button>
	        			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						    <li><a class="dropdown-item" href="<%= request.getContextPath() + "/jsp/private/candidato/CandidatoAreaRiservata.jsp"%>"><button id="bottone-area-riservata">Area riservata</button></a></li>
						    <li><hr class="dropdown-divider"></li>
						    <li><a class="dropdown-item" href="<%= request.getContextPath() + "/LogoutServlet"%>"><button id="log-out" class="log-out0">Log out</button></a></li>
					  	</ul>
	        <%
	        			}
	        			else if(user instanceof Gestore){
	        				Gestore gestore = (Gestore)request.getSession().getAttribute(CostantiServlet.LOGGED_USER);
	        %>
	        			<button class="dropdown bottone-profilo" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"><%= gestore.getNome().toUpperCase().charAt(0)%></button>
	        			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						    <li><a class="dropdown-item" href="<%= request.getContextPath() + "/jsp/private/gestore/GestoreAreaRiservata.jsp"%>"><button id="bottone-area-riservata">Area riservata</button></a></li>
						    <li><hr class="dropdown-divider"></li>
						    <li><a class="dropdown-item" href="<%= request.getContextPath() + "/LogoutServlet"%>"><button id="log-out" class="log-out0">Log out</button></a></li>
					  	</ul>
	        <%			
	        			}
	        %>

	                </div>
            <%
            	}
            	else{
            %>
                <div class="singup">
                	<a href="<%= request.getContextPath() + "/jsp/Signup.jsp"%>"><button id="sign-up" class="sign-up0">Sign up</button></a>
                </div>
                <div class="login">
                    <a href="<%= request.getContextPath() + "/jsp/Login.jsp"%>"><button id="log-in" class="log-in0">Log in</button></a>
                </div>
            <%
            	}
            %>
            </div>
        </div>
    </header>