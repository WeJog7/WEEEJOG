package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.VerifyRecaptcha;
import hei.devweb.wejog.entities.EnvoiMessage;


/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/contact")
public class ContactServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		templateEngine.process("contact", context, resp.getWriter());
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email_contact");
        String name = request.getParameter("name");
        String message = request.getParameter("message");
        
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        
		if(verify){
			String typeOfMail = "contactUs";
			EnvoiMessage.main(email, name, message, typeOfMail);
			System.out.println("The user is not a robot. Permission to send message granted.");
			response.sendRedirect("contactMessageConfirmation");
			
		}
		
		else{
			response.sendRedirect("contact");
		}
        
        
    }

}