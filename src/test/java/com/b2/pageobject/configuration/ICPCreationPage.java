package com.b2.pageobject.configuration;

import com.b2.helper.LocatorHelper;
import com.b2.pageobject.AbstractPage;
import com.b2.ui.Button;
import com.b2.ui.CheckBox;
import com.b2.ui.DropDownList;
import com.b2.ui.HyperLink;
import com.b2.ui.TextBox;
import com.b2.ui.UIElement;

public class ICPCreationPage extends AbstractPage{
	
	public TextBox name() {
		return new TextBox(LocatorHelper.getLocator());
	}
	
	public TextBox description() {
		return new TextBox(LocatorHelper.getLocator());
	}
	
	public CheckBox country()
	{
		return new CheckBox(LocatorHelper.getLocator());
	}

	
	public CheckBox accountClassification()
	{
		return new CheckBox(LocatorHelper.getLocator());
	}
	
	
	public CheckBox industry()
	{
		return new CheckBox(LocatorHelper.getLocator());
	}
	
	
	public CheckBox geography()
	{
		return new CheckBox(LocatorHelper.getLocator());
	}
	
	
	public CheckBox companyType()
	{
		return new CheckBox(LocatorHelper.getLocator());
	}
	
	
	public CheckBox companyHierarchy()
	{
		return new CheckBox(LocatorHelper.getLocator());
	}

	public TextBox countryWeightage()
	{
		return new TextBox(LocatorHelper.getLocator());
	}

	public TextBox accountClassificationWeightage()
	{
		return new TextBox(LocatorHelper.getLocator());
	}
	
	
	public TextBox industryWeightage()
	{
		return new TextBox(LocatorHelper.getLocator());
	}
	
	
	public TextBox geographyWeightage()
	{
		return new TextBox(LocatorHelper.getLocator());
	}
	
	
	public TextBox companyTypeWeightage()
	{
		return new TextBox(LocatorHelper.getLocator());
	}
	
	
	public TextBox companyHierarchyWeightage()
	{
		return new TextBox(LocatorHelper.getLocator());
	}
	
	public HyperLink cancelLink()
	{
		return new HyperLink(LocatorHelper.getLocator());
	}
	
	public HyperLink proceedLink()
	{
		return new HyperLink(LocatorHelper.getLocator());
	}
	
}