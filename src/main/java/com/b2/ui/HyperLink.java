package com.b2.ui;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.b2.driver.B2UIDriver;

public class HyperLink extends UIElement{

	public HyperLink(String locatorString) {
		super(locatorString);
	}
	
	public HyperLink() {
		super();
	}

	
	public HyperLink(WebElement theWebElement) {
		setUseLocator(false);
		switchToDefaultFrame();
		webElement = theWebElement;
	}

	public HyperLink(UIElement theParentUIElement, String locatorString) {
		super(theParentUIElement, locatorString);
	}
	public void click() {
		B2UIDriver.click(this);
	}
	
	public void fclick() {
		B2UIDriver.fclick(this);
	}

	public  void type(String text) {
		if(!isActive)
			B2UIDriver.getLocatorObject(this).sendKeys(text);
		else B2UIDriver.getActiveElement().sendKeys(text);
	}
	
}
