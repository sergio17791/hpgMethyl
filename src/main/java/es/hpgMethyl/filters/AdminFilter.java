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

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.user.GetUser.GetUser;
import es.hpgMethyl.usecases.user.GetUser.GetUserRequest;
import es.hpgMethyl.utils.FacesContextUtils;

public class AdminFilter implements Filter {
	
	final private String LOGIN_URL = "/faces/login.xhtml";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		User user = (User) req.getSession().getAttribute(FacesContextUtils.SESSION_USER);
		
		Boolean authorized = false;
		
		if(user != null && user.getActive() && !user.getRole().equals(UserRole.USER)) {
			try {
				user = new GetUser(new UserDAOHibernate()).execute(
						new GetUserRequest(user.getId())
				).getUser();
				
				if(!user.getActive()) {
					req.getSession().setAttribute(FacesContextUtils.SESSION_USER, null);
				} else if(!user.getRole().equals(UserRole.USER)) {
					authorized = true;
				}
				
			} catch (GetObjectException | UserNotFound e) {
				authorized = false;
			}				
		}
						
		if(!authorized) {
			res.sendRedirect(req.getContextPath() + LOGIN_URL);    
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
}
