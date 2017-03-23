package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import hei.devweb.wejog.managers.EventService;

@WebServlet("/admin/deleteeventadmin")
public class EventDeleteAdminServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idevent = Long.parseLong(req.getParameter("idevent"));
		EventService.getInstance().supprimereventadmin(idevent);
		resp.sendRedirect("home");
	}

}
