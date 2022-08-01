package com.b2.pageobject.login;

import com.b2.helper.LocatorHelper;
import com.b2.pageobject.AbstractPage;
import com.b2.ui.Button;
import com.b2.ui.DropDownList;
import com.b2.ui.HyperLink;
import com.b2.ui.TextBox;
import com.b2.ui.UIElement;
import com.b2.ui.UnorderedList;


public class LoginPage extends AbstractPage{
	

	public HyperLink signinWithGoogle() {
 		return new HyperLink(LocatorHelper.getLocator());
		
	}

	public HyperLink signinWithMicrosoft() {
 		return new HyperLink(LocatorHelper.getLocator());
		
	}

	public TextBox selectTenant() {
		return new TextBox(LocatorHelper.getLocator());
				
	}

	public HyperLink signInButton() {
		return new HyperLink(LocatorHelper.getLocator());
	  	
	}	
}