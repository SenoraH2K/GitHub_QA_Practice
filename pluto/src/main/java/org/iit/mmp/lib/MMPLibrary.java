package org.iit.mmp.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MMPLibrary {
	
	WebDriver driver;
	public MMPLibrary(WebDriver driver)
	{
		this.driver = driver;
	}
	 
	public void launchApplication(String url)
	{
		driver.get(url);

	}
	public void navigateToAModule(String moduleName)
	{
		//click on tab
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();

	}
	public void login(String uname,String pword)
	{
		//login
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		driver.findElement(By.name("submit")).click();
	}
	public String fetchTitle()
	{
		return driver.getTitle();
	}
	public String fetchInvMessage(String expected)
	{
		String text = driver.findElement(By.xpath("//div[text()='"+expected+"']")).getText();
		return text;
	}

}
