package com.testAutomation.reusableFunctions;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.testAutomation.Utility.PropertiesFileReader;

public class ReusableComponents
{
	public static WebDriver driver;
	public String price1;
	public String price2;
	public double ExpectedTotalFare;
	
	public static void selectDateByJS(WebDriver driver,WebElement element,String dateVal) 
	{
			JavascriptExecutor js=((JavascriptExecutor)driver);
			js.executeScript("arguments[0].setAttribute('value','"+dateVal+"');", element);
	}
	
	public String getOnwardTicketPrice()
	{
		//Get the Onward Trip price ------->
		WebElement onwardTripPrice= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[6]/button[1]/span[1]/div[2]/span[1]"));
		price1= (onwardTripPrice.getText()).replaceAll("\\s+","");
		System.out.println("The Onward Trip price is: " + price1);
		
		return price1;
	}
	
	public String getReturnTicketPrice()
	{
		//Get the Return Trip price----->
		WebElement ReturnTripPrice= driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[6]/button[1]/span[1]/div[2]/span[1]"));
		price2= (ReturnTripPrice.getText()).replaceAll("\\s+","");
		System.out.println("The Return Trip price is: " + price2);
		
		return price2;
	}
	
	public double getExpectedTotalFare()
	{
		double PlainFare = Double.parseDouble(price1) + Double.parseDouble(price2);
		double ServiceFee = Math.ceil(PlainFare*0.1);
		double MerchantFee = Math.ceil(PlainFare*0.022);
	
		ExpectedTotalFare = 2 * (PlainFare + ServiceFee + MerchantFee);
	
		System.out.println("The ExpectedTotalFare is: " + ExpectedTotalFare);		
		return ExpectedTotalFare;
	}
}
