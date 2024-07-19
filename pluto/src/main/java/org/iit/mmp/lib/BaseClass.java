package org.iit.mmp.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected WebDriver driver;//default
	protected Properties prop;
	
	@BeforeTest
	public void readPropertyFile() throws IOException
	{
		loadProperties("config/mmpglobal.properties");
		 
		
	}
	@BeforeClass
	public void instantiateDriver() throws IOException
	{
		String env= prop.getProperty("environment");
		String browserType= prop.getProperty("browsertype");
		
		if(env.equals("qa")&& browserType.equals("chrome"))
		{
			loadProperties("config/mmp-qa.properties");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();//declaration
		}
		else if(env.equals("dev")&& browserType.equals("chrome"))
		{
			loadProperties("config/mmp-dev.properties");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();//declaration
		}
	}
	public void loadProperties(String filePath) throws IOException
	{
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		prop = new Properties();
		prop.load(fis);
		
	}

}
