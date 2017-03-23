package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.managers.ArticleService;



@WebServlet("/admin/deletearticleadmin")
public class ArticleDeleteAdminServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idarticle = Long.parseLong(req.getParameter("idarticle"));
		ArticleService.getInstance().supprimerarticleadmin(idarticle);
		resp.sendRedirect("home");
	}

}