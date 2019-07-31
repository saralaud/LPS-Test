package com.testAutomation.StepDef;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.lang.Math;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import java.util.Iterator;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testAutomation.Utility.PropertiesFileReader;
import com.testAutomation.reusableFunctions.ReusableComponents;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class DomesticFlightBooking extends ReusableComponents
{
	
	@Given("^User on the Flight Center home page$")
    public void user_on_the_flight_Center_home_page() throws Throwable 
	{
		PropertiesFileReader obj = new PropertiesFileReader();
		Properties properties = obj.getProperty();
		
		System.setProperty("webdriver.chrome.driver", "FlightCenterTestAutomation\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
    	driver.get(properties.getProperty("browser.HomePageURL"));
    	driver.manage().deleteAllCookies();
		Thread.sleep(1000);
	}
		
    @When("^User click on the Flight link on the menus$")
    public void user_click_on_the_Flight_link_on_the_menus() throws Throwable 
    {
    	WebElement FliLink = driver.findElement(By.xpath("//a[@class='fcc-nav-list__link fcc-nav-link waves-effect fcc-nav-link--top'][contains(text(),'Flights')]"));;
    	FliLink.click();
	}
	    
	@Then("^User click on the Domestic flights link$")
	public void user_click_on_the_Domestic_flights_link() throws Throwable 
	{
	    Thread.sleep(1500);
	    WebElement DomFlight= driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/aside[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]"));
		DomFlight.click();
	}

	@Then("^User should navigated to booking page and verify the page text$")
	public void user_should_navigated_to_booking_page_and_verify_the_page_text() throws Throwable
	{
			if(driver.getPageSource().contains("Domestic Flights")) {
			System.out.println("Domestic Flights Text has been Present"); } else
			System.out.println("Domestic Flights Text is not present");			
	}

	@Then("^User entered Wellington from Depature field and select the dropdown value$")
	public void user_entered_Wellington_from_Depature_field_and_select_the_dropdown_value() throws Throwable 
	{
		Thread.sleep(10000);
		WebElement DepatureCity = driver.findElement(By.xpath("//input[@id='edit-startcitystr']"));
		DepatureCity.click();
		DepatureCity.sendKeys("WLG");
		Thread.sleep(1000);

		List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class,'ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all')]/li/a"));
		for (WebElement option : options)
		{
		    if (option.getText().equals("Wellington, New Zealand - Wellington Airport (WLG)"))
		    {
		       	option.click();
		        break;
		    }
		}
	} 
	
	@Then("^User entered Auckland from Destination field and select the dropdown value$")
	public void user_entered_Auckland_from_Destination_field_and_select_the_dropdown_value() throws Throwable 
	{
		WebElement ArrivalCity = driver.findElement(By.xpath("//input[@id='edit-endcitystr']"));
		ArrivalCity.click();
		ArrivalCity.sendKeys("AKL");
		Thread.sleep(1000);

		List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class,'ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all')]/li/a"));
		for (WebElement option : options)
		{
		    if (option.getText().equals("Auckland, New Zealand - Auckland Airport (AKL)"))
		    {
		       	option.click();
		        break;
		    }
		}
	}

	@Then("^select the depature date from calender$")
	public void select_the_depature_date_from_calender() throws Throwable 
	{
	    WebElement startDate = driver.findElement(By.xpath("//input[@id='startdate']"));
	    String dateVal1 = "05/08/2019";
	    selectDateByJS(driver,startDate,dateVal1);
	}

	@Then("^select the retuning date from calender$")
	public void select_the_retuning_date_from_calender() throws Throwable 
	{
		  WebElement endDate = driver.findElement(By.xpath("//input[@id='enddate']"));
		  String dateVal2 = "28/08/2019";
		  selectDateByJS(driver,endDate,dateVal2);
	}

	@Then("^select the no of passanger as (\\d+) adults$")
	public void select_the_no_of_passanger_as_adults(int arg1) throws Throwable 
	{
		WebElement noofPassengersDD = driver.findElement(By.xpath("//*[@id=\"edit-passengers\"]/legend"));
		noofPassengersDD.click();
		WebElement noofPassengers = driver.findElement(By.xpath("//*[@id=\"edit-passengers\"]/div/div[1]/div/input[3]"));
		noofPassengers.click();
	}

	@Then("^click on the \"([^\"]*)\" button$")
	public void click_on_the_button(String arg1) throws Throwable 
	{
		WebElement findDeal = driver.findElement(By.xpath("//*[@value=\"Find deals\"]"));
		findDeal.click();		
	}
		
	
	@Then("^verify the result page loaded and fares sorted in descending order$")
	public void verify_the_result_page_loaded_and_fares_sorted_in_descending_order() throws Throwable
	{
		Thread.sleep(10000);
		List <WebElement> cheapPrice = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]"));
		for (WebElement option : cheapPrice)
			{
			    if (option.getText().equals("Price (Cheapest)"))
			    {
			    System.out.println("Validation1 Passed : The fares sorted descending (small to large)"); 
				} 
				else
				{
				System.out.println("Validation1 Failed : The fares is not sorted descending (small to large)");	
				} 	
			    
			}
	}
	
	@When("^customer select the cheapest fare for onward trip on the result page$")
	public void customer_select_the_cheapest_fare_for_onward_trip_on_the_result_page() throws Throwable 
	{
		getOnwardTicketPrice();
		
		WebElement OnwardRecord =driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]"));
		OnwardRecord.click();
		Thread.sleep(3000);
		System.out.println("Validation2.1 Passed: Onward flight cheapest ticket booked");
	}

	@Then("^customer click on faretype and navigated to return flights booking page$")
	public void customer_click_on_faretype_and_navigated_to_return_flights_booking_page() throws Throwable 
	{
		WebElement OnwardFareType= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[2]/button[1]"));
		OnwardFareType.click();
		Long start = System.currentTimeMillis();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 WebElement pageVerification= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[1]/h1[1]"));
		 pageVerification.isDisplayed();
		 Long end = System.currentTimeMillis();
			Long totalResTime = (end - start)/1000;
			System.out.println("The Response time for Onward ticket booking is :"+ totalResTime + " seconds");
			if (totalResTime>3.5)
			{System.out.println("Validation4 Failed : The response is not come back within 3.5 seconds ");}
			else
			System.out.println("Validation4 Passed : The response come back within 3.5 seconds");	 
	}

	@Then("^Verify the Select Return Flight text on the result page$")
	public void verify_the_Select_Return_Flight_text_on_the_result_page() throws Throwable 
	{
		Thread.sleep(10000);
	    WebElement pageVerification= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[1]/h1[1]"));
	    String actualtext= pageVerification.getText();
	    assertTrue(actualtext.contains("Select Return Flight"));	    
	}
	
	@When("^customer select the cheapest return flight on booking page$")
	public void customer_select_the_cheapest_return_flight_on_booking_page() throws Throwable 
	{
		getReturnTicketPrice();
		
		WebElement returnRecord= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]"));
		returnRecord.click();
		System.out.println("Validation2.2 Passed: Return flight cheapest ticket booked");
	}
	
	@And("^click on the returning Fare Type and verify the result page$")
	public void click_on_the_returning_Fare_Type_and_verify_the_result_page() throws Throwable 
	{
		Thread.sleep(3000);
		WebElement returnFareType= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]"));
		returnFareType.click();
	}
	
	@Given("^verify the response time$")
	public void verify_the_response_time() throws Throwable 
	{	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						
		Long start = System.currentTimeMillis();
		WebElement resultPage= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]"));
		resultPage.getText();
		Long end = System.currentTimeMillis();
		Long totalResTime = (end - start)/1000;
		System.out.println("The Response time for Onward ticket booking is :"+ totalResTime + " seconds");
		if (totalResTime>3.5)
		{System.out.println("Validation4 Failed : The response is not come back within 3.5 seconds ");}
		else
		System.out.println("Validation4 Passed : The response come back within 3.5 seconds");		
	}
		
	@Given("^Get the price for onward and return tickets and validate the total outcome price$") 
	public void get_the_price_for_onward_and_return_tickets_and_validate_the_total_outcome_price() throws Throwable 
	{
		Thread.sleep(3000);
		WebElement TotalPrice = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]"));
		double ActualTotalFare = Double.parseDouble(TotalPrice.getText().replaceAll("\\s+","").replaceAll("\\$", ""));
		System.out.println("The Actual total price displayed in the result reviw page is :" + ActualTotalFare);
		
		if (ActualTotalFare!=ExpectedTotalFare)
		{
			System.out.println("Validation3 Passed: The Flights booked and the total price looks perfect");
		}
		else System.out.println("Validation3 Failed:Something wrong on the calculation");
	}

	@Given("^verify the proper message for session expire$")
	public void verify_the_proper_message_for_session_expire() throws Throwable 
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html[1]/body[1]/div[10]/div[2]/div[1]/div[1]/h2[1]"));
		Alert alert = driver.switchTo().alert();
		String alertMsg = alert.getText();
		System.out.println(alertMsg);
		alert.accept();
		
		if(alertMsg.contentEquals("Your session has expired"))
		{
			System.out.println("Validation5 Passed: Proper message displays for session expire");
		} else
			System.out.println("Validation5 Failed:Proper message is not display for session expire");
	}
}

	




	
    
    
