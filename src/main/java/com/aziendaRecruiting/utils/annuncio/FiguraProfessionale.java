package com.aziendaRecruiting.utils.annuncio;

public enum FiguraProfessionale {

	SVILUPPATORE("sviluppatore"),
	MANAGER("manager"),
	RISORSE_UMANE("risorse_umane"),
	CUOCO("cuoco"),
	INSEGNANTE("insegnante"),
	OPERARIO("operaio"),
	IMPIEGATO("impiegato");
	
	public final String label;
	
	private FiguraProfessionale(String label) {
		this.label = label;
	}
	
	@Override
    public String toString(){
        return label;
    }
	
	// metodo per passare dalla stringa dell'etichetta alla costante dell'enum
	public static FiguraProfessionale valueOfLabel(String labelOfConstant) {
		
		if(labelOfConstant == null) {
			return null;
		}
		
		for(FiguraProfessionale fp : values()) {
			if(fp.label.equals(labelOfConstant)) {
				return fp;
			}
		}
		return null;
	}
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
}
