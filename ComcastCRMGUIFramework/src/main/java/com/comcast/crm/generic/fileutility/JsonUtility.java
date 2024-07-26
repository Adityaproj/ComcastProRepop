package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility {

	public String getDataFromJsonFile(String Key) throws Throwable {
		FileReader f=new FileReader("./configAppData/appcommondata.json");
		JSONParser parse=new JSONParser();
		Object obj = parse.parse(f);
		JSONObject map = (JSONObject)obj;
		 String data = (String) map.get(Key);
		return data;
		

	}

}
