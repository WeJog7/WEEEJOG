package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.VerifyRecaptcha;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/recoveryPassword")
public class RecoverypassewordServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		templateEngine.process("recoveryPassword", context, resp.getWriter());
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
             
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