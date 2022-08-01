package com.b2.ui;

import com.b2.driver.B2UIDriver;

public class RadioButton extends UIElement{

	public RadioButton(String locatorString) {
		super(locatorString);
	}
	
	public RadioButton() {
		super();
	}

	public RadioButton(UIElement theParentUIElement, String locatorString) {
		super(theParentUIElement, locatorString);
	}

	public void select() {
		B2UIDriver.click(this);
	}
	
	public boolean isSelected(){
		return B2UIDriver.isElementChecked(this);
	}
}
