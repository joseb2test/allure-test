package com.b2.helper;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.b2.constant.FrameworkCommonConstants;

public class ConfigurationReader {
	
	
	JSONParser parser;
	
	public ConfigurationReader()
	{
	 	parser = new JSONParser(getConfigDirectory()+FrameworkCommonConstants.CONFIGURATION_FILE_DEFAULT);
	 			
	}
	
	public ConfigurationReader(String fileName)
	{
		parser = new JSONParser(fileName);
	}
	
	public String getBaseDirectory()
	{
		String path = System.getProperty("user.dir") +File.separator;
		return path;
	}
	
	public String getSourceDirectory()
	{
		String baseDir = getBaseDirectory();
		//return org.apache.commons.io.FilenameUtils.separatorsToSystem(baseDir + FrameworkCommonConstants.SOURCE_DIR);
		return baseDir + FrameworkCommonConstants.SOURCE_DIR + File.separator;
	}
	
	public String getConfigDirectory()
 	{
 		String srcDir = getSourceDirectory();
 		//return org.apache.commons.io.FilenameUtils.separatorsToSystem(srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.CONFIG_LOCATION);		
 		return srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.CONFIG_LOCATION;
 	}

	
	public String getString(String key) throws Exception
	{
		return parser.getString(key);
		
	}
	
	public long getLong(String key) throws Exception
	{
		return parser.getLong(key);
		
	}
	
	public boolean getBoolean(String key) throws Exception
	{
		return parser.getBoolean(key);
		
	}
	
	public String getStringFromObject(String parentKey, String key) throws Exception
	{
		return parser.getStringFromObject(parentKey, key);
		
	}
	
	public long getLongFromObject(String parentKey, String key) throws Exception
	{
		return parser.getLongFromObject(parentKey, key);
	}
	
	public boolean getBooleanFromObject(String parentKey, String key) throws Exception
	{
		return parser.getBooleanFromObject(parentKey, key);
	}
	
	public String getStringFromArray(String arrayName, int index) throws Exception
	{
		return parser.getStringFromArray(arrayName, index);
	}
	
	public long getLongFromArray(String arrayName, int index) throws Exception
	{
		return getLongFromArray(arrayName, index);
	}

	public boolean getBooleanFromArray(String arrayName, int index) throws Exception
	{
		return getBooleanFromArray(arrayName, index);
	}
	
	public String getStringFromArrayOfObjects(String arrayName, int index , String key) throws Exception
	{
		return parser.getStringFromArrayOfObjects(arrayName, index, key);
	}
	
	public long getLongFromArrayOfObjects(String arrayName, int index , String key) throws Exception
	{
		return parser.getLongFromArrayOfObjects(arrayName, index, key);
	}
	
	public boolean getBooleanFromArrayOfObjects(String arrayName, int index , String key) throws Exception
	{
		return parser.getBooleanFromArrayOfObjects(arrayName, index, key);
	}
	
	
	public ArrayList<String> getKeysFromObject(String parent) throws Exception
	{
		return parser.getKeysFromObject(parent);
	}
	
	public static void main(String[] args) throws Exception
	{
		ConfigurationReader reader = new ConfigurationReader();
		
		System.out.println(reader.getString("environment"));
		System.out.println(reader.getLong("retry_count"));
		System.out.println(reader.getBoolean("local_run"));
		System.out.println(reader.getStringFromObject("dev", "url"));
		
		
		
	}
}
