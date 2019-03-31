package resources;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {
	private static Browser single_browser = null;
	public static WebDriver driver;

	
    // static method to create instance of Singleton class 
    public static Browser getInstance(String strBrowserType) 
    { 
        if (single_browser == null) 
        {
           	single_browser = new Browser(); 
           	driver = getWebDriver(strBrowserType);
        }
  
        return single_browser; 
    } 
    
    public WebDriver getWebDriver()
    {
    	return driver;
    }

	@SuppressWarnings("deprecation")
	private static WebDriver getWebDriver(String strBrowserType) {
		switch (strBrowserType.toUpperCase()) {
		/*case "FIREFOX":
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/x-msexcel,application/excel,application/"
					+ "x-excel,application/vnd.ms-excel,text/html,"
					+ "text/plain,application/msword,application/xml,application/x-java-jnlp-file");
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("browser.download.folderList", 2);
			String currentWorkingDir = System.getProperty("user.dir");
			String exportPath = currentWorkingDir + "\\drivers";
			profile.setPreference("browser.download.dir",exportPath);
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("security.enable_java", true);
			profile.setPreference("plugin.state.java", 2);
			
			return new FirefoxDriver();

		case "IE":	
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//drivers//IEDriverServer.exe");
	    	return new InternetExplorerDriver();*/

		case "CHROME":
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
			caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			
			return new ChromeDriver(caps);

		default:
			throw new RuntimeException("Browser is not supported for the test suite.");

		}

	}
	
	public WebElement get_clear_browsing_button(){
	    return driver.findElement(By.id("clearBrowsingDataConfirm"));
	}
	
	public void printNetworkLogs()
	{
		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
		System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
		for (LogEntry entry : entries) {
				if((entry.getMessage().contains("://www.facebook.com/recover/code"))||(entry.getMessage().contains("://www.facebook.com/confirmemail.php")));
		        {
		        	 System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		        }
		}
	}
	
	public boolean verifyNetworkLogs()
	{
		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
		System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
		for (LogEntry entry : entries) {
				if((entry.getMessage().contains("://www.facebook.com/recover/code"))||(entry.getMessage().contains("://www.facebook.com/confirmemail.php")));
		        {
		        	 return true;
		        }
		}
		return false;
	}


	public void clear_cache(){
	    //Clear the cookies and cache for the ChromeDriver instance.
	    driver.get("chrome://settings/clearBrowserData");

	    // wait for the button to appear
	    WebDriverWait wait=new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.elementToBeClickable(get_clear_browsing_button()));

	    // click the button to clear the cache
	    WebElement clearBrowsingButton = get_clear_browsing_button();
	    clearBrowsingButton.click();

	    // wait for the button to be gone before returning
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")));
	}
	
	
	
	public void closeDriver() {
		driver.quit();

	}

}
