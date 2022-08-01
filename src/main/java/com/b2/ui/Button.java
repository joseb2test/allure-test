package com.b2.ui;

import org.openqa.selenium.WebElement;

import com.b2.driver.B2UIDriver;

public class Button extends UIElement{

	public Button(String locatorString) {
		super(locatorString);
	}
	
	public Button() {
		super();
	}

	public Button(WebElement theWebElement) {
		super(theWebElement);
	}

	public Button(UIElement theParentUIElement, String locatorString) {
		super(theParentUIElement, locatorString);
	}

	public  void type(String text) {
		if(!isActive)
			B2UIDriver.getLocatorObject(this).sendKeys(text);
		else B2UIDriver.getActiveElement().sendKeys(text);
	}

}
