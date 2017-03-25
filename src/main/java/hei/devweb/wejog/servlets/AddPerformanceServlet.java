package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import hei.devweb.wejog.entities.Performance;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.PerformanceService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/addperformance", "/admin/addperformance"})
public class AddPerformanceServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));
		
		templateEngine.process("ajouterPerformance", context, resp.getWriter());
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dateAsString=req.getParameter("date_performance");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dateperformance = LocalDate.parse(dateAsString,formatter);
		
		String horaireAsString=req.getParameter("hour");
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime horaireperformance = LocalTime.parse(horaireAsString,formater);
		
		Double dureeperformance=Double.parseDouble(req.getParameter("duration"));
		Double distanceperformance=Double.parseDouble(req.getParameter("distance"));
		Double vitesseperformance=Double.parseDouble(req.getParameter("average"));
		Double calories=Double.parseDouble(req.getParameter("calories"));
		String lieuperformance = req.getParameter("adress");
		
		
	
	
		Performance newPerformance = new Performance(null, dateperformance,horaireperformance,dureeperformance,distanceperformance,vitesseperformance,calories,lieuperformance, null);
		 PerformanceService.getInstance().addPerformance(newPerformance); 
		resp.sendRedirect("home");
}
}


