package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Gestore;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/EliminaAnnuncioServlet")
public class EliminaAnnuncioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			Utente loggedUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Integer idAnnuncio = Integer.parseInt(req.getParameter("idAnnuncio-elimina"));
			
			if(loggedUser instanceof Gestore) {
				
				bLogic.deleteAnnuncio(idAnnuncio);
				
				System.out.println("<OK> Annuncio id=" + idAnnuncio + " eliminato");
				System.out.println();
				
				req.getSession().setAttribute(CostantiServlet.ACTION_MSG, "Annuncio eliminato con successo!"); // TODO
				
				req.getRequestDispatcher("jsp/private/gestore/GestoreAreaRiservata.jsp").forward(req, resp);
				
			}
			else {
				req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO magari mandare alla home
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
}