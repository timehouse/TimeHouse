package miscStruts;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opensymphony.xwork2.conversion.TypeConversionException;

import _11_room.model.RoomVO;
import _11_room.model.RoomVOs;
import _13_roomtype.model.RoomTypeVO;

public class TypeConverterRoomVO extends StrutsTypeConverter {
    private List<RoomVO> list = new LinkedList<RoomVO>();
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
	//先轉為json陣列
	JSONArray rooms = null;
	if (values != null && values.length != 0) {
		JSONParser parser = new JSONParser();
		try {
		    rooms = (JSONArray) parser.parse(values[0]);
		    throw new TypeConversionException("Invalid JSONArray format.");
		} catch (ParseException e) {
			throw new TypeConversionException("Invalid JSONArray format.");
		}
	}
	System.out.println("rooms"+rooms);
	//將json陣列轉為List<RoomVO>
	for (int i = 0; i < rooms.size(); i++) {
		JSONObject JSobj = (JSONObject) rooms.get(i);
		// 接收
		String tempRoom_id = (String) JSobj.get("room_id");
		String tempRStatus = (String) JSobj.get("rStatus");
		String room_type = (String) JSobj.get("room_type");
		String rContext = (String) JSobj.get("rContext");
		String tempRoomType_id = (String) JSobj.get("roomType_id");

		// 驗證&轉換
		if (tempRoom_id == null || !tempRoom_id.matches("^\\d{1,5}$")) {
		    throw new TypeConversionException(String.format("roomId%d", i)+"請輸入1~5位的正確數字");
		}
		if (tempRStatus == null || (!tempRStatus.matches("true") && !tempRStatus.matches("false"))) {
		    throw new TypeConversionException(String.format("rStatus%d", i)+"房間狀態有誤");
		}
		if (room_type == null) {
		    throw new TypeConversionException(String.format("room_type%d", i)+"房型為空");
		}
		if (rContext == null) {
		    throw new TypeConversionException(String.format("rContext%d", i)+"房間狀態為空");
		}
		if (tempRoomType_id == null || !tempRoomType_id.matches("^\\d{1,2}$")) {
		    throw new TypeConversionException(String.format("roomType_id%d", i)+"請輸入1~2位的正確數字");
		}
			Integer id = Integer.parseInt(tempRoom_id);
			boolean rStatus = Boolean.valueOf(tempRStatus);
			Integer roomType_id = Integer.parseInt(tempRoomType_id);
			RoomVO rVo = new RoomVO();
			rVo.setRoom_id(id);
			rVo.setrStatus(rStatus);
			rVo.setrContext(rContext);
			RoomTypeVO rtVo = new RoomTypeVO();
			rtVo.setRoomType_id(roomType_id);
			rVo.setRoomType(rtVo);
			rVo.setRoom_type(room_type);
			list.add(rVo);
	}
	System.out.println("list"+list);
	if(!list.isEmpty()){
	    RoomVOs rVOs=new RoomVOs();
	    rVOs.setRoomVOs(list);
	    return rVOs;
	}
	return null;
    }

    @Override
    public String convertToString(Map context, Object o) {
	return null;
    }

}
