package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.CommentEvent;
import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.entities.User;
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

		if(event!=null){
			context.setVariable("event", event);
			context.setVariable("countUsersSubscribed", ParticipantService.getInstance().countUsersSubscribed(idEvent));

			if(event.getCreatorId()==user.getIdusers() || user.isAdmin()){
				context.setVariable("administrator", "yes");
			}

			List<CommentEvent> commentsList = CommentEventService.getInstance().ListCommentEventToDo(idEvent);
			List<CommentEvent> oldCommentsList = new ArrayList<>();
			List<CommentEvent> recentCommentsList = new ArrayList<>();


			if(!commentsList.isEmpty()){

				if(!user.isAdmin()){

					int g = 0;

					while(g<commentsList.size()){
						if(commentsList.get(g).getCreatorId() == user.getIdusers()){
							commentsList.get(g).setResponse(true);
						}
						g++;
					}
				}

				if(commentsList.size()<=5){
					context.setVariable("isThereComments", "No");
					context.setVariable("recentCommentsList", commentsList);
				}

				else{
					int i= 0;
					int j = commentsList.size()-5;

					while(i<(commentsList.size()-5)){
						oldCommentsList.add(commentsList.get(i));
						i++;
					}

					while(j<(commentsList.size())){
						recentCommentsList.add(commentsList.get(j));
						j++;
					}

					context.setVariable("isThereComments", "yes");
					context.setVariable("commentList", oldCommentsList);
					context.setVariable("recentCommentsList", recentCommentsList);

				}
				context.setVariable("commentTitle", "Commentaries");
			}

			else{
				context.setVariable("commentTitle", "No commentaries to display");
				context.setVariable("isThereComments", "No");
			}

			if(user.isAdmin()){
				templateEngine.process("eventAdmin", context, response.getWriter());
			}
			
			else{
				templateEngine.process("event", context, response.getWriter());
			}
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
