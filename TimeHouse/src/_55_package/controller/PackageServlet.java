package _55_package.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import _10_package.model.PackageService;
import _10_package.model.PackageVO;
import _13_roomtype.model.RoomTypeVO;
import hibernate.util.HibernateUtil;

@WebServlet(
		urlPatterns={"/package.controller"}
)
public class PackageServlet extends HttpServlet {
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private PackageService packageService = new PackageService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		//接收資料
				String temp1   = request.getParameter("package_id");
				String name    = request.getParameter("name");
				String temp2   = request.getParameter("price");
				String temp3   = request.getParameter("start_date");
				String temp4   = request.getParameter("end_date");
				String context = request.getParameter("context");
				String prodaction = request.getParameter("prodaction");
		//存放資料錯誤
				Map<String, String> error = new HashMap<String, String>();
				request.setAttribute("error", error);
				
		//轉換資料		
				int package_id = 0;
				if(temp1!=null && temp1.length()!=0) 
				{
					try 
					{
					package_id = Integer.parseInt(temp1);
					} 
					catch (NumberFormatException e) 
					{
					e.printStackTrace();
					error.put("package_id", "優惠代號只能是數字");
					}
				}
				
				int price = 0;
				if(temp2!=null && temp2.length()!=0) 
				{
					try 
					{
					price = Integer.parseInt(temp2);
					} 
					catch (NumberFormatException e) 
					{
					e.printStackTrace();
					error.put("price", "價格只能是數字");
					}
				}
				
				java.util.Date start_date = null;
				if(temp3!=null && temp3.length()!=0) 
				{
					try 
					{
					start_date = sFormat.parse(temp3);
					} 
					catch (ParseException e) 
					{
					e.printStackTrace();
					error.put("start_date", "日期格式 yyyy-MM-dd");
					}
				}

				java.util.Date end_date = null;
				if(temp4!=null && temp4.length()!=0) 
				{
					try 
					{
					end_date = sFormat.parse(temp4);
					} 
					catch (ParseException e) 
					{
					e.printStackTrace();
					error.put("end_date", "日期格式 yyyy-MM-dd");
					}
				}
				
		//驗證資料
				
				
				
				if("SelectId".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction)) 
				{
					if(package_id==0) 
					{
					error.put("package_id", "請輸入ID才能"+prodaction);
					}
				}
				
				if("Insert".equals(prodaction)){
					if(name.length()==0||name==null){
						error.put("name", "請輸入名字才能"+prodaction);
					}
					if(temp2.length()==0||temp2==null){
						error.put("price", "請輸入價錢才能"+prodaction);
					}
					if(temp3.length()==0||temp3==null){
						error.put("start_date", "請輸入時間才能"+prodaction);
					}
					if(temp4.length()==0||temp4==null){
						error.put("end_date", "請輸入時間才能"+prodaction);
					}
					if(context.length()==0||context==null){
						error.put("context", "請輸入介紹才能"+prodaction);
					}	
				}
				
				if(error!=null && !error.isEmpty())
				{
					request.getRequestDispatcher(
					"/02_Server/_55_Package/Package.jsp").forward(request, response);
					return;
				}
				
				
			
		//呼叫model
				
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				
				PackageVO bean = new PackageVO();
				bean.setPackage_id(package_id);
				bean.setName(name);
				RoomTypeVO roomTypeVO =(RoomTypeVO) session.get(RoomTypeVO.class, 3);
				bean.setRoomtype(roomTypeVO);
				bean.setPrice(price);
				bean.setStart_date(start_date);
				bean.setEnd_date(end_date);			
				bean.setContext(context);
						
				
		//根據model執行結果顯示view
						if("SelectId".equals(prodaction)){
						
						PackageVO	result= packageService.select(package_id);		
						System.out.println("*****"+result);
						request.setAttribute("selectid", result);
						request.getRequestDispatcher(
									"/02_Server/_55_Package/PackageOut.jsp").forward(request, response);
						}
						else if("Select".equals(prodaction)) {
							List<PackageVO> result = packageService.select();
							request.setAttribute("select", result);
							request.getRequestDispatcher(
									"/02_Server/_55_Package/PackageOut.jsp").forward(request, response);
						} else if("Insert".equals(prodaction)) {
							PackageVO result = packageService.insert(bean);
							if(result==null) {
								error.put("action", "Insert failed");
							} else {
								request.setAttribute("insert", result);
							}
							request.getRequestDispatcher(
									"/02_Server/_55_Package/Package.jsp").forward(request, response);
						} else if("Update".equals(prodaction)) {
							 packageService.update(bean);
							
							request.getRequestDispatcher(
									"/02_Server/_55_Package/Package.jsp").forward(request, response);
						} else if("Delete".equals(prodaction)) {
							packageService.delete(package_id);
							
							request.getRequestDispatcher(
									"/02_Server/_55_Package/Package.jsp").forward(request, response);
						} else {
							error.put("action", "Unknown action: "+prodaction);
							request.getRequestDispatcher(
									"/02_Server/_55_Package/Package.jsp").forward(request, response);
						}
		
	}
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
