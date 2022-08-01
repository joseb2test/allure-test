package com.b2.ui;

import org.openqa.selenium.WebElement;

import com.b2.driver.B2UIDriver;

public class CheckBox extends UIElement{

	public CheckBox(String locatorString) {
		super(locatorString);
		// TODO Auto-generated constructor stub
	}
	
	public CheckBox() {
		super();
	}
	
	public CheckBox(WebElement theWebElement) {
		super(theWebElement);
	}

	public CheckBox(UIElement theParentUIElement, String locatorString) {
		super(theParentUIElement, locatorString);
	}

	public void check() {
		B2UIDriver.click(this);
	}

	public boolean isChecked(){
		return B2UIDriver.isElementChecked(this);
	}
}
