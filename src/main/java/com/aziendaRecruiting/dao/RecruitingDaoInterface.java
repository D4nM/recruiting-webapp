package com.aziendaRecruiting.dao;

import java.util.List;

public interface RecruitingDaoInterface<T> {

	public T create(T obj);
	public List<T> retrieve();
	public T update(T obj);
	public void delete(T obj);
	
//	public List<String> retrieveField(String fieldName); // se inutilizzato, eliminare
	
}
