package com.aziendaRecruiting.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.aziendaRecruiting.model.Candidato;

public class CandidatoDao implements RecruitingDaoInterface<Candidato> {

	private EntityManager manager;
	
	public CandidatoDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Candidato create(Candidato obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public List<Candidato> retrieve() {
		return manager.createQuery("SELECT c FROM Candidato c", Candidato.class).getResultList();
	}

	@Override
	public Candidato update(Candidato obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public void delete(Candidato obj) {
		manager.remove(obj);
		
	}

	
	
	public List<String> findMatchingEmail(String emailToCheck) {
		return manager.createQuery("SELECT c.email FROM Candidato c WHERE c.email = :par", String.class)
				.setParameter("par", emailToCheck).getResultList();
	}
	
	public List<String> findMatchingPass(String email) {
		return manager.createQuery("SELECT c.password FROM Candidato c WHERE c.email = :par", String.class)
				.setParameter("par", email).getResultList();
	}
	
	public List<Candidato> retrieveCandidato(String email, String password){
		return manager.createQuery("SELECT c FROM Candidato c WHERE c.email = :parEmail AND c.password = :parPassword", Candidato.class)
				.setParameter("parEmail", email).setParameter("parPassword", password).getResultList();
	}
	
	
//	public Candidato retrieveCandidato(Integer idCandidato) {
//		return manager.createQuery("SELECT c FROM Candidato c WHERE c.id = :parIdCandidato", Candidato.class)
//				.setParameter("parIdCandidato", idCandidato)
//				.getSingleResult();
//	}
	
	public List<Candidato> retrieveCandidatoById(Integer idCandidato) {
		return manager.createQuery("SELECT c FROM Candidato c WHERE c.id = :parIdCandidato", Candidato.class)
				.setParameter("parIdCandidato", idCandidato)
				.getResultList();
	}
	
	public List<Candidato> retrieveCandidatiFromAnnuncio(Integer idAnnuncio) {
		return manager.createQuery("SELECT c FROM "
				+ "(Annuncio an INNER JOIN AnnuncioCandidato ac ON ac.annuncio.id = an.id) "
				+ "INNER JOIN Candidato c ON ac.candidato.id = c.id", Candidato.class)
				.setParameter("parIdAnnuncio", idAnnuncio)
				.getResultList();
	}
	
	public List<Candidato> retrieveCandidatiFromAnnuncioByGestoreApproval(Integer idAnnuncio, Boolean approvalGestore) {
		return manager.createQuery("SELECT c FROM "
				+ "(Annuncio an INNER JOIN AnnuncioCandidato ac ON ac.annuncio.id = an.id) "
				+ "INNER JOIN Candidato c ON ac.candidato.id = c.id"
				+ "WHERE ac.isApprovatoGestore = :parApproval", Candidato.class)
				.setParameter("parIdAnnuncio", idAnnuncio)
				.setParameter("parApproval", approvalGestore)
				.getResultList();
	}
	
	
	/*
	 * SELECT c FROM (Annuncio an INNER JOIN AnnuncioCandidato ac ON ac.annuncio.id = an.id)
	 * 		INNER JOIN Candidato c ON ac.candidato.id = c.id
	 * 
	 * Query in MySQL:
	 * SELECT Candidato.*, Annuncio.id FROM (Annuncio INNER JOIN AnnuncioCandidato ON AnnuncioCandidato.id_annuncio = Annuncio.id)
			INNER JOIN Candidato ON AnnuncioCandidato.id_candidato = Candidato.id;
	 */
}
