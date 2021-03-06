package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CoupleAmiService;


/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/deleteami", "/admin/deleteami"})
public class NetworkFriendDeleteServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");

		Long idFriend = Long.parseLong(req.getParameter("idusers"));

		CoupleAmiService.getInstance().deleteFriend(idFriend, user.getIdusers());
		resp.sendRedirect("myNetwork");
	}
}
