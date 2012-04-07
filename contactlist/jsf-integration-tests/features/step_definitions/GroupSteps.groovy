import contactlist.dto.GroupDTO 
import contactlist.pages.GroupEditPage 
import contactlist.pages.GroupListPage 
import contactlist.tests.TestDriver 

import org.openqa.selenium.support.PageFactory 

this.metaClass.mixin(cuke4duke.GroovyDsl)

Given(~"i want to delete the default group") {
	groupDTO = new GroupDTO(name:"General")
}

Given(~"i want to create a group and delete it") {
	groupDTO = new GroupDTO()
}

Given(~"i want to create a group and rename it") {
	groupDTO = new GroupDTO()
}

Given(~"i want to create a group") {
	groupDTO = new GroupDTO()
}

When(~"select to delete the group") {
	groupEditPage.deleteGroup()
}

When(~"select to confirm the deletion") {
	groupListPage = groupEditPage.confirmDeleteGroup()
}

When(~"select to edit the group \"([^\"]*)\"") { group->
	groupEditPage = groupListPage.editGroup(group)
}
	
When(~"set the group name as \"([^\"]*)\"") { group->
	groupDTO.name = group
}

When(~"rename it to \"([^\"]*)\"") { group->
	groupDTO.name = group
}

When(~"i open the group edit page") {
	groupEditPage = PageFactory.initElements(TestDriver.getDriver(), GroupEditPage.class)
	groupEditPage.open()
}

When(~"i open the group list page") {
	groupListPage = PageFactory.initElements(TestDriver.getDriver(), GroupListPage.class)
	groupListPage.open()
}

When(~"set the fields with the values of the group") {
	groupEditPage.setBean(groupDTO)
}

When(~"click the save button") { 
	groupListPage = groupEditPage.saveGroup()
}

Then(~"the group named \"([^\"]*)\" must be shown in the grid") { group -> 
	groupListPage.containsValueInGrid(group).shouldBe true
}

Then(~"the group named \"([^\"]*)\" must not be shown in the grid") { group ->
	groupListPage.containsValueInGrid(group).shouldBe false
}

Then(~"the confirmation box must be shown") { message->
	groupEditPage.isConfirmDeleteShown().shouldBe true
}

Then(~"the error box must be shown") {
	groupEditPage.isMessageDefaultGroupCantBeDeletedShown().shouldBe true
}