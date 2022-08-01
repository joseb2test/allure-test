package com.b2.pageobject.menu;

import com.b2.helper.LocatorHelper;
import com.b2.pageobject.AbstractPage;
import com.b2.ui.Button;
import com.b2.ui.HyperLink;
import com.b2.ui.TextBox;
import com.b2.ui.UIElement;

public class MenuPage extends AbstractPage{
	

	public HyperLink logout() {
 		return new HyperLink(LocatorHelper.getLocator());
	}
	
	public HyperLink settings() {
		return new HyperLink(LocatorHelper.getLocator());
	}
	 
	public HyperLink serverConfiguration() {
		return new HyperLink(LocatorHelper.getLocator());
	}
	
	public HyperLink logo() {
		return new HyperLink(LocatorHelper.getLocator());
	}
	public HyperLink accountHub() {
		return new HyperLink(LocatorHelper.getLocator());
	}
	public HyperLink unifiedAccountProfile() {
		return new HyperLink(LocatorHelper.getLocator());
	}
	
}