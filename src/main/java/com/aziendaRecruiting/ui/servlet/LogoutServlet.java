package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			Utente loggedUser = (Utente)(req.getSession().getAttribute(CostantiServlet.LOGGED_USER));
			
			if(loggedUser == null) {
				req.getRequestDispatcher("jsp/Home.jsp").forward(req, resp);
			}
			else {
				System.out.println("<!> Logout:");
				System.out.println(loggedUser);
				System.out.println();
				req.getSession().removeAttribute(CostantiServlet.ACTION_MSG);
				req.getSession().removeAttribute(CostantiServlet.LOGGED_USER);
				req.getRequestDispatcher("jsp/Home.jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO reindirizzare alla home
		}
		
	}
	
}
