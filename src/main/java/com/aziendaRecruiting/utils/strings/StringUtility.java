package com.aziendaRecruiting.utils.strings;

public class StringUtility {

	// functions for checking "validity" of strings
	
	public static Boolean isStringValid(String str) {
		if(str == null || str.isEmpty() || str.isBlank()) {
			return false;
		}		
		return true;
	}
	
	public static String checkString(String str) {
		if( ! isStringValid(str)) {
			return null;
		}
		return str;
	}
	
	public static String printShortVersion(String text, Integer numOfChars) {
		
		if(StringUtility.isStringValid(text)) {
			
			text = text.trim();
			
			if(text.length() <= numOfChars) {
				return text;
			}
			return text.substring(0, numOfChars) + "...";
		}
		return null;
	}
	
	public static String returnNotSpecifiedIfSo(String str) {
		
		if(StringUtility.isStringValid(str)) {
			str = str.trim();
			return str;
		}
		return "NON SPECIFICATO";
	}
}
