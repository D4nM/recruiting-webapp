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
import com.aziendaRecruiting.model.Gestore;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.gestore.TipoRicerca;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/RicercaAnnunciGestoreServlet")
public class RicercaAnnunciGestoreServlet extends HttpServlet {

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
			TipoRicerca tipoRicerca = TipoRicerca.valueOfLabel(req.getParameter("ambitoGestore-ricerca"));
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Utente loggedUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			
			if(loggedUser == null) {
				// TODO
			}
			else if(loggedUser instanceof Gestore) {
				
				Gestore gestore = (Gestore) loggedUser;
				
				List<Annuncio> risultatiRicerca = null;
				
				if(tipoRicerca.equals(TipoRicerca.TUTTI)) {
					risultatiRicerca = bLogic.getAnnunci_Gestore(professione, citta);
				}
				else if(tipoRicerca.equals(TipoRicerca.GESTITI)) {
					risultatiRicerca = bLogic.getAnnunciGestiti_Gestore(gestore.getId(), professione, citta);
				}
				else if(tipoRicerca.equals(TipoRicerca.NON_ANCORA_GESTITI)) {
					risultatiRicerca = bLogic.getAnnunciNonGestiti_Gestore(professione, citta);
				}
				else if(tipoRicerca.equals(TipoRicerca.GESTITI_DA_ALTRI)) {
					risultatiRicerca = bLogic.getAnnunciGestitiDaAltri_Gestore(gestore.getId(), professione, citta);
				}
								
				
				if(risultatiRicerca.isEmpty()) {
					risultatiRicerca = null; // TODO passare null se è vuota OPPURE semplicemente passarla vuota?
				}
			
				req.setAttribute(CostantiServlet.RESULT_ANNUNCI, risultatiRicerca);
				req.getRequestDispatcher("/jsp/private/gestore/GestoreAreaRiservata.jsp").forward(req, resp);
			
			}
			else {
				// TODO
			}
			
			
			
					
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/ErrorPage.jsp").forward(req, resp);
		}
	}
}
