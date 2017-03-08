package hei.devweb.wejog.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public abstract class AbstractGenericServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected TemplateEngine createTemplateEngine (HttpServletRequest request){
		ServletContextTemplateResolver templateResolver =  new ServletContextTemplateResolver(request.getServletContext());
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(new Java8TimeDialect());
		return templateEngine;
	}
}
