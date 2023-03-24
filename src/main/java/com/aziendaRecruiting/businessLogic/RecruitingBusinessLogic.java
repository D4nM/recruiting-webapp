package com.aziendaRecruiting.businessLogic;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.aziendaRecruiting.dao.AnnuncioCandidatoDao;
import com.aziendaRecruiting.dao.AnnuncioDao;
import com.aziendaRecruiting.dao.AziendaDao;
import com.aziendaRecruiting.dao.CandidatoDao;
import com.aziendaRecruiting.dao.GestoreDao;
import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.model.AnnuncioCandidato;
import com.aziendaRecruiting.model.Azienda;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.model.Gestore;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.annuncio.StatoVisibilita;
import com.aziendaRecruiting.utils.exceptions.InputFieldException;
import com.aziendaRecruiting.utils.exceptions.NoUserInSessionException;
import com.aziendaRecruiting.utils.exceptions.WrongUserInSessionException;
import com.aziendaRecruiting.utils.strings.StringUtility;

public class RecruitingBusinessLogic {
	
	private EntityManager manager;
	
	private GestoreDao gestoreDao;
	private AziendaDao aziendaDao;
	private CandidatoDao candidatoDao;
	private AnnuncioDao annuncioDao;
	private AnnuncioCandidatoDao annuncioCandidatoDao;

	public RecruitingBusinessLogic(EntityManager manager, GestoreDao gestoreDao, AziendaDao aziendaDao,
			CandidatoDao candidatoDao, AnnuncioDao annuncioDao, AnnuncioCandidatoDao annuncioCandidatoDao) {
		super();
		this.manager = manager;
		this.gestoreDao = gestoreDao;
		this.aziendaDao = aziendaDao;
		this.candidatoDao = candidatoDao;
		this.annuncioDao = annuncioDao;
		this.annuncioCandidatoDao = annuncioCandidatoDao;
	}

	
	
