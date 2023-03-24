package com.aziendaRecruiting.ui.servlet;

import java.time.LocalDate; // necessario per i test
import java.util.ArrayList; // necessario per i test
import java.util.List; // necessario per i test

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.dao.AnnuncioCandidatoDao;
import com.aziendaRecruiting.dao.AnnuncioDao;
import com.aziendaRecruiting.dao.AziendaDao;
import com.aziendaRecruiting.dao.CandidatoDao;
import com.aziendaRecruiting.dao.GestoreDao;
import com.aziendaRecruiting.model.Annuncio;  // necessario per i test
import com.aziendaRecruiting.model.Azienda;  // necessario per i test
import com.aziendaRecruiting.model.Candidato;  // necessario per i test
import com.aziendaRecruiting.model.Gestore;  // necessario per i test
import com.aziendaRecruiting.utils.annuncio.DurataContratto;
import com.aziendaRecruiting.utils.annuncio.Esperienza;
import com.aziendaRecruiting.utils.annuncio.FiguraProfessionale;
import com.aziendaRecruiting.utils.annuncio.LuogoDiLavoro;
import com.aziendaRecruiting.utils.annuncio.StatoVisibilita;
import com.aziendaRecruiting.utils.annuncio.TempoLavorativo;
import com.aziendaRecruiting.utils.annuncio.TitoloDiStudio;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet(value = "/InitServerServlet", loadOnStartup = 1)
public class InitServerServlet extends HttpServlet {

	// Caricamento delle risorse (connessione ed oggetto business logic) all'avvio del server
	
	@Override
	public void init() throws ServletException {
		
		EntityManager manager = null;
		
		try {
			
			EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("AziendaRecruiting_PU");
			manager = managerFactory.createEntityManager();
			
			GestoreDao gestoreDao = new GestoreDao(manager);
			AziendaDao aziendaDao = new AziendaDao(manager);
			CandidatoDao candidatoDao = new CandidatoDao(manager);
			AnnuncioDao annuncioDao = new AnnuncioDao(manager);
			AnnuncioCandidatoDao annuncioCandidatoDao = new AnnuncioCandidatoDao(manager);
			
			RecruitingBusinessLogic bLogic = new RecruitingBusinessLogic(manager, gestoreDao, aziendaDao,
																candidatoDao, annuncioDao, annuncioCandidatoDao);
			
			getServletContext().setAttribute(CostantiServlet.B_LOGIC, bLogic);
			
			
			// TESTING
			Test1(bLogic);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Non si chiude manager perché altrimenti si chiuderebbe la connessione al DB
		
	}
	
	private void Test1(RecruitingBusinessLogic bLogic) {
		
		System.out.println("> Inizio esecuzione Test1()");
		
		final String email = "@mail.com";
		final String pw = "";
		final String nome = "nome";
		final String cognome = "cognome";
		final String telefono = "0000";
		
//		String emailAzienda;
//		String pw;
		final String ragioneSociale = "Azienda Fittizia ";
		final String sedeLegale = "via Fasulla ";
		
		final String titolo = "Titolo generico ";
		final String citta = "Luogo utopico ";
		
		
		Gestore gestore1 = new Gestore("peppe.angelis@recruiting.com", "gestore", "Peppe", "De Angelis");
		bLogic.create(gestore1);
		
		Gestore gestore2 = new Gestore("mark.caltagirone@recruiting.com", "gestore", "Mark", "Caltagirone");
		bLogic.create(gestore2);
		
		List<Annuncio> listaAnnunci = new ArrayList<Annuncio>();
		
		for(int i = 1; i <= 9; ++i) {
			
			Azienda azienda = new Azienda("az"+i+email, "az"+i+pw, ragioneSociale+i, sedeLegale+i);
			bLogic.create(azienda);
			
			
			LocalDate dataCreazione = LocalDate.parse("201" + i + "-01-01");
			Float ral = i*1000f;
			Annuncio annuncio = new Annuncio(titolo+i, dataCreazione, citta+i, ral);
			
			
			
			annuncio.setDurataContratto(DurataContratto.TEMPO_INDETERMINATO);
			annuncio.setEsperienza(Esperienza.JUNIOR);
			annuncio.setFigProfessionale("Professionista generico");
			annuncio.setLuogoDiLavoro(LuogoDiLavoro.IBRIDO);
			annuncio.setNumPostiOfferti(77);
			annuncio.setNumPostiDisponibili(77);
			annuncio.setTempoLavorativo(TempoLavorativo.FULLTIME);
			annuncio.setTitoloStudio(TitoloDiStudio.DIPLOMA);
			annuncio.setTesto("Nullam tellus felis, dapibus eget dolor eget, feugiat convallis odio. Etiam scelerisque, "
					+ "justo ac convallis tempor, diam dui tempor turpis, a tristique lacus arcu eu diam. Cras lorem nulla, "
					+ "suscipit ac ante eu, convallis dapibus sem. Nam molestie congue orci, in tempus felis rhoncus ac. "
					+ "Duis aliquam sollicitudin lectus non consectetur. Donec ultricies vestibulum mollis. Mauris ultricies "
					+ "leo in ullamcorper viverra. Nulla tempor tortor quis ligula fermentum, non fermentum tellus faucibus. "
					+ "Proin ac vehicula ipsum, nec cursus neque. Duis congue ante sit amet arcu finibus egestas. Nunc a dictum risus.");
			
			if(i <= 3) {
				bLogic.createAnnuncio(annuncio, azienda); // l'annuncio è necessariamente SUBBITO collegato ad un'azienda
				bLogic.assegnaGestoreAdAnnuncio(gestore2, annuncio); // assegnamento del gestore all'annuncio
			}
			
			if(i > 3 && i < 7) {
				annuncio.setStatoVisibilita(StatoVisibilita.PUBBLICATO);
				annuncio.setDataPubblicazione(LocalDate.now());
				bLogic.createAnnuncio(annuncio, azienda); // l'annuncio è necessariamente SUBBITO collegato ad un'azienda
				bLogic.assegnaGestoreAdAnnuncio(gestore1, annuncio); // assegnamento del gestore all'annuncio
			}
			
			if(i >= 7) {
				bLogic.createAnnuncio(annuncio, azienda);
				
			}
			
			
			listaAnnunci.add(annuncio);
		}

		for(int i = 1; i <= 9; ++i) {
			LocalDate dataNascita = LocalDate.parse("000" + i + "-01-01");
			Candidato candidato = new Candidato("cand"+i+email, "cand"+i+pw, nome+i, cognome+i, telefono+i, dataNascita);
			
			bLogic.create(candidato);
			
			bLogic.candidaturaAdAnnuncio(candidato, listaAnnunci.get(i-1));
		}
		
		System.out.println("> Fine esecuzione Test1()");
		
	}
	
}
