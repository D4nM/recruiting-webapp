package com.aziendaRecruiting.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Utente {

	private String email;
	private String password;
	
	public Utente() {
		super();
	}

	public Utente(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String toString() {
		return "Utente [email=" + email + ", password=" + password + "]";
	}	
	
}
