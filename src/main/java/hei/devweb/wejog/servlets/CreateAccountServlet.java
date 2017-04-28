package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.EnvoiMessage;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.entities.VerifyRecaptcha;
import hei.devweb.wejog.managers.UserService;

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
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());

		templateEngine.process("creationCompte", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String sex = request.getParameter("reponse");
		boolean sexBoolean;

		String firstName = request.getParameter("name");
		String lastName = request.getParameter("lastName");

		String dateOfBirth = request.getParameter("birth");
		LocalDate date = LocalDate.parse(dateOfBirth);

		String email = request.getParameter("mail");
		String confirmEmail = request.getParameter("ConfirmMail");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		//System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		
		firstName = firstName.replaceAll(" ", "");
		lastName = lastName.replaceAll(" ", "");

		if(sex!=null && firstName!=null && !"".equals(firstName) && lastName!=null && !"".equals(lastName) 
				&& dateOfBirth!=null && !"".equals(dateOfBirth) && email != null && !"".equals(email) 
				&& password != null && !"".equals(password) && UserService.getInstance().getUser(email) == null
				&& UserService.getInstance().getTemporaryUser(email) == null
				&& email.equals(confirmEmail) && password.equals(confirmPassword) && verify){

			//System.out.println("Informations accepted and the user is not a robot. Permission to create an account granted.");

			if(sex.equals("Male")){
				sexBoolean = true;
			}

			else{
				sexBoolean = false;
			}
			
			String correctFirstName="";
			String correctLastName="";
			
			if(firstName.length()<2){
				correctFirstName = firstName.substring(0,1).toUpperCase();
			}
			else{
				correctFirstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();
			}
			
			if(lastName.length()<2){
				correctLastName = lastName.substring(0,1).toUpperCase();
			}
			else{
				correctLastName = lastName.substring(0,1).toUpperCase()+lastName.substring(1).toLowerCase();
			}
			

			try {
				password = UserService.getInstance().genererMotDePasse(password);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (InvalidKeySpecException e1) {
				e1.printStackTrace();
			}

			User newUser = new User(correctLastName, correctFirstName, email, date, password, sexBoolean);

			try{
				String activationKey = UserService.getInstance().generateActivationKey();
				UserService.getInstance().addTemporaryUser(newUser, activationKey);
				String typeOfMail = "createAccount";
				
				User temporaryUser = UserService.getInstance().getTemporaryUser(newUser.getMail());
				
				String activationLink = "https://wejog.herokuapp.com/createAccountActivation?idUser="+temporaryUser.getIdAccountNotActivated()+
						"&idKey="+activationKey;
				
				EnvoiMessage.main(email, correctFirstName, correctLastName, activationLink, typeOfMail);
			}catch (IllegalArgumentException e) {
				System.out.println("Impossible to add the new user");
			}
			response.sendRedirect("creationAccountConfirmation");
		}

		else{
			System.out.println("Incorrect informations or user not verified, permission to create an account not granted.");
			if(UserService.getInstance().getUser(email)!=null || UserService.getInstance().getTemporaryUser(email) != null){
				response.sendRedirect("CreateAccountError");
			}
			else{
			response.sendRedirect("createAccount");
			}
		}

	}

}
