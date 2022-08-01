package com.b2.action;

import java.io.IOException;
import java.util.Map;

public interface B2 {
	
	public Map<String, String> login(String emailID, String password, String tenantName);
		
	public Map<String, String> logout();

	public String getCurrentUrl();
 	 
 	public void doPageRefresh();
 	 
 	public void initializeBrowser();

	public void closeBrowser();

	public String getTestEnvironment();

 }