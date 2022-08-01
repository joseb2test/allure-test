package com.b2.ui;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.b2.driver.B2UIDriver;

public class UnorderedList extends UIElement{

	public UnorderedList(String locator) {
		super(locator);
	}

	public List<WebElement> getUIElementList()
	{
		List<WebElement> allLnkElems = B2UIDriver.getLocatorObjects(this);
		return allLnkElems;

	}

	public String clickFilteredList(List<WebElement>lstelem,int index) {

		int i=0;
		String text = "";
		for(WebElement elem : lstelem)
		{
			if(index==i)
			{
				text = elem.getText();
				elem.click();				
				break;
			}
			else
			{
				i = i+1;
			}
		}

		return text;


	}

	public ArrayList<WebElement> getFilteredList( UnorderedList laType ,String stext)
	{
		List<WebElement> tobefiltered = getUIElementList();
		List<WebElement> tobefilteredtype = getUIElementList();
		ArrayList<WebElement> filteredelems = new ArrayList<WebElement>();

		for (int i = 0; i < tobefilteredtype.size(); i++) {
			WebElement elem = tobefilteredtype.get(i);
			if(elem.getText().contains("(" + stext + ")")) {
				filteredelems.add(tobefiltered.get(i));
			}
		}
		return filteredelems;

	}

}
