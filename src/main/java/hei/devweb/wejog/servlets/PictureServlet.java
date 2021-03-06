package hei.devweb.wejog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;



import hei.devweb.wejog.entities.ImageS3Util;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.managers.UserService;


/**
 * Servlet implementation class PictureServlet
 * @param <RedirectAttributes>
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/user/picture", "/admin/picture"})
public class PictureServlet<RedirectAttributes> extends AbstractGenericServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		context.setVariable("User", httpRequest.getSession().getAttribute("user"));

		templateEngine.process("picture", context, resp.getWriter());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("user");
		
		String amazonPath = "https://s3.eu-west-2.amazonaws.com/wejog/"+user.getIdusers().toString();

		Part picture = req.getPart("image");

		ImageS3Util.uploadImageToAWSS3(picture, user.getIdusers().toString());
		
		UserService.getInstance().updatePicture(user.getIdusers(), amazonPath);
		
		user.setPicturePath(amazonPath);
		
		resp.sendRedirect("profil");
	}
}