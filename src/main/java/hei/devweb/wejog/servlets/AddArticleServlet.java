package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Article;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.ArticleService;


/**
 * Servlet implementation class HomeServlet
 */

@WebServlet(urlPatterns = {"/user/addarticle", "/admin/addarticle"})
public class AddArticleServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));

		templateEngine.process("ajouterArticle", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nomarticle = req.getParameter("titre_article");
		String contenuarticle = req.getParameter("synopsis");	
		String lien = req.getParameter("lien_source");

		if(nomarticle!=null && !"".equals(nomarticle) && contenuarticle!=null && !"".equals(contenuarticle) && lien!=null
				&& !"".equals(lien)){
			lien = lien.trim();
			HttpServletRequest httpRequest = (HttpServletRequest) req;
			User user = (User) httpRequest.getSession().getAttribute("user");
			Long userIdCreator = user.getIdusers();
			
			Article newArticle = new Article(null, nomarticle,contenuarticle,lien, userIdCreator);
			ArticleService.getInstance().addArticle(newArticle); 
			resp.sendRedirect("home");
		}

		else{
			resp.sendRedirect("addarticle");
		}
	}
}

