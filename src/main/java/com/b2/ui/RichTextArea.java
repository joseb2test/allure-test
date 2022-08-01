package com.b2.ui;

import com.b2.driver.B2UIDriver;


public class RichTextArea extends UIElement{

	public RichTextArea(String locatorString) {
		super(locatorString);
	}

	public RichTextArea() {
		super();
	}
	
	public RichTextArea(UIElement theParentUIElement, String locatorString) {
		super(theParentUIElement, locatorString);
	}

	public  void type(String text) {
		if(!isActive)
			B2UIDriver.getLocatorObject(this).sendKeys(text);
		else B2UIDriver.getActiveElement().sendKeys(text);
	}
}
