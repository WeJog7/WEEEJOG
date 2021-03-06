package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CoupleAmiService;
import hei.devweb.wejog.managers.PerformanceService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = {"/user/profilmonth", "/admin/profilmonth"})
public class ProfilMonthServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		Date todayDate1week = Date.valueOf(LocalDate.now().minusMonths(1));
		
		context.setVariable("performanceSelected", "Month");
		
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
	   
	   
	   context.setVariable("fastestdistance", PerformanceService.getInstance().getperformancefastest(user.getIdusers(), todayDate1week)[0]+ " Km");
	   context.setVariable("fastestduration", PerformanceService.getInstance().getperformancefastest(user.getIdusers(), todayDate1week)[1]+ " Min");
	   
	   context.setVariable("longuestdistance", PerformanceService.getInstance().getperformancelonguest(user.getIdusers(), todayDate1week)[0]+ " Km");
	   context.setVariable("longuestduration", PerformanceService.getInstance().getperformancelonguest(user.getIdusers(), todayDate1week)[1]+ " Min");
	   
		context.setVariable("User", user);
		
		templateEngine.process("profil", context, resp.getWriter());
	}
}
