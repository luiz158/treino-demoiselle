package contactlist.tests;

import org.openqa.selenium.Speed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver 


final class TestDriver {
	private static WebDriver driver = new FirefoxDriver()
	
	private TestDriver() {}
	  
	static WebDriver getDriver() {
		driver
	}

}