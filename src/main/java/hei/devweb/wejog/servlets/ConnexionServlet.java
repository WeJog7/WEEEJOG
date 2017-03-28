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
public class ConnexionServlet extends GenericLearningsServlet {
	private static final long serialVersionUID = 3038302649713866775L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			TemplateEngine engine = this.createTemplateEngine(request);
			engine.process("connexion", new WebContext(request, response, getServletContext()), response.getWriter());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		try {
			if (UserService.getInstance().getUser(identifiant)!=null && UserService.getInstance().validerMotDePasse(identifiant, motDePasse)){
				request.getSession().setAttribute("user", UserService.getInstance().getUser(identifiant));

				if (UserService.getInstance().getUser(identifiant).isAdmin()){
					response.sendRedirect("admin/home");
				}
				else{
					response.sendRedirect("user/home");
				}

			} else {
				this.ajouterMessageErreur(request, "L'identifiant et/ou le mot de passe renseigné est incorrect.");
				response.sendRedirect("connexion");
			}
		} catch (IllegalArgumentException e) {
			this.ajouterMessageErreur(request, e.getMessage());
		} catch (WejogSecuriteException e) {
			this.ajouterMessageErreur(request, "Problème à la vérification du mot de passe.");
		}

	}

}
