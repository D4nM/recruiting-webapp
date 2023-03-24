package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/ChiudiAnnuncioServlet")
public class ChiudiAnnuncioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			Integer idAnnuncio = Integer.parseInt(req.getParameter("idAnnuncio-chiusura"));
			
			Annuncio annuncioChiuso = bLogic.closeAnnuncio(idAnnuncio);
			
			
			String titoloAnnuncio = "";
			if(annuncioChiuso != null) {
				titoloAnnuncio = "\"" + annuncioChiuso.getTitolo() + "\" ";
			}
			
			String notifMsg = "Annuncio " + titoloAnnuncio + "chiuso con successo!";
			req.getSession().setAttribute(CostantiServlet.ACTION_MSG, notifMsg);
			
			req.getRequestDispatcher("jsp/private/gestore/GestoreAreaRiservata.jsp").forward(req, resp); 
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
}