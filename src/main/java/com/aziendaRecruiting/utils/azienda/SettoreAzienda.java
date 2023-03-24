package com.aziendaRecruiting.utils.azienda;

//info sugli enum: https://www.baeldung.com/java-enum-values

// https://www.tuttoalternanza.it/guida-ai-settori-lavorativi/
public enum SettoreAzienda {
	AGRICOLTURA("agricoltura"),
	ARTE("arte"),
	CHIMICA("chimica"),
	COMMERCIALE("commerciale"),
	COMUNICAZIONE("comunicazione"),
	EDILIZIA("edilizia"),
	FINANZA("finanza"),
	GRAFICA("grafica"),
	INFORMATICA("informatica"),
	METALMECCANICA("metalmeccanica"),
	PUBBLICITA("pubblicita"),
	RISTORAZIONE("ristorazione"),
	SANITA("sanita"),
	SPETTACOLO("spettacolo"),
	SPORT("sport"),
	TELECOMUNICAZIONI("telecomunicazioni"),
	TRASPORTI("trasporti"),
	TURISMO("turismo");
	
	public final String label;
	
	private SettoreAzienda(String label) {
		this.label = label;
	}
	
	@Override
    public String toString(){
        return label;
    }
	
	// metodo per passare dalla stringa dell'etichetta alla costante dell'enum
	public static SettoreAzienda valueOfLabel(String labelOfConstant) {
		
		if(labelOfConstant == null) {
			return null;
		}
		
		for(SettoreAzienda s : values()) {
			if(s.label.equals(labelOfConstant)) {
				return s;
			}
		}
		return null;
	}
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("ita", "ità").replace("_", " ");
	}
}


