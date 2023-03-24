package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Azienda;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.model.Gestore;
import com.aziendaRecruiting.model.Utente;
import com.aziendaRecruiting.utils.exceptions.InputFieldException;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String user = req.getParameter("email-login");
			String pw = req.getParameter("password-login");
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			
			Utente currentUser = bLogic.retrieveUtente(user, pw); // --> in BL: metodo login(String user, String pw)
			
			if(currentUser == null) {
				// utente non trovato:
				throw new InputFieldException("Utente non registrato");
				
			}
			else {
				System.out.println("<!> Login:");
				System.out.println(currentUser);
				System.out.println();
				
				// TODO i controlli per l'indirizzamento suppongo vadano bene qua, no? O vanno anch'essi messi in BL?
				
				if(currentUser instanceof Gestore) {
					
					req.getSession().setAttribute(CostantiServlet.LOGGED_USER, (Gestore)currentUser);
					req.getRequestDispatcher("jsp/private/gestore/GestoreAreaRiservata.jsp").forward(req, resp);
				}
				else if(currentUser instanceof Azienda) {

					req.getSession().setAttribute(CostantiServlet.LOGGED_USER, (Azienda)currentUser);
					req.getRequestDispatcher("jsp/private/azienda/AziendaAreaRiservata.jsp").forward(req, resp); 
				}
				else if(currentUser instanceof Candidato) {

					req.getSession().setAttribute(CostantiServlet.LOGGED_USER, (Candidato)currentUser);
					req.getRequestDispatcher("jsp/private/candidato/CandidatoAreaRiservata.jsp").forward(req, resp); 
				}

				/* 
				 * TODO PER ROBERTO:
				 * Come sopra, o meglio mettere in sessione l'oggetto Utente non 'castato'?
				 * req.getSession().setAttribute("Utente", currentUser);
				 */
				
			}
			
		}
		catch (InputFieldException e) {
			e.printStackTrace();
			req.setAttribute(CostantiServlet.ERROR_MSG, e.getMessage());
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute(CostantiServlet.ERROR_MSG, "Errore imprevisto");
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp);
		}
		
		
		
	}

}
