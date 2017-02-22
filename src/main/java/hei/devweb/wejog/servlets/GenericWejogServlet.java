package hei.devweb.wejog.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;



import org.thymeleaf.TemplateEngine;

import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import hei.devweb.wejog.entities.Utilisateur;
import hei.devweb.wejog.pojos.MessageContainer;
import hei.devweb.wejog.pojos.MessageContainer.Niveau;



public abstract class GenericWejogServlet extends HttpServlet {

	private static final long serialVersionUID = 8250982756065318974L;

	protected void ajouterMessageErreur(HttpServletRequest request, String message) {
		this.ajouterMessage(request, Niveau.ERROR, message);
	}

	protected void ajouterMessageWarn(HttpServletRequest request, String message) {
		this.ajouterMessage(request, Niveau.WARN, message);
	}

	protected void ajouterMessageInfo(HttpServletRequest request, String message) {
		this.ajouterMessage(request, Niveau.INFO, message);
	}

	protected void ajouterMessageSucces(HttpServletRequest request, String message) {
		this.ajouterMessage(request, Niveau.SUCCESS, message);
	}

	private void ajouterMessage(HttpServletRequest request, Niveau niveau, String message) {
		MessageContainer container = (MessageContainer) request.getSession().getAttribute("messages");
		container.ajouterMessage(niveau, message);
	}

	protected Utilisateur getUtilisateurCourant(HttpServletRequest request) {
		return (Utilisateur) request.getSession().getAttribute("mail");
	}

	protected TemplateEngine createTemplateEngine(HttpServletRequest request) {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(request.getServletContext());
		templateResolver.setPrefix("WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addDialect(new Java8TimeDialect());
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine;
	}
}
