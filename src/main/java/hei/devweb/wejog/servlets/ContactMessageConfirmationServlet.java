package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Servlet implementation class ContactMessageConfirmationServlet
 */
@WebServlet(urlPatterns = {"/user/contactMessageConfirmation"})
public class ContactMessageConfirmationServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;
      
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		templateEngine.process("contactMessageConfirmation", context, resp.getWriter());
	}


}