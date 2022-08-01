package com.b2.factory;


import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.b2.driver.B2UIDriver;
import com.b2.pageobject.AbstractPage;


public class B2PageFactory {

	private static B2PageFactory pageFactoryWrapper;
	
	private B2PageFactory(){
	}

	public synchronized static B2PageFactory getB2PageFactory() throws IOException{
		
		if (pageFactoryWrapper == null){
			pageFactoryWrapper = new B2PageFactory();
		}
		return pageFactoryWrapper;
	}
	
	public <T extends AbstractPage> T getPageObject(Class<T> klass) {
		return PageFactory.initElements(B2UIDriver.getWebDriver(), klass);
	}
}