package com.b2.action.impl;

import java.io.File;
import java.util.Set;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.b2.action.Action;
import com.b2.action.SalesforceActionImp;
import com.b2.helper.APIUtils;
import com.b2.helper.TestDataReader;
import com.b2.helper.TestEnvironment;
import com.b2.logger.Logger;
import com.b2.pageobject.configuration.ScoreConfigurationPage;
import com.b2.pageobject.unifiedaccountprofile.AccountHubPage;

import okhttp3.internal.http.StatusLine;

import com.b2.action.Navigation;
import com.b2.action.impl.B2Action;
import com.b2.action.impl.NavigationAction;
import com.b2.api.API;
import com.b2.api.API.HTTPMethod;
import com.b2.api.runner.APIRunner;
import com.b2.driver.B2UIDriver;



public class SalesforceAction extends Action implements SalesforceActionImp
{
	
	Navigation navigation;
	AccountHubPage unifiedaccountprofile;
//	SalesforceAction salesforceobject;
	TestDataReader tdReader = new TestDataReader(TestEnvironment.getTestEnvironmentObject().getTestEnv());
	public SalesforceAction()
	{
		
	}
	public static String accountName;
	public static String accountWebsite;
	public static String getaccountName()
	
	{
		return accountName;
	}
	public static UUID accountuuid;
	public static String uuid;
    public static void setuuid()
	{
		accountuuid=UUID.randomUUID();
		uuid=accountuuid.toString();
	}
    public static String getuuid()
   	{
   		return uuid;
   	}

//	int randomnum= (int)(Math.random()*1000);
//	accountName= tdReader.getString("account")+randomnum;
	public int createObject(String objectType,String uri) throws Exception
	{
		if((objectType.equals("account"))==true)
		{
			if(unifiedaccountprofile ==null)
			{
				unifiedaccountprofile = b2PageFactory.getPageObject(AccountHubPage.class);
			}
			unifiedaccountprofile.iframe().waitForLoading();
			API salesforceAPI=new API(uri,"sfaccountcreation", HTTPMethod.POST);
			
			setuuid();
			Set<Cookie> cookies = B2UIDriver.getCookie();
//			UUID accountuuid=UUID.randomUUID();
//			String uuid=accountuuid.toString();
			
			int randomnum= (int) (Math.random()*1000);
			accountName= tdReader.getString("account")+randomnum;
			accountWebsite=accountName+".com";
			Thread.sleep(2000);
			Cookie cookie = B2UIDriver.getCookiesNamed("b2Token");
			APIUtils.updateHeaders("<cookie>", cookie.getName()+"="+cookie.getValue(), salesforceAPI);
			APIUtils.updateQueryString("<tenant_id>", tdReader.getString("tenant_id"), salesforceAPI);
			APIUtils.updateQueryString("<secret>", tdReader.getString("secret"), salesforceAPI);
			
			APIUtils.updateBody("<b2sync>", tdReader.getString("B2sync"), salesforceAPI);
			APIUtils.updateBody("<website>", accountWebsite, salesforceAPI);
			APIUtils.updateBody("<name>", accountName, salesforceAPI);
			APIUtils.updateBody("<accountuuid>", getuuid(), salesforceAPI);
			HttpResponse response = APIRunner.executePost(salesforceAPI);
			String responseCode = response.getStatusLine().toString();
			System.out.println("Execution completed - Status is " + responseCode);
			String[] responseCodeSplit=responseCode.split(" ");
			int code=Integer.parseInt(responseCodeSplit[1]);
			return code;	
		}
		return 0;
	}
	
	public int deleteObject(String objectType,String uri) throws Exception
	{
		if((objectType.equals("account"))==true)
		{
			if(unifiedaccountprofile ==null)
			{
				unifiedaccountprofile = b2PageFactory.getPageObject(AccountHubPage.class);
			}

			API salesforceAPI=new API(uri,"sfaccountdeletion", HTTPMethod.POST);
			if(accountName==null)
			{
				createObject(objectType,uri);

				
			}
			searchSalesforceObject(objectType, accountName);
			Set<Cookie> cookies = B2UIDriver.getCookie();
			Thread.sleep(2000);
			Cookie cookie = B2UIDriver.getCookiesNamed("b2Token");
			APIUtils.updateHeaders("<cookie>", cookie.getName()+"="+cookie.getValue(), salesforceAPI);
			APIUtils.updateQueryString("<tenant_id>", tdReader.getString("tenant_id"), salesforceAPI);
			APIUtils.updateQueryString("<secret>", tdReader.getString("secret"), salesforceAPI);
			
			APIUtils.updateBody("<b2sync>", tdReader.getString("B2sync"), salesforceAPI);
			APIUtils.updateBody("<website>", accountWebsite, salesforceAPI);
			APIUtils.updateBody("<name>", accountName, salesforceAPI);
			APIUtils.updateBody("<accountuuid>", getuuid(), salesforceAPI);
			HttpResponse response = APIRunner.executePost(salesforceAPI);
			String responseCode = response.getStatusLine().toString();
			System.out.println("Execution completed - Status is " + responseCode);
			String[] responseCodeSplit=responseCode.split(" ");
			int code=Integer.parseInt(responseCodeSplit[1]);
			return code;	
		}
		return 0;
	}
	
