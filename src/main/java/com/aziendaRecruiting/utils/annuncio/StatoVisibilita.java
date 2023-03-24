package com.aziendaRecruiting.utils.annuncio;

public enum StatoVisibilita {
	
	CREATO("creato"),
	PUBBLICATO("pubblicato"),
	CHIUSO("chiuso");
	
	public final String label;
	
	private StatoVisibilita(String label) {
		this.label = label;
	}
	
	@Override
    public String toString(){
        return label;
    }
	
	// metodo per passare dalla stringa dell'etichetta alla costante dell'enum
	public static StatoVisibilita valueOfLabel(String labelOfConstant) {
		
		if(labelOfConstant == null) {
			return null;
		}
		
		for(StatoVisibilita s : values()) {
			if(s.label.equals(labelOfConstant)) {
				return s;
			}
		}
		return null;
	}
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
	
}
