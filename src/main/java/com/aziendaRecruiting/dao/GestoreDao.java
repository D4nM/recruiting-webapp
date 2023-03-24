package com.aziendaRecruiting.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.aziendaRecruiting.model.Gestore;

public class GestoreDao implements RecruitingDaoInterface<Gestore> {

	private EntityManager manager;
	
	public GestoreDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Gestore create(Gestore obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public List<Gestore> retrieve() {
		return manager.createQuery("SELECT g FROM Gestore g", Gestore.class).getResultList();
	}

	@Override
	public Gestore update(Gestore obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public void delete(Gestore obj) {
		manager.remove(obj);
		
	}
	
	
	
//	// TODO magari renderla generica e farle ritornare un campo passato in argomento
//	public List<String> retrieveEmailList() {
//		return manager.createQuery("SELECT g.email FROM Gestore g", String.class).getResultList();
//	}
//
//	@Override
//	public List<String> retrieveField(String fieldName) {
//		return manager.createQuery("SELECT g.:parameter FROM Gestore g", String.class)
//				.setParameter("parameter", fieldName).getResultList();
//	}
	
	public List<String> findMatchingEmail(String emailToCheck) {
		return manager.createQuery("SELECT g.email FROM Gestore g WHERE g.email = :par", String.class)
				.setParameter("par", emailToCheck).getResultList();
	}
	
	public List<String> findMatchingPass(String email) {
		return manager.createQuery("SELECT g.password FROM Gestore g WHERE g.email = :par", String.class)
				.setParameter("par", email).getResultList();
	}
	
	public List<Gestore> retrieveGestore(String email, String password){
		return manager.createQuery("SELECT g FROM Gestore g WHERE g.email = :parEmail AND g.password = :parPassword", Gestore.class)
				.setParameter("parEmail", email).setParameter("parPassword", password).getResultList();
	}
	
	
	
	
}
