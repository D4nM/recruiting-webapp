package com.aziendaRecruiting.utils.annuncio;

public enum LuogoDiLavoro {
	
	IN_PRESENZA("in_presenza"),
	IBRIDO("ibrido"),
	DA_CASA("da_casa");
	
	public final String label;
	
	private LuogoDiLavoro(String label) {
		this.label = label;
	}
	
	@Override
    public String toString(){
        return label;
    }
	
	// metodo per passare dalla stringa dell'etichetta alla costante dell'enum
	public static LuogoDiLavoro valueOfLabel(String labelOfConstant) {
		
		if(labelOfConstant == null) {
			return null;
		}
		
		for(LuogoDiLavoro l : values()) {
			if(l.label.equals(labelOfConstant)) {
				return l;
			}
		}
		return null;
	}
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
}
