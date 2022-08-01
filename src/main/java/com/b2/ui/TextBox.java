package com.b2.ui;

import org.openqa.selenium.Keys;

import com.b2.driver.B2UIDriver;


public class TextBox extends UIElement{

	public TextBox(String locatorString) {
		super(locatorString);
	}
	
	public TextBox() {
		super();
	}

	public TextBox(UIElement theParentUIElement, String locatorString) {
		super(theParentUIElement, locatorString);
	}

	public  void type(String text) {
		if(!isActive)
			B2UIDriver.getLocatorObject(this).sendKeys(text);
		else B2UIDriver.getActiveElement().sendKeys(text);
	}
	
	public  void type(Keys key) {
		if(!isActive)
			B2UIDriver.getLocatorObject(this).sendKeys(key);
		else B2UIDriver.getActiveElement().sendKeys(key);
	}
	
	public  void clear() {
		B2UIDriver.getLocatorObject(this).clear();
	}

	
	public  String getContent() {
		return B2UIDriver.getLocatorObject(this).getAttribute("value");
	}
	
	public  void select(String value) {
		if(!isActive)
		{
			B2UIDriver.getLocatorObject(this).click();
			B2UIDriver.getLocatorObject(this).sendKeys(value);
			B2UIDriver.getLocatorObject(this).sendKeys(Keys.DOWN);
			if(value.equalsIgnoreCase("high"))
			{
				B2UIDriver.getLocatorObject(this).sendKeys(Keys.DOWN);
			}
			B2UIDriver.getLocatorObject(this).sendKeys(Keys.TAB);
			
		}
		else
		{
			B2UIDriver.getActiveElement().click();
			B2UIDriver.getActiveElement().sendKeys(value);
			B2UIDriver.getActiveElement().sendKeys(Keys.DOWN);
			if(value.equalsIgnoreCase("high"))
			{
				B2UIDriver.getActiveElement().sendKeys(Keys.DOWN);
			}
			B2UIDriver.getActiveElement().sendKeys(Keys.TAB	);
			
		}
			
		
	}
}
