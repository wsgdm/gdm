package cn.tx.utils;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class JSONUtils {
	
	
	
	public static void printJSONArray(HttpServletResponse response, Collection coll, String [] exclude) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(exclude);
		JSONArray jsonArray = JSONArray.fromObject(coll, jc);
		String result = jsonArray.toString();
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
