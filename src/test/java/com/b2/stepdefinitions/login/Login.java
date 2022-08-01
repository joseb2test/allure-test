package com.b2.stepdefinitions.login;


import com.b2.action.Navigation;
import com.b2.action.impl.B2Action;
import com.b2.action.impl.NavigationAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class Login {

	B2Action b2;
	Navigation navigation;
	
	@Given("{string} is logged into B2")
	public void is_logged_into_B2(String string) 
	{
		b2 = new B2Action();
		b2.login();
	}

	@When("he navigates to {string}")
	public void he_navigates_to(String string) 
	{
		navigation = new NavigationAction();
		navigation.navigatesToView(string);
	}

}