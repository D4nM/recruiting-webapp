package com.aziendaRecruiting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.aziendaRecruiting.utils.azienda.SettoreAzienda;

@Entity
public class Azienda extends Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String ragioneSociale;
	private String recapitoTelefonico;
	private String sedeLegale;
	
	@Enumerated(EnumType.STRING)
	private SettoreAzienda settore;
	
	@OneToMany(mappedBy = "azienda")
	private List<Annuncio> annunci = new ArrayList<Annuncio>();
	
	public Azienda() {
		super();
	}

	public Azienda(String email, String password, 
					String ragioneSociale, String recapitoTelefonico, 
					String sedeLegale, SettoreAzienda settore) {
		super(email, password);
		this.ragioneSociale = ragioneSociale;
		this.recapitoTelefonico = recapitoTelefonico;
		this.sedeLegale = sedeLegale;
		this.settore = settore;
	}

	public Azienda(String email, String password, String ragioneSociale, String sedeLegale) {
		super(email, password);
		this.ragioneSociale = ragioneSociale;
		this.sedeLegale = sedeLegale;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getRecapitoTelefonico() {
		return recapitoTelefonico;
	}
	public void setRecapitoTelefonico(String recapitoTelefonico) {
		this.recapitoTelefonico = recapitoTelefonico;
	}
	public String getSedeLegale() {
		return sedeLegale;
	}
	public void setSedeLegale(String sedeLegale) {
		this.sedeLegale = sedeLegale;
	}
	public SettoreAzienda getSettore() {
		return settore;
	}
	public void setSettore(SettoreAzienda settore) {
		this.settore = settore;
	}

	
	@Override
	public String toString() {
		return super.toString() + "\n"
				+ "Azienda [id=" + id + ", ragioneSociale=" + ragioneSociale
				+ ", recapitoTelefonico=" + recapitoTelefonico
				+ ", sedeLegale=" + sedeLegale + ", settore=" + settore + "]";
	}

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
		Azienda other = (Azienda) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
