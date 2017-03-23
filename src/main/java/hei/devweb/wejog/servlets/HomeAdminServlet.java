package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.managers.ArticleService;
import hei.devweb.wejog.managers.EventService;
import hei.devweb.wejog.managers.PerformanceService;

/**
 * Servlet implementation class HomeAdminServlet
 */
@WebServlet("/admin/home")
public class HomeAdminServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		context.setVariable("performances",PerformanceService.getInstance().ListPerformanceToDo());
		context.setVariable("articles",ArticleService.getInstance().ListArticleToDo());
		context.setVariable("events",EventService.getInstance().ListEventToDo());
		templateEngine.process("homeAdmin", context, resp.getWriter());
	}


}
