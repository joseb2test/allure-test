package com.b2.stepdefinitions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.b2.action.Action;
import com.b2.action.impl.B2Action;
import com.b2.constant.FrameworkCommonConstants;
import com.b2.driver.B2UIDriver;
import com.b2.helper.ConfigurationReader;
import com.b2.helper.DataHelper;
import com.b2.helper.JSONParser;

import com.b2.helper.TestEnvironment;
import com.b2.logger.Logger;
import com.b2.stepdefinitions.login.Login;
import com.b2.stepdefinitions.scoreconfiguratiion.ICP;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Base 
{
	B2Action bootstrap;
	
	
//	public static void main(String[] args)throws Exception
//	{
//		Base base = new Base();
//		base.initialize();
//		base.setup();
//		
//		Login login = new Login();
//		String string = "Tom";
//		login.is_logged_into_B2(string);
//		System.out.println("Login completed");
//		login.he_navigates_to("Score Configuration");
//		System.out.println("Navigation to Score Configuration completed");
//		
//		ICP icp =  new ICP();
//		icp.he_clicks("ICP");
//		icp.he_enters_ICP_name_as("Test");
//		icp.he_selects_the_attributes("country,accountClassification, industry, geography,companyType, companyHierarchy");
//		icp.he_selects_attribute_weightage("Very High, High, Medium, Low, Very Low, Low");
//		icp.he_saves_the_ICP();
//		icp.icp_should_be_created_successfully();
//		
//		base.tearDown(null);
//		
//	}
	
	
	@BeforeTest
	public void initialize() throws Exception
	{
		System.out.println("Initialization started!!!...");
		
		Map<String, String> theSetUpInfo = new HashMap<String, String>();
		
		ConfigurationReader reader = new ConfigurationReader();
		
		theSetUpInfo.put(FrameworkCommonConstants.BROWSER, reader.getString(FrameworkCommonConstants.BROWSER));
		theSetUpInfo.put(FrameworkCommonConstants.GRID, String.valueOf(reader.getBoolean(FrameworkCommonConstants.GRID)));
		theSetUpInfo.put(FrameworkCommonConstants.ENVIRONMENT, reader.getString(FrameworkCommonConstants.ENVIRONMENT));
		
		theSetUpInfo.put(FrameworkCommonConstants.TENANT, reader.getString(FrameworkCommonConstants.TENANT));
		theSetUpInfo.put(FrameworkCommonConstants.HOST_IP, reader.getString(FrameworkCommonConstants.HOST_IP));
		
		theSetUpInfo.put(FrameworkCommonConstants.HOST_PORT, String.valueOf(reader.getLong(FrameworkCommonConstants.HOST_PORT)));
		theSetUpInfo.put(FrameworkCommonConstants.REMOTE_HOST_IP, reader.getString(FrameworkCommonConstants.REMOTE_HOST_IP));
		theSetUpInfo.put(FrameworkCommonConstants.REMOTE_HOST_PORT, reader.getString(FrameworkCommonConstants.REMOTE_HOST_PORT));
		theSetUpInfo.put(FrameworkCommonConstants.BROWSER_STACK_KEY, reader.getString(FrameworkCommonConstants.BROWSER_STACK_KEY));
		theSetUpInfo.put(FrameworkCommonConstants.BROWSER_STACK_USER, reader.getString(FrameworkCommonConstants.BROWSER_STACK_USER));
		
		theSetUpInfo.put(FrameworkCommonConstants.CHROME_PROFILE_DIR, reader.getString(FrameworkCommonConstants.CHROME_PROFILE_DIR));
		
		
		theSetUpInfo.put(FrameworkCommonConstants.RETRY_COUNT, String.valueOf(reader.getLong(FrameworkCommonConstants.RETRY_COUNT)));
		theSetUpInfo.put(FrameworkCommonConstants.DOWNLOAD_DIR, reader.getString(FrameworkCommonConstants.DOWNLOAD_DIR));
		theSetUpInfo.put(FrameworkCommonConstants.URL, reader.getStringFromObject(reader.getString(FrameworkCommonConstants.ENVIRONMENT), FrameworkCommonConstants.URL));
		theSetUpInfo.put(FrameworkCommonConstants.TIMEOUT, reader.getStringFromObject(reader.getString(FrameworkCommonConstants.ENVIRONMENT), FrameworkCommonConstants.TIMEOUT));
		TestEnvironment.getTestEnvironmentObject(theSetUpInfo);	
		Action.setUp();
		System.out.println("Initialization completed!!!...");
	}
	
    @Before
    public void setup() 
    {
    	
    	bootstrap = new B2Action();
        try
        {
        	bootstrap.initializeBrowser();
        	
        }catch(Exception e)
        {
        	Logger.error("Not able to initialize the browser!!! ");
        	Logger.error(e.toString());
        }
    	
    }
 
    @After
    public void tearDown(Scenario scenario) 
    {
    	try
        {
    		Thread.sleep(8000);//This is to see the results before the browser closes 
			bootstrap.closeBrowser();
        	
        }catch(Exception e)
        {
        	Logger.error("Not able to initialize the browser!!! ");
        	Logger.error(e.toString());
        }
        
    }
    
    
    
 
}