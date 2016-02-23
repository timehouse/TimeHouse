package _56_broomType.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import _12_roompic.model.RoomPicVO;
import _13_roomtype.model.RoomTypeVO;
import _56_broomType.model.BroomTypeService;

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024
	* 1024 * 500 * 5)
@WebServlet(urlPatterns = { "/broomType/broomTypeServlet" })
public class BroomTypeServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String action = req.getParameter("action");
	System.out.println("action:" + action);
	// 確認服務項目
	if ("uploadAll".equals(action)) {
	    uploadAll(req, resp);
	} else if ("getImg".equals(action)) {
		getImgService(req, resp);
	} else if ("listRoomType".equals(action)) {
	    listRoomType(req, resp);
	} else if ("subOne".equals(action)) {
	    suboneService(req, resp);
	} else if ("delOne".equals(action)) {
	    deloneService(req, resp);
	} 	
    }

    private void deloneService(HttpServletRequest req, HttpServletResponse resp) {
	// 建立service
	BroomTypeService btService = new BroomTypeService();
	// 輸入
	String tempId = req.getParameter("id");
	// 驗證&轉換
	int id = Integer.valueOf(tempId);
	// 呼叫model
	btService.delOneRoomType(id);
	// 收資料服務不回傳顯示
    }

    private void suboneService(HttpServletRequest req, HttpServletResponse resp) {
	// 建立service
	BroomTypeService btService = new BroomTypeService();
	// 輸入
	String tempId = req.getParameter("id");
	String room_type = req.getParameter("room_type");
	String tempRtCapacity_num = req.getParameter("rtCapacity_num");
	String tempRtCount = req.getParameter("rtCount");
	String tempRtWeekday_rate = req.getParameter("rtWeekday_rate");
	String tempRtWeekend_rate = req.getParameter("rtWeekend_rate");
	// 驗證&轉換

	int id = Integer.valueOf(tempId);
	int rtCapacity_num = Integer.valueOf(tempRtCapacity_num);
	int rtCount = Integer.valueOf(tempRtCount);
	int rtWeekday_rate = Integer.valueOf(tempRtWeekday_rate);
	int rtWeekend_rate = Integer.valueOf(tempRtWeekend_rate);

	// 呼叫model
	btService.updateOneRoomType(id, room_type, rtCapacity_num, rtCount, rtWeekday_rate, rtWeekend_rate);
	// 收資料服務不回傳顯示
    }

    private void uploadAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	// 建立service
	BroomTypeService btService = new BroomTypeService();
	// 建立map
	Map<String, String> errorMap = null;
	errorMap = new HashMap<String, String>();
	req.getSession().setAttribute("errorMap", errorMap);

	// 參數列表
	String fldName = "";
	String value = "";
	String fileName = "";
	InputStream is = null;

	int roomPicid = 0;
	int roomTypeid = 0;
	// 輸入&驗證
	Collection<Part> parts = req.getParts();
	if (parts != null) {
	    for (Part p : parts) {
		fldName = p.getName();
		value = req.getParameter(fldName);
		if (p.getContentType() == null) {
		    if (fldName.equals("roomPicid")) {
			if (value.matches("\\d{1,}")) {
			    roomPicid = Integer.parseInt(value);
			} else {
			    errorMap.put("roomPicid", "error:roomPicid未輸入數字");
			}
		    } else if (fldName.equals("roomTypeid")) {
			if (value.matches("\\d{1,}")) {
			    roomTypeid = Integer.parseInt(value);
			} else {
			    errorMap.put("roomTypeid", "error:roomTypeid未輸入數字");
			}
		    }
		} else {
		    // file才有ContentType
		    fileName = btService.getFileName(p);
		    System.out.println("fileName:" + fileName);
		    if (fileName != null) {
			fileName = btService.adjustFileName(fileName, btService.IMAGE_FILENAME_LENGTH);
			if (fileName != null && fileName.trim().length() > 0) {
			    // 抓取InputStream
			    is = p.getInputStream();
			} else {
			    errorMap.put("file", "error:file有問題");
			}
		    } else {
			errorMap.put("file", "error:沒有file");
		    }
		}
	    }
	} else {
	    errorMap.put("part", "error:沒有part");
	}

	// 呼叫:存入資料
	if (errorMap.isEmpty()) {
	    System.out.println("準備儲存");
	    final String Path = getServletContext().getRealPath("/");
	    btService.saveImg(roomPicid, roomTypeid, is, fileName,Path);
	}
	listRoomType(req,resp);
	//resp.sendRedirect(req.getContextPath() + "/02_Server/_56_RoomType/RoomType.jsp");
    }

    private void getImgService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	// 建立service
	BroomTypeService btService = new BroomTypeService();
	int imgid = Integer.parseInt(req.getParameter("imgid"));
	InputStream is = null;
	OutputStream os = null;
	
	RoomPicVO rPicVO = btService.getImgByPicid(imgid);
	is = new ByteArrayInputStream(rPicVO.getRpPic());
	os = resp.getOutputStream();
	int count = 0;
	byte[] bytes = new byte[1024];
	while ((count = is.read(bytes)) != -1) {
	    os.write(bytes, 0, count);
	}
	// req.setAttribute("picVo1", btService.getImgByPicid(1));
    }

    private void listRoomType(HttpServletRequest req, HttpServletResponse resp) {
	// 建立service和錯誤訊息
	BroomTypeService btService = new BroomTypeService();

	Map<String, String> errorMap = null;
	errorMap = new HashMap<String, String>();
	req.getSession().setAttribute("errorMap", errorMap);

	// 宣告參數:roomTypeVOs
	List<RoomTypeVO> roomTypeVOs = null;

	roomTypeVOs = btService.getRoomTypes();
	req.setAttribute("roomTypes", roomTypeVOs);
	for (RoomTypeVO rtVo : roomTypeVOs) {
	    rtVo.setRoom_type(rtVo.getRoom_type().trim());	
	}

	
	//顯示網頁
	if (roomTypeVOs != null) {
	    try {
		req.getRequestDispatcher("/02_Server/_56_RoomType/RoomType.jsp").forward(req, resp);
	    } catch (ServletException | IOException e) {
		e.printStackTrace();
	    }
	} else {
	    errorMap.put("roomTypeVOs", "roomTypeVOs null");
	}
	return;
    }
}
