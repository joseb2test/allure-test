package com.b2.pageobject.configuration;

import com.b2.helper.LocatorHelper;
import com.b2.pageobject.AbstractPage;
import com.b2.ui.HyperLink;
import com.b2.ui.UIElementList;

public class ScoreConfigurationPage extends AbstractPage{
	
	HyperLink icpLink;
	
	
	public HyperLink createICP() {
		return new HyperLink(LocatorHelper.getLocator());
	}
	
	public HyperLink textToFocus() {
		return new HyperLink(LocatorHelper.getLocator());
	}
	
	public HyperLink iframe()
	{
		return new HyperLink(LocatorHelper.getLocator());
	}
}
