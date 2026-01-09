package testCases;

import testBase.BaseClass;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;


public class TC002_LoginTest  extends BaseClass{
	
	HomePage homepage;
	LoginPage  loginpage;
	MyAccountPage myaccountpage;
	
	@Test(groups= {"Sanity" , "Master"})
	public void  verify_logintest() throws InterruptedException {
		logger.info("************************************** Starting  TC002_LoginTest ************************ ");
		//Home Page
	homepage= new HomePage(driver);
	homepage.clickMyAccount();
	homepage.clickLogin();
	//login Page
	loginpage= new LoginPage(driver);
	loginpage.enterEmail(prop.getProperty("email"));
	loginpage.enterPassword(prop.getProperty("password"));
	loginpage.clickloginbutton();
	Thread.sleep(5000);
	//My account page
	myaccountpage= new MyAccountPage(driver);
	//Assert1
	boolean loginsuccessmsg= myaccountpage.isMyAccountPageExists();
	Assert.assertTrue(loginsuccessmsg);
	//Assert2
	boolean logoutlinkdisplayed= myaccountpage.checklogoutLink();
	
	if (logoutlinkdisplayed==true)
	 {
		Assert.assertTrue(true);
		System.out.println("logout link is displayed");
		
	} else
	{
		Assert.assertTrue(false);
		System.out.println("logout link is NOT displayed");
	}
	myaccountpage.clicklogoutLink();
	logger.info("************************************** Completed TC002_LoginTest ************************ ");
	}
}
