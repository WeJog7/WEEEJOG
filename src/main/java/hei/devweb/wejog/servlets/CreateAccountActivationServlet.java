package hei.devweb.wejog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.EnvoiMessage;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class CreateAccountActivationServlet
 */
@WebServlet("/createAccountActivation")
public class CreateAccountActivationServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		String idAccountNotActivated = req.getParameter("rdg534d");
		String idKey = req.getParameter("sefrby45");
		
		Long trueIdAccountNotActivated = Long.parseLong(idAccountNotActivated.substring(0, 2));
		
		String trueIdKey = idKey.substring(10, 20);
		
		User temporaryUser = UserService.getInstance().getTemporaryUser(trueIdAccountNotActivated);

		if(temporaryUser!=null){
			if(trueIdKey.equals(temporaryUser.getActivationKey())){
				
				User newUser = new User(temporaryUser.getNom(),temporaryUser.getPrenom(),temporaryUser.getMail(),temporaryUser.getDatedenaissance(),
						temporaryUser.getMotdepasse(),temporaryUser.isSexe());
				
				try{
					UserService.addUser(newUser);
					String typeOfMail = "activateAccount";
					String contenu = "If you have connection problem, please contact us by way of our website.";
					EnvoiMessage.main(temporaryUser.getMail(), temporaryUser.getPrenom(), temporaryUser.getNom(), contenu, typeOfMail);
				}catch (IllegalArgumentException e) {
					System.out.println("Impossible to add the new user");
				}
				
				UserService.deleteTemporaryUser(trueIdAccountNotActivated);
			}
			
			templateEngine.process("creationCompteActivation", context, resp.getWriter());
		}
		
		else{
			resp.sendRedirect("createAccount");
		}
	}

}
