package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.EnvoiMessage;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.exceptions.WejogSecuriteException;
import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet(urlPatterns = {"/user/changePassword", "/admin/changePassword"})
public class ChangePasswordServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		templateEngine.process("changePassword", context, resp.getWriter());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String email = request.getParameter("email");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User member = (User) httpRequest.getSession().getAttribute("user");
		
		try {
			if(email.equals(member.getMail()) && UserService.getInstance().validerMotDePasse(member.getMail(), oldPassword) 
					&& newPassword.equals(newPasswordConfirmation)){
				
				String typeOfMail = "changePassword";
				UserService.getInstance().updatePassword(member.getIdusers(), newPassword);
				EnvoiMessage.main(member.getMail(), member.getPrenom(), newPassword, typeOfMail);
				response.sendRedirect("changePasswordConfirmation");
			}
			
			else{
				response.sendRedirect("changePassword");
			}
		} catch (WejogSecuriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
