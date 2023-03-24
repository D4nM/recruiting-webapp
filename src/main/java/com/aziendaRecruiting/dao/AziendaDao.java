package com.aziendaRecruiting.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.aziendaRecruiting.model.Azienda;

public class AziendaDao implements RecruitingDaoInterface<Azienda> {

	private EntityManager manager;
	
	public AziendaDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Azienda create(Azienda obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public List<Azienda> retrieve() {
		return manager.createQuery("SELECT a FROM Azienda a", Azienda.class).getResultList();
	}

	@Override
	public Azienda update(Azienda obj) {
		manager.persist(obj);
		return obj;
	}

	@Override
	public void delete(Azienda obj) {
		manager.remove(obj);
		
	}
	
	
	
	public List<String> findMatchingEmail(String emailToCheck) {
		return manager.createQuery("SELECT a.email FROM Azienda a WHERE a.email = :par", String.class)
				.setParameter("par", emailToCheck).getResultList();
	}
	
	public List<String> findMatchingPass(String email) {
		return manager.createQuery("SELECT a.password FROM Azienda a WHERE a.email = :par", String.class)
				.setParameter("par", email).getResultList();
	}
	
	public List<Azienda> retrieveAzienda(String email, String password){
		return manager.createQuery("SELECT a FROM Azienda a WHERE a.email = :parEmail AND a.password = :parPassword", Azienda.class)
				.setParameter("parEmail", email).setParameter("parPassword", password).getResultList();
	}
	
}
