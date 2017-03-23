package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
      
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		templateEngine.process("contact", context, resp.getWriter());
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email_contact");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String message = request.getParameter("message");
        
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		
		firstName = firstName.replaceAll(" ", "");
		lastName = lastName.replaceAll(" ", "");
		String checkMessage = message.replaceAll(" ", "");
        
		if(email!=null && !"".equals(email) && firstName!=null && !"".equals(firstName)	&& lastName!=null && !"".equals(lastName)
				&& message!=null && !"".equals(message) && !"".equals(checkMessage) && verify){
			String typeOfMail = "contactUs";
			EnvoiMessage.main(email, firstName, lastName, message, typeOfMail);
			// System.out.println("The user is not a robot. Permission to send message granted.");
			response.sendRedirect("contactMessageConfirmation");
		}
		
		else{
			//System.out.println("User not verified or wrong message, permission not granted.");
			response.sendRedirect("contact");
		}
        
    }

}