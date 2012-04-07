package contactlist.dto;

import contactlist.Bind;

class GroupDTO {

	Long id

	@Bind(xpath="//input[@id='name']")
	String name

}