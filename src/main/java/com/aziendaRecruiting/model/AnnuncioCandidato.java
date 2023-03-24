package com.aziendaRecruiting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AnnuncioCandidato {
	
	// TODO Id in this case is superfluous, isn't it? -----------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_candidato")
	private Candidato candidato;
	
	@ManyToOne
	@JoinColumn(name = "id_annuncio")
	private Annuncio annuncio;
	
	private Boolean isApprovatoGestore = null;
	private Boolean isApprovatoAzienda = null;
	
	
	public AnnuncioCandidato() {
		super();
	}

	public AnnuncioCandidato(Candidato candidato, Annuncio annuncio) {
		super();
		this.candidato = candidato;
		this.annuncio = annuncio;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getIsApprovatoGestore() {
		return isApprovatoGestore;
	}
	public void setIsApprovatoGestore(Boolean isApprovatoGestore) {
		this.isApprovatoGestore = isApprovatoGestore;
	}
	public Boolean getIsApprovatoAzienda() {
		return isApprovatoAzienda;
	}
	public void setIsApprovatoAzienda(Boolean isApprovatoAzienda) {
		this.isApprovatoAzienda = isApprovatoAzienda;
	}
	public Candidato getCandidato() {
		return candidato;
	}
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	public Annuncio getAnnuncio() {
		return annuncio;
	}
	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}

	
	@Override
	public String toString() {
		return "AnnuncioCandidato [id=" + id + ", candidato=" + candidato + ", annuncio=" + annuncio
				+ ", isApprovatoGestore=" + isApprovatoGestore + ", isApprovatoAzienda=" + isApprovatoAzienda + "]";
	}
	
	
	
	
	
}
