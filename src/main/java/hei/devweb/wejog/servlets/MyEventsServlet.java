package hei.devweb.wejog.servlets;

import java.io.IOException;
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
 * Servlet implementation class MyEventsServlet
 */
@WebServlet(urlPatterns = {"/user/myEvents", "/admin/myEvents"})
public class MyEventsServlet extends AbstractGenericServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);	
		
		context.setVariable("myevents",EventService.getInstance().ListmyEvent(user.getIdusers()));
		
		context.setVariable("inscritevents",EventService.getInstance().ListInscritEvent(event.getIdevent() ,user.getIdusers()));
		
		templateEngine.process("myEvents", context, resp.getWriter());
	}


}