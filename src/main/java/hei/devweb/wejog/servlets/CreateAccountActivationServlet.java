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
		
		Long IdAccountNotActivated = Long.parseLong(req.getParameter("idUser"));
		String idKey = req.getParameter("idKey");
		
		User temporaryUser = UserService.getInstance().getTemporaryUser(IdAccountNotActivated);

		if(temporaryUser!=null){
			if(idKey.equals(temporaryUser.getActivationKey())){
				
				User newUser = new User(temporaryUser.getNom(),temporaryUser.getPrenom(),temporaryUser.getMail(),temporaryUser.getDatedenaissance(),
						temporaryUser.getMotdepasse(),temporaryUser.isSexe());
				
				try{
					UserService.addUser(newUser);
					String typeOfMail = "activateAccount";
					EnvoiMessage.main(temporaryUser.getMail(), temporaryUser.getPrenom(), temporaryUser.getNom(), temporaryUser.getMotdepasse(),
							typeOfMail);
				}catch (IllegalArgumentException e) {
					System.out.println("Impossible to add the new user");
				}
				
				UserService.deleteTemporaryUser(IdAccountNotActivated);
			}
			
			templateEngine.process("creationCompteActivation", context, resp.getWriter());
		}
		
		else{
			resp.sendRedirect("createAccount");
		}
	}

}
