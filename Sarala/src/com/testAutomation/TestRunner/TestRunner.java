package com.testAutomation.TestRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
		features = "./Features",
		glue     = {"com.testAutomation.StepDef"},
		tags   	 = {"@FunctionalTest"},
		plugin   = {"pretty","html:target/site/cucumber-pretty","json:target/cucumber.json"},
		monochrome= true)

public class TestRunner 
{
	private static TestNGCucumberRunner testNGCucumberRunner;
	  // private static Logger log = Logger.getLogger(TestRunner.class);
		
		@BeforeClass(alwaysRun = true)
		public void setUpClass() throws Exception
		{
		//log.info("The intiatlization started: ");
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		}
		
		@Test(dataProvider = "features")
		public void feature(CucumberFeatureWrapper CucumberFeature) 
		{
		testNGCucumberRunner.runCucumber(CucumberFeature.getCucumberFeature());
		}
			
		@DataProvider
		public Object[][] features()
		{
			return testNGCucumberRunner.provideFeatures();			
		}
		
		@AfterClass(alwaysRun=true)
		public void tearDownClass() throws Exception
		{
			testNGCucumberRunner.finish();
			//log.info("The Completion of test....");		
		}	
}
