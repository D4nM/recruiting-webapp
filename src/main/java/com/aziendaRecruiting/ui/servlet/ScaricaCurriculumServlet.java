package com.aziendaRecruiting.ui.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aziendaRecruiting.businessLogic.RecruitingBusinessLogic;
import com.aziendaRecruiting.model.Candidato;
import com.aziendaRecruiting.utils.servlet.CostantiServlet;

@WebServlet("/ScaricaCurriculumServlet")
public class ScaricaCurriculumServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RecruitingBusinessLogic bLogic = (RecruitingBusinessLogic)getServletContext().getAttribute(CostantiServlet.B_LOGIC);
			
			
			System.out.println("req.getParameter(\"idCandidato\") == " + req.getParameter("idCandidato"));
			Integer idCandidato = Integer.parseInt(req.getParameter("idCandidato"));
			System.out.println("idCandidato=" + idCandidato);
			
			Candidato candidato = bLogic.getCandidatoById(idCandidato);
			
			Blob file = candidato.getCvFile();
			String fileName = candidato.getCvFileName();
			
			/*
			 * Impostazione response: si impostano delle impostazioni dell'header dei messaggi Http
			 * 		1) setContentType() --> imposta il MIME-type del file
			 * 		2) (OPZ.) setHeader()
			 */
			resp.setContentType("application/pdf");
			resp.setHeader("Content-disposition", "attachment; filename=" + fileName);
			
			InputStream is = file.getBinaryStream(); // ottenimento dell'input stream dell'oggetto blob
			OutputStream os = resp.getOutputStream(); // ottenimento dell'output stream della response http
			
			byte [] buffer = new byte[1024];
			
			int read = -1;
			while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
				os.write(buffer, 0, read);
			}
			
			os.flush();
			
			os.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/Login.jsp").forward(req, resp); // TODO
		}
	}
}

