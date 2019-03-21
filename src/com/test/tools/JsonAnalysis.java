package com.test.tools;

import java.util.Map;



import net.sf.json.JSONObject;

public class JsonAnalysis {

    final static String DEBUG = "JsonAnalysis";

	/**
	 * 
	 *
	 * @param
	 * @return json String
	 */
	public String getJsonString(Map map) { 

		JSONObject jsonObject = new JSONObject();
		if (map == null) {

			System.out.println(DEBUG + "getJsonString the param is null");
			return null;
		}
		jsonObject.putAll(map);
		return jsonObject.toString();

	}
	/**
	 *???
	 * @param jsonString
	 * @param key
	 * @return the value of the key 
	 * if it don't have the value  return null
	 */
	public static String getValue(String jsonString, String key) {
		if(jsonString == null || key == null){
			
			return null;
		}
		if (jsonString.contains(key)) {
			JSONObject jobj = JSONObject.fromObject(jsonString);
			return jobj.getString(key);

		} else {

			
			return null;
		}

		
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	
	/*public String getJsonArray(Object obj){
		String Jmsg =null;
		JsonMessage  d =null;
		if(obj instanceof JsonMessage){
			
			d =(JsonMessage)obj;
			
		}
		
		if(obj == null|| d==null){
			
			return null;
		}
		JSONArray ja =new JSONArray();
		ja.add(d);
		Jmsg =ja.toJSONString();
		
		return Jmsg;
		
		
		
	}*/

	public String getJsonByObject(Object obj){
		String Jmsg =null;
		
		if(obj != null){
			JSONObject jb =JSONObject.fromObject(obj);
			Jmsg =jb.toString();
		}
		return Jmsg;
		
		
	}

}
