package contactlist.pages;

import contactlist.WebPage 
import contactlist.tests.TestDriver;
import org.openqa.selenium.WebDriver 

 
abstract class Page {

	void open() {
		TestDriver.getDriver().get(getUrl())
	}

	String getUrl() {
		if ( this.getClass().isAnnotationPresent(WebPage.class)) {
			WebPage webPage = this.getClass().getAnnotation(WebPage.class)
			return webPage.url()
		}
	}

	WebDriver getDriver() {
		TestDriver.getDriver()
	}
	
}