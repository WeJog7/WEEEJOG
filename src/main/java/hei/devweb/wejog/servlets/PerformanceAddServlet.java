package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.Performance;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.PerformanceService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/addperformance", "/admin/addperformance"})
public class PerformanceAddServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));

		templateEngine.process("ajouterPerformance", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dateAsString=req.getParameter("date_performance");
		LocalDate dateperformance = LocalDate.parse(dateAsString);
		Double dureeperformance=Double.parseDouble(req.getParameter("duration"));
		Double distanceperformance=Double.parseDouble(req.getParameter("distance"));
		
		Double vitesseperformance = ((distanceperformance*60)/dureeperformance);
		System.out.println("vitesse:"+vitesseperformance);
		Double calories=Double.parseDouble(req.getParameter("calories"));

		if(dateAsString!=null && !"".equals(dateAsString) && dureeperformance!=null && !"".equals(dureeperformance) && distanceperformance!=null 
				&& !"".equals(distanceperformance) && vitesseperformance!=null && !"".equals(vitesseperformance) && calories!=null 
				&& !"".equals(calories)){
			
			HttpServletRequest httpRequest = (HttpServletRequest) req;
			User user = (User) httpRequest.getSession().getAttribute("user");
			Long userIdCreator = user.getIdusers();

			
			Performance newPerformance = new Performance(null, dateperformance,dureeperformance,distanceperformance,vitesseperformance,
					calories, userIdCreator);
			PerformanceService.getInstance().addPerformance(newPerformance); 
			resp.sendRedirect("home");
		}

		else{
			resp.sendRedirect("addperformance");
		}
	}
}