package com.aziendaRecruiting.utils.annuncio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatterCustom {

	public static DateTimeFormatter DTF_IT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static String toItalianFormat(LocalDate dateToFormat) {
		return dateToFormat.format(DTF_IT);
	}
	
}
