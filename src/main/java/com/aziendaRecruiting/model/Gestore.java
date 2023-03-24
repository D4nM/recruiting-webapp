package com.aziendaRecruiting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Gestore extends Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy = "gestore")
	private List<Annuncio> annunci = new ArrayList<Annuncio>();
	
	// empty constructor used by Hibernate
	public Gestore() {
		super();
	}

	public Gestore(String email, String password, String nome, String cognome) {
		super(email, password);
		this.nome = nome;
		this.cognome = cognome;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Annuncio> getAnnunci() {
		return annunci;
	}
	public void setAnnunci(List<Annuncio> annunci) {
		this.annunci = annunci;
	}

	@Override
	public String toString() {
		return super.toString() + "\n"
				+ "Gestore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + "]";
	}

	// la classe padre ha bisogno di qualcosa relativo ad equals() per farlo funzionare bene??? TODO
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gestore other = (Gestore) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
