package com.b2.action.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.b2.action.Action;
import com.b2.action.ScoreConfiguration;
import com.b2.driver.B2UIDriver;
import com.b2.helper.TestDataReader;
import com.b2.helper.TestEnvironment;
import com.b2.logger.Logger;
import com.b2.pageobject.configuration.ICPCreationPage;
import com.b2.pageobject.configuration.ScoreConfigurationPage;


public class ScoreConfigurationAction extends Action implements ScoreConfiguration
{
	ScoreConfigurationPage scoreConfigPage;
	ICPCreationPage icpCreationPage; 
	
	TestDataReader tdReader = new TestDataReader(TestEnvironment.getTestEnvironmentObject().getTestEnv());
	
	public ScoreConfigurationAction()
	{
		
	}
	
	public void clickICPLink()
	{
		scoreConfigPage = b2PageFactory.getPageObject(ScoreConfigurationPage.class);
		
		B2UIDriver.refreshPage();
		B2UIDriver.switchToFrame(scoreConfigPage.iframe());
		
		scoreConfigPage.createICP().waitForLoading();
		scoreConfigPage.createICP().click();
	}
	
	public boolean createICP(String name,ArrayList<String> attributeNames,ArrayList<String> attributeWeights )
	{
		if(icpCreationPage ==null)
		{
			icpCreationPage = b2PageFactory.getPageObject(ICPCreationPage.class);
		}
		if(scoreConfigPage == null)
		{
			scoreConfigPage = b2PageFactory.getPageObject(ScoreConfigurationPage.class);
		}
		
		String description;
		try
		{
			description = tdReader.getStringFromObject("icp", "description");
			
		}catch(Exception e)
		{
			description = "";
			e.printStackTrace();
		}
		
		try
		{
			icpCreationPage.name().waitForLoading();
			icpCreationPage.name().type(name);
			icpCreationPage.description().waitForLoading();
			icpCreationPage.description().type(description);
			
			int i = 0;
			
			for(String attributeName : attributeNames)
			{
				if(attributeName.equalsIgnoreCase("country"))
				{
					icpCreationPage.country().waitForLoading();
					icpCreationPage.country().check();
					icpCreationPage.countryWeightage().waitForLoading();
					icpCreationPage.countryWeightage().select(attributeWeights.get(i));
				}
				else if(attributeName.equalsIgnoreCase("accountclassification"))
				{
					icpCreationPage.accountClassification().waitForLoading();
					icpCreationPage.accountClassification().check();
					icpCreationPage.accountClassificationWeightage().waitForLoading();
					icpCreationPage.accountClassificationWeightage().select(attributeWeights.get(i));
				}
				else if(attributeName.equalsIgnoreCase("industry"))
				{
					icpCreationPage.industry().waitForLoading();
					icpCreationPage.industry().check();
					icpCreationPage.industryWeightage().waitForLoading();
					icpCreationPage.industryWeightage().select(attributeWeights.get(i));
				}
				else if(attributeName.equalsIgnoreCase("geography"))
				{
					icpCreationPage.geography().waitForLoading();
					icpCreationPage.geography().check();
					icpCreationPage.geographyWeightage().waitForLoading();
					icpCreationPage.geographyWeightage().select(attributeWeights.get(i));
				}
				else if(attributeName.equalsIgnoreCase("companytype"))
				{
					icpCreationPage.companyType().waitForLoading();
					icpCreationPage.companyType().check();
					icpCreationPage.companyTypeWeightage().waitForLoading();
					icpCreationPage.companyTypeWeightage().select(attributeWeights.get(i));
				}
				else if(attributeName.equalsIgnoreCase("companyhierarchy"))
				{
					icpCreationPage.companyHierarchy().waitForLoading();
					icpCreationPage.companyHierarchy().check();
					icpCreationPage.companyHierarchyWeightage().waitForLoading();
					icpCreationPage.companyHierarchyWeightage().select(attributeWeights.get(i));
					
				}
				else
				{}
					
				i++;
			}
			icpCreationPage.proceedLink().waitForLoading();;
			icpCreationPage.proceedLink().click();
			
			Logger.info("ICP Created Successfully : "+name);
			
			
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			Logger.info("ICP creation failed!!! : "+name);
			return false;
		}
		
	}
	
}
