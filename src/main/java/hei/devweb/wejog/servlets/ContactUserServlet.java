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
import hei.devweb.wejog.entities.User;

/**
 * Servlet implementation class ContactUserServlet
 */
@WebServlet(urlPatterns = {"/user/contactUser", "/admin/contactUser"})
public class ContactUserServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));
		
		templateEngine.process("contactUser", context, resp.getWriter());
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String message = request.getParameter("message");
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
		User member = (User) httpRequest.getSession().getAttribute("user");
        
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        
		if(verify){
			String typeOfMail = "contactUs";
			EnvoiMessage.main(member.getMail(), member.getPrenom(), member.getNom(), message, typeOfMail);
			System.out.println("The user is not a robot. Permission to send message granted.");
			response.sendRedirect("contactUserMessageConfirmation");
			
		}
		
		else{
			System.out.println("User not verified, permission not granted.");
			response.sendRedirect("contactUser");
		}
        
        
    }

}