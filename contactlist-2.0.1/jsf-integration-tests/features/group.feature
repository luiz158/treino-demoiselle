Feature: Groups
	In order to create, delete and update groups

Scenario: Creating a new group
	Given i want to create a group
	And set the group name as "Family"
	When i open the group edit page
	And set the fields with the values of the group
	And click the save button
	Then the group named "Family" must be shown in the grid

Scenario: Edit an existing group
	Given i want to create a group and rename it
	When i open the group edit page
	And set the group name as "Girls"
	And set the fields with the values of the group
	And click the save button
	And select to edit the group "Girls"
	And rename it to "Friends"
	And set the fields with the values of the group
	And click the save button
	Then the group named "Friends" must be shown in the grid
	Then the group named "Girls" must not be shown in the grid

Scenario: Delete a group and show confirmation dialog
	Given i want to create a group and delete it
	When i open the group edit page
	And set the group name as "DontDelete"
	And set the fields with the values of the group
	And click the save button
	And select to edit the group "DontDelete"
	And select to delete the group
	Then the confirmation box must be shown

Scenario: Delete a group
	Given i want to create a group and delete it
	When i open the group edit page
	And set the group name as "DeleteMe"
	And set the fields with the values of the group
	And click the save button
	And select to edit the group "DeleteMe"
	And select to delete the group
	And select to confirm the deletion
	Then the group named "DeleteMe" must not be shown in the grid
	
Scenario: Can't delete the default group
	Given i want to delete the default group
	When i open the group list page
	And select to edit the group "General"
	And select to delete the group
	And select to confirm the deletion
	Then the error box must be shown