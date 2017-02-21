package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.managers.EventService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/addarticle")
public class AddArticleServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		templateEngine.process("ajouterArticle", context, resp.getWriter());
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nomarticle = req.getParameter("titre_article");
		String dateAsString=req.getParameter("date_performance");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(dateAsString,formatter);
		
		String horaireAsString=req.getParameter("hour");
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate horaire = LocalDate.parse(horaireAsString,formater);
		
		Double dureeevent=Double.parseDouble(req.getParameter("duration"));
		Double distanceevent=Double.parseDouble(req.getParameter("distance"));
		String lien = req.getParameter("lien_source");
		
		
	
	
		Event newEvent = new Event(null, nomarticle ,horaire,dureeevent,distanceevent,lien , null, null);
		 EventService.getInstance().addEvent(newEvent); 
		resp.sendRedirect("home");
}
}

