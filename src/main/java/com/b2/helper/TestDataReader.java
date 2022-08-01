package com.b2.helper;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.b2.constant.FrameworkCommonConstants;

public class TestDataReader extends ConfigurationReader{
	
	public TestDataReader()
	{
	  parser = null;
	}
	public void setTestEnvironment(String envName)
	{
		String testFileName = getTestDataDirectory()+ File.separator + envName+".json";
		parser = new JSONParser(testFileName);
	}
	
	public TestDataReader(String envName)
	{
		String testFileName = getTestDataDirectory()+ File.separator + envName+".json";
		parser = new JSONParser(testFileName);
	}
	
	public static String getTestDataDirectory()
	{
		String srcDir = System.getProperty("user.dir") +File.separator+ FrameworkCommonConstants.SOURCE_DIR;
		return (srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.DATA_LOCATION);		
	}
	
	
		public static void main(String[] args) throws Exception
	{
		TestDataReader reader = new TestDataReader("prod");
		
		System.out.println(reader.getString("tenant"));
		System.out.println(reader.getStringFromObject("icp", "name"));
		
	}
}
