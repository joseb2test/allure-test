package com.b2.ui;

import org.openqa.selenium.WebElement;

public class MenuItem extends UIElement{

	protected boolean isClickable;

	public MenuItem(String locatorString) {
		super(locatorString);
	}

	public MenuItem() {
		super();
	}
	
	public MenuItem(WebElement theWebElement) {
		super(theWebElement);
	}

	public boolean isClickable() {
		return isClickable;
	}

	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}

}
