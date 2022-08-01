package com.b2.ui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.b2.driver.B2UIDriver;

public class UIElementList<T extends UIElement> {

	protected List<T> uiElements;
	protected String locatorString;
	protected WebElement parentWebElement;
	private boolean useLocator = true;
	protected boolean hasParent = false;

	public UIElementList(String theLocatorString){
		setUseLocator(true);
		setHasParent(false);
		locatorString = theLocatorString;
	}
	
	public UIElementList(UIElement theUIElement, String theLocatorString) {
		setUseLocator(true);
		setHasParent(true);		
		setParentWebElement(theUIElement.getWebElement());
		setLocatorString(theLocatorString);
	}

	public List<T> getChildElements(Class<T> klass) {
		Constructor<?> constructor = null;
		try {
			constructor = klass.getConstructor();
		} catch (Exception e) {
			e.printStackTrace();
		} 

		List<T> uiElementList = new ArrayList<T>();
		List<WebElement>  elementList = B2UIDriver.getLocatorObjects(this);

		for (WebElement anElement : elementList){
			try {
				@SuppressWarnings("unchecked")
				T t = (T) constructor.newInstance();
				
				t.setWebElement(anElement);
				uiElementList.add(t);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return uiElementList;
	}
	
	public String getLocatorString() {
		return locatorString;
	}

	public void setLocatorString(String theLocatorString) {
		this.locatorString = theLocatorString;
	}

	public List<T> getUiElements() {
		return uiElements;
	}

	public void setUiElements(List<T> theUIElements) {
		this.uiElements = theUIElements;
	}
	
	public void waitForLoading() {
		B2UIDriver.waitForAnElementList(this);
	}

	public void setUseLocator(boolean useLocator) {
		this.useLocator = useLocator;
	}

	public boolean isUseLocator() {
		return useLocator;
	}
	
	public boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}
	
	public WebElement getParentWebElement() {
		return parentWebElement;
	}

	public void setParentWebElement(WebElement parentWebElement) {
		this.parentWebElement = parentWebElement;
	}

}
