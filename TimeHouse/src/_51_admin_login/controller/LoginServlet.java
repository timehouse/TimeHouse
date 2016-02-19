package _51_admin_login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_admin.model.AdminService;
import _01_admin.model.AdminVO;

@WebServlet(urlPatterns={"/admin/login.controller"})
public class LoginServlet extends HttpServlet{

	AdminService service = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String pid = request.getParameter("id");
		String password = request.getParameter("password");
		
		Map<String,String> error = new HashMap<String,String>();
		request.setAttribute("error",error);

		if(pid==null || pid.trim().length()==0){
			error.put("id", "Please Insert Admin ID");
		}
		if(password==null || password.trim().length()==0){
			error.put("password", "Please Insert Admin Password");
		}
		
		int id = 0;
		if(pid!=null && pid.trim().length()>0){
			try {
				id = Integer.parseInt(pid);
			} catch (NumberFormatException e) {
				error.put("id", "ID must be Integer");
			}
		}
		
		if(error!=null && !error.isEmpty()){
			request.getRequestDispatcher("/02_Server/_51_AdminLogin/AdminLogin.jsp").forward(request, response);
			return;
		};	
		
		AdminVO bean = service.login(id,password);;		
		
		if(bean==null){
			error.put("notExist", "Account Do Not Exist");
			request.getRequestDispatcher("/02_Server/_51_AdminLogin/AdminLogin.jsp").forward(request, response);
			return;
		}else{
			session.setAttribute("AdminLogin", bean);
			
			String dest = (String) session.getAttribute("destination");
			if(dest!=null && dest.length()!=0) {
				session.removeAttribute("destination");
				response.sendRedirect(dest);
			} else {
				String path = request.getContextPath();
				response.sendRedirect(path+"/02_Server/index.jsp");
			}
		}		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
