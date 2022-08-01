package com.b2.stepdefinitions.scoreconfiguratiion;

import org.openqa.selenium.By;

import com.b2.action.impl.ScoreConfigurationAction;
import com.b2.driver.B2UIDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.containsString;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ICP 
{
	ScoreConfigurationAction scoreConfigAction = new ScoreConfigurationAction();
	String icpName;
	String icpDescription;
	ArrayList<String> attributeNames = new ArrayList<String>();
	ArrayList<String> attributeWeightages = new ArrayList<String>();
	Map<String, String> attributes = new HashMap<>();
	
	@When("he clicks {string}")
	public void he_clicks(String string) 
	{
		scoreConfigAction.clickICPLink();
	}

	@When("he enters ICP name as {string}")
	public void he_enters_ICP_name_as(String string) {
		icpName = string;
		icpDescription = string+" Description";
		B2UIDriver.takeScreenshot();
	}

	@When("he selects the attributes {string}")
	public void he_selects_the_attributes(String string) throws Exception {
	    
		if(string == null || string.equals("")) 
		{
			throw new Exception("Attribute Name can't be null");
		}
		String[] attrs = string.split(",");
		for(String attrName : attrs)
		{
			if(attrName != null && !attrName.equals(""))
			{
				attributeNames.add(attrName.trim());
			}
			
		}
		
	}

	@When("he selects attribute weightage {string}")
	public void he_selects_attribute_weightage(String string) throws Exception{
		if(string == null || string.equals("")) 
		{
			throw new Exception("Attribute Weightage can't be null");
		}
		String[] attrs = string.split(",");
		for(String attrWeight : attrs)
		{
			if(attrWeight != null && !attrWeight.equals(""))
			{
				attributeWeightages.add(attrWeight.trim());
			}
			
		}
	}

	@When("he saves the ICP")
	public void he_saves_the_ICP() throws Exception
	{
		if(attributeNames.size() != attributeWeightages.size())
		{
			throw new Exception("No. of attribute names and attribute weightages are not matching!!!"); 
		
		}
		
		scoreConfigAction.createICP(icpName, attributeNames, attributeWeightages);
		
    }

	@Then("ICP should be created successfully")
	public void icp_should_be_created_successfully() 
	{
		
	}
	 
    
}