package hei.devweb.wejog.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Article;
import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.Performance;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.ArticleService;
import hei.devweb.wejog.managers.EventService;
import hei.devweb.wejog.managers.PerformanceService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/home", "/admin/home"})
public class HomeServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		
		List<Article> listArticles = ArticleService.getInstance().ListArticleToDo();
		
		if(!listArticles.isEmpty()){
			context.setVariable("articleTitle","Articles");
			context.setVariable("articles",listArticles);
		}
		
		List<Performance> listPerformances = PerformanceService.getInstance().ListPerformanceToDo(user.getIdusers());
		
		if(!listPerformances.isEmpty()){
			context.setVariable("performanceTitle","Performances");
			context.setVariable("performances", listPerformances);
		}
		
		List<Event> listEvents = EventService.getInstance().ListEventToDo();
		
		if(!listEvents.isEmpty()){
			context.setVariable("eventTitle","Events");
			context.setVariable("events", listEvents);
		}

		if(user.isAdmin()){
			templateEngine.process("homeAdmin", context, resp.getWriter());
		}
		else{
			templateEngine.process("home", context, resp.getWriter());
		}
	}
}