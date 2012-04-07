package contactlist.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import contactlist.tests.TestDriver;


class DataTable {
 
	boolean containsRowWithText(text) {
		def result = false
		try {
			TestDriver.getDriver().findElement(By.xpath("//a[@href='#' and contains(text(),'" + text + "')]"))
			result = true
		} catch (NoSuchElementException e) {
			result = false
		}
		result
	}
  
}