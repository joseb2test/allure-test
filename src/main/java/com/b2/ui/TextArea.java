package com.b2.ui;

import com.b2.driver.B2UIDriver;


public class TextArea extends UIElement{

	public TextArea(String locator) {
		super(locator);
	}

	public TextArea() {
		super();
	}
	
	public TextArea(UIElement theParentUIElement, String locatorString) {
		super(theParentUIElement, locatorString);
	}

	public  void type(String text) {
		if(!isActive)
			B2UIDriver.getLocatorObject(this).sendKeys(text);
		else B2UIDriver.getActiveElement().sendKeys(text);
	}
}
