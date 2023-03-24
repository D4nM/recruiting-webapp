package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/CandidaturaServlet")
public class CandidaturaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Candidato candidato = (Candidato) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			
			Integer idAnnuncio = Integer.parseInt(req.getParameter("idAnnuncio-candidatura"));
			Annuncio annuncio = bLogic.getAnnuncioById(idAnnuncio);
			
			// TODO magari controlli sui possibili valori di 'idAnnuncio' e 'annuncio'?
			
			if(candidato == null) {
				// TODO
				
				//provvisorio
				throw new Exception("<!> Exception: candidato is null");
			}
			else {
				bLogic.candidaturaAdAnnuncio(candidato, annuncio);
				
				System.out.println("<OK> Candidatura:");
				System.out.println("\t Utente: id=" + candidato.getId() + "  email=" + candidato.getEmail());
				System.out.println("\t Annuncio: id=" + annuncio.getId() + "  titolo=" + annuncio.getTitolo());
				System.out.println();
				
				String notifMsg = "Candidatura a \"" + annuncio.getTitolo() + "\" di " + annuncio.getAzienda().getRagioneSociale()  + " inviata con successo!";
				req.getSession().setAttribute(CostantiServlet.ACTION_MSG, notifMsg);

				
				resp.sendRedirect(req.getContextPath() + "/ViewAnnuncioServlet" + "?idAnnuncio=" + idAnnuncio);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
	
}
