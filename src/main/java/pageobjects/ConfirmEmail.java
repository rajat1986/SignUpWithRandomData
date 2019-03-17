package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmEmail {
	WebDriver driver;
	@FindBy(xpath = "//div[contains(text(),\"Confirm Your Email\")]")
	private WebElement confirmLink;
	
	public ConfirmEmail(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean getConfirmation()
	{
		try
		{
			return this.confirmLink.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
