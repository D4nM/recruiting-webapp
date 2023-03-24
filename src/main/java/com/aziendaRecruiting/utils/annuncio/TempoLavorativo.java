package com.aziendaRecruiting.utils.annuncio;

public enum TempoLavorativo {

	FULLTIME("full-time"),
	PARTTIME("part-time"),
	A_CHIAMATA("a_chiamata");
	
	public final String label;
	
	private TempoLavorativo (String label) {
        this.label = label;
    }
	
	@Override
    public String toString(){
        return label;
    }
	
	public static TempoLavorativo valueOfLabel(String labelOfConstant) {
    	
    	if(labelOfConstant == null) {
			return null;
		}
    	
    	for (TempoLavorativo tl : TempoLavorativo.values()) {
    		if (tl.label.equals(labelOfConstant)) {
    			return tl;
    		}
    	}
    	return null;
    }
	
	public String printPrettyLabel() {
		return (label.substring(0,1).toUpperCase() + label.substring(1)).replace("_", " ");
	}
	
}
