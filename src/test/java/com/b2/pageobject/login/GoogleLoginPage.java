package com.b2.pageobject.login;

import com.b2.helper.LocatorHelper;
import com.b2.pageobject.AbstractPage;
import com.b2.ui.Button;
import com.b2.ui.HyperLink;
import com.b2.ui.TextBox;
import com.b2.ui.UIElement;

public class GoogleLoginPage extends AbstractPage{
	

	public TextBox emailID() {
 		return new TextBox(LocatorHelper.getLocator());
		
	}

	public HyperLink emailIDSubmit() {
 		return new HyperLink(LocatorHelper.getLocator());
		
	}

	public TextBox password() {
 		return new TextBox(LocatorHelper.getLocator());
		
	}

	public HyperLink passwordSubmit() {
 		return new HyperLink(LocatorHelper.getLocator());
		
	}
	
}