package com.human.thymeleaf.util;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class JsonUtil {

	public String stringify(List<String> list) {
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		return obj.toString();
	}
	
	public List<String> parse(String jsonStr) {
		JSONParser parser = new JSONParser();
		List<String> list = null;
		if (jsonStr == null || jsonStr.equals(""))
			return list;
		try {
			JSONObject jsonList = (JSONObject) parser.parse(jsonStr);
			JSONArray jsonArr = (JSONArray) jsonList.get("list");
			list = (List<String>) jsonArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
