package com.b2.pageobject.dashboard;

import com.b2.helper.LocatorHelper;
import com.b2.pageobject.AbstractPage;
import com.b2.ui.HyperLink;

public class DashboardPage extends AbstractPage
{
	public HyperLink pageTitle() {
		return new HyperLink(LocatorHelper.getLocator());
	}

}
