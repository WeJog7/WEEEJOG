package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

		if(email != null){

			if(UserService.getInstance().getUser(email) != null && verify){


				String typeOfMail = "forgetPassword";
				User member = UserService.getInstance().getUser(email);
				String newRandomPassword = UserService.getInstance().generateRandomPassword();

				try {
					UserService.getInstance().updatePassword(member.getIdusers(), newRandomPassword);
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				EnvoiMessage.main(member.getMail(), member.getPrenom(), member.getNom(), newRandomPassword, typeOfMail);

				response.sendRedirect("recoveryPasswordConfirmation");
			}

			else{
				response.sendRedirect("recoveryPassword");
			}
		}

		else{
			response.sendRedirect("recoveryPassword");
		}

	}

}