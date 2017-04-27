package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CommentEventService;

/**
 * Servlet implementation class CommentDeleteServlet
 */

@WebServlet(urlPatterns = {"/admin/deleteComment","/user/deleteComment"})
public class CommentDeleteServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idComment = Long.parseLong(req.getParameter("idComment"));
		Long idEvent = Long.parseLong(req.getParameter("idEvent"));

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");

		if(CommentEventService.getInstance().getCommentEvent(idComment).getCreatorId() == user.getIdusers() || user.isAdmin()){
			CommentEventService.getInstance().deleteCommentEvent(idComment);
		}

		resp.sendRedirect("event?idEvent="+idEvent);
	}

}
