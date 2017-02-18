/*package hei.devweb.wejog.servlets;

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
/*@WebServlet("/addevent")
public class AddEventServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	/*protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		context.setVariable("events",EventService.getInstance().ListEventToDo());
		templateEngine.process("ajouterEvenement", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dateAsString=req.getParameter("dateevent");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(dateAsString,formatter);
		
		String horaireAsString=req.getParameter("horaireevent");
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("00:00:00");
		LocalDate horaire = LocalDate.parse(horaireAsString,formater);
		
		Double dureeevent=Double.parseDouble(req.getParameter("dureeevent"));
		Double distanceevent=Double.parseDouble(req.getParameter("distanceevent"));
		String lieuevent = req.getParameter("lieuevent");
		
		
	
	
		Event newEvent = new Event(0, date,horaire,dureeevent,distanceevent,lieuevent, 0, 0);
		 EventService.getInstance().AddEvent(newEvent); 
		resp.sendRedirect("home");
}
}*/
