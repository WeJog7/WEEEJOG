package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.VerifyRecaptcha;
import hei.devweb.wejog.entities.envoiMessage;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/createAccount")
public class CreateAccountServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		templateEngine.process("creationCompte", context, resp.getWriter());
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        
        String dateOfBirth = request.getParameter("birth");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateOfBirth,formatter);
        
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		 
		if(verify){
			System.out.println("The user is not a robot. Permission to create account granted.");
			response.sendRedirect("creationAccountConfirmation");
			
		}
		
		else{
			System.out.println("User not verified, permission not granted.");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>You missed the Captcha.</font>");
		}
        
    }

}
