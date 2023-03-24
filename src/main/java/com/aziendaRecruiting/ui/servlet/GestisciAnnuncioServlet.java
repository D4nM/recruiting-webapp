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
import com.aziendaRecruiting.utils.strings.StringUtility;

@WebServlet("/GestisciAnnuncioServlet")
public class GestisciAnnuncioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			/*
			 * Questa servlet viene chiamata solo da altre servlet in cui ci dovrebbero già essere
			 * controlli sull'utente in sessione e sull'annuncio, per cui non penso sia necessario fare
			 * i controlli anche qua.
			 */
			
			Gestore gestore = (Gestore) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			Annuncio annuncio = (Annuncio) req.getAttribute(CostantiServlet.ANNUNCIO_IN_VIEW);
			
			bLogic.assegnaGestoreAdAnnuncio(gestore, annuncio);
			
			System.out.println("<OK> Assegnazione gestore ad annuncio:");
			System.out.println("\t idGestore=" + gestore.getId() + "  idAnnuncio=" + annuncio.getId());
			System.out.println();
			
			
			String prevActionMsg = (String) req.getSession().getAttribute(CostantiServlet.ACTION_MSG);
			if(StringUtility.isStringValid(prevActionMsg)) {
				req.getSession().setAttribute(CostantiServlet.ACTION_MSG, prevActionMsg + "\nAnnuncio \"" + annuncio.getTitolo() + "\" preso in carico!");
			}
						
//			req.getRequestDispatcher("jsp/private/gestore/GestoreAreaRiservata.jsp").forward(req, resp); // TODO decidere dove viene reindirizzato
			resp.sendRedirect(req.getContextPath() + "/ViewAnnuncioServlet" + "?idAnnuncio=" + annuncio.getId());
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
	
}
