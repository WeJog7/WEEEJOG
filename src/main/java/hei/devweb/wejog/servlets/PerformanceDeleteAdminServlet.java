package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.PerformanceService;

@WebServlet(urlPatterns = {"/admin/deleteperformanceadmin","/user/deletePerformance"})
public class PerformanceDeleteAdminServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idperformance = Long.parseLong(req.getParameter("idperformance"));

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");

		if(PerformanceService.getInstance().getperformance(idperformance).getUserCreatorId() == user.getIdusers() || user.isAdmin()){
			PerformanceService.getInstance().supprimerPerformance(idperformance);
		}

		resp.sendRedirect("home");
	}

}
