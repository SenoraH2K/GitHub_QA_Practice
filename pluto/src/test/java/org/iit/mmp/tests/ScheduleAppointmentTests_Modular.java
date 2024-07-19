package org.iit.mmp.tests;

import java.util.HashMap;

import org.iit.mmp.lib.BaseClass;
import org.iit.mmp.lib.Helper;
import org.iit.mmp.lib.MMPLibrary;
import org.iit.mmp.lib.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests_Modular extends BaseClass {
	 
	 
	public HashMap<String, String> bookAppointment(String doctorName,int noofDays)
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();
		expectedHMap.put("doctor", doctorName);
		
		driver.switchTo().frame("myframe");
		String date = Utility.generateFutureDate(noofDays,"dd/MMMM/YYYY");
		String dateArr[] = date.split("/");
		expectedHMap.put("date", date);
		 
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
		String time="10Am";
		new Select(driver.findElement(By.id("time"))).selectByValue(time);
		expectedHMap.put("time", time);
		Helper helperObj = new Helper();
		helperObj.waitforElement(driver,40,By.id("status"));
		helperObj.waitforText(driver,driver.findElement(By.id("status")),"OK",30);
		
		driver.findElement(By.id("ChangeHeatName")).click();
		driver.switchTo().defaultContent();
 
		helperObj.waitforElement(driver,40,By.id("sym"));	
		
		String appointment="To meet doctor for Checkup";
		driver.findElement(By.id("sym")).sendKeys(appointment);
		expectedHMap.put("appointment", appointment);
	 
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		return expectedHMap;
	}
	
	 
	
	public HashMap<String, String> fetchPatientTableData()
	{
		String date = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		String time= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
		String appointment= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		String doctor = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText();
		System.out.println(date+"---"+time+"---"+ appointment+"--"+ doctor);
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("date", date);
		actualHMap.put("time", time);
		actualHMap.put("appointment", appointment);
		actualHMap.put("doctor", doctor);
		return actualHMap;
		
	}
	@Parameters({"doctor","duration"})
	@Test
	public void validatebookAppointment(String doctor,String duration)
	{
		MMPLibrary mmpLib = new MMPLibrary(driver);

		mmpLib.launchApplication(prop.getProperty("patienturl"));
		 
		mmpLib.login(prop.getProperty("patientusername"),prop.getProperty("patientpassword"));

		mmpLib.navigateToAModule("Schedule Appointment");

		HashMap<String,String> expectedHMap = bookAppointment(doctor,Integer.parseInt(duration));
		
		HashMap<String,String> actualHMap = fetchPatientTableData();

		System.out.println(expectedHMap);
		
		System.out.println(actualHMap);
		
		Assert.assertEquals(actualHMap,expectedHMap);

	}
	 
}






