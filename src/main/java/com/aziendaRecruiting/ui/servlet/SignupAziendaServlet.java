package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Azienda;
import com.aziendaRecruiting.utils.azienda.SettoreAzienda;
import com.aziendaRecruiting.utils.exceptions.InputFieldException;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/SignupAziendaServlet")
public class SignupAziendaServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String email = req.getParameter("email-signup");
			String password = req.getParameter("password-signup");
			String ragioneSociale = req.getParameter("ragione-sociale");
			String sedeLegale = req.getParameter("sede-legale");
			String telefono = req.getParameter("telefono");
			SettoreAzienda settore = SettoreAzienda.valueOfLabel(req.getParameter("settore"));			
			
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			if(bLogic.isEmailCandidatoRegistered(email) || bLogic.isEmailAziendaRegistered(email)) {
				// se è già registrato
				req.setAttribute(CostantiServlet.ERROR_MSG, "Utente con questa e-mail già registrato");
				req.getRequestDispatcher("jsp/Signup.jsp").forward(req, resp);
			}
			else {
				// se non è registrato
				Azienda azienda = new Azienda(email, password, ragioneSociale, sedeLegale);
				azienda.setRecapitoTelefonico(telefono);
				azienda.setSettore(settore);
				
				bLogic.create(azienda);
				
				System.out.println("<OK> Azienda registrata sul DB:");
				System.out.println(azienda);
				System.out.println();
				
				req.getSession().setAttribute(CostantiServlet.LOGGED_USER, azienda); // TODO controllare che per caso non ci sia già un utente loggato???
				req.getRequestDispatcher("jsp/private/azienda/AziendaAreaRiservata.jsp").forward(req, resp);
				
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
