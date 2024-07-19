package org.iit.mmp.lib;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

	
	public WebElement waitforElement(WebDriver driver,long secs,By by)
	{
		 
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(secs));
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return e;
	}
	public boolean waitforText(WebDriver driver,WebElement we,String expectedText,long secs)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		boolean result = wait.until(ExpectedConditions.textToBePresentInElement( we,expectedText));
		return result;
	}
	
}
