package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.EventService;


/**
 * Servlet implementation class EventUpdateServlet
 */

@WebServlet(urlPatterns = {"/user/updateEvent", "/admin/updateEvent"})
public class EventUpdateServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(request);
		WebContext context = new WebContext(request, response, request.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		
		
		Long idEvent = Long.parseLong(request.getParameter("idEvent"));
		Event event = EventService.getInstance().getEvent(idEvent);
		
		if(event!=null && (event.getCreatorId() == user.getIdusers() || user.isAdmin())){
			context.setVariable("event", event);
			// faire la suite de la récupération des propriétés de l'événement pour pré-remplir les champs (voir update description)
			
		}
		
		else{
			response.sendRedirect("myEvents");
		}

		templateEngine.process("updateEvent", context, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateAsString = request.getParameter("date_performance");
		LocalDate date = LocalDate.parse(dateAsString);
	
		String hourAsString = request.getParameter("hour");
		Integer hour = Integer.parseInt(request.getParameter("hour"));
		String minutesAsString = request.getParameter("minutes");
		Integer minutes = Integer.parseInt(request.getParameter("minutes"));
		String momentOfTheDay = request.getParameter("reponse");

		Double duration=Double.parseDouble(request.getParameter("duration"));
		Double distance=Double.parseDouble(request.getParameter("distance"));
		String place = request.getParameter("adress");
		Double latitude = Double.parseDouble(request.getParameter("latitude"));
		Double longitude = Double.parseDouble(request.getParameter("longitude"));
		String details = request.getParameter("details");
		
		/*if(details.equals("")){
			details = "None";
		}
		
		String timeAsString = hourAsString+":"+minutesAsString;
		
		if(dateAsString!=null && !"".equals(dateAsString) && hourAsString!=null && !"".equals(hourAsString) && minutesAsString!=null 
				&& !"".equals(minutesAsString) && duration!=null && !"".equals(duration) && momentOfTheDay!=null && !"".equals(momentOfTheDay)
				&& distance!=null && !"".equals(distance) && place!=null && !"".equals(place)){

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			User user = (User) httpRequest.getSession().getAttribute("user");

			Long userIdCreator = user.getIdusers();

			Event newEvent = new Event(null,date, timeAsString, hour, minutes, momentOfTheDay,duration,distance,place, latitude, longitude, userIdCreator,
					details);
			EventService.getInstance().addEvent(newEvent); 
			response.sendRedirect("myEvents");
		}

		else{
			response.sendRedirect("updateEvent");
		}*/
		
		System.out.println("date "+date);
		System.out.println("duration "+duration);
		System.out.println("distance "+distance);
		System.out.println("place "+place);
		System.out.println("details "+details);
	}
}
