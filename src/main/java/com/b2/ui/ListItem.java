package com.b2.ui;

import com.b2.driver.B2UIDriver;

public class ListItem extends UIElement{

	public ListItem(String theLocatorString) {
		super(theLocatorString);
	}
	
	public void click() {
		B2UIDriver.getLocatorObject(this).click();
	}

}
