package com.aziendaRecruiting.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.aziendaRecruiting.model.Annuncio;
import com.aziendaRecruiting.utils.annuncio.StatoVisibilita;

public class AnnuncioDao implements RecruitingDaoInterface<Annuncio> {

	private EntityManager manager;
	
	public AnnuncioDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Annuncio create(Annuncio obj) {
		manager.persist(obj);
		return obj; // TODO se non serve, eliminare la restituzione dell'oggetto stesso
	}

	@Override
	public List<Annuncio> retrieve() {
		return manager.createQuery("SELECT a FROM Annuncio a", Annuncio.class).getResultList();
	}

	@Override
	public Annuncio update(Annuncio obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public void delete(Annuncio obj) {
		manager.remove(obj);
		
	}
	
	
	
	public List<Annuncio> retrieveListAnnuncioByState(StatoVisibilita statoVisibilita) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE a.statoVisibilita = :parState", Annuncio.class)
				.setParameter("parState", statoVisibilita).getResultList();
	}
	
	public List<Annuncio> retrieveAnnuncioById(Integer idAnnuncio) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE a.id = :parId", Annuncio.class)
				.setParameter("parId", idAnnuncio).getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciOfCandidato(Integer idCandidato) {
		return manager.createQuery("SELECT a FROM Annuncio a JOIN AnnuncioCandidato ac ON a.id = ac.annuncio.id "
				+ "WHERE a.statoVisibilita = :parState AND ac.candidato.id = :parIdCandidato", Annuncio.class)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.setParameter("parIdCandidato", idCandidato)
				.getResultList();
	}
	
	
	
	
	//
	// Ricerche di Candidato / Utente generico - campo/i di ricerca caratteristico/i: a.statoVisibilita = StatoVisibilita.PUBBLICATO
	//
		
	public List<Annuncio> retrieveAllAnnunci() {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE a.statoVisibilita = :parState", Annuncio.class)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciByCitta(String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.statoVisibilita = :parState) AND "
				+ "(a.citta = :parCitta)", Annuncio.class)
				.setParameter("parCitta", citta)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciByProfessione(String professione) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.statoVisibilita = :parState) AND "
				+ "(a.figProfessionale = :parProf)", Annuncio.class)
				.setParameter("parProf", professione)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}

	public List<Annuncio> retrieveAnnunciByProfessioneCitta(String professione, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.statoVisibilita = :parState) AND "
				+ "(a.figProfessionale = :parProf) AND (a.citta = :parCitta)", Annuncio.class)
				.setParameter("parProf", professione)
				.setParameter("parCitta", citta)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}
	
	
	
	
	//
	// Ricerche di Gestore - campo/i di ricerca caratteristico/i: dataPubblicazione può essere NULL
	//
		
	// ricerche senza restrizione sullo stato dell'annuncio
	
