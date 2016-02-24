package miscStruts;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class TypeConverterJSONArray extends StrutsTypeConverter {
	private JSONArray json;

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values != null && values.length != 0) {
			JSONParser parser = new JSONParser();
			try {
				json = (JSONArray) parser.parse(values[0]);
				return json;
			} catch (ParseException e) {
				e.printStackTrace();
				throw new TypeConversionException("Invalid JSONArray format.");
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object obj) {
		if(obj!=null && (obj instanceof org.json.simple.JSONArray)) {
			String temp = ((org.json.simple.JSONArray) obj).toJSONString();
			return temp;
		}
		return null;
	}

}
