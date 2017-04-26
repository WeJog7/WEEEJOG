package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.CommentEvent;
import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.ArticleService;
import hei.devweb.wejog.managers.CommentEventService;
import hei.devweb.wejog.managers.EventService;
import hei.devweb.wejog.managers.ParticipantService;

/**
 * Servlet implementation class EventServlet
 */

@WebServlet(urlPatterns = {"/user/event", "/admin/event"})
public class EventServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
    
	private Long idEvent=null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(request);
		WebContext context = new WebContext(request, response, request.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		
		idEvent = Long.parseLong(request.getParameter("idEvent"));
		LocalDate todayDate = LocalDate.now();
		Event event = EventService.getInstance().getEvent(idEvent, todayDate);
		
		
		context.setVariable("countUsersSubscribed", ParticipantService.getInstance().countUsersSubscribed(idEvent));
		
		if(event!=null){
			context.setVariable("event", event);
			if(event.getCreatorId()==user.getIdusers() || user.isAdmin()){
				context.setVariable("administrator", "yes");
			}
			
			context.setVariable("commentList", CommentEventService.getInstance().ListCommentEventToDo(idEvent));
			
			templateEngine.process("event", context, response.getWriter());
		}
		
		else{
			response.sendRedirect("home");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("user");
		
		String content= request.getParameter("newCommentaryContent");
		LocalDateTime dateEssai = LocalDateTime.now();
		String date = dateEssai.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		if(content!=null && !"".equals(content)){
			CommentEvent newComment= new CommentEvent(null,idEvent,user.getIdusers(),date,content);
			CommentEventService.getInstance().addCommentEvent(newComment); 
		}
		
		response.sendRedirect("event?idEvent="+idEvent);
		
	}
}
