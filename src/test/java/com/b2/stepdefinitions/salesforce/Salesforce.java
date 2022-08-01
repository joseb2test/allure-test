package com.b2.stepdefinitions.salesforce;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.b2.action.impl.SalesforceAction;
import com.b2.action.impl.ScoreConfigurationAction;
import com.b2.helper.TestDataReader;
import com.b2.helper.TestEnvironment;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.containsString;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Salesforce {
	SalesforceAction salesforceObject= new SalesforceAction();
	TestDataReader tdReader = new TestDataReader(TestEnvironment.getTestEnvironmentObject().getTestEnv());
	
	  @When("he creates a new {string} in salesforce")
	    public void he_creates_a_new_account_in_salesforce(String string) throws Exception  {
		  String uri=tdReader.getString("salesforce_uri");
		 int code=salesforceObject.createObject(string,uri);
	if(code>500)
	{
		Assert.assertTrue(false);
	}
	else
		Assert.assertTrue(true);
	    }
	
	  @When("he deletes a {string} in salesforce")
	    public void he_deletes_a_in_salesforce(String string) throws Exception  {
		  String uri=tdReader.getString("salesforce_uri");
		 int code=salesforceObject.deleteObject(string,uri);
	if(code>500)
	{
		Assert.assertTrue(false);
	}
	else
		Assert.assertTrue(true);
	    }
	  @When("he updates an {string} in salesforce")
	    public void he_updates_an_in_salesforce(String string) throws Exception  {
		  String uri=tdReader.getString("salesforce_uri");
		 int code=salesforceObject.updateObject(string,uri);
	if(code>500)
	{
		Assert.assertTrue(false);
	}
	else
		Assert.assertTrue(true);
	    }
	

	  @Then("the {string} should be present in Account Hub")
	  public void the_should_be_present_in_Account_Hub(String string) {
	    	String objectType=string;
	    	objectType=objectType.toLowerCase().trim();
	    	String objectName=  SalesforceAction.getaccountName();
			if((salesforceObject.searchSalesforceObject(objectType,objectName)) == true)
			{
				System.out.println("success");
				Assert.assertTrue(true);
			}
			else 
				Assert.assertTrue(false);

	       
	    }
	  @Then("the {string} should not be present in Account Hub")
	  public void the_should_not_be_present_in_Account_Hub(String string) {
	    	String objectType=string;
	    	objectType=objectType.toLowerCase().trim();
	    	String objectName=  SalesforceAction.getaccountName();
			if((salesforceObject.searchToast(objectType,objectName)) == true)
			{
				System.out.println("delete success");
				Assert.assertTrue(true);
			}
			else 
				Assert.assertTrue(false);

	       
	    }
	 
	  @Then("the {string} should be updated successfully")
	  public void the_should_be_updated_successfully(String string) {
		 	String objectType=string;
	    	objectType=objectType.toLowerCase().trim();
	    	String objectName=  SalesforceAction.getaccountName();
			if((salesforceObject.searchWebsite(objectType,objectName)) == true)
			{
				System.out.println("update success");
				Assert.assertTrue(true);
			}
			else 
				Assert.assertTrue(false); 
	    }

}
