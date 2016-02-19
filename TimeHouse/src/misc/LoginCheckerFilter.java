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

@WebFilter(urlPatterns={"/01_Client/_04_ReviseMInfo/*"
						,"/01_Client/memberCenter.jsp"
						,"/01_Client/_03_MemberPassword/ChangePassword.jsp"
})
public class LoginCheckerFilter implements Filter {

	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		HttpSession session = request.getSession();
		String temp = (String) session.getAttribute("LoginOK");
		if(temp!=null){
			chain.doFilter(request, response);
		}else {
			String uri = request.getRequestURI();
			session.setAttribute("dest", uri);
			
			String path = request.getContextPath();
			response.sendRedirect(path+"/01_Client/_02_MemberLogin/login.jsp");
		}
	}

	@Override
	public void destroy() {

	}

}
