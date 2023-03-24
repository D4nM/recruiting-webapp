package com.aziendaRecruiting.ui.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;


@WebFilter("/jsp/private/*")
public class AccessFilter implements Filter { 
       
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("Filtro d'accesso AREA PRIVATA GENERALE attivato");
		
		try {
			
			Utente loggedUser = (Utente)((HttpServletRequest)request).getSession().getAttribute(CostantiServlet.LOGGED_USER);
			
			if(loggedUser == null) {
				// se NON c'è un utente loggato:
				System.out.println("In AccessFilter: currentUser è null");
				request.getRequestDispatcher("jsp/Login.jsp").forward(request, response); // TODO magari reindirizzare alla home
			}
			else {
				// se c'è un utente loggato:
				chain.doFilter(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("jsp/Login.jsp").forward(request, response); // TODO magari reindirizzare alla home
		}
	}

}
