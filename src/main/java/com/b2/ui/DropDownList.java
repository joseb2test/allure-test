package com.b2.ui;

import com.b2.driver.B2UIDriver;

public class DropDownList extends UIElement{

	public DropDownList(String locatorString) {
		super(locatorString);
	}
	
	public DropDownList() {
		super();
	}

	public  void selectItem(String sItem) {
		B2UIDriver.selectDropDown(B2UIDriver.getLocatorObject(this), sItem);	
	}
	
	public  void selectValue(String sItem) {
		B2UIDriver.selectValueFromDropDown(B2UIDriver.getLocatorObject(this), sItem);	
	}
	
	public  void selectValueVisible(String sItem) {
		B2UIDriver.selectValueVisible(B2UIDriver.getLocatorObject(this), sItem);	
	}
	
	public String selectedItem(){
		return B2UIDriver.selectedItem(this);
	}
	
	public String[] getItemList(){
		return B2UIDriver.getValueFromDropDown(B2UIDriver.getLocatorObject(this));
	}
	

}
