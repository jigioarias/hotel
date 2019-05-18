package co.com.hoteles.turin.filters;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Codigo Realizado por Natali Monsalve Ramirez Derechos reservados de Natali
 * Monsalve Ramirez
 */


public class TurinFilter implements Filter {


	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

	    HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURL = request.getContextPath() + "/login.xhtml";

        boolean loggedIn = (session != null) && (session.getAttribute("usuario") != null);
        int loginRequest = request.getRequestURI().indexOf(loginURL);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
        
      /* System.out.println(request.getRequestURI());
       System.out.println(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
       System.out.println(loggedIn);
        */
        if (loggedIn || loginRequest!=-1 || resourceRequest) {
          
            chain.doFilter(request, response); // So, just continue request.
        }else {
            response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.
        }
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	
	public void destroy() {
		// TODO Auto-generated method stub

	}

}