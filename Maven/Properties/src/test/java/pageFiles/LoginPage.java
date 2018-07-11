package pageFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='login']")
	public WebElement goSignIn;
	
	@FindBy(id="email")
	public WebElement loginName;
	
	@FindBy(id="passwd")
	public WebElement passwd;
	
	@FindBy(xpath="(//button[@type='submit'])[3]")
	public WebElement clickSignIn;
	
	@FindBy(xpath="//a[@title='Sign out']")
	public WebElement checkLogin;
	
	public void loginMethod(String user, String pass) {
		loginName.sendKeys(user);
		passwd.sendKeys(pass);
		clickSignIn.click();
	}
	
}


