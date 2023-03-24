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
import com.aziendaRecruiting.model.AnnuncioCandidato;
import com.aziendaRecruiting.model.Azienda;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.model.Gestore;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/ViewAnnuncioServlet")
public class ViewAnnuncioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
//			Utente loggedUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Integer idAnnuncio = Integer.parseInt(req.getParameter("idAnnuncio"));
			Annuncio annuncio = bLogic.getAnnuncioById(idAnnuncio);
			
			
			
			if(annuncio == null) {
				req.getRequestDispatcher("jsp/   .jsp").forward(req, resp); // TODO magari reindirizzare alla pagina precedente?
			}
			else {
				req.setAttribute(CostantiServlet.ANNUNCIO_IN_VIEW, annuncio); // impostazione dell'annuncio corrente
				
				Utente loggedUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
				
				List<AnnuncioCandidato> candidature = null;
				
				if(loggedUser instanceof Candidato) {
					Candidato candidato = (Candidato) loggedUser;
					
					Boolean hasAppliedToAnnuncio = bLogic.hasCandidatoAlreadyAppliedToAnnuncio(candidato.getId(), idAnnuncio);
					
					req.setAttribute(CostantiServlet.CANDIDATO_HAS_ALREADY_APPLIED, hasAppliedToAnnuncio);
					
				}
				else {
					
					if(loggedUser instanceof Azienda) {
						Azienda azienda = (Azienda) loggedUser;
						
						candidature = bLogic.getCandidatureOfAnnuncio_Azienda(idAnnuncio);
						
					}
					else if(loggedUser instanceof Gestore) {
						Gestore gestore = (Gestore) loggedUser;
						
						candidature = bLogic.getCandidatureOfAnnuncio(idAnnuncio);
					}
				}
				
//				req.setAttribute(CostantiServlet.LIST_CANDIDATI_ANNUNCIO, listCandidati); // impostazione della lista dei candidati (Candidato) associata all'annuncio
				req.setAttribute(CostantiServlet.CANDIDATURE, candidature); // impostazione della lista della candidature (AnnuncioCandidato) relative ad un tipo di utente
				
				
				req.getRequestDispatcher("jsp/ViewAnnuncio.jsp").forward(req, resp);
			}
			
			
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/     .jsp").forward(req, resp); // TODO
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
	
}
