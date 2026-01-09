package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	HomePage homepage;
	LoginPage loginpage;
	MyAccountPage myaccountpage;

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="datadriven") // getting data provider from different
																				// class
	public void verify_loginDDT(String email, String pwd, String exp) {
  try {
		logger.info("************************************** Starting  TC003_LoginDDT ************************ ");
		// Home Page
		homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickLogin();
		// login Page
		loginpage = new LoginPage(driver);
		loginpage.enterEmail(email);
		loginpage.enterPassword(pwd);
		loginpage.clickloginbutton();
		// Thread.sleep(5000);
		// My account page
		myaccountpage = new MyAccountPage(driver);
		boolean tragetPage = myaccountpage.isMyAccountPageExists();
		/*
		 Data is valid - login success - tes
		 t pass - Logout
		                          login failed  -test fail
		                          
		Data is invalid - login sucesss-test fail - logout
		                           login failed -test pass                          
		                          
		 */
		
		if (exp.equalsIgnoreCase("valid")) 
		{
				 if (tragetPage == true)
				 {
					 myaccountpage.clicklogoutLink();
					 Assert.assertTrue(true);
				 }else
				 {
					 Assert.assertTrue(false);
					 
				 }
		}
		if (exp.equalsIgnoreCase("invalid")) 
		{
			if (tragetPage == true)
			 {
				 myaccountpage.clicklogoutLink();
				 Assert.assertTrue(false);
			 }else
			 {
				 Assert.assertTrue(true);
				 
			 }
		 }
  } catch(Exception e)
  {   Assert.fail();  
  }
  logger.info("************************************** Completed  TC003_LoginDDT ************************ ");
	}
	
}
