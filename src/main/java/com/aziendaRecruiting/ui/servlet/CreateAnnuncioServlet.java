package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.model.Azienda;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.annuncio.DurataContratto;
import com.aziendaRecruiting.utils.annuncio.Esperienza;
import com.aziendaRecruiting.utils.annuncio.LuogoDiLavoro;
import com.aziendaRecruiting.utils.annuncio.TempoLavorativo;
import com.aziendaRecruiting.utils.annuncio.TitoloDiStudio;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/CreateAnnuncioServlet")
public class CreateAnnuncioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			Utente currentUser = (Utente) req.getSession().getAttribute(CostantiServlet.LOGGED_USER);
			
//			if(currentUser == null) {
//				// TODO
//			}
//			else if(currentUser instanceof Azienda) {
				
				String titolo = req.getParameter("titolo-annuncio");
				String testo = req.getParameter("testo-annuncio");
				String citta = req.getParameter("citta-annuncio");
				Integer numPostiOfferti = Integer.parseInt(req.getParameter("numero-posti-offerti"));
				Float ral = Float.parseFloat(req.getParameter("ral-annuncio"));
				LuogoDiLavoro luogoLavoro = LuogoDiLavoro.valueOfLabel(req.getParameter("luogo-lavoro"));
				String figProfessionale = req.getParameter("professione-annuncio");
				Esperienza esperienza = Esperienza.valueOfLabel(req.getParameter("esperienza-annuncio"));
				DurataContratto durataContratto = DurataContratto.valueOfLabel(req.getParameter("durata-contratto-annuncio"));
				TempoLavorativo tempoLavorativo = TempoLavorativo.valueOfLabel(req.getParameter("tempo-lavorativo-annuncio"));
				TitoloDiStudio titoloStudio = TitoloDiStudio.valueOfLabel(req.getParameter("titolo-studio-annuncio"));
				
				RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
				
				Annuncio annuncio = new Annuncio(titolo, luogoLavoro, numPostiOfferti, testo, figProfessionale, esperienza);
				annuncio.setCitta(citta);
				annuncio.setRal(ral);
				annuncio.setDurataContratto(durataContratto);
				annuncio.setTempoLavorativo(tempoLavorativo);
				annuncio.setTitoloStudio(titoloStudio);
				
				Azienda azienda = (Azienda)currentUser;
				
				bLogic.createAnnuncio(annuncio, azienda);
				
				System.out.println("<OK> Annuncio creato sul DB:");
				System.out.println("\t id_azienda: " + annuncio.getAzienda().getId());
				System.out.println(annuncio);
				System.out.println();
				
//			}
//			else {
//				// se l'utente in sessione per QUALCHE MOTIVO non è un'azienda
//				// TODO
//			}
			
			String notifMsg = "Annuncio \"" + annuncio.getTitolo() + "\" creato con successo!";	//TODO
			
			req.getSession().setAttribute(CostantiServlet.ACTION_MSG, notifMsg);
			
			req.getRequestDispatcher("jsp/private/azienda/AziendaAreaRiservata.jsp").forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/private/azienda/AziendaAreaRiservata.jsp").forward(req, resp); // TODO cambiare pagina di arrivo
			// TODO messaggio del tipo "Annuncio creato con successo"
		}
	}
	
}
