package misc;

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
import javax.servlet.http.HttpSession;

import _01_admin.model.AdminVO;

@WebFilter(urlPatterns={"/02_Server/index.jsp"})
public class AdminLoginFilter implements Filter {

	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		HttpSession session = request.getSession();
		AdminVO temp = (AdminVO) session.getAttribute("AdminLogin");
		if(temp!=null){
			chain.doFilter(request, response);
		}else {
			String uri = request.getRequestURI();
			session.setAttribute("destination", uri);
			
			String path = request.getContextPath();
			response.sendRedirect(path+"/02_Server/_51_AdminLogin/AdminLogin.jsp");
		}
	}

	@Override
	public void destroy() {

	}

}
