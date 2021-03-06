package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class UserBlock
 */
@WebServlet("/admin/blockUser")
public class UserBlockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idusers = Long.parseLong(request.getParameter("idusers"));
		UserService.getInstance().blockUser(idusers);
		
		response.sendRedirect("UsersList");
	}

}
