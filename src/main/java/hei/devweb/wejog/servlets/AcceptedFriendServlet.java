package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.wejog.entities.CoupleAmis;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CoupleAmiService;

@WebServlet(urlPatterns = {"/user/acceptedFriend", "/admin/acceptedFriend"})
public class AcceptedFriendServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		Long userIdseeker = user.getIdusers();
		
		Long idusers = Long.parseLong(req.getParameter("idusers"));
	  
		
		CoupleAmis newCoupleAmis = new CoupleAmis(userIdseeker, idusers);
		CoupleAmiService.getInstance().acceptedFiend(newCoupleAmis);
		CoupleAmiService.getInstance().deleteAsking(idusers, userIdseeker);
		
		resp.sendRedirect("AskingToBeFriend");
}}