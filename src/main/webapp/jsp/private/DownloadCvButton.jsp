<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
	<form action="<%= request.getContextPath() + "/ScaricaCurriculumServlet"%>" method="post">
		<input type="hidden" name="idCandidato" value="<%= request.getParameter("idCandidato") %>">
		<input type="submit" id="scarica-cv-btn" value="Scarica CV">
	</form>
</div>
