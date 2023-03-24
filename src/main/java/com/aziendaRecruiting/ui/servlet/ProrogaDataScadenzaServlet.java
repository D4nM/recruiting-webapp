package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/ProrogaDataScadenzaServlet")
public class ProrogaDataScadenzaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Integer idAnnuncio = Integer.parseInt(req.getParameter("idAnnuncio-proroga"));
			
			LocalDate nuovaDataScadenza = LocalDate.parse(req.getParameter("dataScadenza-nuova"));
			
			bLogic.setDataScadenzaOfAnnuncio(idAnnuncio, nuovaDataScadenza);
			
			String notifMsg = "Data di scadenza dell'annuncio prorogata con successo!";
			req.getSession().setAttribute(CostantiServlet.ACTION_MSG, notifMsg); //TODO
			
			resp.sendRedirect(req.getContextPath() + "/ViewAnnuncioServlet" + "?idAnnuncio=" + idAnnuncio);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
}
