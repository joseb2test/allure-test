package com.b2.helper;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.b2.api.API.HTTPMethod;
import com.b2.constant.FrameworkCommonConstants;

public class APIDataReader extends ConfigurationReader{
	
	String fileName;
	
	public APIDataReader()
	{
	  parser = null;
	}

	public APIDataReader(String apiName, HTTPMethod method) throws Exception
	{
		String apiDataDir = getAPIDataDirectory();
		parser = new JSONParser(getAPIFileName(apiName+"_"+method.toString().toLowerCase(), apiDataDir));
	
	}
	
	public void setTestEnvironment(String apiName) throws Exception
	{
		String apiDataDir = getAPIDataDirectory();
		parser = new JSONParser(getAPIFileName(apiName, apiDataDir));
		
	}

	private String getAPIFileName(String apiName,String apiDataDir) throws Exception
	{
		File dir = new File(apiDataDir);
		String fileName = findAPIFile(apiName, apiDataDir);
		if(fileName == null)
		{
			throw new Exception("There is no API data file'"+apiName+".json' defined in "+apiDataDir);
		}
		return fileName;
	}
	
   public String findAPIFile(String apiName, String apiDataDir) {

        File root = new File( apiDataDir );
        File[] list = root.listFiles();
        if (list == null) return null;

        for ( File f : list ) 
        {
            if ( f.isDirectory() ) 
            {
            	findAPIFile( apiName, f.getAbsolutePath() );
                
            }
            else 
            {
            
            	if(f.getName().startsWith(apiName) && f.getName().endsWith(".json"))
            	{
            		fileName = f.getAbsolutePath();
            	}
               
            }
        }
        
        return fileName;
    }
	

	public static String getAPIDataDirectory()
	{
		String srcDir = System.getProperty("user.dir") +File.separator+ FrameworkCommonConstants.SOURCE_DIR;
		return (srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.API_DATA_LOCATION);		
				
	}
	
	
		public static void main(String[] args) throws Exception
	{
		APIDataReader reader = new APIDataReader("insyncwebhook", HTTPMethod.POST);
		System.out.println(reader.getString("uri"));
	
		ArrayList<String> keys = reader.getKeysFromObject("querystring");
		
		for(String key : keys)
		{
			System.out.println(key);
		}
		
	}
}
