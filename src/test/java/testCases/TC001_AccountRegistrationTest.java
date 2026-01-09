package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
//import com.github.javafaker.Faker;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.TestDataUtil;

public class TC001_AccountRegistrationTest extends BaseClass {
	HomePage homepage;
	AccountRegistrationPage accountregistration;

	@Test(groups={"Regression","Master"})
	void verify_account_registration() throws InterruptedException {
		logger.info("****************** Strating  TC001_AccountRegistrationTest *********************************");
		try {
			homepage = new HomePage(driver);
			logger.info("****************** Clicked on  My Account link *********************************");
			homepage.clickMyAccount();
			logger.info("****************** Clicked on Registor  link *********************************");
			homepage.clickRegister();
			accountregistration = new AccountRegistrationPage(driver);
			/*
			 * Dynamic data generated thru Faker Faker faker = new Faker();
			 * accountregistration.enterFirstName(faker.name().firstName());
			 * accountregistration.enterLastName(faker.name().lastName());
			 * accountregistration.enterEmail(faker.internet().emailAddress());
			 * accountregistration.enterTelePhoneNumber(faker.phoneNumber().subscriberNumber
			 * (10)); String pwd = faker.internet().password();
			 * accountregistration.enterPassword(pwd);
			 * accountregistration.enterConfirmPassword(pwd);
			 */
			// Java built-in generators for dynamic data
			logger.info("****************** Filling data  on Registor  form *********************************");
			accountregistration.enterFirstName(TestDataUtil.firstName());
			accountregistration.enterLastName(TestDataUtil.lastName());
			accountregistration.enterEmail(TestDataUtil.email());
			accountregistration.enterTelePhoneNumber(TestDataUtil.phone());
			String pwd = TestDataUtil.password();
			accountregistration.enterPassword(pwd);
			accountregistration.enterConfirmPassword(pwd);

			accountregistration.clickPrivacyAgreement();
			accountregistration.clickContinueButton();
			logger.info("****************** Registration completed  successfully *********************************");
			String configmsg = accountregistration.getConfirmationMessage();
			logger.info("****************** Validating expected message *********************************");
			Thread.sleep(2000);

			if (configmsg.equals("Your Account Has Been Created!")) {
               
				Assert.assertTrue(true);
				System.out.println("*******y*************Registration completed successfully***************************");

			} else {
				logger.error("test failed ...");
				logger.debug("Debug logd failed");
				System.out.println("Registration NOT completed");
				Assert.assertTrue(false);

			}
			//Assert.assertEquals(configmsg, "Your 2Account Has Been Created!");


		} catch (Exception e) {
		
			Assert.fail();
		}
		accountregistration.clickSuccessContinueButton();
		Thread.sleep(2000);
		logger.info("****************** Finished  TC001_AccountRegistrationTest *********************************");
	}
	
}
