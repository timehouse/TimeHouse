package _56_broomType.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import _12_roompic.model.RoomPicVO;
import _13_roomtype.model.BroomTypeService;

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet(
		urlPatterns={"/123"}
)
public class BroomTypeServlet extends HttpServlet {
	private BroomTypeService btService = new BroomTypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		String fldName ="";
		String value ="";
		String fileName="";
		long sizeInBytes = 0;
		InputStream is = null;
		OutputStream os = null;
	
		int roomPicid  = 0;
		int roomTypeid = 0;	
		//確認服務項目
		if("uploadAll".equals(action)){
			//輸入&驗證
			Collection<Part> parts = req.getParts();
			if (parts != null){
				for(Part p:parts){
					fldName = p.getName();
					value = req.getParameter(fldName);
					if(p.getContentType() == null){
						if(fldName.equals("roomPicid")){
							if(value.matches("\\d{1,}")){
								roomPicid = Integer.parseInt(value);
							} else {
								System.out.println("error:roomPicid未輸入數字");
							}
						}else if(fldName.equals("roomTypeid")){
							if(value.matches("\\d{1,}")){
								roomTypeid = Integer.parseInt(value);
							} else {
								System.out.println("error:roomTypeid未輸入數字");
							}	
						} 
					}
					else{
						//file才有ContentType
						fileName= btService.getFileName(p);
						if(fileName!=null){
							fileName= btService.adjustFileName(fileName, btService.IMAGE_FILENAME_LENGTH);
							if(fileName !=null && fileName.trim().length()>0){
								//抓取InputStream
								//sizeInBytes = p.getSize();
								is = p.getInputStream();
							} else {
								System.out.println("error:file有問題");
							}
						} else {
							System.out.println("error:沒有file");
						}
					}
				}
			} else{
				System.out.println("error:沒有part");
			}
			
			System.out.println("準備儲存");
			//呼叫:存入資料
			btService.saveImg(roomPicid, roomTypeid, is,fileName);
		}
		if("getImg".equals(action)){
			RoomPicVO rPicVO=btService.getImgByPicid(1);
			is = new ByteArrayInputStream(rPicVO.getRpPic()); 
			os = resp.getOutputStream();
			int count =0;
			byte[] bytes = new byte[1024];
			while ((count = is.read(bytes)) != -1) {
				os.write(bytes, 0, count);
			}
			System.out.println("有呼叫getImg");
			//req.setAttribute("picVo1", btService.getImgByPicid(1));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
