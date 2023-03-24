package com.aziendaRecruiting.utils.candidato;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;

import org.hibernate.engine.jdbc.BlobProxy;

public class BlobConverter {

	public static Blob generateBlob(String filePath) throws Exception {
		
		/*
		 * InputStream: classe madre dei flussi di dati in input.
		 * OutputStream: classe madre dei flussi di dati in output.
		 * 
		 * - Quasi tutte le operazioni I/O sollevano eccezioni checked.
		 */
		InputStream is = new FileInputStream(filePath); // "classica" apertura di un file in lettura
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // ByteArrayOutputStream è l'ideale per copiare byte
		
		byte [] buffer = new byte[1024]; // variabile d'appoggio; buffer su cui caricare man mano "blocchi" di dati
		
		int read = -1; // variabile d'appoggio: rappresenterà effettivamente il N° di byte che si riescono a leggere
		
		/*
		 * read() : metodo fondamentale per leggere i dati;
		 * 		restituisce il N° di byte letti e caricati nel buffer specificato;
		 * 		restituisce -1 se non ne legge nessuno
		 */
		while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
			baos.write(buffer, 0, read);
		}
		baos.flush(); // svuota il buffer di scrittura interno di Java
		
		byte [] data = baos.toByteArray();
		
		Blob result = BlobProxy.generateProxy(data); // generazione del Blob
		
		baos.close(); // chiusura dello stream
		is.close(); // chiusura del file
		
		return result;
	}
	
	
	public static void saveFile(Blob blob, String filePath) throws Exception {
		
		InputStream is = blob.getBinaryStream(); // lettura del Blob
    	
		FileOutputStream fos = new FileOutputStream(filePath); // creazione di uno stream (cioè un file) in scrittura
		
		// segue una copia analoga delle istruzioni viste nel metodo sopra
		byte [] buffer = new byte[1024];
		
		int read = -1;
		
		while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
			fos.write(buffer, 0, read);
		}
		
		fos.flush();
		
		fos.close();
	}
	
}
