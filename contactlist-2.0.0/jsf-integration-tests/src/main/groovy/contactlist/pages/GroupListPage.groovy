package contactlist.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import contactlist.WebPage 
import contactlist.tests.TestDriver;
import contactlist.widgets.DataTable;

@WebPage(url="http://localhost:8080/contactlist-jsf/group_list.jsf")
class GroupListPage extends Page {
	DataTable dataTable = new DataTable()
	GroupEditPage groupEditPage = PageFactory.initElements(TestDriver.getDriver(), GroupEditPage.class)

	Boolean containsValueInGrid(value) {
		dataTable.containsRowWithText(value)
	}
 
	GroupEditPage editGroup(value) {
		TestDriver.getDriver().findElement(By.xpath("//a[@href='#' and contains(text(),'" + value + "')]")).click()
		groupEditPage
	}

	void deleteGroup(string) {

	}

}
