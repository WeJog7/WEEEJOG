package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.EventService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/addevent", "/admin/addevent"})
public class AddEventServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));

		templateEngine.process("ajouterEvenement", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dateAsString = req.getParameter("date_performance");
		LocalDate date = LocalDate.parse(dateAsString);
	
		Integer hour = Integer.parseInt(req.getParameter("hour"));
		Integer minutes = Integer.parseInt(req.getParameter("minutes"));
		String momentOfTheDay = req.getParameter("reponse");

		Double dureeevent=Double.parseDouble(req.getParameter("duration"));
		Double distanceevent=Double.parseDouble(req.getParameter("distance"));
		String lieuevent = req.getParameter("adress");
		
		if(dateAsString!=null && !"".equals(dateAsString) && hour!=null && !"".equals(hour) && minutes!=null && !"".equals(minutes) 
				&& dureeevent!=null && !"".equals(dureeevent) && momentOfTheDay!=null && !"".equals(momentOfTheDay)	&& distanceevent!=null 
				&& !"".equals(distanceevent) && lieuevent!=null && !"".equals(lieuevent)){

			HttpServletRequest httpRequest = (HttpServletRequest) req;
			User user = (User) httpRequest.getSession().getAttribute("user");

			Long userIdCreator = user.getIdusers();

			String firstNameCreator = user.getPrenom();

			Event newEvent = new Event(null,date, hour, minutes,momentOfTheDay,dureeevent,distanceevent,lieuevent, userIdCreator, firstNameCreator);
			EventService.getInstance().addEvent(newEvent); 
			resp.sendRedirect("myEvents");
		}
		else{
			resp.sendRedirect("home");
		}
	}
}