package hei.devweb.wejog.servlets;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Article;
import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.Participant;
import hei.devweb.wejog.entities.Performance;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.ArticleService;
import hei.devweb.wejog.managers.EventService;
import hei.devweb.wejog.managers.ParticipantService;
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
		List<Article> listArticlesAllowedToDeleteByUser = listArticles;

		if(!listArticles.isEmpty()){
			context.setVariable("articleTitle","Articles posted by the community");
			
			if(user.isAdmin()){
				context.setVariable("articles",listArticles);
			}
			
			if(!user.isAdmin()){
				for(int i=0;i<listArticles.size();i++){
					if(listArticles.get(i).getUserCreatorId() == user.getIdusers()){
						listArticles.get(i).setResponse("yes");
					}
					System.out.println(listArticles.get(i).getResponse());
				}
				context.setVariable("articles",listArticles);
			}
		}
		
		if(listArticles.isEmpty()){
			context.setVariable("articleTitle","No articles to display");
		}

		List<Performance> listPerformances = PerformanceService.getInstance().ListPerformanceToDo(user.getIdusers());

		if(!listPerformances.isEmpty()){
			context.setVariable("performanceTitle","Personal performances");
			context.setVariable("performances", listPerformances);
		}
		
		if(listPerformances.isEmpty()){
			context.setVariable("performanceTitle","No personal performances to display");
		}

		Date todayDate = Date.valueOf(LocalDate.now());

		List<Event> listEvents = EventService.getInstance().ListEventToDo(todayDate, user.getIdusers());
		List<Participant> eventInscrit = ParticipantService.getInstance().ListEvenementsInscrits(user.getIdusers());
		//List<Long> listIdEventInscrit = new LinkedList<Long>();
		List<Event> listEventsToDisplay = listEvents;

		/*for(int i=0;i<eventInscrit.size();i++){
			listIdEventInscrit.add((eventInscrit.get(i)).getIdevent());
		}*/

		for(int i=0;i<listEvents.size();i++){
			for(int j=0;j<eventInscrit.size();j++){
				if(listEvents.get(i).getIdevent() == eventInscrit.get(j).getIdevent()){
					System.out.println(eventInscrit.get(j).getIdevent());
					listEventsToDisplay.remove(listEvents.get(i));
				}
			}
		}

		if(!listEventsToDisplay.isEmpty()){
			context.setVariable("eventTitle","Events available");
			context.setVariable("events", listEventsToDisplay);
		}
		
		if(listEventsToDisplay.isEmpty()){
			context.setVariable("eventTitle","No events available");
		}

		if(user.isAdmin()){
			templateEngine.process("homeAdmin", context, resp.getWriter());
		}
		else{
			templateEngine.process("home", context, resp.getWriter());
		}
	}
}