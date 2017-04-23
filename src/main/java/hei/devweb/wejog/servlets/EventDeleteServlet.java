package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.EventService;

@WebServlet(urlPatterns = {"/admin/deleteEvent","/user/deleteEvent"})
public class EventDeleteServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idevent = Long.parseLong(req.getParameter("idEvent"));

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		Event event = EventService.getInstance().getEvent(idevent);
		
		if(event!=null){
			if(event.getCreatorId() == user.getIdusers() || user.isAdmin()){
				EventService.getInstance().deleteEvent(idevent);
			}
		}

		resp.sendRedirect("home");
	}

}
