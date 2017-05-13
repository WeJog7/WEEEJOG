package hei.devweb.wejog.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.exceptions.WejogSecuriteException;
import hei.devweb.wejog.managers.UserService;


@WebServlet("/connexion")
public class ConnexionServlet extends GenericWejogServlet {
	private static final long serialVersionUID = 3038302649713866775L;

	private boolean error;
	private boolean block;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			TemplateEngine engine = this.createTemplateEngine(request);
			WebContext context = new WebContext(request, response, request.getServletContext());

			if(error){
				context.setVariable("ConnexionError", "Mail adress or password incorrect");
			}
			
			if(block){
				context.setVariable("ConnexionError", "Account blocked");
			}
			//engine.process("connexion", new WebContext(request, response, getServletContext()), response.getWriter());
			engine.process("connexion", context, response.getWriter());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		try {
			if (UserService.getInstance().getUser(identifiant)!=null && UserService.getInstance().validerMotDePasse(identifiant, motDePasse)
					&& !UserService.getInstance().getUser(identifiant).isBlock() && !UserService.getInstance().getUser(identifiant).isDeleted()){
				request.getSession().setAttribute("user", UserService.getInstance().getUser(identifiant));

				error = false;
				block = false;

				if (UserService.getInstance().getUser(identifiant).isAdmin()){
					response.sendRedirect("admin/home");
				}
				else{
					response.sendRedirect("user/home");
				}

			} else {
				if(UserService.getInstance().getUser(identifiant)==null || !UserService.getInstance().validerMotDePasse(identifiant, motDePasse)){
					this.ajouterMessageErreur(request, "L'identifiant et/ou le mot de passe renseigné est incorrect.");
					error = true;
				}

				else{
					this.ajouterMessageErreur(request, "User blocked by Admin.");
					block = true;
				}
				response.sendRedirect("connexion");
			}
		} catch (IllegalArgumentException e) {
			this.ajouterMessageErreur(request, e.getMessage());
		} catch (WejogSecuriteException e) {
			this.ajouterMessageErreur(request, "Problème à la vérification du mot de passe.");
		}

	}

}
