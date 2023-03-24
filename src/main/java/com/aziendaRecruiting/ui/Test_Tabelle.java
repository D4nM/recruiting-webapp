package com.aziendaRecruiting.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.dao.AnnuncioCandidatoDao;
import com.aziendaRecruiting.dao.AnnuncioDao;
import com.aziendaRecruiting.dao.AziendaDao;
import com.aziendaRecruiting.dao.CandidatoDao;
import com.aziendaRecruiting.dao.GestoreDao;
import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.model.Azienda;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.model.Gestore;

public class Test_Tabelle {

	public static void main(String[] args) {
		
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
			
			
			//
			// TESTING
			//
			
			final String email = "email@mail.com";
			final String pw = "password";
			final String nome = "nome";
			final String cognome = "cognome";
			final String telefono = "0000";
			
//			String emailAzienda;
//			String pw;
			final String ragioneSociale = "azienda";
			final String sedeLegale = "sede";
			
			final String titolo = "titolo";
			final String citta = "citta";
			
			
			Gestore gestore1 = new Gestore("GEST"+email, pw+"GEST", nome+"GEST", cognome+"GEST");
			bLogic.create(gestore1);
			
			List<Annuncio> listaAnnunci = new ArrayList<Annuncio>();
			
			for(int i = 1; i <= 9; ++i) {
				
				Azienda azienda = new Azienda(email+i, pw+i, ragioneSociale+i, sedeLegale+i);
				bLogic.create(azienda);
				
				
				LocalDate dataCreazione = LocalDate.parse("000" + i + "-01-01");
				Float ral = i*1000f;
				Annuncio annuncio = new Annuncio(titolo+i, dataCreazione, citta+i, ral);
				bLogic.createAnnuncio(annuncio, azienda); // l'annuncio è necessariamente SUBBITO collegato ad un'azienda
				
				
				bLogic.assegnaGestoreAdAnnuncio(gestore1, annuncio); // assegnamento del gestore all'annuncio
				
				listaAnnunci.add(annuncio);
			}

			for(int i = 1; i <= 9; ++i) {
				LocalDate dataNascita = LocalDate.parse("000" + i + "-01-01");
				Candidato candidato = new Candidato(email+i, pw+i, nome+i, cognome+i, telefono+i, dataNascita);
				
				bLogic.create(candidato);
				
				bLogic.candidaturaAdAnnuncio(candidato, listaAnnunci.get(i-1));
			}

			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			manager.close();
			System.out.println("  > Test_Tabelle terminato");
			System.exit(0);
			
		}
		
	}
	
}
