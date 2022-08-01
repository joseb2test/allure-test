package com.b2.action;

import java.io.IOException;
import com.b2.driver.B2UIDriver;
import com.b2.factory.B2PageFactory;

public abstract class Action 
{
	protected static B2PageFactory b2PageFactory;

	public B2PageFactory getB2PageFactory() {
		return b2PageFactory;
	}

	public void setB2PageFactory(B2PageFactory theB2PageFactory) {
		b2PageFactory = theB2PageFactory;
	}

	public static void setUp() throws IOException{
		b2PageFactory = B2PageFactory.getB2PageFactory();
	}

	protected  void initializeBrowser() throws Exception {
		B2UIDriver.setUpUrl();
		B2UIDriver.setDesiredCapabilities();
		B2UIDriver.setUpWebDriver();
		B2UIDriver.openUrl();
	}

	public void close ()	
	{
		B2UIDriver.closeBrowser();
	}
	
}
