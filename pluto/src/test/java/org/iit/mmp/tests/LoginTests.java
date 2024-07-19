package org.iit.mmp.tests;

import java.io.IOException;

import org.iit.mmp.lib.BaseClass;
import org.iit.mmp.lib.MMPLibrary;
import org.iit.mmp.lib.Utility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseClass {
	
	@Test(dataProvider="DP")
	public void validateInvLogin(String uname,String pword,String expected)
	{
		
		MMPLibrary mmpLib = new MMPLibrary(driver);
		mmpLib.launchApplication(prop.getProperty("patienturl"));
		mmpLib.login(uname, pword);
		String actual = mmpLib.fetchInvMessage(expected);
		//String expected="home";
		Assert.assertEquals(actual, expected);
	}
	@DataProvider(name="DP")
	public String[][] feedDP() throws IOException
	{
		String data[][]= Utility.readXLSX("MMPData.xlsx","data");
		return data;
	}

}
