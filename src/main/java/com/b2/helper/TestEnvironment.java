package com.b2.helper;

import java.io.IOException;
import java.util.Map;

import com.b2.driver.B2UIDriver.BrowserType;
import com.b2.constant.FrameworkCommonConstants;

public class TestEnvironment {
	private String testEnv;
	private BrowserType browserType;
	private static TestEnvironment env;
	private String tenantName; 
	private String isRunOnRemote;
	
	private String seleniumHost;
	private String seleniumPort;
	private String url;
	private String maxTimeOut;
	
	private String retryCount;
	private String downloadDir;
	private String browserstackUser; 
	private String browserstackKey; 
	
	private String chromeProfileDir;

	public static synchronized TestEnvironment getTestEnvironmentObject (String theBrowserType,String theTestEnv, String theTenantId, String theUrl)
	 {
		 if (env == null)
			 env = new TestEnvironment();
		 env.setTestEnv(theTestEnv);
		 env.setBrowserType(BrowserType.valueOf(theBrowserType.toUpperCase()));
		 env.setTenantName(theTenantId);
		 env.setUrl(theUrl);
		 return env;
	 }

	 public String getChromeProfileDir() {
		return chromeProfileDir;
	}

	public void setChromeProfileDir(String chromeProfileDir) {
		this.chromeProfileDir = chromeProfileDir;
	}

	public static synchronized TestEnvironment getTestEnvironmentObject (Map<String, String> theTestEnvData) throws IOException{
		 if (env == null)
			 env = new TestEnvironment();
		 env.setTestEnv(theTestEnvData.get(FrameworkCommonConstants.ENVIRONMENT));
		 env.setBrowserType(BrowserType.valueOf(theTestEnvData.get(FrameworkCommonConstants.BROWSER).toUpperCase()));
		 try{
			 env.setBrowserstackUser(theTestEnvData.get(FrameworkCommonConstants.BROWSER_STACK_USER));
			 env.setBrowserstackKey(theTestEnvData.get(FrameworkCommonConstants.BROWSER_STACK_KEY));
		 }catch(Exception e){
			 
		 }
		
		 env.setChromeProfileDir(theTestEnvData.get(FrameworkCommonConstants.CHROME_PROFILE_DIR));
		 env.setTenantName(theTestEnvData.get(FrameworkCommonConstants.TENANT));
		 env.setSeleniumHost(theTestEnvData.get(FrameworkCommonConstants.HOST_IP));
		 env.setSeleniumPort(theTestEnvData.get(FrameworkCommonConstants.HOST_PORT));
		 env.setIsRunOnRemote(FrameworkCommonConstants.GRID);
		 env.setUrl(theTestEnvData.get(FrameworkCommonConstants.URL));
		 
		 String sTimeout = theTestEnvData.get(FrameworkCommonConstants.TIMEOUT);
		 try
		 {
			 Integer.parseInt(sTimeout);
		 }catch(Exception e)
		 {
			 sTimeout = "30";
		 }
		 env.setMaxTimeOut(sTimeout);
		 
		 env.setRetryCount(theTestEnvData.get(FrameworkCommonConstants.RETRY_COUNT));
		 env.setDownloadDir(theTestEnvData.get(FrameworkCommonConstants.DOWNLOAD_DIR));
		 return env;
	 }

	 public static synchronized TestEnvironment getTestEnvironmentObject(){
		 if (env == null)
			 env = new TestEnvironment();
		 return env;
	 }

	 private TestEnvironment(){

	 }

	 public String getTestEnv() {
		 return testEnv;
	 }

	 public void setTestEnv(String theTestEnv) {
		 this.testEnv = theTestEnv;
	 }

	 public BrowserType getBrowserType() {
		 return browserType;
	 }

	 public void setBrowserType(BrowserType theBrowserType) {
		 this.browserType = theBrowserType;
	 }
	 
	 
	 public String getTenantName() {
		 return tenantName;
	 }

	 public void setTenantName(String theTenantName) {
		 this.tenantName = theTenantName;
	 }
	 
	 public String getIsRunOnRemote() {
		 return isRunOnRemote;
	 }

	  public void setIsRunOnRemote(String theRunOnRemote) {
		 this.isRunOnRemote = theRunOnRemote;
	 }

	 public String getSeleniumHost() {
		 return seleniumHost;
	 }

	 public void setSeleniumHost(String theSeleniumHost) {
		 this.seleniumHost = theSeleniumHost;
	 }

	 public String getSeleniumPort() {
		 return seleniumPort;
	 }

	 
	 public void setSeleniumPort(String theSeleniumPort) {
		 this.seleniumPort = theSeleniumPort;
	 }

	 public String getUrl() {
		 return url;
	 }

	 public void setUrl(String theUrl) {
		 this.url = theUrl;
	 }

	 public String getMaxTimeOut() {
		 return maxTimeOut;
	 }

	 public void setMaxTimeOut(String maxTimeOut) {
		 this.maxTimeOut = maxTimeOut;
	 }

	 
	 public String getRetryCount() {
		 return retryCount;
	 }	 
	 
	 public void setRetryCount(String retryCount) {
		 this.retryCount = retryCount;
	 }
	 
	 public String getDownloadDir() {
		 return downloadDir;
	 }

	 public void setDownloadDir(String theDownloadDir) {
		 this.downloadDir = theDownloadDir;
	 }
	 
	 public String getBrowserstackUser() {
		 return browserstackUser;
	 }

	 public void setBrowserstackUser(String theBrowserstackUser) {
		 this.browserstackUser = theBrowserstackUser;
	 }
	 
	 public String getBrowserstackKey() {
		 return browserstackKey;
	 }

	 public void setBrowserstackKey(String theBrowserstackKey) {
		 this.browserstackKey = theBrowserstackKey;
	 }

}
