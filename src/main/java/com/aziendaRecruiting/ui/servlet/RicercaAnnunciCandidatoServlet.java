package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/RicercaAnnunciCandidatoServlet")
public class RicercaAnnunciCandidatoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			req.setAttribute(CostantiServlet.SEARCH_HAPPENED, req.getParameter("ricercaEffettuata"));
			
			
			String professione = req.getParameter("professione-ricerca");
			String citta = req.getParameter("citta-ricerca");
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			List<Annuncio> risultatiRicerca = bLogic.getAnnunci(professione, citta);
			
			if(risultatiRicerca.isEmpty()) {
				risultatiRicerca = null; // TODO passare null se è vuota OPPURE semplicemente passarla vuota?
			}
			
			req.setAttribute(CostantiServlet.RESULT_ANNUNCI, risultatiRicerca);
			
			
			
			Utente loggedUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			
			if(req.getParameter("ricercaPubblica") != null) {
				req.getRequestDispatcher("/jsp/Home.jsp").forward(req, resp); 
			}
			else if(loggedUser != null && loggedUser instanceof Candidato) {
				req.getRequestDispatcher("/jsp/private/candidato/CandidatoAreaRiservata.jsp").forward(req, resp);
			}
			
					
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
}
