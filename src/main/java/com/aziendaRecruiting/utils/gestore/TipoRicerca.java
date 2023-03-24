package com.aziendaRecruiting.utils.gestore;

public enum TipoRicerca {

	TUTTI("tutti"),
	GESTITI("gestiti_da_me"),
	NON_ANCORA_GESTITI("non_ancora_gestiti"),
	GESTITI_DA_ALTRI("gestiti_da_altri");
	
	public final String label;
	
	private TipoRicerca(String label) {
		this.label = label;
	}
	
	@Override
    public String toString(){
        return label;
    }
	
	// metodo per passare dalla stringa dell'etichetta alla costante dell'enum
	public static TipoRicerca valueOfLabel(String labelOfConstant) {
		
		if(labelOfConstant == null) {
			return null;
		}
		
		for(TipoRicerca tr : values()) {
			if(tr.label.equals(labelOfConstant)) {
				return tr;
			}
		}
		return null;
	}
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
}
