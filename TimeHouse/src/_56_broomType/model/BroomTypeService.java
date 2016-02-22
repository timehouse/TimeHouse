package _56_broomType.model;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.Part;

import com.sun.swing.internal.plaf.basic.resources.basic;
import com.sun.swing.internal.plaf.basic.resources.basic_sv;

import _12_roompic.model.RoomPicDAO;
import _12_roompic.model.RoomPicDAO_interface;
import _12_roompic.model.RoomPicVO;
import _13_roomtype.model.RoomTypeDAO;
import _13_roomtype.model.RoomTypeVO;
import _56_broomType.controller.BroomTypeServlet;
import javafx.scene.image.Image;

public class BroomTypeService {

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
		RoomPicDAO rpDao = new RoomPicDAO();
		
		RoomPicVO rpVo =rpDao.findByPrimaryKey(i);
		if(rpVo!=null)
			return rpVo;
		return null;
	}
	
	public void saveImg(int roomPicid,int roomTypeid, InputStream is,String fileName,final String path) {
		BufferedInputStream in;
		BufferedOutputStream out;
		RoomPicDAO rpDao = new RoomPicDAO();
		RoomTypeDAO rtDAO = new RoomTypeDAO();
		try {
			// 設定除了圖以外的資料
			RoomPicVO rPicVO = rpDao.findByPrimaryKey(roomPicid);
			if(rPicVO==null){
				rPicVO = new RoomPicVO();
				rPicVO.setRoomPic_id(roomPicid);
			}
			
			RoomTypeVO rTypeVO = rtDAO.findByPrimaryKey(roomTypeid);
			rPicVO.setRoomType(rTypeVO);
			//讀取圖片串流
			in = new BufferedInputStream(is); 
			BufferedImage bImage = ImageIO.read(in);
			in.close();
			//壓縮圖片
			int h=113;
			int w=200;
			BufferedImage newimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			newimg.getGraphics().drawImage(bImage, 0, 0, w, h, null);
			
//			if(in.available()>0){
//			    out.write(in.read(new byte[in.available()]));
//			}
		
			//將原圖和縮圖寫入伺服器
			File file1 = new File(path+"/img/roomPics/"+fileName);
			file1.createNewFile();
			File file2 = new File(path+"/img/roomPics/small"+fileName);
			file2.createNewFile();
			System.out.println(path);
			FileOutputStream fStream1 = new FileOutputStream(file1);
			FileOutputStream fStream2 = new FileOutputStream(file2);
			out=  new BufferedOutputStream(fStream1);
			ImageIO.write(bImage, "jpg", out);
			fStream1.close();
			out=  new BufferedOutputStream(fStream2);
			ImageIO.write(newimg, "jpg", out);
			fStream2.close();
			
			// 將圖片寫入資料庫
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			out = new BufferedOutputStream(bs);
			ImageIO.write(newimg, "jpg", out);
			rPicVO.setRpPic(bs.toByteArray());
			rPicVO.setRpPicName(fileName);
//			rpDao.insert(rPicVO);
			rpDao.update(rPicVO);
			
			bs.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<RoomTypeVO> getRoomTypes(){
		RoomTypeDAO rtDAO = new RoomTypeDAO();
	    return rtDAO.getAll();
	}

	public void updateOneRoomType(int id, String room_type, int rtCapacity_num, int rtCount, int rtWeekday_rate,
		int rtWeekend_rate) {
		RoomTypeDAO rtDAO = new RoomTypeDAO();
	    RoomTypeVO rtVO = new RoomTypeVO();
	    rtVO.setRoomType_id(id);
	    rtVO.setRoom_type(room_type);
	    rtVO.setRtCapacity_num(rtCapacity_num);
	    rtVO.setRtCount(rtCount);
	    rtVO.setRtWeekday_rate(rtWeekday_rate);
	    rtVO.setRtWeekend_rate(rtWeekend_rate);
	    rtDAO.insert(rtVO);
	}

	public void delOneRoomType(int id) {
		RoomTypeDAO rtDAO = new RoomTypeDAO();
	    rtDAO.delete(id);
	}
}
