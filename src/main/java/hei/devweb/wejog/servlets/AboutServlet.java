package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.AboutService;

/**
 * Servlet implementation class AboutServlet
 */
@WebServlet(urlPatterns = {"/user/about", "/admin/about"})
public class AboutServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);
		
		context.setVariable("AboutContenu", AboutService.getInstance().getContenu());
		
		if(user.isAdmin()){
			templateEngine.process("aboutAdmin", context, resp.getWriter());
		}
		else{
			templateEngine.process("about", context, resp.getWriter());
		}
	}
}