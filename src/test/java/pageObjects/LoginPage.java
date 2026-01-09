package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	protected WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * private By txtEmail = By.cssSelector("#input-email"); 
	 * private By txtPassword = By.cssSelector("#input-password"); 
	 * private By btnLogin = By.cssSelector(input[value='Login']");
	 * 
	 */

	@FindBy(css = "#input-email")
	private WebElement txtEmail;

	@FindBy(css = "#input-password")
	private WebElement txtPassword;

	@FindBy(css = "input[value='Login']")
	private WebElement btnLogin;

	

	
	
	
	public void enterEmail(String email) {

		txtEmail.sendKeys(email);

	}

	public void enterPassword(String pwd) {

		txtPassword.sendKeys(pwd);

	}

	public void clickloginbutton() {

		btnLogin.click();

	}
	

	

}
