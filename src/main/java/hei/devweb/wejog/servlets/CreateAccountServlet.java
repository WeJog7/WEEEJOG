package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		// TODO Auto-generated method stub
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
		/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");*/
		LocalDate date = LocalDate.parse(dateOfBirth);

		String email = request.getParameter("mail");
		String confirmEmail = request.getParameter("ConfirmMail");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

		if(sex!=null && UserService.getInstance().getUser(email) == null 
				&& email.equals(confirmEmail) && password.equals(confirmPassword) && verify){

			//System.out.println("Informations accepted and the user is not a robot. Permission to create an account granted.");

			if(sex.equals("Male")){
				sexBoolean = true;
			}

			else{
				sexBoolean = false;
			}

			try {
				password = UserService.getInstance().genererMotDePasse(password);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			User newUser = new User(lastName, firstName, email, date, password, sexBoolean);

			try{
				UserService.addUser(newUser);
				String typeOfMail = "createAccount";
				EnvoiMessage.main(email, firstName, confirmPassword, typeOfMail);
			}catch (IllegalArgumentException e) {
				System.out.println("Impossible to add the new user");
			}
			response.sendRedirect("creationAccountConfirmation");
		}

		else{
			System.out.println("Incorrect informations or user not verified, permission to create an account not granted.");
			response.sendRedirect("createAccount");
		}

	}

}
