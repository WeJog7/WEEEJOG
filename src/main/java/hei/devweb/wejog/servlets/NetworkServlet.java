package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.CoupleAmis;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CoupleAmiService;
import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/myNetwork", "/admin/myNetwork"})
public class NetworkServlet extends AbstractGenericServlet{
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		
		List<CoupleAmis> friendsList = CoupleAmiService.getInstance().ListAmis(user.getIdusers());
		List<Long> friendsIdList = new LinkedList<Long>();
		List<User> friendsUsersList = new LinkedList<User>();
		
		for(int i=0;i<friendsList.size();i++){
			if(friendsList.get(i).getIdusers1()!=user.getIdusers()){
				friendsIdList.add(friendsList.get(i).getIdusers1());
			}
			
			if(friendsList.get(i).getIdusers2()!=user.getIdusers()){
				friendsIdList.add(friendsList.get(i).getIdusers2());
			}	
		}
		
		for(int i=0;i<friendsIdList.size();i++){
			friendsUsersList.add(UserService.getInstance().getUser(friendsIdList.get(i)));
		}
		
		if(!friendsUsersList.isEmpty()){
			context.setVariable("friends", friendsUsersList);
		}
		
		
		templateEngine.process("myNetwork", context, resp.getWriter());
	}
}
