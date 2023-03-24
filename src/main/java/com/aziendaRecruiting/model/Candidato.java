package com.aziendaRecruiting.model;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.aziendaRecruiting.utils.annuncio.DateFormatterCustom;

@Entity
public class Candidato extends Utente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String cognome;
	private String recapitoTelefonico;
	private LocalDate dataNascita;
	private String comuneResidenza;
	
	private Blob cvFile;
	private String cvFileName;
	
	@OneToMany(mappedBy = "candidato")
	private List<AnnuncioCandidato> listaCandidature = new ArrayList<AnnuncioCandidato>();
	
	public Candidato() {
		super();
	}

	public Candidato(String email, String password, String nome, 
							String cognome, String recapitoTelefonico, LocalDate dataNascita) {
		super(email, password);
		this.nome = nome;
		this.cognome = cognome;
		this.recapitoTelefonico = recapitoTelefonico;
		this.dataNascita = dataNascita;
	}
	
	public Candidato(String email, String password, String nome, 
			String cognome, String recapitoTelefonico, LocalDate dataNascita,
			String comuneResidenza) {
		super(email, password);
		this.nome = nome;
		this.cognome = cognome;
		this.recapitoTelefonico = recapitoTelefonico;
		this.dataNascita = dataNascita;
		this.comuneResidenza = comuneResidenza;
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
	public String getRecapitoTelefonico() {
		return recapitoTelefonico;
	}
	public void setRecapitoTelefonico(String recapitoTelefonico) {
		this.recapitoTelefonico = recapitoTelefonico;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getComuneResidenza() {
		return comuneResidenza;
	}
	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	public List<AnnuncioCandidato> getListaCandidature() {
		return listaCandidature;
	}
	public void setListaCandidature(List<AnnuncioCandidato> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}
	
	
	public Blob getCvFile() {
		return cvFile;
	}

	public void setCvFile(Blob cvFile) {
		this.cvFile = cvFile;
	}

	public String getCvFileName() {
		return cvFileName;
	}

	public void setCvFileName(String cvFileName) {
		this.cvFileName = cvFileName;
	}

	
	@Override
	public String toString() {
		return super.toString() + "\n"
				+ "Candidato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", recapitoTelefonico="
				+ recapitoTelefonico + ", dataNascita=" + dataNascita + ", comuneResidenza=" + comuneResidenza 
				+ ", cvFileName="+ cvFileName + "]";
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
		Candidato other = (Candidato) obj;
		return Objects.equals(id, other.id);
	}
	
	
	public String getFormattedDataNascita() {
		if(dataNascita != null) {
			return DateFormatterCustom.toItalianFormat(dataNascita);
		}
		System.out.println("<!> In getFormattedDataNascita(): dataNascita is null\n");
		return "N.D.";
	}
	
	
}
