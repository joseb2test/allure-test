package com.b2.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.b2.constant.FrameworkCommonConstants;
import com.b2.logger.Logger;



public  class DataHelper
{
	
	 public static List<String> getTestDataFileName(String courseOfferingId) {
    	String fileLocation = getTestDataDirectory(); 
    	return getTestDataFileName(courseOfferingId, fileLocation);
  	  }
  	 
    public static List<String> getTestDataFileName(String courseOfferingId, String dataLocation) 
    {
    	File dir = new File(dataLocation);
    	List<String> fileList = new ArrayList<String>();
 		if (dir.isDirectory()){
 		   String[] dirList = dir.list();
 		   for (String dirName : dirList){
 			   File fileName = new File(dataLocation+dirName);
 			   if (!fileName.isDirectory() &&
 						   fileName.getAbsolutePath().endsWith(courseOfferingId.replace(":", "_") + "_Data.xml") ){
 	    		    					   fileList.add(fileName.getPath());
 	    	    }
 			}
 		}
 		return fileList;
 	  }
    	
    	public static String getTestDataDirectory()
    	{
    		String srcDir = System.getProperty("user.dir") +File.separator+ FrameworkCommonConstants.SOURCE_DIR;
    		return (srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.DATA_LOCATION);		
    	}
    	
     	public static String getTestLoggerDirectory()
     	{
     		String srcDir = System.getProperty("user.dir") +File.separator+ FrameworkCommonConstants.SOURCE_DIR;
     		return (srcDir + FrameworkCommonConstants.RESOURCE_LOCATION + FrameworkCommonConstants.LOGGER_LOCATION);		
     	}
     	
	  	 public static Properties getDataProperties(String courseOfferingId) throws IOException {
	    	String fileLocation = getTestDataDirectory(); 
	  		Properties prop = getProperties(fileLocation, courseOfferingId);
	   	    return prop;
	 	  }
	  	 
		   	protected static Properties getProperties(String dataLocation,String courseOfferingId) throws IOException{
		    	  	Properties properties = new Properties();
		    	  	File[] dataFileLists = null;
		    	  	File dataDir = null;
		    		File dir = new File(dataLocation);
		    		if (dir.isDirectory()){
		    		   String[] dirList = dir.list();
		    		   for (String dirName : dirList){
		    			   dataDir = new File(dataLocation+dirName);
		    			   if (dataDir.isDirectory()){
		    				   dataFileLists = dataDir.listFiles();
		    				   if (dataFileLists != null){
		    					   for (File aFile : dataFileLists){
		    						   if (aFile.isDirectory()){
		    							   dataDir = new File(dataLocation+aFile);
		    							   dataFileLists = dataDir.listFiles();
		    							   if (dataFileLists != null){
		    								   for (File otherFile : dataFileLists){
				    		    				   if (!otherFile.isDirectory() && aFile.getAbsolutePath().endsWith(courseOfferingId.replace(":", "_") + "_Data.xml") || aFile.getAbsolutePath().endsWith(courseOfferingId.replace(":", "_") + "_Data.xml")  || (aFile.getAbsolutePath().endsWith(FrameworkCommonConstants.DATA_COMMON + ".xml")) ){
				    		    					   properties.putAll(readFile(aFile));
				    		     				   }
		    								   }
		    							   }
		    						   }else {
		    							   if (aFile.getAbsolutePath().endsWith(courseOfferingId.replace(":", "_") + "_Data.xml") || aFile.getAbsolutePath().endsWith(courseOfferingId.replace(":", "_") + "_Data.xml")  || (aFile.getAbsolutePath().endsWith(FrameworkCommonConstants.DATA_COMMON + ".xml")) ){
		    		    					   properties.putAll(readFile(aFile));
		    		     				   }
		    							 
		    						   }
		    					   }
		    				   }
		    			   } else  {
		    				   properties.putAll(readFile(dataDir));
		    			   }
		    		   }
		    		} else 	{
		    		   dataFileLists = dir.listFiles();
		    		   for (File aFile : dataFileLists){
						   if (aFile.isDirectory()){
							    dataDir = new File(dataLocation+aFile);
		 				   }else if (aFile.getAbsolutePath().endsWith(courseOfferingId.replace(":", "_") + "_Data.xml") || aFile.getAbsolutePath().endsWith(courseOfferingId.replace(":", "_") + "_Data.xml")  || (aFile.getAbsolutePath().endsWith(FrameworkCommonConstants.DATA_COMMON + ".xml")) ){
		 				   {
		 					  properties.putAll(readFile(aFile));
						   }
					     }
		    		   }
		    		}
		      		return properties;
		   	 }
		  	 
