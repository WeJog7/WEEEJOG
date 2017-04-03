package hei.devweb.wejog.servlets;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.Participant;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.EventService;
import hei.devweb.wejog.managers.ParticipantService;

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
		
		List<Event> listEventAdministrated = EventService.getInstance().ListmyEvent(user.getIdusers(),todayDate);
		
		if(!listEventAdministrated.isEmpty()){
			context.setVariable("eventAdministrator", "My event(s) administrated");	
			context.setVariable("myevents",listEventAdministrated);
		}
		
		List<Participant> eventInscrit = ParticipantService.getInstance().ListEvenementsInscrits(user.getIdusers());
		List<Long> listIdEventInscrit = new LinkedList<Long>();
		List<Event> evenementsInscrits = new LinkedList<Event>();
		
		for(int i=0;i<eventInscrit.size();i++){
			listIdEventInscrit.add((eventInscrit.get(i)).getIdevent());
			evenementsInscrits.add(EventService.getInstance().getEvent(listIdEventInscrit.get(i),todayDate));
		}
		
		if(!evenementsInscrits.isEmpty()){
			context.setVariable("eventRegistered", "Event(s) where i am registered");	
		}
		context.setVariable("inscritevents",evenementsInscrits);
		
		templateEngine.process("myEvents", context, resp.getWriter());
	}


}