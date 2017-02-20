package hei.devweb.wejog.servlets;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet("/connexion")
public class ConnexionServlet extends AbstractGenericServlet  {

	private static final long serialVersionUID = -1488650966375438002L;

	private Map<String, String> utilisateursAutorises;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		TemplateEngine templateEngine =this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, req.getServletContext());
		templateEngine.process("connexion", context, resp.getWriter());
	}
	
	@Override
	public void init() throws ServletException {
		utilisateursAutorises = new HashMap<>();
		utilisateursAutorises.put("habib", "fc859cdc0c5fff3178da076e767acddcabaf187447bccc45:37819be8b0bc0c93e4baf89faa5676d9317e54ef2e6742d9");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiantSaisi = request.getParameter("mail");
		String motDePasseSaisi = request.getParameter("password");
		try {
			if(utilisateursAutorises.containsKey(identifiantSaisi) && MotDePasseUtils.validerMotDePasse(motDePasseSaisi, utilisateursAutorises.get(identifiantSaisi))) {
				request.getSession().setAttribute("utilisateurConnecte", identifiantSaisi);
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		} 
		response.sendRedirect("home");
	}

}