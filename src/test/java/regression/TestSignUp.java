package regression;

import dataprovider.SignUpDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import pageobjects.ConfirmEmail;
import pageobjects.SignUp;
import resources.Browser;

public class TestSignUp {
	public SignUp objSignUp;
	public ConfirmEmail objConfirmEmail;
	public WebDriver driver;
	public Browser browser;
	public String strBrowserType="Chrome";

	@BeforeTest
	public void setup()throws InterruptedException  {
		this.browser = Browser.getInstance(strBrowserType);
		this.driver = this.browser.getWebDriver();

		this.driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void everyTime() {
		//this.browser.clear_cache();
		try {
			this.driver.get("https://www.facebook.com");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.objSignUp = new SignUp(this.driver);
		PageFactory.initElements(this.driver, this.objSignUp);
		this.objSignUp.initializeVariables();
	}
	
	@Test(dataProvider = "SignUpTestData", dataProviderClass=SignUpDataProvider.class)
	public void TestSignUpSuccess(String fName, String lName, String email, String pwd, String mm, String dd, String yyyy, String sex) throws InterruptedException{
		this.objSignUp.setFirstName(fName);
		this.objSignUp.setLastName(lName);
		this.objSignUp.setMobileEmail(email);
		this.objSignUp.setConfirmEmail(email);
		this.objSignUp.setPassword(pwd);
		this.objSignUp.setMonthByVisibleText(mm);
		this.objSignUp.setDayByVisibleText(dd);
		this.objSignUp.setYearByVisibleText(yyyy);
		this.objSignUp.checkSex(sex);
		this.objSignUp.clickSubmit();
		this.objConfirmEmail = new ConfirmEmail(this.driver);
		PageFactory.initElements(this.driver, this.objConfirmEmail);
		//Thread.sleep(10000);
		if(!this.browser.verifyNetworkLogs())
			org.testng.Assert.fail("Test Failed : No email confirmation prompted");
		
	}
	
	@AfterTest
	public void teardown()throws InterruptedException  {
		this.browser.closeDriver();
	}
	

}
