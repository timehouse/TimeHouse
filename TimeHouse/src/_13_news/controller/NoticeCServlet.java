package _13_news.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _08_notice.model.NoticeService;
import _08_notice.model.NoticeVO;


@WebServlet(
		urlPatterns={"/newsc.controller"}
		)
public class NoticeCServlet extends HttpServlet {
	private static SimpleDateFormat sFormat = new SimpleDateFormat ("yyyy-MM-dd");
	private NoticeService noticeService = new NoticeService();
	
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //設定編碼
		
		List<NoticeVO> result = noticeService.select();
		request.setAttribute("select", result);
		request.getRequestDispatcher(
				"/01_Client/_13_News/NewsOut.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
