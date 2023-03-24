package com.aziendaRecruiting.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.aziendaRecruiting.model.AnnuncioCandidato;

public class AnnuncioCandidatoDao implements RecruitingDaoInterface<AnnuncioCandidato>{

	private EntityManager manager;

	public AnnuncioCandidatoDao(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	
	@Override
	public AnnuncioCandidato create(AnnuncioCandidato obj) {
		manager.persist(obj);
		return obj; // TODO se non serve, eliminare la restituzione dell'oggetto stesso
	}

	@Override
	public List<AnnuncioCandidato> retrieve() {
		return manager.createQuery("SELECT a FROM AnnuncioCandidato a", AnnuncioCandidato.class).getResultList();
	}

	@Override
	public AnnuncioCandidato update(AnnuncioCandidato obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public void delete(AnnuncioCandidato obj) {
		manager.remove(obj);
		
	}
	
	// usato in caso di Gestore
	public List<AnnuncioCandidato> retrieveCandidatureFromAnnuncio(Integer idAnnuncio) {
		return manager.createQuery("SELECT ac FROM AnnuncioCandidato ac WHERE ac.annuncio.id = :parIdAnnuncio", AnnuncioCandidato.class)
				.setParameter("parIdAnnuncio", idAnnuncio)
				.getResultList();
	}
	
	public List<AnnuncioCandidato> retrieveCandidatureOfCandidato(Integer idCandidato) {
		return manager.createQuery("SELECT ac FROM AnnuncioCandidato ac WHERE ac.candidato.id = :parIdCandidato", AnnuncioCandidato.class)
				.setParameter("parIdCandidato", idCandidato)
				.getResultList();
	}
	
	// usato in caso di Candidato
	public List<AnnuncioCandidato> retrieveCandidaturaOfCandidatoToAnnuncio(Integer idCandidato, Integer idAnnuncio) {
		return manager.createQuery("SELECT ac FROM AnnuncioCandidato ac WHERE "
				+ "ac.candidato.id = :parIdCandidato AND ac.annuncio.id = :parIdAnnuncio", AnnuncioCandidato.class)
				.setParameter("parIdCandidato", idCandidato)
				.setParameter("parIdAnnuncio", idAnnuncio)
				.getResultList();
	}
	
	// usato in caso di Azienda
	public List<AnnuncioCandidato> retrieveCandidatureFromAnnuncioByGestoreApproval(Integer idAnnuncio, Boolean approvalGestore) {
		return manager.createQuery("SELECT ac FROM AnnuncioCandidato ac WHERE "
				+ "ac.annuncio.id = :parIdAnnuncio AND ac.isApprovatoGestore = :parApprovalGestore", AnnuncioCandidato.class)
				.setParameter("parIdAnnuncio", idAnnuncio)
				.setParameter("parApprovalGestore", approvalGestore)
				.getResultList();
	}
	
}
