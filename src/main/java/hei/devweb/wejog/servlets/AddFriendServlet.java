package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet(urlPatterns = {"/user/addFriend", "/admin/addFriend"})
public class AddFriendServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	private String recherche;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User",user);
		
		if(recherche!=null){
		
		List<User> listFound = UserService.getInstance().ListSearchAmi(recherche, user.getIdusers());

		context.setVariable("addusers", listFound);
		}
		
		templateEngine.process("addFriend", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String identity = request.getParameter("friendSearch");
		
		recherche = identity;
		
		response.sendRedirect("addFriend");
	}

}