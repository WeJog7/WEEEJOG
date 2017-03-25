package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Servlet implementation class ChangePasswordErrorServlet
 */
@WebServlet(urlPatterns = {"/user/changePasswordError", "/admin/changePasswordError"})
public class ChangePasswordErrorServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));

		templateEngine.process("changePasswordError", context, resp.getWriter());
	}

}
