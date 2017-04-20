package hei.devweb.wejog.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.wejog.entities.CoupleAmis;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.CoupleAmiService;
import hei.devweb.wejog.managers.UserService;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet(urlPatterns = {"/user/addFriend", "/admin/addFriend"})
public class FriendSearchServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	private String recherche;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		context.setVariable("User",user);

		if(recherche!=null){
			// List<Long> listUsersIdFound = UserService.getInstance().listUsersIdFound(recherche, user.getIdusers());
			
			List<String> completeIdentity = new ArrayList<String>();
			
			if(recherche.contains(" ")){
				completeIdentity= Arrays.asList(recherche.split(" "));
			}
			
			List<Long> listUsersIdFound = new LinkedList<Long>();
			
			List<Long> listUsersIdFoundCompleteIdentity = new LinkedList<Long>();
			
			if(completeIdentity.size()==2){
				for (int i=0;i<2;i++){
					listUsersIdFoundCompleteIdentity = UserService.getInstance().listUsersIdFound(completeIdentity.get(i), user.getIdusers());
					
					int j = 0;
					while(j<listUsersIdFoundCompleteIdentity.size()){
						if(!listUsersIdFound.contains(listUsersIdFoundCompleteIdentity.get(j))){
							listUsersIdFound.add(listUsersIdFoundCompleteIdentity.get(j));
						}
						j++;
					}
				}
			}
			
			
			else{
				listUsersIdFound = UserService.getInstance().listUsersIdFound(recherche, user.getIdusers());
			}

			if(!listUsersIdFound.isEmpty()){
				List<CoupleAmis> friendCoupleList = CoupleAmiService.getInstance().ListAmis(user.getIdusers());
				List<Long> friendsIdList = new LinkedList<Long>();
				List<User> friendsUserList = new LinkedList<User>();

				for(int i=0;i<friendCoupleList.size();i++){
					if(friendCoupleList.get(i).getIdusers1()!=user.getIdusers()){
						friendsIdList.add(friendCoupleList.get(i).getIdusers1());
					}

					if(friendCoupleList.get(i).getIdusers2()!=user.getIdusers()){
						friendsIdList.add(friendCoupleList.get(i).getIdusers2());
					}
				}

				for(int i=0;i<friendsIdList.size();i++){
					if(listUsersIdFound.contains(friendsIdList.get(i))){
						listUsersIdFound.remove(friendsIdList.get(i));
					}
				}

				for(int i=0;i<listUsersIdFound.size();i++){
					friendsUserList.add(UserService.getInstance().getUser(listUsersIdFound.get(i)));
				}

				context.setVariable("result", "Friend(s) Found :");
				context.setVariable("usersFound", friendsUserList);
			}
			
			else{
				context.setVariable("result", "No person found");
			}
			recherche = null;
		}
		templateEngine.process("addFriend", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String identity = request.getParameter("friendSearch");

		recherche = identity;

		response.sendRedirect("addFriend");
	}
}