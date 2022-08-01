package com.b2.ui;

import org.openqa.selenium.WebElement;

import com.b2.driver.B2UIDriver;


public abstract class UIElement {


	protected boolean isClickable;
	protected String locatorString;
	protected boolean isElementPresent;
	protected boolean isVisible;
	protected boolean isSelected;
	protected boolean isContentEditable;
	protected String attribute;
	protected boolean isActive;
	private boolean useLocator = true;
	private boolean hasParent = false;
	protected WebElement webElement;
	protected WebElement parentWebElement;


	public UIElement(String theLocatorString) {
		setUseLocator(true);
		setHasParent(false);
		this.locatorString = theLocatorString;
		
	}

	public UIElement() { 
		setUseLocator(false);
	}

	public UIElement(WebElement theWebElement) {
		setUseLocator(false);
		setHasParent(false);
		webElement = theWebElement;
	}

	public UIElement(UIElement theParentUIElement, String locatorString) {
		setParentWebElement(theParentUIElement.getWebElement());
		this.locatorString = locatorString;
		setUseLocator(true);
		setHasParent(true);
	}
	
	public WebElement getParentWebElement() {
		return parentWebElement;
	}

	public void setParentWebElement(WebElement parentWebElement) {
		this.parentWebElement = parentWebElement;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean theIsActive) {
		this.isActive = theIsActive;
	}

	public String getLocatorString() {
		return locatorString;
	}


	public void setLocatorString(String theLocatorString) {
		this.locatorString = theLocatorString;
	}

	public boolean isElementPresent() {
		boolean present = false;
		try{
			B2UIDriver.getLocatorObject(this);
			present = true;
		}catch(Exception e){
			present = false;
		} 
		return present; 

	}

	public boolean isPresent() {
		return B2UIDriver.isElementPresent(this);
	}
	
	public String getInnerHTML()
	{
		return (String) B2UIDriver.executeScript("return arguments[0].innerHTML", this);
		
		
	}
	public void setElementPresent(boolean isElementPresent) {
		this.isElementPresent = isElementPresent;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public boolean isVisible() {
		return B2UIDriver.getLocatorObject(this).isDisplayed();
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String theAttribute) {
		this.attribute = theAttribute;
	}

	public String getCssValue(String attribute) {
		return B2UIDriver.getCssValue(this, attribute);
	}
	
	public void waitForLoading() {
		B2UIDriver.waitForAnElement(this);
	}
	
	public void waitForElementToBePresent() {
		B2UIDriver.waitForAnElementPresent(this);
	}

	public void waitForElementToBeVisible() {
		B2UIDriver.waitForAnElementVisible(this);
	}

	public void switchToFrame(String id){
		B2UIDriver.switchToFrame(id);
	}
	
	public void SwitchToFrameWebElement()
	{
		B2UIDriver.switchToFrame(this);
	}
	
	public void mouseOver(){
		B2UIDriver.mouseover(this);
	}
	
	public void mouseOverClick(){
		B2UIDriver.mouseOverClick(this);
	}
	
	public void focus(){
		B2UIDriver.focus(this);
	}
	
	public void refreshPage(){
		B2UIDriver.refreshPage();
	}

	public void switchToDefaultFrame(){
		B2UIDriver.switchToDefaultFrame();
	}

	public int getChildCount() {
		return B2UIDriver.getChildElementsCount(this);
	}

	public boolean isContentEditable() {
		return isContentEditable;
	}

	public void setContentEditable(boolean isContentEditable) {
		this.isContentEditable = isContentEditable;
	}

	public boolean isUseLocator() {
		return useLocator;
	}

	public void setUseLocator(boolean theUseLocator) {
		this.useLocator = theUseLocator;
	}

	@Deprecated
	public String getElementText() {
		return B2UIDriver.getText(this);
	}

	public String getText() {
		return B2UIDriver.getText(this);
	}
	
	public boolean isEnabled() {
		return B2UIDriver.isEnabled(this);
	}

	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}

	public boolean isClickable() {
		return isClickable;
	}

	public void click() {
		
		B2UIDriver.click(this);
	}

	public void fclick() {
		B2UIDriver.fclick(this);
	}
	
	public WebElement getWebElement() {
		
		return webElement;
	}

	public void setWebElement(WebElement thWebElement) {
		this.webElement = thWebElement;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	public boolean isHasParent() {
		return hasParent;
	}

	public String getAttributeValue(String attributeName) {
		setAttribute(attributeName);
		return B2UIDriver.getAttributeValue(this);
	}
	
	public  void selectItem(String sItem) {
		B2UIDriver.selectDropDown(B2UIDriver.getLocatorObject(this), sItem);	
	}
	
	public  void selectValue(String sItem) {
		B2UIDriver.selectValueFromDropDown(B2UIDriver.getLocatorObject(this), sItem);	
	}
	
	public String selectedItem(){
		return B2UIDriver.selectedItem(this);
	}
	
	public String[] getItemList(){
		return B2UIDriver.getValueFromDropDown(B2UIDriver.getLocatorObject(this));
	}

}
