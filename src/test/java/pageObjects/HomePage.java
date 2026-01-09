package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	//constructor 
	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}
	//Locators 
	@FindBy(xpath="//a[@title='My Account']") 
	 private WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	private WebElement InkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	private WebElement InkLogin;
	
	
	// Action Methods
	
	public void  clickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	
	public void  clickRegister()
	{
		InkRegister.click();
	}


	public void  clickLogin()
	{
		InkLogin.click();
	}
	
}
