package cn.tx.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;


public class DateConversion extends StrutsTypeConverter {

	
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class class1) {
		Date date = null;
		if(arg1 != null && arg1.length > 0){
			if(StringUtils.isNotBlank(arg1[0]) && class1 == Date.class){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = sdf.parse(arg1[0]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return date;
	}

	
	@Override
	public String convertToString(Map arg0, Object arg1) {
		String str = "";
		if(arg1 != null && (arg1.getClass() == Date.class || arg1.getClass() == Timestamp.class)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			str = sdf.format(arg1);		
		}
		return str;
	}

}
