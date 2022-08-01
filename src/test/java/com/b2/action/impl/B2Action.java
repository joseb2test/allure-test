package com.b2.action.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Keys;

import com.b2.action.Action;
import com.b2.action.B2;
import com.b2.api.API;
import com.b2.api.API.HTTPMethod;
import com.b2.api.runner.APIRunner;
import com.b2.driver.B2UIDriver;
import com.b2.constant.CommonConstants;
import com.b2.helper.APIUtils;
import com.b2.helper.TestDataReader;
import com.b2.helper.TestEnvironment;
import com.b2.logger.Logger;
import com.b2.pageobject.dashboard.DashboardPage;
import com.b2.pageobject.login.GoogleLoginPage;
import com.b2.pageobject.login.LoginPage;
import com.b2.pageobject.menu.MenuPage;

import okhttp3.Cookie;
import okhttp3.Headers;

public class B2Action extends Action implements B2{

	TestDataReader tdReader = new TestDataReader();
	
	public B2Action()
	{
		System.out.println(getTestEnvironment());
		tdReader.setTestEnvironment(getTestEnvironment());
	}

	public  Map<String, String>  login()
	{
		String tenant = null;
		Map<String, String> loginInfo = new HashMap<String, String>();
		try {
			
			tenant = tdReader.getString("tenant");
			String gmailId = tdReader.getString("gmail_id");
			String gmailPassword = tdReader.getString("gmail_password");
			
			
			Logger.info("Logging into the applicaiton with tenant "+tenant);
			loginInfo = login(gmailId, gmailPassword, tenant);

		} catch (Exception e) {
			
			loginInfo.put(CommonConstants.STATUS, CommonConstants.STATUS_FAILURE);
			Logger.info("Logging into the application with "+tenant+" FAILED!!!");
			loginInfo.put(CommonConstants.ERROR_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
		return loginInfo;
	}

		
	public  Map<String, String> logout() {
		Map<String, String> logoutInfo = new HashMap<String, String>();
		Logger.info("Logout is in the process!!!");
		MenuPage menu = b2PageFactory.getPageObject(MenuPage.class);
		
		try{
			menu.logout().waitForLoading();
			menu.logout().click();
			logoutInfo.put(CommonConstants.STATUS, CommonConstants.STATUS_SUCCESS);
			closeBrowser();
			
		} catch (Exception e){
			e.getStackTrace();
			logoutInfo.put(CommonConstants.STATUS, CommonConstants.STATUS_FAILURE);
			logoutInfo.put(CommonConstants.ERROR_MESSAGE,e.getMessage());
			return logoutInfo;
		}
		return logoutInfo;

	}

	public Map<String, String> login(String gmailID, String gmailPassword, String tenant)
	{
		Map<String, String> loginInfo = new HashMap<String, String>();
		try
		{
			LoginPage login = b2PageFactory.getPageObject(LoginPage.class);
			DashboardPage dashboard = b2PageFactory.getPageObject(DashboardPage.class);
			GoogleLoginPage googleLogin = b2PageFactory.getPageObject(GoogleLoginPage.class);
			
			
			String uri=tdReader.getString("googleIdUri");
			API googleidAPI=new API(uri,"googleid", HTTPMethod.POST);
			CloseableHttpResponse response = (CloseableHttpResponse)APIRunner.executePost(googleidAPI);
			String responseCode = response.getStatusLine().toString();
			System.out.println("Execution completed - Status is " + responseCode);
			String[] responseCodeSplit=responseCode.split(" ");
			int code=Integer.parseInt(responseCodeSplit[1]);
			String accessToken = null;
			String idToken = null;
			String permission =null;
			System.out.println(permission);
			String tenantId1 = null;
			String requestBody = APIUtils.getJSONRequestBody(response);
			if(code==200)
			{
				try 
				{
					idToken = APIUtils.getValueFromJSONNode(requestBody, "/id_token");
					System.out.println(idToken);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				//System.out.println(object);
			}
			String tenantName=tdReader.getString("tenant");
			String uri1=tdReader.getString("googleSecondUri");
			API googlesecondAPI=new API(uri1,"googlesecond", HTTPMethod.POST);
			//APIUtils.updateQueryString("<id_token>", idToken, googlesecondAPI);
			APIUtils.updateBody("<id_token>", idToken, googlesecondAPI);
			APIUtils.updateBody("<tenantName>", tenantName, googlesecondAPI);
			response = (CloseableHttpResponse)APIRunner.executePost(googlesecondAPI);
			responseCode = response.getStatusLine().toString();
			System.out.println("Execution completed - Status is " + responseCode);
			String[] responseCodesplit=responseCode.split(" ");
			code=Integer.parseInt(responseCodesplit[1]);
			requestBody = APIUtils.getJSONRequestBody(response);
			
			if(code==200)
			{
				try 
				{
					accessToken = APIUtils.getValueFromJSONNode(requestBody, "/token");
					permission = APIUtils.getValueFromJSONNode(requestBody, "/permissions");
					tenantId1= APIUtils.getValueFromJSONNode(requestBody, "/tenantId");
					accessToken = accessToken.replaceAll("^\"|\"$", "");
					accessToken = accessToken.trim();
					tenantId1 = tenantId1.replaceAll("^\"|\"$", "");
					tenantId1 = tenantId1.trim();
					System.out.println(accessToken);
					System.out.println(permission);
					System.out.println(tenantId1);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				
//				Cookie cookie =new Cookie("token",accessToken);
//				Cookie cookie1 =new Cookie("permissions",permission);
//				Cookie cookie2 =new Cookie("tenan\tId",tenantId1);

				B2UIDriver.setUpCookie("b2Token",accessToken);
//				B2UIDriver.setUpCookie("b2bToken", accessToken);				
//				B2UIDriver.setUpCookie("b2bPermission",permission);
//				B2UIDriver.setUpCookie("b2bTenantId",tenantId1);
				B2UIDriver.setLocalStorage("b2bToken", accessToken);				
				B2UIDriver.setLocalStorage("b2bPermission",permission);
				B2UIDriver.setLocalStorage("b2bTenantId",tenantId1);
				B2UIDriver.setUrl("http://login.bamboobox.in/#/account-dashboard");
				B2UIDriver.openUrl();
				
				
//					Header header = response.get;
//		            if(header != null)
//		            {
//		                System.out.println("For Cookie : "+ header.getValue());
//		                
//		            }
//		            header = response.getFirstHeader("Cookies");
//		            if(header != null)
//		            {
//		                System.out.println("For Cookies : "+ header.getValue());
//		                
//		            }
//					//accessToken = APIUtils.getValueFromJSONNode(response, "/value");
//					
////			
				//System.out.println(object);
			} 
//		
//		
//			login.signinWithGoogle().waitForLoading();
//			login.signinWithGoogle().click();
//
////			Set<String> handles = B2UIDriver.getWebDriver().getWindowHandles();
////			
////			String parent = B2UIDriver.getWebDriver().getWindowHandle();
////		
////			for(String handle : handles)
////			{
////				if((!parent.equals(handle))==true)
////				{
////					B2UIDriver.switchToWindow(handle);
////				}
////			}
////			googleLogin.emailID().waitForLoading();
////			googleLogin.emailID().type(gmailID);
////
////
////			
////			googleLogin.emailIDSubmit().waitForLoading();
////			
////			googleLogin.emailIDSubmit().click();
////
////			googleLogin.password().waitForLoading();
////			googleLogin.password().type(gmailPassword);
////
////			googleLogin.passwordSubmit().waitForLoading();
////			googleLogin.passwordSubmit().click();
////			B2UIDriver.switchToWindow(parent);
//
//			login.selectTenant().waitForLoading();
//	
//			login.selectTenant().select(tenant);
//		
//
//			
//			login.signInButton().waitForLoading();
//			
//			login.signInButton().click();
//		
		
			try
			{
				dashboard.pageTitle().waitForElementToBePresent();
	
			} catch(Exception e){
				Logger.info("Login into the Application for the tenant "+tenant+" FAILED!!!");
			}
			
			Logger.info("Logged into Application for the tenant "+tenant+" successfully");
		}
		catch(Exception e){
			System.out.println(e);
			loginInfo.put(CommonConstants.STATUS, CommonConstants.STATUS_FAILURE);
			loginInfo.put(CommonConstants.ERROR_MESSAGE, e.getMessage());
			return loginInfo;
		}	
		return loginInfo;
	}
	
	public void initializeBrowser(){
		try {
			super.initializeBrowser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCurrentUrl()	
	{
		return B2UIDriver.getCurrentLocation();
	}

	public void doPageRefresh() {
		Logger.info("Refreshing the page");
		B2UIDriver.getWebDriver().navigate().refresh();
	}

	public void closeBrowser() {
		B2UIDriver.closeBrowser();

	}
	public String getTestEnvironment(){
		return TestEnvironment.getTestEnvironmentObject().getTestEnv();
	}
}