package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/admin/UsersList")
public class UserListServlet extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		context.setVariable("users",UserService.getInstance().listerUsers());
		
		if(!UserService.getInstance().usersBlockList().isEmpty()){
			context.setVariable("AreThereUsersBlockedToDisplay","yes");
			context.setVariable("usersBlockList",UserService.getInstance().usersBlockList());
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));
		
		templateEngine.process("UsersList", context, resp.getWriter());
	}

}