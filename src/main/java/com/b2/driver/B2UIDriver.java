package com.b2.driver;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.b2.constant.FrameworkCommonConstants;
import com.b2.helper.TestEnvironment;
import com.b2.pageobject.AbstractPage.LocatorType;
import com.b2.ui.UIElement;
import com.b2.ui.UIElementList;
import io.github.bonigarcia.wdm.WebDriverManager;
public class B2UIDriver 
{

	public enum BrowserType{
		FIREFOX, 
		CHROME,
	}

	protected static BrowserType browserType;
	private static String url;
	private static RemoteWebDriver webDriver;
	
	private static DesiredCapabilities desiredCapabilities;
	
	
	public void get(String url)
	{

		webDriver.get(url);
	}

	public String getCurrentUrl()
	{
		return webDriver.getCurrentUrl();
	}

	public  static Capabilities getCapabilities()
	{
		return webDriver.getCapabilities();
	}
	
	public static WebElement getActiveElement()
	{
		return webDriver.switchTo().activeElement();
	}

	public static void click(UIElement theElement) 
	{
		getLocatorObject(theElement).click();
	}

	public static void fclick(UIElement theElement) {
		WebElement element = getLocatorObject(theElement);
		((JavascriptExecutor)webDriver).executeScript("arguments[0].onclick();", element);
	}

	public static void clear(UIElement theElement) {
		getLocatorObject(theElement).clear();
	}

	public static boolean isElementPresent(UIElement theElement) {
	     return webDriver.findElements(getLocatorObjectBy(theElement)).size() > 0;
	 }
	
	public static String getAttributeValue(UIElement theElement) {
		return getLocatorObject(theElement).getAttribute(theElement.getAttribute());
	}

	public static int getChildElementsCount(UIElement theElement) {
		return getLocatorObjects(theElement).size();
	}

	public static JavascriptExecutor getJavascriptExecutor() {
		return ((JavascriptExecutor) webDriver);
	}

	public static Object executeScript(String script,UIElement theElement)
	{
		JavascriptExecutor jsExec = getJavascriptExecutor();
		return jsExec.executeScript(script,getLocatorObject(theElement));
	}

	public static By getLocatorObjectBy(UIElement theElement) {
		if(theElement.isUseLocator() && !theElement.isHasParent()) {
			String locator = theElement.getLocatorString();
			By locatorToBeIdentifiedBy = null;
			LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
			locator = locator.substring((locator.indexOf("=")+1));			
			switch (identifier) {
			case XPATH:
				locatorToBeIdentifiedBy = By.xpath(locator);
				break;
			case CSS:
				locatorToBeIdentifiedBy = By.cssSelector(locator);
				break;
			case ID:
				locatorToBeIdentifiedBy = By.id(locator);
				break;
			case NAME:
				locatorToBeIdentifiedBy = By.name(locator);
				break;
			case CLASS:
				locatorToBeIdentifiedBy =By.className(locator);
				break;
			case LINK:
				locatorToBeIdentifiedBy = By.linkText(locator);
				break;

			}
			return locatorToBeIdentifiedBy;
		} 

		else if(theElement.isUseLocator() && theElement.isHasParent()) {
			String locator = theElement.getLocatorString();
			WebElement parentWebElement = theElement.getParentWebElement();
			By locatorToBeIdentifiedBy = null;
			LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
			locator = locator.substring((locator.indexOf("=")+1));
			switch (identifier) {
			case XPATH:
				locatorToBeIdentifiedBy = By.xpath(locator);
				break;
			case CSS:
				locatorToBeIdentifiedBy = By.cssSelector(locator);
				break;
			case ID:
				locatorToBeIdentifiedBy = By.id(locator);
				break;
			case NAME:
				locatorToBeIdentifiedBy = By.name(locator);
				break;
			case CLASS:
				locatorToBeIdentifiedBy = By.className(locator);
				break;
			case LINK:
				locatorToBeIdentifiedBy = By.linkText(locator);
				break;
			}
			return locatorToBeIdentifiedBy;
		} 

		return null;
	}

