package contactlist.widgets

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import contactlist.tests.TestDriver;


class CommandButton {
	String text
	private WebElement webElement
	
	CommandButton(text) {
		this.text = text;
	}

	void click() {
		if ( webElement == null ) {
			def xpath = "//span[contains(text(),'" + text + "')]"
			webElement = TestDriver.getDriver().findElement(By.xpath(xpath))
		}
		webElement.click()
	}

}