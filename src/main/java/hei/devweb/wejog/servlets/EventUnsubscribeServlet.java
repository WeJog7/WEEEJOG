package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.EventService;
import hei.devweb.wejog.managers.ParticipantService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/desinscrireparticipant", "/admin/desinscrireparticipant"})
public class EventUnsubscribeServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");

		Long idevent = Long.parseLong(req.getParameter("idevent"));

		if(EventService.getInstance().getEvent(idevent) != null){
			ParticipantService.getInstance().DesinscrireEvent(idevent, user.getIdusers());
		}

		resp.sendRedirect("myEvents");

	}
}
