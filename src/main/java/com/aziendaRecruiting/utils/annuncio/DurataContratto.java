package com.aziendaRecruiting.utils.annuncio;

public enum DurataContratto {

	STAGE("stage"),
	APPRENDISTATO("apprendistato"),
	TEMPO_DETERMINATO("tempo_determinato"),
	TEMPO_INDETERMINATO("tempo_indeterminato");
	
	public final String label;
	
	private DurataContratto(String label) {
		this.label = label;
	}
	
	@Override
    public String toString(){
        return label;
    }
	
	// metodo per passare dalla stringa dell'etichetta alla costante dell'enum
	public static DurataContratto valueOfLabel(String labelOfConstant) {
		
		if(labelOfConstant == null) {
			return null;
		}
		
		for(DurataContratto dc : values()) {
			if(dc.label.equals(labelOfConstant)) {
				return dc;
			}
		}
		return null;
	}
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
	
}