	public static WebElement getLocatorObject(UIElement theElement) {
		if(theElement.isUseLocator() && !theElement.isHasParent()) {
			String locator = theElement.getLocatorString();
			WebElement locatorToBeIdentifiedBy = null;
			LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
			locator = locator.substring((locator.indexOf("=")+1));			
			switch (identifier) {
			case XPATH:
				locatorToBeIdentifiedBy = webDriver.findElement(By.xpath(locator));
				break;
			case CSS:
				locatorToBeIdentifiedBy = webDriver.findElement(By.cssSelector(locator));
				break;
			case ID:
				locatorToBeIdentifiedBy = webDriver.findElement(By.id(locator));
				break;
			case NAME:
				locatorToBeIdentifiedBy = webDriver.findElement(By.name(locator));
				break;
			case CLASS:
				locatorToBeIdentifiedBy = webDriver.findElement(By.className(locator));
				break;
			case LINK:
				locatorToBeIdentifiedBy = webDriver.findElement(By.linkText(locator));
				break;
			case LINKP:
				locatorToBeIdentifiedBy = webDriver.findElement(By.partialLinkText(locator));
				break;
			default:
				locatorToBeIdentifiedBy = webDriver.findElement(By.cssSelector(locator));
				break;
			}
			return locatorToBeIdentifiedBy;
		} 

		else if(theElement.isUseLocator() && theElement.isHasParent()) {
			String locator = theElement.getLocatorString();
			WebElement parentWebElement = theElement.getParentWebElement();
			WebElement locatorToBeIdentifiedBy = null;
			LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
			locator = locator.substring((locator.indexOf("=")+1));
			switch (identifier) {
			case XPATH:
				locatorToBeIdentifiedBy = parentWebElement.findElement(By.xpath(locator));
				break;
			case CSS:
				locatorToBeIdentifiedBy = parentWebElement.findElement(By.cssSelector(locator));
				break;
			case ID:
				locatorToBeIdentifiedBy = parentWebElement.findElement(By.id(locator));
				break;
			case NAME:
				locatorToBeIdentifiedBy = parentWebElement.findElement(By.name(locator));
				break;
			case CLASS:
				locatorToBeIdentifiedBy = parentWebElement.findElement(By.className(locator));
				break;
			case LINK:
				locatorToBeIdentifiedBy = parentWebElement.findElement(By.linkText(locator));
				break;
			case LINKP:
				locatorToBeIdentifiedBy = webDriver.findElement(By.partialLinkText(locator));
				break;
			default:
				locatorToBeIdentifiedBy = webDriver.findElement(By.cssSelector(locator));
				break;
			}
			return locatorToBeIdentifiedBy;
		} 

		else if(!theElement.isUseLocator() && !theElement.isHasParent())
			return theElement.getWebElement();
		return null;
	}

	public static List<WebElement> getLocatorObjects(UIElement theElement) {
		String locator = theElement.getLocatorString();
		List<WebElement> locatorToBeIdentifiedBy = null;
		LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
		locator = locator.substring(locator.indexOf("=")+1);
		switch (identifier) {
		case XPATH:
			locatorToBeIdentifiedBy = webDriver.findElements(By.xpath(locator));
			break;
		case CSS:
			locatorToBeIdentifiedBy = webDriver.findElements(By.cssSelector(locator));
			break;
		case ID:
			locatorToBeIdentifiedBy = webDriver.findElements(By.id(locator));
			break;
		case NAME:
			locatorToBeIdentifiedBy = webDriver.findElements(By.name(locator));
			break;
		case CLASS:
			locatorToBeIdentifiedBy = webDriver.findElements(By.className(locator));
			break;
		case LINK:
			locatorToBeIdentifiedBy = webDriver.findElements(By.linkText(locator));
			break;
		}
		return locatorToBeIdentifiedBy;
	}


