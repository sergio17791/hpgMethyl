package es.hpgMethyl.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.hpgMethyl.entities.User;
import es.hpgMethyl.utils.FacesContextUtils;

public class GuestFilter implements Filter {
	
	final private String ROOT_URL = "/";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		User user = (User) req.getSession().getAttribute(FacesContextUtils.SESSION_USER);
			
		if(user != null) {
			res.sendRedirect(req.getContextPath() + ROOT_URL);    
			return;			
		}
						
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
}
