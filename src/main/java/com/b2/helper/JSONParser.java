package com.b2.helper;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.b2.constant.FrameworkCommonConstants;

public class JSONParser {
	
	JSONObject jsonObject;
	String fileName;
	
	public JSONParser()
	{
	 	fileName = getConfigDirectory()+FrameworkCommonConstants.CONFIGURATION_FILE_DEFAULT;
	 			
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public JSONParser(String fileName)
	{
		this.fileName = fileName;
	}
	
	private JSONObject getJSONObject() throws Exception
	{
		if(fileName == null)
		{
			throw new Exception ("File name is not set for the parser. Call new JSONParser(String fileName) or call setFileName()");
		}
		
		try
		{
			jsonObject =  (JSONObject) new org.json.simple.parser.JSONParser().parse(new FileReader(fileName));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return jsonObject; 
		
	}
	
	public static String getBaseDirectory()
	{
		String path = System.getProperty("user.dir") +File.separator;
		return path;
	}
	
	public static String getSourceDirectory()
	{
		String baseDir = getBaseDirectory();
		//return org.apache.commons.io.FilenameUtils.separatorsToSystem(baseDir + FrameworkCommonConstants.SOURCE_DIR);
		return baseDir + FrameworkCommonConstants.SOURCE_DIR + File.separator;
	}
	
	public static String getConfigDirectory()
 	{
 		String srcDir = getSourceDirectory();
 		//return org.apache.commons.io.FilenameUtils.separatorsToSystem(srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.CONFIG_LOCATION);		
 		return srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.CONFIG_LOCATION;
 	}

	
	public String getString(String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		return (String)jsonObject.get(key);
		
	}
	
	public long getLong(String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		return (Long)jsonObject.get(key);
		
	}
	
	public boolean getBoolean(String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		return (Boolean)jsonObject.get(key);
		
	}
	
	
	public String getStringFromObject(String parentKey, String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONObject parentObject =  (JSONObject)jsonObject.get(parentKey);
		return (String)parentObject.get(key);
	}
	
	public ArrayList<String> getKeysFromObject(String parentKey) throws Exception
	{
		
		ArrayList<String> list = new ArrayList<>();
		
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONObject parentObject =  (JSONObject)jsonObject.get(parentKey);
		
		Iterator<String> iterator = parentObject.keySet().iterator();
		while(iterator.hasNext())
		{
			list.add(iterator.next());
		}
		return list;
		
		
		
	}
	
	
	public long getLongFromObject(String parentKey, String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONObject parentObject =  (JSONObject)jsonObject.get(parentKey);
		return (Long)parentObject.get(key);
	}
	
	public boolean getBooleanFromObject(String parentKey, String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONObject parentObject =  (JSONObject)jsonObject.get(parentKey);
		return (Boolean)parentObject.get(key);
	}
	
	public String getStringFromArray(String arrayName, int index) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONArray array =  (JSONArray)jsonObject.get(arrayName);
		
		return (String)array.get(index);
	}
	
	public long getLongFromArray(String arrayName, int index) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONArray array =  (JSONArray)jsonObject.get(arrayName);
		
		return (Long)array.get(index);
	}

	public boolean getBooleanFromArray(String arrayName, int index) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONArray array =  (JSONArray)jsonObject.get(arrayName);
		
		return (Boolean)array.get(index);
	}
	
	public String getStringFromArrayOfObjects(String arrayName, int index , String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONArray array =  (JSONArray)jsonObject.get(arrayName);
		
		JSONObject parent = (JSONObject)array.get(index);
		
		return (String)parent.get(key);
	}
	
	public long getLongFromArrayOfObjects(String arrayName, int index , String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONArray array =  (JSONArray)jsonObject.get(arrayName);
		
		JSONObject parent = (JSONObject)array.get(index);
		
		return (Long)parent.get(key);
	}
	
	public boolean getBooleanFromArrayOfObjects(String arrayName, int index , String key) throws Exception
	{
		if(jsonObject == null)
		{
			jsonObject = getJSONObject();
		}
		
		JSONArray array =  (JSONArray)jsonObject.get(arrayName);
		
		JSONObject parent = (JSONObject)array.get(index);
		
		return (Boolean)parent.get(key);
	}
	
	public static void main(String[] args) throws Exception
	{
		JSONParser parser = new JSONParser("src/test/resources/test.json");
		
		System.out.println(parser.getString("firstName"));
		System.out.println(parser.getLong("age"));
		System.out.println(parser.getBoolean("startup"));
		
		System.out.println(parser.getStringFromArray("founderNames", 1));
		System.out.println(parser.getLongFromArray("seatNumber", 1));
		System.out.println(parser.getBooleanFromArray("profitabilityForLast6Months", 1));
		
		System.out.println(parser.getStringFromObject("address", "city"));
		System.out.println(parser.getLongFromObject("address", "postalCode"));
		System.out.println(parser.getBooleanFromObject("address", "mnc"));
		
		System.out.println(parser.getStringFromArrayOfObjects("phoneNumbers", 1, "type"));
		System.out.println(parser.getLongFromArrayOfObjects("phoneNumbers", 1, "number"));
		System.out.println(parser.getBooleanFromArrayOfObjects("phoneNumbers", 1, "primary"));
		
	}
}
