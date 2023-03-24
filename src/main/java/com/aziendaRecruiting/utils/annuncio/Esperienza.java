package com.aziendaRecruiting.utils.annuncio;

public enum Esperienza {

	JUNIOR("junior"),
	MIDDLE("middle"),
	SENIOR("senior"),
	MENO_DI_UN_ANNO("<1_anno"),
	UN_ANNO("1_anno"),
	DUE_ANNI("2_anni"),
	TRE_ANNI("3_anni"),
	QUATTRO_ANNI("4_anni"),
	CINQUE_ANNI("5_anni"),
	SOPRA_CINQUE_ANNI(">5_anni");
	
	public final String label;
	
	private Esperienza(String label) {
		this.label = label;
	}
	
	@Override
    public String toString(){
        return label;
    }
	
	// metodo per passare dalla stringa dell'etichetta alla costante dell'enum
	public static Esperienza valueOfLabel(String labelOfConstant) {
		
		if(labelOfConstant == null) {
			return null;
		}
		
		for(Esperienza e : values()) {
			if(e.label.equals(labelOfConstant)) {
				return e;
			}
		}
		return null;
	}
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
	
}
