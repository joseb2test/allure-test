package com.b2.constant;

import java.io.File;

public class FrameworkCommonConstants {


	public static final String SEPERATOR = File.separator;
	
	public static final String GRID = "grid";
	public static final String ENVIRONMENT = "environment";
	public static final String BROWSER = "browser";
	public static final String TENANT = "tenant";
	public static final String HOST_IP = "selenium_host_ip";
	public static final String HOST_PORT = "selenium_host_port";
	public static final String REMOTE_HOST_IP = "remote_host_ip";
	public static final String REMOTE_HOST_PORT = "remote_host_port";
	public static final String BROWSER_STACK_USER = "browserstack_user";
	public static final String BROWSER_STACK_KEY = "browserstack_key";
	
	public static final String RETRY_COUNT = "retry_count";
	public static final String DOWNLOAD_DIR ="download_dir";
	 
	public static final String URL = "url";
	public static final String TIMEOUT = "timeout";
	
	public static final String CHROME_PROFILE_DIR = "chrome_profile_dir";
	
	public static final String RESOURCE_LOCATION =SEPERATOR+"test"+SEPERATOR+"resources"+SEPERATOR+"com"+SEPERATOR+"b2";
    public static final String CONFIG_LOCATION =SEPERATOR+"Configuration"+SEPERATOR;
    public static final String CONFIGURATION_FILE_DEFAULT = "configuration.json";
    public static final String SOURCE_DIR ="src";
   
    
    
    //
    //
    public static final String DATA_LOCATION = SEPERATOR+"TestData"+SEPERATOR;
    
    public static final String API_DATA_LOCATION = SEPERATOR+"api"+SEPERATOR;
    
    public static final String DATA_COMMON = "common";
	public static final String LOCATOR_LOCATION = SEPERATOR+"UILocators"+SEPERATOR;
    public static final String POPUP_ACTION_OPEN_OK = "ok";
	public static final String POPUP_ACTION_CANCEL = "cancel";
	public static final String POPUP_ACTION_SAVE_OK = "saveOk";
	public static final String POPUP_ACTION_OPEN_CANCEL = "openCancel";
	public static final String POPUP_ACTION_SAVE_CANCEL = "saveCancel";
	public static final String FILE_ACTION = "fileAction";
	public static final String FILE_ACTION_CLOSE = "close";
	public static final String FILE_ACTION_OPEN = "open";
	public static final String POPUP_ACTION = "popupAction";
	public static final String ERROR_MESSAGE = "stackTrace";

	public static final String LOG4J_PROPERTY_FILE = SEPERATOR+"log4j.properties";
	public static final String LOGGER_LOCATION = SEPERATOR+"Logger";

	public static final String CHROME_DRIVER_LOC  = 	"src"+SEPERATOR+"test"+SEPERATOR+"resources"+SEPERATOR+"com"+SEPERATOR+"b2"+SEPERATOR+"Utils"+SEPERATOR+"chromedriver.exe";
	public static final String KEY_EXPORT_FOLDER = "exportFolder";
	public static final String CHROME_DRIVER_LOC_LINUX  = 	"src"+SEPERATOR+"test"+SEPERATOR+"resources"+SEPERATOR+"com"+SEPERATOR+"b2"+SEPERATOR+"Utils"+SEPERATOR+"chromedriver";
	
	
}

