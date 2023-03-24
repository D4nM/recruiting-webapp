package com.aziendaRecruiting.utils.annuncio;

public enum TitoloDiStudio {
	
	TERZA_MEDIA("diploma_di_terza_media"),
	DIPLOMA("diploma_di_scuola_superiore"),
	LAUREA_TRIENNALE("laurea_triennale"),
	LAUREA_MAGISTRALE("laurea_magistrale"),
	DOTTORATO("dottorato_di_ricerca");
	
	public final String label;
	
    private TitoloDiStudio (String label) {
        this.label = label;
    }
    
    @Override
    public String toString(){
        return label;
    }
    
    public static TitoloDiStudio valueOfLabel(String labelOfConstant) {
    	
    	if(labelOfConstant == null) {
			return null;
		}
    	
    	for (TitoloDiStudio ts : TitoloDiStudio.values()) {
    		if (ts.label.equals(labelOfConstant)) {
    			return ts;
    		}
    	}
    	return null;
    }
    
    public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
	
}
