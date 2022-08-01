package com.b2.helper;

import java.io.File;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.b2.constant.FrameworkCommonConstants;


public class LocatorHelper extends DataHelper{

	private static Properties locatorProperties = getLocatorProperties();
	
	public static String getLocator() {
		StackTraceElement[] elements = new Throwable().getStackTrace();
		if (locatorProperties == null){
			locatorProperties = getLocatorProperties();
		}
		
		String locator = (String)locatorProperties.get(elements[1].getClassName() + "." +  elements[1].getMethodName());

		return (String) locatorProperties.get(elements[1].getClassName() + "." +  elements[1].getMethodName());
	}
	
  	 public static Properties getLocatorProperties(){ //throws IOException {
    	 String fileLocation = getlocatorsDirectory();
  		Properties prop = null;
		try {
			prop = getProperties(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return prop;
 	  }
  	 
  	 public static String getlocatorsDirectory()
  	 {
		String srcDir =  System.getProperty("user.dir") + File.separator + FrameworkCommonConstants.SOURCE_DIR;
		return srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.LOCATOR_LOCATION;		
  	 }
 }
