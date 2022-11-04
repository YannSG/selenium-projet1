package fr.ecoleql.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

	public static WebDriver getDriver(Browser browser) {
		WebDriver driver = null;
		if (browser == Browser.CHROME) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser == Browser.EDGE) {
			System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		} else if (browser == Browser.FIREFOX) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser == Browser.SAFARI) {
			driver = new SafariDriver();
		}
		return driver;
	}

}