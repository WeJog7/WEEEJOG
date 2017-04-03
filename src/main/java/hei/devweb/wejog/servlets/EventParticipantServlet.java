package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.Participant;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.EventService;
import hei.devweb.wejog.managers.ParticipantService;



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/addparticipant", "/admin/addparticipant"})
public class EventParticipantServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebContext context = new WebContext(req, resp, req.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		Long idevent = Long.parseLong(req.getParameter("idevent"));

		Event event = EventService.getInstance().getEvent(idevent);

		if(event != null){
			if(event.getUsergestion() != user.getIdusers()){
				Participant newParticipant = new Participant(null, idevent, user.getIdusers());
				ParticipantService.getInstance().RegistredToEvent(newParticipant);
			}
		}

		resp.sendRedirect("myEvents");
	}
}
