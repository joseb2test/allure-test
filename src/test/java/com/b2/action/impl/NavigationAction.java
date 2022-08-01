package com.b2.action.impl;

import java.util.Map;

import com.b2.action.Action;
import com.b2.action.Navigation;
import com.b2.action.ScoreConfiguration;
import com.b2.logger.Logger;
import com.b2.pageobject.configuration.ICPCreationPage;
import com.b2.pageobject.configuration.ScoreConfigurationPage;
import com.b2.pageobject.menu.MenuPage;


public class NavigationAction extends Action implements Navigation
{
	public NavigationAction()
	{
		
	
	}
	
	public boolean navigatesToView(String viewName)
	{
		boolean result = false;
		
		if(viewName == null || viewName.equalsIgnoreCase(""))
			result = false;
		else if (viewName.trim().equalsIgnoreCase("Score Configuration"))
		{
			result = naviagateToScoreConfiguration();
		}
		else if (viewName.trim().equalsIgnoreCase("Account Hub"))
		{
			result = navigateToAccountHub();
		}
		return result;
		
	}
	
	private boolean navigateToAccountHub() {
		
		try
		{
		MenuPage menu = b2PageFactory.getPageObject(MenuPage.class);
		
		
		menu.logo().waitForLoading();
		menu.logo().mouseOver();
		
		menu.accountHub().waitForLoading();
		menu.accountHub().click();
		
		menu.unifiedAccountProfile().waitForLoading();
		menu.unifiedAccountProfile().click();
		
		return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			
			Logger.info("Navigation to account hub Failed!!! : ");
			e.printStackTrace();
			return false;
		}
		
		
	}

	public boolean naviagateToScoreConfiguration()
	{
		try
		{
			MenuPage menu = b2PageFactory.getPageObject(MenuPage.class);
			
			menu.logo().waitForLoading();
			menu.logo().mouseOver();
			
			menu.settings().waitForLoading();
			menu.settings().click();
			
			menu.serverConfiguration().waitForLoading();
			menu.serverConfiguration().click();
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			
			Logger.info("Navigation to score configuration Failed!!! : ");
			e.printStackTrace();
			return false;
		}
	}
	
}