	public static List<WebElement> getLocatorObjects(UIElementList<? extends UIElement> theElementList) {
		if(theElementList.isUseLocator() && !theElementList.isHasParent()) {
			String locator = theElementList.getLocatorString();
			List<WebElement> locatorToBeIdentifiedBy = null;
			LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
			locator = locator.substring(locator.indexOf("=")+1);
			switch (identifier) {
			case XPATH:
				locatorToBeIdentifiedBy = webDriver.findElements(By.xpath(locator));
				break;
			case CSS:
				locatorToBeIdentifiedBy = webDriver.findElements(By.cssSelector(locator));
				break;
			case ID:
				locatorToBeIdentifiedBy = webDriver.findElements(By.id(locator));
				break;
			case NAME:
				locatorToBeIdentifiedBy = webDriver.findElements(By.name(locator));
				break;
			case CLASS:
				locatorToBeIdentifiedBy = webDriver.findElements(By.className(locator));
				break;
			case LINK:
				locatorToBeIdentifiedBy = webDriver.findElements(By.linkText(locator));
				break;
			}
			return locatorToBeIdentifiedBy;
		}

		else if(theElementList.isUseLocator() && theElementList.isHasParent()) {
			String locator = theElementList.getLocatorString();
			WebElement parentWebElement = theElementList.getParentWebElement();
			List<WebElement> locatorToBeIdentifiedBy = null;
			LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
			locator = locator.substring(locator.indexOf("=")+1);
			switch (identifier) {
			case XPATH:
				locatorToBeIdentifiedBy = parentWebElement.findElements(By.xpath(locator));
				break;
			case CSS:
				locatorToBeIdentifiedBy = parentWebElement.findElements(By.cssSelector(locator));
				break;
			case ID:
				locatorToBeIdentifiedBy = parentWebElement.findElements(By.id(locator));
				break;
			case NAME:
				locatorToBeIdentifiedBy = parentWebElement.findElements(By.name(locator));
				break;
			case CLASS:
				locatorToBeIdentifiedBy = parentWebElement.findElements(By.className(locator));
				break;
			case LINK:
				locatorToBeIdentifiedBy = parentWebElement.findElements(By.className(locator));
				break;	
			}
			return locatorToBeIdentifiedBy;
		}
		return null;
	}

	
	public static String getText(UIElement theElement) {
		return getLocatorObject(theElement).getText();
	}

	public static String getCssValue(UIElement theElement, String attribute) {
		return getLocatorObject(theElement).getCssValue(attribute);
	}

	public static boolean isEnabled(UIElement theElement) {
		return getLocatorObject(theElement).isEnabled();
	}

	public static String getCurrentLocation() {
		return webDriver.getCurrentUrl();
	}

	public static void openUrl() 
	{
		webDriver.get(url);
	}

	public static void switchToDefaultFrame() 
	{
		webDriver.switchTo().defaultContent();
	}

	public static void switchToFrame(String id) {
		webDriver.switchTo().frame(id);
	}

	public static void switchToFrame(UIElement theElement) 
	{
		webDriver.switchTo().frame(getLocatorObject(theElement));
	}

	public static void refreshPage() 
	{
		webDriver.navigate().refresh();
	}

	public static void waitForAnElement(final UIElement theElement) {
		try{
			int maxTimeout = new Integer(TestEnvironment.getTestEnvironmentObject().getMaxTimeOut()).intValue();
			new WebDriverWait(webDriver,maxTimeout)
			.until(new ExpectedCondition<WebElement>(){
				public WebElement apply(WebDriver d) {
					return getLocatorObject(theElement);
				}});
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e){
			e.printStackTrace();

		}
	} 

	public static void waitForAnElementPresent(final UIElement theElement) {
		try{
			int maxTimeout = new Integer(TestEnvironment.getTestEnvironmentObject().getMaxTimeOut()).intValue();
			new WebDriverWait(webDriver, maxTimeout).
			until(ExpectedConditions.presenceOfElementLocated(getLocatorObjectBy(theElement)));

		} catch (Exception e){
			/*Reporter.log("There was an issue in finding the webelement, " + theElement.getClass() + e.getMessage());
			e.printStackTrace();*/

		}
	} 
	
	public static void waitForAnElementVisible(final UIElement theElement) {
		try{
			int maxTimeout = new Integer(TestEnvironment.getTestEnvironmentObject().getMaxTimeOut()).intValue();
			new WebDriverWait(webDriver, maxTimeout).
			until(ExpectedConditions.visibilityOf(getLocatorObject(theElement)));

		} catch (Exception e){
			/*Reporter.log("There was an issue in finding the webelement, " + theElement.getClass() + e.getMessage());
			e.printStackTrace();*/

		}
	} 

