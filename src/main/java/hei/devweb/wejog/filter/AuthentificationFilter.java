package hei.devweb.wejog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthentificationFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");   
		if(identifiant == null || "".equals(identifiant)) {        
			HttpServletResponse httpResponse = (HttpServletResponse) response;     
			httpResponse.sendRedirect("/connexion");
			return;   
			}     
		chain.doFilter(request, response); 
	}

	@Override
	public void destroy() {
	
	}

}
