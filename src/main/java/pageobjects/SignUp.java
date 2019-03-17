package pageobjects;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.*;

public class SignUp {
	Browser browser;
	public WebDriver driver;
	
	@FindBy(xpath = "//*[@name='firstname']")
	private WebElement txtbxFirstName;
	
	@FindBy(xpath = "//*[@name='lastname']")
	private WebElement txtbxLastName;
	
	@FindBy(xpath = "//*[@name='reg_email__']")
	private WebElement txtbxMobileEmail;
	
	@FindBy(xpath = "//*[@name='reg_email_confirmation__']")
	private WebElement txtbxConfirmEmail;
	
	@FindBy(xpath = "//*[@name='reg_passwd__' and @type='password']")
	private WebElement txtbxPassword;
	
	@FindBy(xpath = "//*[@id='month']")
	private WebElement dropdownMonth;
	
	@FindBy(xpath = "//*[@id='day']")
	private WebElement dropdownDay;
	
	@FindBy(xpath = "//*[@id='year']")
	private WebElement dropdownYear;
	
	@FindBy(xpath = "//*[@name='sex' and @value='1']")
	private WebElement radobtnFemale;
	
	@FindBy(xpath = "//*[@name='sex' and @value='2']")
	private WebElement radobtnMale;
	
	@FindBy(xpath = "//*[@type='submit' and @name='websubmit']")
	private WebElement btnSubmit;
	
	@FindBy(xpath = "//div[@class='_4t2a'")
	private WebElement dlgConfirmBirth;
	
	@FindBy(xpath = "//div[@class='_4-i2 _pig _50f4'")
	private WebElement lblConfirmBirth;
	
	@FindBy(xpath = "//a[@role='button' and @class='_42ft _4jy0 layerCancel _2rsa uiOverlayButton _4jy3 _517h _51sy']")
	private WebElement btnNoCnfBirth;
	
	@FindBy(xpath = "//*[@type='submit' and @class='_42ft _4jy0 layerConfirm _2rsa uiOverlayButton _4jy3 _4jy1 selected _51sy']")
	private WebElement btnYesCnfBirth;
	
	private HashMap<String,WebElement> sexMap = new HashMap<String, WebElement>();
	private Select selectMonth;
	private Select selectDay;
	private Select selectYear;

	public SignUp(WebDriver driver) {
		this.driver = driver;

	}	
	
	public void initializeVariables()
	{
		this.sexMap.put("male",this.radobtnMale);
		this.sexMap.put("female",this.radobtnFemale);
		this.selectMonth = new Select(this.dropdownMonth);
		this.selectDay = new Select(this.dropdownDay);
		this.selectYear = new Select(this.dropdownYear);
	}
	
	public void setFirstName(String strFname)
	{
		this.txtbxFirstName.sendKeys(strFname);
	}
	
	public String getFirstName()
	{
		return this.txtbxFirstName.getText();
	}
	
	public void setLastName(String strLname)
	{
		this.txtbxLastName.sendKeys(strLname);
	}
	
	public String getLastName()
	{
		return this.txtbxLastName.getText();
	}
	
	public void setMobileEmail(String strMobileEmail)
	{
		this.txtbxMobileEmail.sendKeys(strMobileEmail);
	}
	
	public String getMobileEmail()
	{
		return this.txtbxMobileEmail.getText();
	}
	
	public void setConfirmEmail(String strEmail)
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(txtbxConfirmEmail));
		this.txtbxConfirmEmail.sendKeys(strEmail);
	}
	
	public boolean getVisibilityStatusConfirmEmail()
	{
		return txtbxConfirmEmail.isDisplayed();
	}
	
	public String getConfirmEmail()
	{
		return this.txtbxConfirmEmail.getText();
	}
	
	public void setPassword(String strPassword)
	{
		this.txtbxPassword.sendKeys(strPassword);
	}
	
	public String getPassword()
	{
		return this.txtbxPassword.getText();
	}
	
	public boolean setMonthByVisibleText(String strMonth)
	{
		try
		{
			this.selectMonth.selectByVisibleText(strMonth);
			return true;
		}
		catch(Exception e){
			System.out.println("Unable to set Month  : "+e.getMessage());
			org.testng.Assert.fail("Unable to set Month : "+strMonth);
			return false;
		}
	}
	
	public String getMonth()
	{
		return this.selectMonth.getFirstSelectedOption().getText();
	}
	
	public boolean setDayByVisibleText(String strDay)
	{
		try
		{
			this.selectDay.selectByVisibleText(strDay);
			return true;
		}
		catch(Exception e){
			System.out.println("Unable to set Day  : "+e.getMessage());
			org.testng.Assert.fail("Unable to set Day : "+strDay);
			return false;
		}
	}
	
	public String getDay()
	{
		return this.selectDay.getFirstSelectedOption().getText();
	}
	
	public boolean setYearByVisibleText(String strYear)
	{
		try
		{
			this.selectYear.selectByVisibleText(strYear);
			return true;
		}
		catch(Exception e){
			System.out.println("Unable to set Year  : "+e.getMessage());
			org.testng.Assert.fail("Unable to set Year : "+strYear);
			return false;
		}
	}
	
	public String getYear()
	{
		return this.selectYear.getFirstSelectedOption().getText();
	}
	
	public boolean checkSex(String strSex)
	{
		try
		{
			this.sexMap.get(strSex).click();
			 return true;
		}
		catch(Exception e){
			org.testng.Assert.fail("Unable to set Sex : Male");
			return false;
		}
	}
	
	public boolean getSexStatus(String strSex)
	{
		return this.sexMap.get(strSex).isSelected();
	}
		
	public void clickSubmit()
	{
		this.btnSubmit.click();
	}
	
	public void handleAlert()
	{
        // Switching to Alert        
        Alert alert = this.driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= this.driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        		
        // Accepting alert		
        alert.accept();	
	}
}