	public int updateObject(String objectType,String uri) throws Exception
	{
		if((objectType.equals("account"))==true)
		{
			if(unifiedaccountprofile ==null)
			{
				unifiedaccountprofile = b2PageFactory.getPageObject(AccountHubPage.class);
			}
			unifiedaccountprofile.iframe().waitForLoading();
			API salesforceAPI=new API(uri,"sfaccountupdation", HTTPMethod.POST);
			if(accountName==null)
			{
				createObject(objectType,uri);

			}
			searchSalesforceObject(objectType, accountName);
			Set<Cookie> cookies = B2UIDriver.getCookie();
			accountWebsite=accountName+"updatedwebsite.com";
			Thread.sleep(2000);
			Cookie cookie = B2UIDriver.getCookiesNamed("b2Token");
			APIUtils.updateHeaders("<cookie>", cookie.getName()+"="+cookie.getValue(), salesforceAPI);
			APIUtils.updateQueryString("<tenant_id>", tdReader.getString("tenant_id"), salesforceAPI);
			APIUtils.updateQueryString("<secret>", tdReader.getString("secret"), salesforceAPI);
			
			APIUtils.updateBody("<b2sync>", tdReader.getString("B2sync"), salesforceAPI);
			APIUtils.updateBody("<websiteupdate>", accountWebsite, salesforceAPI);
			APIUtils.updateBody("<name>", accountName, salesforceAPI);
			APIUtils.updateBody("<accountuuid>", getuuid(), salesforceAPI);
			HttpResponse response = APIRunner.executePost(salesforceAPI);
			String responseCode = response.getStatusLine().toString();
			System.out.println("Execution completed - Status is " + responseCode);
			String[] responseCodeSplit=responseCode.split(" ");
			int code=Integer.parseInt(responseCodeSplit[1]);
			return code;	
		}
		return 0;
	}

	public boolean searchSalesforceObject(String objectType, String objectName) {
		if((objectType.equals("account"))==true)
		{
			if(unifiedaccountprofile ==null)
			{
				unifiedaccountprofile = b2PageFactory.getPageObject(AccountHubPage.class);
			}
			navigation = new NavigationAction();
			navigation.navigatesToView("Account Hub");
			B2UIDriver.refreshPage();
			B2UIDriver.switchToFrame(unifiedaccountprofile.iframe());
			try{
			unifiedaccountprofile.searchbar().waitForLoading();
			unifiedaccountprofile.searchbar().type(objectName);
			unifiedaccountprofile.downElement().waitForLoading();
			unifiedaccountprofile.downElement().click();
			}
			catch(Exception e)
			{
				System.out.println(" Logger.info(ICP creation failed!!! : ");
				return false;
			}
			try {
				unifiedaccountprofile.accountheader().waitForLoading();
			if((unifiedaccountprofile.accountheader().getText().equals(objectName))==true)
			{
				unifiedaccountprofile.downarrow().waitForLoading();
				unifiedaccountprofile.downarrow().click();
				unifiedaccountprofile.accountWebsite().waitForLoading();
			return true;
			}}
			catch(Exception e)
			{
				   //Take the screenshot
//		       B2UIDriver.takeScreenshot();
				System.out.println(e);
				return false;
			}
		}
		return false;
		
}
	public boolean searchToast(String objectType, String objectName) {
		if((objectType.equals("account"))==true)
		{
			if(unifiedaccountprofile ==null)
			{
				unifiedaccountprofile = b2PageFactory.getPageObject(AccountHubPage.class);
			}
			B2UIDriver.refreshPage();
			B2UIDriver.switchToFrame(unifiedaccountprofile.iframe());
			try{
			unifiedaccountprofile.searchbar().waitForLoading();
			unifiedaccountprofile.searchbar().type(objectName);
			unifiedaccountprofile.searchbar().type(Keys.ENTER);
			}
			catch(Exception e)
			{
				System.out.println(" Logger.info(ICP creation failed!!! : ");
				return false;
			}
			unifiedaccountprofile.toast().waitForLoading();
			String toasttext=unifiedaccountprofile.toast().getText();
			if((toasttext.equals("Account is not present in the list"))==true)
			{
				return true;
			}
			else
				return false;
		}
		return false;
		
}
	
	public boolean searchWebsite(String objectType, String objectName) {
		if((objectType.equals("account"))==true)
		{
			if(unifiedaccountprofile ==null)
			{
				unifiedaccountprofile = b2PageFactory.getPageObject(AccountHubPage.class);
			}
			B2UIDriver.refreshPage();
			B2UIDriver.switchToFrame(unifiedaccountprofile.iframe());
			unifiedaccountprofile.searchbar().waitForLoading();
			unifiedaccountprofile.searchbar().type(objectName);

			unifiedaccountprofile.downElement().waitForLoading();
			unifiedaccountprofile.downElement().click();
			unifiedaccountprofile.accountheader().waitForLoading();
			unifiedaccountprofile.downarrow().waitForLoading();
			unifiedaccountprofile.downarrow().click();
			unifiedaccountprofile.accountWebsite().waitForLoading();
			String websitetext=unifiedaccountprofile.accountWebsite().getText();
			if((websitetext.equals(objectName+"updatedwebsite.com"))==true)
			{
				System.out.println("New:"+websitetext);
				return true;
			}
			else
				return false;
		}
		return false;
		
}
	
}
