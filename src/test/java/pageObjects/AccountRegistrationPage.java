package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	//Constructor 
	
	public AccountRegistrationPage(WebDriver driver) 
	{
				 super(driver);
	}
	
	//Locators  for Register page 
	/*
	   Interview Answer (Memorize This)

		Yes, we can use WebElements without private, but it breaks encapsulation.
		Recommended approach is using private locators (By or @FindBy) and exposing only business methods from Page Object classes.
		*/
	  
	@FindBy(css="#input-firstname")
	private WebElement  txtFirstName;
	
	@FindBy(css="#input-lastname")
	private WebElement  txtLastName;
	
	@FindBy(css="#input-email")
	private WebElement  txtEmail;
	
	@FindBy(id="input-telephone")
	private WebElement  txtTelePhone;
	

	@FindBy(id="input-password")
	private WebElement  txtPassword;
	
	@FindBy(css="#input-confirm")
	private WebElement  txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement  chkBoxAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement  btnContinue;
	
		
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement  msgConfirmation;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement  btnSuccessContinue;
	

			
	// Action Methods for Register page fields 

	
	
	public  void enterFirstName(String  fname) 
	{
				txtFirstName.sendKeys(fname);
	}
	
	public  void enterLastName(String  lname) 
	{
		txtLastName.sendKeys(lname);
	}
	
	public  void enterEmail(String  email) 
	{
		txtEmail.sendKeys(email);
	}
	
	public  void enterTelePhoneNumber(String  phone) 
	{
		txtTelePhone.sendKeys(phone);
	}
	
		
	public  void enterPassword(String  pwd) 
	{
		txtPassword.sendKeys(pwd);
	}
	
	public  void enterConfirmPassword(String  pwd) 
	{
		txtConfirmPassword.sendKeys(pwd);
	}	
	
	public  void clickPrivacyAgreement() 
	{
		chkBoxAgree.click();
	}	
	
	
	public  void clickContinueButton() 
	{
		//Sol1
		btnContinue.click();
		/*
		//Sol2
		btnContinue.submit();
		
		//Sol3
		Actions act = new Actions(driver);
		 act.moveToElement(btnContinue).click().perform();
		  //Sol4
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].click();", btnContinue);
		 
		 //Sol5
		  btnContinue.sendKeys(Keys.RETURN);
		//Sol 6
		 WebDriverWait mywait= new WebDriverWait(driver, Duration.ofSeconds(10));
		 mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		 	
		*/
		
	}	
	
	public String  getConfirmationMessage()
  {
		
	try {
		return (msgConfirmation.getText());
		} catch (Exception e) {
		return (e.getMessage());
	}
	}
	
	
	public  void clickSuccessContinueButton() 
	{
		
		btnSuccessContinue.click();
	}
	
	
}
	
	
	
	
	





