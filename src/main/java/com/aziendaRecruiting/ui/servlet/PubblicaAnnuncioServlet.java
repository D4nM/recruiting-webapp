package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.model.Gestore;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/PubblicaAnnuncioServlet")
public class PubblicaAnnuncioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Integer idAnnuncio = Integer.parseInt(req.getParameter("idAnnuncio-pubblicazione")); //TODO controlli sul parsing
			Annuncio annuncio = bLogic.getAnnuncioById(idAnnuncio);
			
			Gestore gestore = (Gestore) req.getSession().getAttribute(CostantiServlet.LOGGED_USER); // TODO controlli?
					
			
			// TODO riscrivere le cose qua presenti in BL
			/*
			 * ideale:
			 * bLogic.pubblicaAnnuncio(idAnnuncio); --> in BL controlli di esistenza e di successo ecc.
			 */
			
			if(annuncio == null) {
				// TODO
			}
			else {
				
				bLogic.pubblicaAnnuncio(annuncio); // TODO 
				
				System.out.println("<OK> Pubblicazione annuncio:");
				System.out.println("\t Gestore: id=" + gestore.getId() + "  email=" + gestore.getEmail());
				System.out.println("\t Annuncio: id=" + annuncio.getId() + "  titolo=" + annuncio.getTitolo());
				System.out.println();
				
				
				String notifMsg = "Annuncio \"" + annuncio.getTitolo() + "\" pubblicato!";
				req.getSession().setAttribute(CostantiServlet.ACTION_MSG, notifMsg); //TODO migliorare messaggio
				
				// invio dei dati alla servlet dedicata per l'assegnazione del gestore all'annuncio
				req.setAttribute(CostantiServlet.ANNUNCIO_IN_VIEW, annuncio);
				req.getRequestDispatcher("/GestisciAnnuncioServlet").forward(req, resp);
				
				
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
}
