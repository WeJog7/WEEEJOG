package hei.devweb.wejog.servlets;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
 * Servlet implementation class MyEventsServlet
 */
@WebServlet(urlPatterns = {"/user/myEvents", "/admin/myEvents"})
public class MyEventsServlet extends AbstractGenericServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		
		Date todayDate = Date.valueOf(LocalDate.now());
		
		List<Event> listEventAdministrated = EventService.getInstance().ListmyEventAdministrated(user.getIdusers(),todayDate);
		
		if(!listEventAdministrated.isEmpty()){
			context.setVariable("eventAdministrator", "My event(s) administrated");	
			context.setVariable("myEvents",listEventAdministrated);
		}
		
		if(listEventAdministrated.isEmpty()){
			context.setVariable("eventAdministrator", "No event(s) administrated");	
		}
		
		List<Event> listEventSubscribed = EventService.getInstance().listEventsSubscribed(user.getIdusers(), todayDate);
		
		if(!listEventSubscribed.isEmpty()){
			context.setVariable("eventRegistered", "Event(s) where i am registered");
			context.setVariable("eventsSubscribed",listEventSubscribed);
		}
		
		if(listEventSubscribed.isEmpty()){
			context.setVariable("eventRegistered", "No Event(s) subscribed to display");
		}
		
		templateEngine.process("myEvents", context, resp.getWriter());
	}
}