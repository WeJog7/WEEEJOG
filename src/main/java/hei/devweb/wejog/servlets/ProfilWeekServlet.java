package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CoupleAmiService;
import hei.devweb.wejog.managers.PerformanceService;
import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/profil", "/admin/profil"})
public class ProfilWeekServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		Date todayDate1week = Date.valueOf(LocalDate.now().minusWeeks(1));
		
		
		context.setVariable("description", UserService.getDescription(user.getIdusers()));
		
	   context.setVariable("numberAsking",CoupleAmiService.getInstance().countAsking(user.getIdusers()));
	   context.setVariable("TotalTime",PerformanceService.getInstance().countTimePerformance(user.getIdusers()));
	   context.setVariable("TotalDistance",PerformanceService.getInstance().countDistancePerformance(user.getIdusers()));
	   context.setVariable("TotalRace",PerformanceService.getInstance().countNumberOfRace(user.getIdusers()));
	   
	   if ( PerformanceService.getInstance().getperformance1KM(user.getIdusers(), todayDate1week) != 0 ){
		   
	   context.setVariable("Week1KM", PerformanceService.getInstance().getperformance1KM(user.getIdusers(), todayDate1week)+" MIN");
	   }
	   else {
		   context.setVariable("Week1KM", "None");
	   }
	   
	   if ( PerformanceService.getInstance().getperformance5KM(user.getIdusers(), todayDate1week) !=0 ){
	   context.setVariable("Week5KM", PerformanceService.getInstance().getperformance5KM(user.getIdusers(), todayDate1week)+" MIN");
	   }
	   else {
		   context.setVariable("Week5KM", "None");
	   }
	   if( PerformanceService.getInstance().getperformance10KM(user.getIdusers(), todayDate1week)!=0){
	   context.setVariable("Week10KM", PerformanceService.getInstance().getperformance10KM(user.getIdusers(), todayDate1week)+" MIN");
	   }
	   else{
		   context.setVariable("Week10KM", "None");
	   }
	   
	   if(PerformanceService.getInstance().getperformance42KM(user.getIdusers(), todayDate1week)!=0){
	   context.setVariable("Week42KM", PerformanceService.getInstance().getperformance42KM(user.getIdusers(), todayDate1week)+" MIN");
	   }
	   else{
		   context.setVariable("Week42KM", "None");
	   }
		context.setVariable("User", user);
		
		templateEngine.process("profil", context, resp.getWriter());
	}


}
