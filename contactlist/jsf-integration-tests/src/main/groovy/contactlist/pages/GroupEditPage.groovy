package contactlist.pages;


import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import contactlist.WebPage 
import contactlist.dto.*;
import contactlist.widgets.CommandButton 

@WebPage(url="http://localhost:8080/contactlist-jsf/group_edit.jsf") 
class GroupEditPage extends EditPage<GroupDTO> {
	
	CommandButton cmdSave = new CommandButton("Salvar")
	CommandButton cmdDelete = new CommandButton("Excluir")
	
	@FindBy(xpath="//input[@value='Sim, manda ver!']")
	WebElement btnConfirmDelete

	@FindBy(xpath="//input[@value='Nããão, desculpe!']")
	WebElement btnDontConfirm

	@FindBy(xpath="//div[contains(@aria-labelledby,'ui-dialog-title')]")
	WebElement divConfirm
	
	GroupListPage saveGroup() {
		cmdSave.click()
		new GroupListPage()
	}
 
	void deleteGroup() {
		cmdDelete.click()
		new GroupListPage()
	}

	GroupListPage confirmDeleteGroup() {
		cmdDelete.click()
		btnConfirmDelete.click()
		new GroupListPage()
	}

	boolean isConfirmDeleteShown() {
		divConfirm.getAttribute("style").contains("display: block")
	}
  
	boolean isMessageDefaultGroupCantBeDeletedShown() {
		boolean result = false
		try {
			this.getDriver().findElement(By.xpath("//div[@class='ui-growl ui-widget']"));
			result = true
		} catch (NoSuchElementException exception) {
		
		}
		return result
	}
	
}
