package org.iit.mmp.tests;

import java.time.Duration;

import org.iit.mmp.lib.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests {

	@Test
	public void validatebookAppointment()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		//login
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		//click on tab
		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[text()='Dr.Charlie']/ancestor::ul/following-sibling::button")).click();

		driver.switchTo().frame("myframe");
		String dateArr[] = Utility.generateFutureDate(220,"dd/MMMM/YYYY").split("/");
		System.out.println(dateArr[0]);//11
		System.out.println(dateArr[1]);//August
		System.out.println(dateArr[2]);//2025
		driver.findElement(By.id("datepicker")).click();
		String expectedDay = dateArr[0];
		String expectedMonth = dateArr[1];
		String expectedYear = dateArr[2];
		String actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();//2024
		
		while(!(actualYear.equals(expectedYear)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		}
		
		String actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();//June
		while(!(actualMonth.equals(expectedMonth)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		}
		driver.findElement(By.linkText(expectedDay)).click();
		new Select(driver.findElement(By.id("time"))).selectByValue("10Am");			
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("status")),"OK"));
		driver.findElement(By.id("ChangeHeatName")).click();
		driver.switchTo().defaultContent();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sym")));
		driver.findElement(By.id("sym")).sendKeys("To meet doctor for Checkup");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		String date = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		String time= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
		String appointment= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		String doctor = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText();
		System.out.println(date+"---"+time+"---"+ appointment+"--"+ doctor);


	}
	@Test
	public void validateSendMessages()
	{

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		//login
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		//click on tab
		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();

		
	}
}






