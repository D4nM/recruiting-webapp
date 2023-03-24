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
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.exceptions.NoUserInSessionException;
import com.aziendaRecruiting.utils.exceptions.WrongUserInSessionException;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/OttieniAnnunciDiCandidatoServlet")
public class OttieniAnnunciDiCandidatoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			req.setAttribute(CostantiServlet.SEARCH_HAPPENED, req.getParameter("ricercaEffettuata"));
			
			Utente loggedUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);

			List<Annuncio> risultatiRicerca = bLogic.getAnnunciOfCandidato(loggedUser);
			
			req.setAttribute(CostantiServlet.RESULT_ANNUNCI, risultatiRicerca);
			
			req.getRequestDispatcher("/jsp/private/candidato/CandidatoAreaRiservata.jsp").forward(req, resp);
			
		}
		catch (NoUserInSessionException e) {
			e.printStackTrace();
			req.setAttribute(CostantiServlet.ERROR_MSG, e.getMessage());
			req.getRequestDispatcher("jsp/ErrorPage.jsp").forward(req, resp);
		}
		catch (WrongUserInSessionException e) {
			e.printStackTrace();
			req.setAttribute(CostantiServlet.ERROR_MSG, e.getMessage());
			req.getRequestDispatcher("jsp/ErrorPage.jsp").forward(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/ErrorPage.jsp").forward(req, resp);
		}
	}
}