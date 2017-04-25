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
import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class FriendProfilYearServlet
 */

@WebServlet(urlPatterns = {"/user/friendProfilYear", "/admin/friendProfilYear"})
public class FriendProfilYearServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User", user);

		Date todayDate1week = Date.valueOf(LocalDate.now().minusMonths(1));

		Long idUser = Long.parseLong(req.getParameter("idUser"));
		User friend = UserService.getInstance().getUser(idUser);

		if(CoupleAmiService.getInstance().getFriendCouple(user.getIdusers(), friend.getIdusers()) !=null){

			context.setVariable("numberAsking",CoupleAmiService.getInstance().countAsking(friend.getIdusers()));
			context.setVariable("TotalTime",PerformanceService.getInstance().countTimePerformance(friend.getIdusers()));
			context.setVariable("TotalDistance",PerformanceService.getInstance().countDistancePerformance(friend.getIdusers()));
			context.setVariable("TotalRace",PerformanceService.getInstance().countNumberOfRace(friend.getIdusers()));

			if ( PerformanceService.getInstance().getperformance1KM(friend.getIdusers(), todayDate1week) != 0 ){

				context.setVariable("Week1KM", PerformanceService.getInstance().getperformance1KM(friend.getIdusers(), todayDate1week)+" MIN");
			}
			else {
				context.setVariable("Week1KM", "None");
			}

			if ( PerformanceService.getInstance().getperformance5KM(friend.getIdusers(), todayDate1week) !=0 ){
				context.setVariable("Week5KM", PerformanceService.getInstance().getperformance5KM(friend.getIdusers(), todayDate1week)+" MIN");
			}
			else {
				context.setVariable("Week5KM", "None");
			}
			if( PerformanceService.getInstance().getperformance10KM(friend.getIdusers(), todayDate1week)!=0){
				context.setVariable("Week10KM", PerformanceService.getInstance().getperformance10KM(friend.getIdusers(), todayDate1week)+" MIN");
			}
			else{
				context.setVariable("Week10KM", "None");
			}

			if(PerformanceService.getInstance().getperformance42KM(friend.getIdusers(), todayDate1week)!=0){
				context.setVariable("Week42KM", PerformanceService.getInstance().getperformance42KM(friend.getIdusers(), todayDate1week)+" MIN");
			}
			else{
				context.setVariable("Week42KM", "None");
			}

			context.setVariable("fastestdistance", PerformanceService.getInstance().getperformancefastest(friend.getIdusers(), todayDate1week)[0]+ " Km");
			context.setVariable("fastestduration", PerformanceService.getInstance().getperformancefastest(friend.getIdusers(), todayDate1week)[1]+ " Min");

			context.setVariable("longuestdistance", PerformanceService.getInstance().getperformancelonguest(friend.getIdusers(), todayDate1week)[0]+ " Km");
			context.setVariable("longuestduration", PerformanceService.getInstance().getperformancelonguest(friend.getIdusers(), todayDate1week)[1]+ " Min");

			context.setVariable("friendUser", friend);
			
			templateEngine.process("friendProfil", context, resp.getWriter());
		}

		else{
			resp.sendRedirect("home");
		}
	}
}