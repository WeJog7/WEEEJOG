package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.ArticleService;


@WebServlet(urlPatterns = {"/admin/deleteArticle","/user/deleteArticle"})
public class ArticleDeleteServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idarticle = Long.parseLong(req.getParameter("idarticle"));

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");

		if(ArticleService.getInstance().getArticle(idarticle).getCreatorId() == user.getIdusers() || user.isAdmin()){
			ArticleService.getInstance().deleteArticle(idarticle);
		}

		resp.sendRedirect("home");
	}

}