		    protected static Properties readFile(File dataFile) throws InvalidPropertiesFormatException, IOException{
		    		Properties aProperty = new Properties();
		    		
		    	    String fileName = dataFile.getAbsolutePath();
					if (fileName.endsWith(".xml") || fileName.endsWith(".properties")){
							   InputStream	thePropFile = new FileInputStream(fileName);
							   if (thePropFile != null){
			     	      	   		Properties newProp = new Properties();
			     	      	   		if (fileName.endsWith(".xml"))
			     	      	   			newProp.loadFromXML(thePropFile);
			     	      	   		else if(fileName.endsWith(".properties"))
			     	      	   			newProp.load(thePropFile);
			     		     	    Set<Entry<Object,Object>> propEntrySet = newProp.entrySet();
			     		     	    if (propEntrySet != null && !propEntrySet.isEmpty()){
			     			     	    Iterator<Entry<Object,Object>> propIter = propEntrySet.iterator();
			     			      	    while(propIter.hasNext()){
			     			     	    	Entry<Object,Object> theEntry = propIter.next();
			     			     	    	aProperty.put(theEntry.getKey(), theEntry.getValue());
			     			     	    }
			     		     	    }
			     				}
					 }
					return aProperty;
		    }

		    public static Properties getProperties() throws IOException {
	       		String fileLocation = getConfigDirectory();
	    		Properties prop = getProperties(fileLocation);
	      	    return prop;
	    	  }
	     	 
	     	public static String getConfigDirectory()
	     	{
	     		String srcDir = getSourceDirectory();
	     		//return org.apache.commons.io.FilenameUtils.separatorsToSystem(srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.CONFIG_LOCATION);		
	     		return srcDir + FrameworkCommonConstants.RESOURCE_LOCATION+FrameworkCommonConstants.CONFIG_LOCATION;
	     	}
	     	
	    	public static String getSourceDirectory()
	    	{
	    		String baseDir = getBaseDirectory();
	    		return baseDir + FrameworkCommonConstants.SOURCE_DIR;
	    	}
	    	
	    	public static String getBaseDirectory()
	    	{
	    		String path = System.getProperty("user.dir") +File.separator;
	    		return path;
	    	}
	    	
	     	protected static Properties getProperties(String dataLocation) throws IOException{
	    	  	Properties properties = new Properties();;
	    	  	File[] dataFileLists = null;
	    	  	File dataDir = null;
	    		File dir = new File(dataLocation);
	    	
	    		if (dir.exists()){
	    		   	if (Logger.isInfoEnabled()){
	    		   		//Logger.info("This directory exists: " +dir.getAbsolutePath());
	    		   	}
	    		}
	    		if (dir.isDirectory()){
	    		   String[] dirList = dir.list();
	    		   for (String dirName : dirList){
	    			   dataDir = new File(dataLocation + dirName);
	    			   if (dataDir.isDirectory()){
	    				   dataFileLists = dataDir.listFiles();
	    				   if (dataFileLists != null){
	    					   for (File aFile : dataFileLists){
	    						   if (aFile.isDirectory()){
	    							   dataDir = new File(dataLocation + aFile);
	    							   dataFileLists = dataDir.listFiles();
	    							   if (dataFileLists != null){
	    								   for (File otherFile : dataFileLists){
			    		    				   if (!otherFile.isDirectory() && ((otherFile.getAbsolutePath().endsWith(".xml") || otherFile.getAbsolutePath().endsWith(".properties")))){
			    		    					   properties.putAll(readFile(aFile));
			    		     				   }
	    								   }
	    							   }
	    						   }else if (aFile.getAbsolutePath().endsWith(".xml") || (aFile.getAbsolutePath().endsWith(".properties"))){
	    							   properties.putAll(readFile(aFile));
	    						   }
	    					   }
	    				   }
	    			   } else  {
	    				   properties.putAll(readFile(dataDir));
	    			   }
	    		   }
	    		} else 	{
	    		   dataFileLists = dir.listFiles();
	    		   for (File aFile : dataFileLists){
					   if (aFile.isDirectory()){
						    dataDir = new File(dataLocation+aFile);
	 				   }else {
	 					  properties.putAll(readFile(aFile));
					   }
				   }
	    		}
	      		 return properties;
	     	 }
	
}
