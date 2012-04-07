package contactlist.tests;

import groovy.util.GroovyTestCase;
import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import contactlist.dto.GroupDTO;
import contactlist.pages.GroupEditPage;
import contactlist.pages.GroupListPage;

/**
 * Integration tests.
 * 
 */
class GroupTest extends GroovyTestCase {

	void testSuccessfulAdd() {

		// Given i want to add a new group.
		GroupDTO groupDTO = new GroupDTO(name:"Família")

		// When i open the edit page
		GroupEditPage groupEditPage = PageFactory.initElements(TestDriver.getDriver(), GroupEditPage.class)
		groupEditPage.open()

		// And set the group name
		groupEditPage.setBean(groupDTO)

		// And click in save
		GroupListPage groupListPage = groupEditPage.saveGroup()

		// Then the new group must exist in grid list.
		Assert.assertTrue(groupListPage.containsValueInGrid(groupDTO.getName()))
	}

	@Test
	void testChangeGroupName() {
		
		// Given i want to edit an existing group
		GroupDTO newGroup = new GroupDTO(name:"Piriguetes")
		
		// When i add a new group
		GroupEditPage groupEditPage = PageFactory.initElements(TestDriver.getDriver(), GroupEditPage.class)
		groupEditPage.open()

		// And save it.
		groupEditPage.setBean(newGroup)
		GroupListPage groupListPage = groupEditPage.saveGroup()

		// And edit this new group;
		groupEditPage = groupListPage.editGroup("Piriguetes")
		
		// And change the group name.
		groupEditPage.setBean(new GroupDTO(name:"Brothers"))
		
		// And click in save button.
		groupListPage = groupEditPage.saveGroup()
		
		// Then the older group must disappear from the grid.
		Assert.assertTrue(!groupListPage.containsValueInGrid("Piriguetes"))
		
		// And the new group (the older group but with a new name) must exist in the grid.
		Assert.assertTrue(groupListPage.containsValueInGrid("Brothers"))
	}

}