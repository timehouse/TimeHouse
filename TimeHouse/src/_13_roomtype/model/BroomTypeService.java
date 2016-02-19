package _13_roomtype.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import _12_roompic.model.RoomPicDAO;
import _12_roompic.model.RoomPicDAO_interface;
import _12_roompic.model.RoomPicVO;

public class BroomTypeService {
	private RoomPicDAO rpDao = new RoomPicDAO();
	private RoomTypeDAO rtDAO = new RoomTypeDAO();
	
	// 從老師購物車co來的part取檔名
	public String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	// 從老師購物車co來的縮檔名
    // 本方法調整fileName的長度小於或等於maxLength。
	// 如果fileName的長度小於或等於maxLength，直接傳回fileName
	// 否則保留最後一個句點與其後的附檔名，縮短主檔名使得fileName的總長度
	// 等於maxLength。
	public final int IMAGE_FILENAME_LENGTH = 20;
	public String adjustFileName(String fileName, int maxLength) {
		  int length = fileName.length();
		  if ( length <= maxLength ) {
			  return fileName ;
		  }
    	  int n      = fileName.lastIndexOf(".");
          int sub    = fileName.length() - n - 1;
          fileName = fileName.substring(0, maxLength-1-sub) + "." 
                       + fileName.substring(n+1); 
		return fileName;
	}
	
	public RoomPicVO getImgByPicid(int i){
		RoomPicVO rpVo =rpDao.findByPrimaryKey(i);
		if(rpVo!=null)
			return rpVo;
		return null;
	}
	
	public void saveImg(int roomPicid,int roomTypeid, InputStream is,String fileName) {
		BufferedInputStream in;
		BufferedOutputStream out;
		try {
			// 設定除了圖以外的資料
			RoomPicVO rPicVO = new RoomPicVO();
			rPicVO.setRoomPic_id(roomPicid);
			RoomTypeVO rTypeVO = rtDAO.findByPrimaryKey(roomTypeid);
			rPicVO.setRoomType(rTypeVO);

			// // 讀取圖片
			in = new BufferedInputStream(is);
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			out = new BufferedOutputStream(bs);
			byte[] data = new byte[1024];
			int length;
			while ((length = in.read(data)) != -1) {
				out.write(data);
			}

			// 將圖片傳出
			rPicVO.setRpPic(bs.toByteArray());
			rPicVO.setRpPicName(fileName);
			RoomPicDAO_interface rtI = rpDao;
			rtI.insert(rPicVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
