package com.aziendaRecruiting.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.aziendaRecruiting.utils.annuncio.DateFormatterCustom;
import com.aziendaRecruiting.utils.annuncio.DurataContratto;
import com.aziendaRecruiting.utils.annuncio.Esperienza;
import com.aziendaRecruiting.utils.annuncio.LuogoDiLavoro;
import com.aziendaRecruiting.utils.annuncio.StatoVisibilita;
import com.aziendaRecruiting.utils.annuncio.TempoLavorativo;
import com.aziendaRecruiting.utils.annuncio.TitoloDiStudio;

@Entity
public class Annuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titolo;
	
	@Enumerated(EnumType.STRING)
	private StatoVisibilita statoVisibilita = StatoVisibilita.CREATO;
	
	private LocalDate dataCreazione = LocalDate.now(); // Come impostare timezone di Roma???
	private LocalDate dataPubblicazione;
	private LocalDate dataScadenza = dataCreazione.plusDays(180); // impostazione automatica della data di scadenza
	
	private String citta;
	
	@Enumerated(EnumType.STRING)
	private LuogoDiLavoro luogoDiLavoro;
	
	private Integer numPostiOfferti; // posti iniziali
	private Integer numPostiDisponibili; // posti rimanenti
	
	private Float ral;
	
	@Lob
	//@Column(length = 5000)
	@Column(columnDefinition="TEXT")
	private String testo;
	
	private String figProfessionale;
	
	@Enumerated(EnumType.STRING)
	private Esperienza esperienza;
	@Enumerated(EnumType.STRING)
	private DurataContratto durataContratto;
	@Enumerated(EnumType.STRING)
	private TempoLavorativo tempoLavorativo;
	@Enumerated(EnumType.STRING)
	private TitoloDiStudio titoloStudio;
	
	@ManyToOne
	@JoinColumn(name = "id_gestore")
	private Gestore gestore;
	
	@ManyToOne
	@JoinColumn(name = "id_azienda")
	private Azienda azienda;
	
	@OneToMany(mappedBy = "annuncio")
	private List<AnnuncioCandidato> listaCandidati = new ArrayList<AnnuncioCandidato>();
	
	
	public Annuncio() {
		super();
	}
	
	public Annuncio(String titolo, LocalDate dataCreazione, String citta, Float ral) {
		super();
		this.titolo = titolo;
		this.dataCreazione = dataCreazione;
		this.citta = citta;
		this.ral = ral;
	}
	
	public Annuncio(String titolo, LuogoDiLavoro luogoDiLavoro, Integer numPostiOfferti, String testo,
			String figProfessionale, Esperienza esperienza) {
		super();
		this.titolo = titolo;
		this.luogoDiLavoro = luogoDiLavoro;
		this.numPostiOfferti = numPostiOfferti;
		this.testo = testo;
		this.figProfessionale = figProfessionale;
		this.esperienza = esperienza;
		
		this.numPostiDisponibili = numPostiOfferti; // aggiunta
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public StatoVisibilita getStatoVisibilita() {
		return statoVisibilita;
	}
	public void setStatoVisibilita(StatoVisibilita statoVisibilita) {
		this.statoVisibilita = statoVisibilita;
	}
	public LocalDate getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public LocalDate getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public LuogoDiLavoro getLuogoDiLavoro() {
		return luogoDiLavoro;
	}
	public void setLuogoDiLavoro(LuogoDiLavoro luogoDiLavoro) {
		this.luogoDiLavoro = luogoDiLavoro;
	}
	public Integer getNumPostiOfferti() {
		return numPostiOfferti;
	}
	public void setNumPostiOfferti(Integer numPostiOfferti) {
		this.numPostiOfferti = numPostiOfferti;
	}
	public Integer getNumPostiDisponibili() {
		return numPostiDisponibili;
	}
	public void setNumPostiDisponibili(Integer numPostiDisponibili) {
		this.numPostiDisponibili = numPostiDisponibili;
	}
	public Float getRal() {
		return ral;
	}
	public void setRal(Float ral) {
		this.ral = ral;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getFigProfessionale() {
		return figProfessionale;
	}
	public void setFigProfessionale(String figProfessionale) {
		this.figProfessionale = figProfessionale;
	}
	public Esperienza getEsperienza() {
		return esperienza;
	}
	public void setEsperienza(Esperienza esperienza) {
		this.esperienza = esperienza;
	}
	public DurataContratto getDurataContratto() {
		return durataContratto;
	}
	public void setDurataContratto(DurataContratto durataContratto) {
		this.durataContratto = durataContratto;
	}
	public TempoLavorativo getTempoLavorativo() {
		return tempoLavorativo;
	}
	public void setTempoLavorativo(TempoLavorativo tempoLavorativo) {
		this.tempoLavorativo = tempoLavorativo;
	}
	public TitoloDiStudio getTitoloStudio() {
		return titoloStudio;
	}
	public void setTitoloStudio(TitoloDiStudio titoloStudio) {
		this.titoloStudio = titoloStudio;
	}
	public Gestore getGestore() {
		return gestore;
	}
	public void setGestore(Gestore gestore) {
		this.gestore = gestore;
	}
	public Azienda getAzienda() {
		return azienda;
	}
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	
	public List<AnnuncioCandidato> getListaCandidati() {
		return listaCandidati;
	}
	public void setListaCandidati(List<AnnuncioCandidato> listaCandidati) {
		this.listaCandidati = listaCandidati;
	}


	@Override
	public String toString() {
		return "Annuncio [id=" + id + ", titolo=" + titolo + ", statoVisibilita=" + statoVisibilita + ", dataCreazione=" + dataCreazione
				+ ", dataPubblicazione=" + dataPubblicazione + ", dataScadenza=" + dataScadenza + ", citta=" + citta
				+ ", luogoDiLavoro=" + luogoDiLavoro + ", numPostiOfferti=" + numPostiOfferti + ", numPostiDisponibili="
				+ numPostiDisponibili + ", ral=" + ral + ", testo=" + testo + ", figProfessionale=" + figProfessionale
				+ ", esperienza=" + esperienza + ", durataContratto=" + durataContratto + ", tempoLavorativo="
				+ tempoLavorativo + ", titoloStudio=" + titoloStudio + "]";
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
		Annuncio other = (Annuncio) obj;
		return Objects.equals(id, other.id);
	}
	
	
	public String getFormattedDataPubblicazione() {
		if(dataPubblicazione != null) {
			return DateFormatterCustom.toItalianFormat(dataPubblicazione);
		}
		return "NON ANCORA PUBBLICATO";
	}
	
	public String getFormattedDataScadenza() {
		if(dataPubblicazione != null) {
			return DateFormatterCustom.toItalianFormat(dataScadenza);
		}
		return "NON ANCORA PUBBLICATO";
	}
	

	
}