	//
	// FUNZIONALITA'
	//
	
	
	// recupero dell'utente dal DB
	public Utente retrieveUtente(String email, String password) throws InputFieldException {
		try {
			manager.getTransaction().begin();
			
			// controllo email su Gestore
			List<String> emailResultGestore = gestoreDao.findMatchingEmail(email);
			if(emailResultGestore.size() == 1) {
				
				// recupero del gestore con le credenziali passate come parametri
				List<Gestore> resultGestore = gestoreDao.retrieveGestore(email, password);
				
				manager.getTransaction().commit();
				
				return retrieveFirstUtenteInList(resultGestore);
				
			}
			else if(emailResultGestore.isEmpty()) {
				// nessuna corrispondenza in Gestore
				// controllo email su Azienda
				
				List<String> emailResultAzienda = aziendaDao.findMatchingEmail(email);				
				if(emailResultAzienda.size() == 1) {
					
					// recupero dell'azienda con le credenziali passate come parametri
					List<Azienda> resultAzienda = aziendaDao.retrieveAzienda(email, password);
					
					manager.getTransaction().commit();
					
					return retrieveFirstUtenteInList(resultAzienda);
				}
				else if(emailResultAzienda.isEmpty()) {
					// nessuna corrispondenza in Azienda
					// controllo email su Candidato
					
					List<String> emailResultCandidato = candidatoDao.findMatchingEmail(email);
					if(emailResultCandidato.size() == 1) {
						
						// recupero del candidato con le credenziali passate come parametri
						List<Candidato> resultCandidato = candidatoDao.retrieveCandidato(email, password);
						
						manager.getTransaction().commit();
						
						return retrieveFirstUtenteInList(resultCandidato);
					}
					else if(emailResultCandidato.isEmpty()) {
						// email NON TROVATA da nessuna parte
						manager.getTransaction().commit();
						return null; //------------------------------------------------------------
						// ^^^^ sostituire con: throw new InputFieldException("Utente non registrato");
					}
					else {
						manager.getTransaction().commit();
						// email di Candidato in molteplice copia sul DB
						throw new InputFieldException("Utente duplicato");
					}
					
				}
				else {
					manager.getTransaction().commit();
					// email di Azienda in molteplice copia sul DB
					throw new InputFieldException("Utente duplicato");
				}
			}
			else {
				manager.getTransaction().commit();
				// email di Gestore in molteplice copia sul DB
				throw new InputFieldException("Utente duplicato");
			}
						
		}
		catch (InputFieldException e) {
			// "expected exception"
			System.out.println("Eccezione da retrieveUtente(): " + e.getMessage());
			throw e;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	// Ricerca annunci
	//
	
	// Ricerca di Candidato o di utente pubblico
	// TODO cambiare nome???
	public List<Annuncio> getAnnunci(String professione, String citta) {
		try {
			
			List<Annuncio> risultatiRicerca = null;
			
			manager.getTransaction().begin();
			
			if(StringUtility.isStringValid(professione)) {
				if(StringUtility.isStringValid(citta)) {
					risultatiRicerca = annuncioDao.retrieveAnnunciByProfessioneCitta(professione, citta);
				}
				else {
					// se 'citta' non è una stringa valida
					risultatiRicerca = annuncioDao.retrieveAnnunciByProfessione(professione);
				}
			}
			else if(StringUtility.isStringValid(citta)){
				// se 'professione' non è una stringa valida
				risultatiRicerca = annuncioDao.retrieveAnnunciByCitta(citta);
			}
			else {
				// se né un campo né l'altro sono stringhe valide
				risultatiRicerca = annuncioDao.retrieveAllAnnunci();
			}
			
			manager.getTransaction().commit();
			
			return risultatiRicerca;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	// Ricerca di Azienda
	public List<Annuncio> getAnnunci_Azienda(Integer idAzienda, String professione, String citta) {
		try {
			
			List<Annuncio> risultatiRicerca = null;
			
			manager.getTransaction().begin();
			
			if(StringUtility.isStringValid(professione)) {
				if(StringUtility.isStringValid(citta)) {
					risultatiRicerca = annuncioDao.retrieveAnnunciByProfessioneCitta_Azienda(idAzienda, professione, citta);
				}
				else {
					// se 'citta' non è una stringa valida
					risultatiRicerca = annuncioDao.retrieveAnnunciByProfessione_Azienda(idAzienda, professione);
				}
			}
			else if(StringUtility.isStringValid(citta)){
				// se 'professione' non è una stringa valida
				risultatiRicerca = annuncioDao.retrieveAnnunciByCitta_Azienda(idAzienda, citta);
			}
			else {
				// se né un campo né l'altro sono stringhe valide
				risultatiRicerca = annuncioDao.retrieveAllAnnunci_Azienda(idAzienda);
			}
			
			manager.getTransaction().commit();
			
			return risultatiRicerca;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	// Ricerca di Gestore: TUTTI gli annunci visibili a esso (cioè letteralmente tutti)
	public List<Annuncio> getAnnunci_Gestore(String professione, String citta) {
		try {
			
			List<Annuncio> risultatiRicerca = null;
			
			manager.getTransaction().begin();
			
			if(StringUtility.isStringValid(professione)) {
				if(StringUtility.isStringValid(citta)) {
					risultatiRicerca = annuncioDao.retrieveAnnunciByProfessioneCitta_Gestore(professione, citta);
				}
				else {
					// se 'citta' non è una stringa valida
					risultatiRicerca = annuncioDao.retrieveAnnunciByProfessione_Gestore(professione);
				}
			}
			else if(StringUtility.isStringValid(citta)){
				// se 'professione' non è una stringa valida
				risultatiRicerca = annuncioDao.retrieveAnnunciByCitta_Gestore(citta);
			}
			else {
				// se né un campo né l'altro sono stringhe valide
				risultatiRicerca = annuncioDao.retrieve();
			}
			
			manager.getTransaction().commit();
			
			return risultatiRicerca;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	// Ricerca di Gestore: annunci GESTITI da esso
	public List<Annuncio> getAnnunciGestiti_Gestore(Integer idGestore, String professione, String citta) {
		try {
			
			List<Annuncio> risultatiRicerca = null;
			
			manager.getTransaction().begin();
			
			if(StringUtility.isStringValid(professione)) {
				if(StringUtility.isStringValid(citta)) {
					risultatiRicerca = annuncioDao.retrieveAnnunciGestitiByProfessioneCitta_Gestore(idGestore, professione, citta);
				}
				else {
					// se 'citta' non è una stringa valida
					risultatiRicerca = annuncioDao.retrieveAnnunciGestitiByProfessione_Gestore(idGestore, professione);
				}
			}
			else if(StringUtility.isStringValid(citta)){
				// se 'professione' non è una stringa valida
				risultatiRicerca = annuncioDao.retrieveAnnunciGestitiByCitta_Gestore(idGestore, citta);
			}
			else {
				// se né un campo né l'altro sono stringhe valide
				risultatiRicerca = annuncioDao.retrieveAllAnnunciGestiti_Gestore(idGestore);
			}
			
			manager.getTransaction().commit();
			
			return risultatiRicerca;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	// Ricerca di Gestore: annunci NON ANCORA GESTITI da nessuno
	public List<Annuncio> getAnnunciNonGestiti_Gestore(String professione, String citta) {
		try {
			
			List<Annuncio> risultatiRicerca = null;
			
			manager.getTransaction().begin();
			
			if(StringUtility.isStringValid(professione)) {
				if(StringUtility.isStringValid(citta)) {
					risultatiRicerca = annuncioDao.retrieveAnnunciNonGestitiByProfessioneCitta_Gestore(professione, citta);
				}
				else {
					// se 'citta' non è una stringa valida
					risultatiRicerca = annuncioDao.retrieveAnnunciNonGestitiByProfessione_Gestore(professione);
				}
			}
			else if(StringUtility.isStringValid(citta)){
				// se 'professione' non è una stringa valida
				risultatiRicerca = annuncioDao.retrieveAnnunciNonGestitiByCitta_Gestore(citta);
			}
			else {
				// se né un campo né l'altro sono stringhe valide
				risultatiRicerca = annuncioDao.retrieveAllAnnunciNonGestiti_Gestore();
			}
			
			manager.getTransaction().commit();
			
			return risultatiRicerca;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	// Ricerca di Gestore: annunci GESTITI DA ALTRI gestori
	public List<Annuncio> getAnnunciGestitiDaAltri_Gestore(Integer idGestoreEscluso, String professione, String citta) {
		try {
			
			List<Annuncio> risultatiRicerca = null;
			
			manager.getTransaction().begin();
			
			if(StringUtility.isStringValid(professione)) {
				if(StringUtility.isStringValid(citta)) {
					risultatiRicerca = annuncioDao.retrieveAnnunciGestitiDaAltriByProfessioneCitta_Gestore(idGestoreEscluso, professione, citta);
				}
				else {
					// se 'citta' non è una stringa valida
					risultatiRicerca = annuncioDao.retrieveAnnunciGestitiDaAltriByProfessione_Gestore(idGestoreEscluso, professione);
				}
			}
			else if(StringUtility.isStringValid(citta)){
				// se 'professione' non è una stringa valida
				risultatiRicerca = annuncioDao.retrieveAnnunciGestitiDaAltriByCitta_Gestore(idGestoreEscluso, citta);
			}
			else {
				// se né un campo né l'altro sono stringhe valide
				risultatiRicerca = annuncioDao.retrieveAllAnnunciGestitiDaAltri_Gestore(idGestoreEscluso);
			}
			
			manager.getTransaction().commit();
			
			return risultatiRicerca;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	// Annuncio
	//
	public List<Annuncio> getAnnunciOfCandidato(Utente loggedUser) throws Exception {
		try {
			manager.getTransaction().begin();

			List<Annuncio> annunciResult = null;
			
			if(loggedUser == null) {
				throw new NoUserInSessionException("Accesso agli annunci iscritti bloccato: utente non presente in sessione.");
			}
			else if(loggedUser instanceof Candidato) {
				annunciResult = annuncioDao.retrieveAnnunciOfCandidato(((Candidato)loggedUser).getId());
			}
			else {
				throw new WrongUserInSessionException("Accesso agli annunci iscritti bloccato: l'utente in sessione non e' un Candidato.");
			}
			
			manager.getTransaction().commit();
			
			return annunciResult;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public List<Annuncio> getListAnnuncioByState(StatoVisibilita statoVisibilita) {
		try {
			manager.getTransaction().begin();
			
			List<Annuncio> annunciResult = annuncioDao.retrieveListAnnuncioByState(statoVisibilita);
			
			manager.getTransaction().commit();
			
			return annunciResult;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public Annuncio getAnnuncioById(Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
						
			List<Annuncio> annuncioResult = annuncioDao.retrieveAnnuncioById(idAnnuncio);
			
			manager.getTransaction().commit();
			
			if(annuncioResult.size() == 1) {
				return annuncioResult.get(0);
			}
			else if(annuncioResult.isEmpty()) {
				return null;
			}
			else {
				// caso di num risultati > 1
				
				// TODO tirare un altro errore custom??
				
				// provvisorio
				System.out.println("<!> getAnnuncioById() ha rilevato due annunci con id uguale");
				return null; 
			}
			
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void pubblicaAnnuncio(Annuncio annuncio) {
		try {
//			manager.getTransaction().begin();
			
			// TODO non è richiesto accesso al DAO, giusto? In tal caso, meglio metterlo qua o direttamente nella servlet? //----------------------
			annuncio.setStatoVisibilita(StatoVisibilita.PUBBLICATO);
			annuncio.setDataPubblicazione(LocalDate.now());
			
//			manager.getTransaction().commit();
		}
		catch (Exception e) {
//			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void assegnaGestoreAdAnnuncio(Gestore gestore, Annuncio annuncio) {
		try {
			
			manager.getTransaction().begin();
			
			// per questi comandi, si assume che gli oggetti in questione siano gestiti da Hibernate
			annuncio.setGestore(gestore);
						
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public Annuncio closeAnnuncio(Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
			
			List<Annuncio> annuncioList = annuncioDao.retrieveAnnuncioById(idAnnuncio);
			
			Annuncio annuncioChiuso = null;
			
			if(annuncioList.isEmpty()) {
				// TODO
			}
			else if(annuncioList.size() == 1) {
				
				annuncioChiuso = annuncioList.get(0);
				annuncioChiuso.setStatoVisibilita(StatoVisibilita.CHIUSO);
				
			}
			else {
				// TODO
			}
			
			manager.getTransaction().commit();
			
			return annuncioChiuso;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void deleteAnnuncio(Integer idAnnuncio) { //-----------------------------------------------------------------------------------------------
		try {
			manager.getTransaction().begin();
			
			List<Annuncio> annuncioList = annuncioDao.retrieveAnnuncioById(idAnnuncio);
			
			if(annuncioList.isEmpty()) {
				// TODO
			}
			else if(annuncioList.size() == 1) {
				
				Annuncio annuncio = annuncioList.get(0);
				
				for(AnnuncioCandidato candidatura : annuncio.getListaCandidati()) {
					annuncioCandidatoDao.delete(candidatura);
				}
								
				annuncioDao.delete(annuncio);
				
			}
			else {
				// TODO
			}
			
			manager.getTransaction().commit();
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void setDataScadenzaOfAnnuncio(Integer idAnnuncio, LocalDate nuovaDataScadenza) {
		try {
			manager.getTransaction().begin();
			
			List<Annuncio> annuncioList = annuncioDao.retrieveAnnuncioById(idAnnuncio);
			
			if(annuncioList.isEmpty()) {
				// TODO
			}
			else if(annuncioList.size() == 1) {
				
				Annuncio annuncio = annuncioList.get(0);
				
				if(nuovaDataScadenza.isAfter(annuncio.getDataScadenza())) {
					annuncio.setDataScadenza(nuovaDataScadenza);
					System.out.println("<OK> Annuncio id=" + annuncio.getId() + " : " + "dataScadenza prorogata a " + nuovaDataScadenza);
					System.out.println();
				}
				else {
					// TODO
					
					System.out.println("<!> in setDataScadenzaOfAnnuncio() per Annuncio id=" + annuncio.getId());
					System.out.println("\tnuovaDataScadenza è precedente ad annuncio.getDataScadenza()");							
					System.out.println();
				}
				
				
			}
			else {
				// TODO
			}
			
			manager.getTransaction().commit();
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
	
	
	// Candidato-Annuncio
	//
	
	/* cambiare nome in 'applyToAnnuncio' ?? */
	public void candidaturaAdAnnuncio(Candidato candidato, Annuncio annuncio) {
		try {
			manager.getTransaction().begin();
			
			AnnuncioCandidato annuncioCandidato = new AnnuncioCandidato(candidato, annuncio);
			annuncioCandidatoDao.create(annuncioCandidato);
						
			manager.getTransaction().commit();
						
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public AnnuncioCandidato getCandidatura(Integer idCandidato, Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
			
			List<AnnuncioCandidato> candidatura = annuncioCandidatoDao.retrieveCandidaturaOfCandidatoToAnnuncio(idCandidato, idAnnuncio);
			
			manager.getTransaction().commit();
			
			if(candidatura.isEmpty()) {
				// TODO
				return null;
			}
			else if(candidatura.size() == 1) {
				return candidatura.get(0);
			}
			else {
				// TODO
				return null;
			}
			
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<AnnuncioCandidato> getCandidatureOfAnnuncio_Azienda(Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
			
			List<AnnuncioCandidato> candidature = annuncioCandidatoDao.retrieveCandidatureFromAnnuncioByGestoreApproval(idAnnuncio, true);
			
			manager.getTransaction().commit();
			
			return candidature;
						
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<AnnuncioCandidato> getCandidatureOfAnnuncio(Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
			
			List<AnnuncioCandidato> candidature = annuncioCandidatoDao.retrieveCandidatureFromAnnuncio(idAnnuncio);
						
			manager.getTransaction().commit();
			
			return candidature;
						
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public Boolean hasCandidatoAlreadyAppliedToAnnuncio(Integer idCandidato, Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
			
			List<AnnuncioCandidato> result = annuncioCandidatoDao.retrieveCandidaturaOfCandidatoToAnnuncio(idCandidato, idAnnuncio);
			
			manager.getTransaction().commit();
			
			if(result.isEmpty()) {
				return false;
			}
			else if(result.size() == 1) {
				return true;
			}
			else {
				// caso con più di un risultato
				// TODO qualcosa
				return false;
			}
				
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void setGestoreApprovalOfCandidatura(AnnuncioCandidato candidatura, Boolean state) {
		try {
			manager.getTransaction().begin();
			
			candidatura.setIsApprovatoGestore(state); // ---------------------------------------------------------------------------
			
			manager.getTransaction().commit();
			
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void setAziendaApprovalOfCandidatura(AnnuncioCandidato candidatura, Boolean state) {
		try {
			manager.getTransaction().begin();
			
			candidatura.setIsApprovatoAzienda(state); // ---------------------------------------------------------------------------
			
			if(state.equals(true)) {
				Annuncio annuncio = candidatura.getAnnuncio();
				
				if(annuncio.getNumPostiDisponibili() > 0) {
					annuncio.setNumPostiDisponibili(annuncio.getNumPostiDisponibili() - 1);
				}
				
				if(annuncio.getNumPostiDisponibili() <= 0) {
					
					annuncio.setStatoVisibilita(StatoVisibilita.CHIUSO);
					
					System.out.println("<!> Annuncio id=" + annuncio.getId() + " è stato CHIUSO");
					System.out.println();
				}
			}			
			
			manager.getTransaction().commit();
			
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	// TODO eliminare se inutilizzato
	public List<AnnuncioCandidato> getCandidatureOfCandidato(Integer idCandidato) {
		try {
			manager.getTransaction().begin();
			
			List<AnnuncioCandidato> listCandidature = annuncioCandidatoDao.retrieveCandidatureOfCandidato(idCandidato);
			
			manager.getTransaction().commit();
			
			return listCandidature;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
	// Candidato
	//
	public Candidato getCandidatoById(Integer idCandidato) {
		try {
			manager.getTransaction().begin();
			
			List<Candidato> listCandidato = candidatoDao.retrieveCandidatoById(idCandidato);
			
			manager.getTransaction().commit();
			
			if(listCandidato.isEmpty()) {
				// TODO
				return null;
			}
			else if(listCandidato.size() == 1) {
				return listCandidato.get(0);
			}
			else {
				// TODO
				return null;
			}
			
			
			
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public Boolean isEmailCandidatoRegistered(String emailToCheck) throws InputFieldException {
		try {
			manager.getTransaction().begin();
			
			List<String> emailResult = candidatoDao.findMatchingEmail(emailToCheck);
						
			manager.getTransaction().commit();
			
			return isStringUniqueInList(emailResult);
			
		}
		catch (InputFieldException e) {
			// "expected exception"
			System.out.println("Eccezione da isEmailCandidatoRegistered(): " + e.getMessage());
			throw e;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Candidato> getCandidati_Gestore(Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
			
			List<Candidato> listCandidati = candidatoDao.retrieveCandidatiFromAnnuncio(idAnnuncio);
			
			manager.getTransaction().commit();
			
			return listCandidati;
			
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Candidato> getCandidati_Azienda(Integer idAnnuncio) {
		try {
			manager.getTransaction().begin();
			
			List<Candidato> listCandidati = candidatoDao.retrieveCandidatiFromAnnuncioByGestoreApproval(idAnnuncio, true);
			
			manager.getTransaction().commit();
			
			return listCandidati;
			
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
	
	
	// Azienda
	//
	public Boolean isEmailAziendaRegistered(String emailToCheck) throws InputFieldException {
		try {
			manager.getTransaction().begin();
			
			List<String> emailResult = aziendaDao.findMatchingEmail(emailToCheck);
						
			manager.getTransaction().commit();
			
			return isStringUniqueInList(emailResult);
			
		}
		catch (InputFieldException e) {
			// "expected exception"
			System.out.println("Eccezione da isEmailAziendaRegistered(): " + e.getMessage());
			throw e;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	

	
	
	
	// Gestore
	//
	public Boolean isEmailGestoreRegistered(String emailToCheck) throws InputFieldException {
		try {
			manager.getTransaction().begin();
			
			List<String> emailResult = gestoreDao.findMatchingEmail(emailToCheck);
						
			manager.getTransaction().commit();
			
			return isStringUniqueInList(emailResult);
			
		}
		catch (InputFieldException e) {
			// "expected exception"
			System.out.println("Eccezione da isEmailAziendaRegistered(): " + e.getMessage());
			throw e;
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	

	
	
	
	// Utilità o funzioni di appoggio
	//
	private Utente retrieveFirstUtenteInList(List<? extends Utente> list) throws InputFieldException {
		// restituisce il risultato della lista se è unico, altrimenti lancia eccezioni
		if(list.size() == 1) {
			return list.get(0);
		}
		else if(list.isEmpty()) {
			throw new InputFieldException("Password errata");
		}
		else {
			throw new InputFieldException("Errore imprevisto: password duplicata");
		}
	}
	
	private Boolean isStringUniqueInList(List<String> stringList) throws InputFieldException {
		if(stringList.size() == 1) {
			return true;
		}
		else if(stringList.isEmpty()) {
			return false;
		}
		else { 
			// caso di num risultati > 1
			throw new InputFieldException("Utente già registrato");
		}
	}
		
	
	
	
	//
	// PER TESTING
	//
	public void create(Candidato candidato) {
		try {
			manager.getTransaction().begin();
			
			candidatoDao.create(candidato);
			
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void create(Azienda azienda) {
		try {
			manager.getTransaction().begin();
			
			aziendaDao.create(azienda);
			
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	public void create(Gestore gestore) {
		try {
			manager.getTransaction().begin();
			
			gestoreDao.create(gestore);
			
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void create(Annuncio annuncio, Azienda azienda, Gestore gestore) {
		try {
			manager.getTransaction().begin();
			
			annuncioDao.create(annuncio); //---------------------
			
			
			
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void createAnnuncio(Annuncio annuncio, Azienda azienda) {
		try {
			manager.getTransaction().begin();
			
			annuncio.setAzienda(azienda);
			annuncioDao.create(annuncio);
			
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
	
	
}
