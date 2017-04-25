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
 * Servlet implementation class EventServlet
 */

@WebServlet(urlPatterns = {"/user/event", "/admin/event"})
public class EventServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(request);
		WebContext context = new WebContext(request, response, request.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		
		Long idEvent = Long.parseLong(request.getParameter("idEvent"));
		LocalDate todayDate = LocalDate.now();
		Event event = EventService.getInstance().getEvent(idEvent, todayDate);
		
		if(event!=null){
			context.setVariable("event", event);
			if(event.getCreatorId()==user.getIdusers() || user.isAdmin()){
				context.setVariable("administrator", "yes");
			}
			templateEngine.process("event", context, response.getWriter());
		}
		
		else{
			response.sendRedirect("home");
		}
		
	}

}