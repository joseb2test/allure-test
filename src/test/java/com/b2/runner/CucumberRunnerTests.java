package com.b2.runner;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@Test
@CucumberOptions(plugin = { "pretty" }, tags = "", features = "src/test/resources/features/salesforce.feature", glue = {"com.b2.stepdefinitions"})
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
}