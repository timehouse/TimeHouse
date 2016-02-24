package _13_news.controller;

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

import _08_notice.model.NoticeService;
import _08_notice.model.NoticeVO;
import _10_package.model.PackageVO;
import hibernate.util.HibernateUtil;


@WebServlet(
		urlPatterns={"/news.controller"}
		)
public class NoticeServlet extends HttpServlet {
	private static SimpleDateFormat sFormat = new SimpleDateFormat ("yyyy-MM-dd");
	private NoticeService noticeService = new NoticeService();
   
    @Override
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8"); //設定編碼
    	
		//接收資料
		String temp1 = request.getParameter("noticeId");
		String title = request.getParameter("title");
		String temp3 = request.getParameter("startdate");
		String temp4 = request.getParameter("enddate");
		String content = request.getParameter("content");
		String prodaction = request.getParameter("prodaction");
		
		System.out.println("noticeId=" + temp1);
		
		//轉換資料
				Map<String,String> error = new HashMap<String,String>();
				request.setAttribute("error",error);

				int noticeId = 0;
					if(temp1!=null && temp1.length()!=0){
					try {
						noticeId = Integer.parseInt(temp1);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						error.put("id", "id must be an integer");
					}
					}
				
				java.util.Date startdate = null;	
					if(temp3!=null && temp3.length()!=0){
	
						try {
							startdate = sFormat.parse(temp3);
						} catch (ParseException e) {
							error.put("startdate", "startdate must be a date");
							e.printStackTrace();
						}				
					}	
				
					java.util.Date enddate = null;	
					if(temp4!=null && temp4.length()!=0){
	
						try {
							enddate = sFormat.parse(temp4);
						} catch (ParseException e) {
							error.put("enddate", "enddate must be a date");
							e.printStackTrace();
						}				
					}	
					
		//驗證資料
					
					if("SelectId".equals(prodaction) || "Update".equals(prodaction)) 
					{
						if(noticeId==0) 
						{
						error.put("noticeId", "請輸入ID才能"+prodaction);
						}
					}
					
					if("Insert".equals(prodaction)){
						
						if(title.length()==0||title==null){
							error.put("title", "請輸入標題才能"+prodaction);
						}
						if(temp3.length()==0||temp3==null){
							error.put("startdate", "請輸入發佈時間才能"+prodaction);
						}
						if(temp4.length()==0||temp4==null){
							error.put("enddate", "請輸入結束時間才能"+prodaction);
						}
						if(content.length()==0||content==null){
							error.put("content", "請輸入內容才能"+prodaction);
						}	
					}
					
					
					if(error!=null && !error.isEmpty()){
						request.getRequestDispatcher(
								"/02_Server/_53_News/NewsOut.jsp").forward(request, response);
						return;
					}
					
					
		//呼叫model
					
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					
					NoticeVO bean = new NoticeVO();
					bean.setNoticeId(noticeId);
					bean.setTitle(title);
					bean.setStartdate(startdate);
					bean.setEnddate(enddate);			
					bean.setContent(content);
		
	   //根據model執行結果顯示view		
					if("Select".equals(prodaction)) {
						List<NoticeVO> result = noticeService.select();
						request.setAttribute("select", result);
						request.getRequestDispatcher(
								
								"/02_Server/_53_News/NewsOut.jsp").forward(request, response);
					}else if("Insert".equals(prodaction)) {
						NoticeVO result = noticeService.insert(bean);
						if(result==null) {
							error.put("action", "Insert failed");
						} else {
							request.setAttribute("insert", result);
						}
						request.getRequestDispatcher(
								"/02_Server/_53_News/NewsOut.jsp").forward(request, response);
					}else if("Update".equals(prodaction)) {
						noticeService.update(bean);
							
						request.getRequestDispatcher(
								"/02_Server/_53_News/NewsOut.jsp").forward(request, response);
					}else {
						error.put("action", "Unknown action: "+prodaction);
						request.getRequestDispatcher(
								"/02_Server/_53_News/News.jsp").forward(request, response);
					}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
