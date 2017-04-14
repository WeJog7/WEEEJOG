package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.CoupleAmis;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CoupleAmiService;
import hei.devweb.wejog.managers.UserService;


/**
 * Servlet implementation class UserListServlet
 */
@WebServlet(urlPatterns = {"/user/AskingToBeFriend", "/admin/AskingToBeFriend"})
public class FriendInvitationsListServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		

		List<CoupleAmis> askingList = CoupleAmiService.getInstance().ListAsking(user.getIdusers());
		List<Long> askingIdList = new LinkedList<Long>();
		List<User> askingUsersList = new LinkedList<User>();
		
		for(int i=0;i<askingList.size();i++){
			if(askingList.get(i).getIdusers1()!=user.getIdusers()){
				askingIdList.add(askingList.get(i).getIdusers1());
			}
			
			if(askingList.get(i).getIdusers2()!=user.getIdusers()){
				askingIdList.add(askingList.get(i).getIdusers2());
			}	
		}
		
		for(int i=0;i<askingIdList.size();i++){
			askingUsersList.add(UserService.getInstance().getUser(askingIdList.get(i)));
		}
		
		if(!askingUsersList.isEmpty()){
			context.setVariable("askings", askingUsersList);
		}
		
		templateEngine.process("AskingToBeFriend", context, resp.getWriter());
	}



}