	public static void waitForAnElementList(final UIElementList<? extends UIElement> theElementList) {
		int maxTimeout = new Integer(TestEnvironment.getTestEnvironmentObject().getMaxTimeOut()).intValue();
		new WebDriverWait(webDriver, maxTimeout+30)
		.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d) {
				List<WebElement> webElements = getLocatorObjects(theElementList);
				return !webElements.isEmpty();
			}});
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isTextPresent(String textValue){
		System.out.println("Looking for the element...: " + textValue); 
		WebElement webElement = webDriver.findElement(By.tagName("html"));
		if(webElement.getText().contains(textValue)) {
			return true;
		} else {
			System.out.println("Element not found : " + textValue);
			return false;
		}
	}

	public static void selectWindow(int window) {
		Set<String> winNames=webDriver.getWindowHandles();
		webDriver.switchTo().window((String) winNames.toArray()[window-1]);
	}
	
	public static void switchToWindow(String winHandle) {
		webDriver.switchTo().window(winHandle);
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String theUrl) {
		url = theUrl;

	}

	public static RemoteWebDriver getWebDriver() {
		return webDriver;
	}

	public static void setWebDriver(RemoteWebDriver theWebDriver) {
		B2UIDriver.webDriver = theWebDriver;
	}

	public static void setUpWebDriver() throws Exception {
		String seleniumHost = TestEnvironment.getTestEnvironmentObject().getSeleniumHost();
		String seleniumPort = TestEnvironment.getTestEnvironmentObject().getSeleniumPort();
		if(seleniumHost.contains("localhost")) {
			switch(TestEnvironment.getTestEnvironmentObject().getBrowserType()) {
			case FIREFOX:		
				System.out.println("Selecting the default firefox profile");
				webDriver = new FirefoxDriver();
				break;
			case CHROME:
				try{
					System.setProperty("webdriver.chrome.driver", FrameworkCommonConstants.CHROME_DRIVER_LOC);  
					//WebDriverManager.chromedriver().setup();
					webDriver = new ChromeDriver(getDesiredCapabilities());
					webDriver.manage().window().maximize();
					
				}catch (Exception e)
				{
					throw e;
				}
				break;
			default:
				throw new RuntimeException("Browser type unsupported");
			}
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} 
		else {
			//Code for invoking remote webdriver
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			webDriver = new RemoteWebDriver(new URL("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"), capability);
			webDriver.setFileDetector(new LocalFileDetector());
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			webDriver.manage().window().maximize();
		}
	}


	/**
	 * 
	 * @throws IOException
	 */
	public static LocalStorage local;
	public static void setUpCookie(String key,String value) throws Exception
	{
		Cookie cookie = new Cookie.Builder(key, value).domain(".bamboobox.in").build();
		webDriver.manage().addCookie(cookie);
		//webDriver.manage().addCookie(new Cookie(key, value));
//		local = ((WebStorage) webDriver).getLocalStorage();    
//		System.out.println(local);
//	    local.setItem(key, value);

	}
	public static void setLocalStorage(String key,String value) throws Exception
	{
		

		local = ((WebStorage) webDriver).getLocalStorage();    
		System.out.println(local);
	    local.setItem(key, value);
	    System.out.println(local.getItem(key));

	}
	public static void setUpUrl() throws Exception
	{
		url = TestEnvironment.getTestEnvironmentObject().getUrl();
		System.out.println("Application URL : "+url);
	}

	public static void deleteCookies() {
		webDriver.manage().deleteAllCookies();
	}
	
	public static Cookie getCookiesNamed(String cookieName)
	{
		return webDriver.manage().getCookieNamed(cookieName);
	}


	public static Set<Cookie> getCookie()
	{
		return webDriver.manage().getCookies();
	}
	
	
	public static void closeBrowser() {
		try{
			webDriver.close();

		}catch(org.openqa.selenium.remote.UnreachableBrowserException ex){
			ex.printStackTrace();
		}catch (UnhandledAlertException ex1){
			ex1.getStackTrace();
			B2UIDriver.getWebDriver().switchTo().alert().dismiss();
		}catch (ElementNotVisibleException ex3){
			ex3.getStackTrace();
		}
	}

	public static void quitBrowser() {
		try{
			webDriver.quit();

		}catch(org.openqa.selenium.remote.UnreachableBrowserException ex){
			ex.printStackTrace();
		}
	}
	
	public static  List<UIElement> getElements(UIElement theElement) {
		Constructor<?> constructor = null;
		try {
			constructor = theElement.getClass().getConstructor();
		} catch (Exception e) {
			e.printStackTrace();
		} 

		List<UIElement> uiElementList = new ArrayList<UIElement>();
		List<WebElement>  elementList = B2UIDriver.getLocatorObjects(theElement);

		for (WebElement anElement : elementList){
			try {
				UIElement uiElement = (UIElement) constructor.newInstance();
				uiElement.setWebElement(anElement);
				uiElementList.add(uiElement);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return uiElementList;
	}

	public static void alert()
	{
		webDriver.switchTo().alert();
	}

	public static void mouseover(UIElement theElement)
	{
		new Actions(webDriver).moveToElement(getLocatorObject(theElement)).build().perform();
	}

	public static void mouseOverClick(UIElement theElement)
	{
		if (!(webDriver instanceof SafariDriver)) {
			new Actions(webDriver).moveToElement(getLocatorObject(theElement)).click().perform();

		}else{
			try{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('click', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onclick');}";
				JavascriptExecutor js = (JavascriptExecutor) webDriver;
				js.executeScript(mouseOverScript, getLocatorObject(theElement));
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}

	public static void dragdrop(UIElement fromElement,UIElement toElement) {
		WebElement LocatorFrom = getLocatorObject(fromElement);
		WebElement LocatorTo = getLocatorObject(toElement);
		String xto=Integer.toString(LocatorTo.getLocation().x);
		String yto=Integer.toString(LocatorTo.getLocation().y);
		((JavascriptExecutor)webDriver).executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
				"simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
				LocatorFrom,xto,yto);
	}

	public static void focus(UIElement theElement)
	{
		if(getLocatorObject(theElement).getTagName().equals("input")){
			getLocatorObject(theElement).sendKeys("");
		} 
		else{
			try{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) webDriver;
				js.executeScript(mouseOverScript, getLocatorObject(theElement));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public static void selectDropDown(WebElement theSelectElement,String valToSelect)
	{
		Select select = new Select(theSelectElement);  
		// Get a list of the options 
		List<WebElement> options = select.getOptions();  
		// For each option in the list, verify if it's the one you want and then click it 
		for (WebElement we : options) {     
			if (we.getText().equalsIgnoreCase(valToSelect)) {         
				System.out.println(String.format("Value is: %s", we.getText()));
				we.click();
				break;     
			} 
		} 

	}

	public static void selectValueFromDropDown(WebElement theSelectElement,String valToSelect)
	{
		Select select = new Select(theSelectElement);  
		// Get a list of the options 
		List<WebElement> options = select.getOptions();  
		// For each option in the list, verify if it's the one you want and then click it 
		for (WebElement we : options) {     
			if (we.getText().equalsIgnoreCase(valToSelect)) {         
				System.out.println(String.format("Value is: %s", we.getText()));
				select.selectByValue(valToSelect);
				break;     
			} 
		} 

	}

	public static void selectValueVisible(WebElement theSelectElement,String valToSelect){
		new Select(theSelectElement).selectByVisibleText(valToSelect);  
	}

	public static String[] getValueFromDropDown(WebElement theSelectElement)
	{
		Select select = new Select(theSelectElement);  
		// Get a list of the options 
		List<WebElement> options = select.getOptions();
		String[] value = new String[options.size()];
		for (int i=0;i<options.size();i++) {
			value[i] =options.get(i).getText();
		}
		return value;
	}

	public static String selectedItem(UIElement theSelectElement)
	{ 
		Select select = new Select(getLocatorObject(theSelectElement)); 
		WebElement anElement = select.getFirstSelectedOption();
		return anElement.getText();     
	}

	public static boolean isElementChecked(UIElement theElement){
		boolean retValue = false;
		if ((getLocatorObject(theElement)).isSelected()){
			retValue = true;
		}
		return retValue;
	}



	public static void setDesiredCapabilities() throws Exception {
		desiredCapabilities = new DesiredCapabilities();
		switch(TestEnvironment.getTestEnvironmentObject().getBrowserType()) {
		case CHROME:
			desiredCapabilities = DesiredCapabilities.chrome();	
			desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, "true");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless"); 
			options.addArguments("--disable-gpu");
			options.addArguments("window-size=1920x1080");
			options.addArguments("test-type");
			options.addArguments("allow-running-insecure-content");
			options.addArguments("ignore-certificate-errors");
			options.addArguments("--user-data-dir="+TestEnvironment.getTestEnvironmentObject().getChromeProfileDir());
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			desiredCapabilities.setCapability("elementScrollBehavior", 1);
			break;
		default:
			desiredCapabilities = DesiredCapabilities.firefox();
			desiredCapabilities.setCapability("elementScrollBehavior", 1);
			break;
		}
	}

	private static DesiredCapabilities getDesiredCapabilities() {
		return desiredCapabilities;
	}

	public static Object executeJavaScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return js.executeScript(script);
	}

	public static String getPageSource() {
		return webDriver.getPageSource();
	}

	public static void redirectToURL(String url) {
		webDriver.navigate().to(url);
	}
	public static void takeScreenshot()
	{
		 File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
	        
	        //Copy the file to a location and use try catch block to handle exception
	        try {
	        	int screenshotLocation= (int) (Math.random()*100);
	            FileUtils.copyFile(screenshot, new File("C:/Users/aishwarya/Downloads/screenshotLocation"+screenshotLocation+".png"));
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	}

}
