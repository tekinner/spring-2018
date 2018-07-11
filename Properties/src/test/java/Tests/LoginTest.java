package Tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pageFiles.ConfigReader;
import pageFiles.LoginPage;
import pageFiles.TestBase;

public class LoginTest extends TestBase{

	LoginPage lg;
	
	@Test
	public void loginPositive() {
		
		lg = new LoginPage(driver);
		
		driver.get(ConfigReader.getProperty("URL"));
		lg.goSignIn.click();
		lg.loginMethod(ConfigReader.getProperty("username1"),ConfigReader.getProperty("password1") );
		assertEquals(lg.checkLogin.getText(), "Sign out");
	}
	
	
	
	
	
	
	
}