//	public List<Annuncio> retrieveAllAnnunci_Gestore() {
//		return manager.createQuery("SELECT a FROM Annuncio", Annuncio.class)
//				.getResultList();
//	}
	// questa sopra ^^^^ è uguale a retrieve()
	
	public List<Annuncio> retrieveAnnunciByCitta_Gestore(String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.citta = :parCitta)", Annuncio.class)
				.setParameter("parCitta", citta)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciByProfessione_Gestore(String professione) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.figProfessionale = :parProf)", Annuncio.class)
				.setParameter("parProf", professione)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciByProfessioneCitta_Gestore(String professione, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE "
				+ "(a.figProfessionale = :parProf) AND (a.citta = :parCitta)", Annuncio.class)
				.setParameter("parProf", professione)
				.setParameter("parCitta", citta)
				.getResultList();
	}
	
	
	
	// ricerche sugli annunci gestiti da un particolare gestore
	
	public List<Annuncio> retrieveAllAnnunciGestiti_Gestore(Integer idGestore) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id = :parIdGestore)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciGestitiByCitta_Gestore(Integer idGestore, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id = :parIdGestore) AND "
				+ "(a.citta = :parCitta)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.setParameter("parCitta", citta)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciGestitiByProfessione_Gestore(Integer idGestore, String professione) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id = :parIdGestore) AND "
				+ "(a.figProfessionale = :parProf)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.setParameter("parProf", professione)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciGestitiByProfessioneCitta_Gestore(Integer idGestore, String professione, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id = :parIdGestore) AND "
				+ "(a.figProfessionale = :parProf) AND (a.citta = :parCitta)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.setParameter("parProf", professione)
				.setParameter("parCitta", citta)
				.getResultList();
	}
	
	
	
	// ricerche sugli annunci non ancora presi in carico da nessuno // TODO dà problemi a.gestore.id? È meglio a.gestore IS NULL ?
	
	public List<Annuncio> retrieveAllAnnunciNonGestiti_Gestore() {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NULL)", Annuncio.class)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciNonGestitiByCitta_Gestore(String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NULL) AND "
				+ "(a.citta = :parCitta)", Annuncio.class)
				.setParameter("parCitta", citta)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciNonGestitiByProfessione_Gestore(String professione) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NULL) AND "
				+ "(a.figProfessionale = :parProf)", Annuncio.class)
				.setParameter("parProf", professione)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciNonGestitiByProfessioneCitta_Gestore(String professione, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NULL) AND "
				+ "(a.figProfessionale = :parProf) AND (a.citta = :parCitta)", Annuncio.class)
				.setParameter("parProf", professione)
				.setParameter("parCitta", citta)
				.getResultList();
	}
		
	
	
	// ricerche sugli annunci gestiti da altri // TODO vanno bene le query così? Dà problemi a.gestore.id? È meglio a.gestore IS NOT NULL ?
	
	public List<Annuncio> retrieveAllAnnunciGestitiDaAltri_Gestore(Integer idGestore) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NOT NULL) AND (a.gestore.id != :parIdGestore)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciGestitiDaAltriByCitta_Gestore(Integer idGestore, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NOT NULL) AND (a.gestore.id != :parIdGestore) AND "
				+ "(a.citta = :parCitta)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.setParameter("parCitta", citta)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciGestitiDaAltriByProfessione_Gestore(Integer idGestore, String professione) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NOT NULL) AND (a.gestore.id != :parIdGestore) AND "
				+ "(a.figProfessionale = :parProf)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.setParameter("parProf", professione)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciGestitiDaAltriByProfessioneCitta_Gestore(Integer idGestore, String professione, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.gestore.id IS NOT NULL) AND (a.gestore.id != :parIdGestore) AND "
				+ "(a.figProfessionale = :parProf) AND (a.citta = :parCitta)", Annuncio.class)
				.setParameter("parIdGestore", idGestore)
				.setParameter("parProf", professione)
				.setParameter("parCitta", citta)
				.getResultList();
	}
	
	
	
	
	
	//
	// Ricerche di Azienda - campo/i di ricerca caratteristico/i: a.azienda.id = idAzienda, a.statoVisibilita = StatoVisibilita.PUBBLICATO
	//
	
	// TODO fare modifiche per cui l'azienda può vedere anche i suoi annunci NON ancora pubblicato (ma solo creati)?
	
	public List<Annuncio> retrieveAllAnnunci_Azienda(Integer idAzienda) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.azienda.id = :parIdAzienda) AND (a.statoVisibilita = :parState)", Annuncio.class)
				.setParameter("parIdAzienda", idAzienda)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciByCitta_Azienda(Integer idAzienda, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.azienda.id = :parIdAzienda) AND (a.statoVisibilita = :parState) AND "
				+ "(a.citta = :parCitta)", Annuncio.class)
				.setParameter("parIdAzienda", idAzienda)
				.setParameter("parCitta", citta)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}
	
	public List<Annuncio> retrieveAnnunciByProfessione_Azienda(Integer idAzienda, String professione) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.azienda.id = :parIdAzienda) AND (a.statoVisibilita = :parState) AND "
				+ "(a.figProfessionale = :parProf)", Annuncio.class)
				.setParameter("parIdAzienda", idAzienda)
				.setParameter("parProf", professione)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}

	public List<Annuncio> retrieveAnnunciByProfessioneCitta_Azienda(Integer idAzienda, String professione, String citta) {
		return manager.createQuery("SELECT a FROM Annuncio a WHERE (a.azienda.id = :parIdAzienda) AND (a.statoVisibilita = :parState) AND "
				+ "(a.figProfessionale = :parProf) AND (a.citta = :parCitta)", Annuncio.class)
				.setParameter("parIdAzienda", idAzienda)
				.setParameter("parProf", professione)
				.setParameter("parCitta", citta)
				.setParameter("parState", StatoVisibilita.PUBBLICATO)
				.getResultList();
	}
	
}
