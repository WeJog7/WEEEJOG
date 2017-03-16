package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.managers.UserService;




@WebServlet("/admin/deleteuser")
public class UserDeleteServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idusers = Long.parseLong(req.getParameter("idusers"));
		UserService.getInstance().supprimerusers(idusers);
		resp.sendRedirect("UsersList");
	}

}