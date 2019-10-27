package org.kakarrot.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/member/myPage")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest res = (HttpServletRequest) request;
		HttpServletResponse req = (HttpServletResponse) response;
		
		if( res.getSession().getAttribute("member") == null ) {
			req.sendRedirect("/member/login");
			return;
		}
		
		chain.doFilter(request, response);
	}

}
