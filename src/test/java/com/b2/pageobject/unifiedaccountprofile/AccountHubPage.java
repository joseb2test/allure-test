package com.b2.pageobject.unifiedaccountprofile;

import com.b2.helper.LocatorHelper;
import com.b2.pageobject.AbstractPage;
import com.b2.ui.Button;
import com.b2.ui.HyperLink;
import com.b2.ui.TextBox;



public class AccountHubPage extends AbstractPage{

	public TextBox searchbar() {
 		return new TextBox(LocatorHelper.getLocator());
	}
	public HyperLink iframe() {
 		return new HyperLink(LocatorHelper.getLocator());
	}
	public HyperLink accountheader() {
 		return new HyperLink(LocatorHelper.getLocator());
	}
	public HyperLink intent() {
 		return new HyperLink(LocatorHelper.getLocator());
	}
	public HyperLink toast() {
 		return new HyperLink(LocatorHelper.getLocator());
	}
	public Button downarrow() {
 		return new Button(LocatorHelper.getLocator());
	}
	public HyperLink accountWebsite() {
 		return new HyperLink(LocatorHelper.getLocator());
	}
	public HyperLink downElement() {
 		return new HyperLink(LocatorHelper.getLocator());
	}
}
