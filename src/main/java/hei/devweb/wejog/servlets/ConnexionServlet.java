package hei.devweb.wejog.servlets;

import learnings.exceptions.LearningsSecuriteException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.managers.UtilisateurManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericWejogServlet {
	private static final long serialVersionUID = 3038302649713866775L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateur") == null) {
			TemplateEngine engine = this.createTemplateEngine(request);
			engine.process("/connexion", new WebContext(request, response, getServletContext()), response.getWriter());
		} else {
			response.sendRedirect("/home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		try {
			if (UtilisateurManager.getInstance().validerMotDePasse(identifiant, motDePasse)) {
				request.getSession().setAttribute("utilisateur", UtilisateurManager.getInstance().getUtilisateur(identifiant));
			} else {
				this.ajouterMessageErreur(request, "Le mot de passe renseigné est faux.");
			}
		} catch (IllegalArgumentException e) {
			this.ajouterMessageErreur(request, e.getMessage());
		} catch (LearningsSecuriteException e) {
			this.ajouterMessageErreur(request, "Problème à la vérification du mot de passe.");
		}

		response.sendRedirect("connexion");
	}

}
