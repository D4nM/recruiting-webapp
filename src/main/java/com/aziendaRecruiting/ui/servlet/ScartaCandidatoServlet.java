package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.AnnuncioCandidato;
import com.aziendaRecruiting.model.Azienda;
import com.aziendaRecruiting.model.Gestore;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/ScartaCandidatoServlet")
public class ScartaCandidatoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			Utente loggedUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Integer idAnnuncio = Integer.parseInt(req.getParameter("idAnnuncio"));
			Integer idCandidato = Integer.parseInt(req.getParameter("idCandidato-scartato"));
			
			AnnuncioCandidato candidatura = bLogic.getCandidatura(idCandidato, idAnnuncio);
			
			if(loggedUser instanceof Azienda) {
				bLogic.setAziendaApprovalOfCandidatura(candidatura, false);
			}
			else if(loggedUser instanceof Gestore) {
				bLogic.setGestoreApprovalOfCandidatura(candidatura, false);
			}
			
			String notifMsg = "Candidato " + candidatura.getCandidato().getNome() + " " + candidatura.getCandidato().getCognome() + " scartato!";
			req.getSession().setAttribute(CostantiServlet.ACTION_MSG, notifMsg);			
			
			resp.sendRedirect(req.getContextPath() + "/ViewAnnuncioServlet" + "?idAnnuncio=" + idAnnuncio);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
}
