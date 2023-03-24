package com.aziendaRecruiting.ui.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.utils.candidato.BlobConverter;
import com.aziendaRecruiting.utils.exceptions.InputFieldException;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/SignupCandidatoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // interessa al web server: oltre alla soglia specificata si useranno file d'appoggio
					maxFileSize = 1024 * 1024 * 10, // grandezza massima del file caricabile dall'utente
					maxRequestSize = 1024 * 1024 * 10 * 5) // grandezza massima DELLA REQUEST
public class SignupCandidatoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			
			/*
			 *  TODO IL SEGUENTE SAREBBE DA METTERE TUTTO IN BL
			 * 
			 */
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			String email = req.getParameter("email-signup");
			String password = req.getParameter("password-signup");
			String nome = req.getParameter("nome");
			String cognome = req.getParameter("cognome");
			String telefono = req.getParameter("telefono");
			LocalDate dataNascita = LocalDate.parse(req.getParameter("data-di-nascita"), dtf);
			String comuneResidenza = req.getParameter("comune-residenza");
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
						
			if(bLogic.isEmailCandidatoRegistered(email) || bLogic.isEmailAziendaRegistered(email)) {
				// se è già registrato
				req.setAttribute(CostantiServlet.ERROR_MSG, "Utente con questa e-mail già registrato");
				req.getRequestDispatcher("jsp/Signup.jsp").forward(req, resp);
			}
			else {
				// se non è registrato
				
				Candidato candidato = new Candidato(email, password, nome, cognome, telefono, dataNascita);
				
				// 1. crea oggetto java
				// 2. passa l'oggetto alla BL, dove si faranno i controlli sui dati inseriti
				// 3. è la BL che solleva eccezioni che reindirizzano altrove
				
				candidato.setComuneResidenza(comuneResidenza);
				
				
				
				// alla servlet compete la gestione dell'upload del file CV
				
				
				/*
				 * getRealPath
				 * getRealPath(String str) : passare come argomento la stringa vuota farà restituire la radice della web app
				 * 
				 * Al posto di uploadDir si mette il nome della cartella per i file che verrà creata sul server
				 */
				String uploadPath = getServletContext().getRealPath("") + File.separator + "cv_candidati"; // definizione del percorso di salvataggio dei file
				
				File uploadDir = new File(uploadPath);
				
				if (!uploadDir.exists()) { // controlla che la directory non esista già
					uploadDir.mkdir(); // crea la directory
				}
				
				String filePath = null;
				// ciclo che scorre le 'parti' inviate con la form
				for ( Part part : req.getParts() ) {
					
					String fileName = part.getSubmittedFileName(); // restituisce null se il parametro analizzato non è il file in input
					
					if ( fileName != null && !fileName.isEmpty() ) {
						// file trovato
						
						filePath = uploadPath + File.separator + fileName; 
						part.write(filePath); // scrittura del file sulla directory nel server
						
						Blob blob = BlobConverter.generateBlob(filePath);
						
						candidato.setCvFile(blob);
						candidato.setCvFileName(fileName);
						
					}
				}
				
				
				
				
				
				bLogic.create(candidato);
				
				
				System.out.println("<OK> Candidato registrato sul DB:");
				System.out.println(candidato);
				System.out.println();
				
				req.getSession().setAttribute(CostantiServlet.LOGGED_USER, candidato); // TODO controllare che per caso non ci sia già un utente loggato???
				req.getRequestDispatcher("jsp/private/candidato/CandidatoAreaRiservata.jsp").forward(req, resp);
			}
			
		}
		catch (InputFieldException e) {
			e.printStackTrace();
			req.setAttribute(CostantiServlet.ERROR_MSG, e.getMessage());
			req.getRequestDispatcher("jsp/Signup.jsp").forward(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Signup.jsp").forward(req, resp);
		}
	}
}
