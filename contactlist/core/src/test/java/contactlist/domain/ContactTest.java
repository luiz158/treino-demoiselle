package contactlist.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;


public class ContactTest {

	@Test
	public void testAddPhone() {
		Phone phone = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		Contact contact = new Contact();
		contact.addPhone(phone);
		assertEquals(Long.valueOf(1),((Phone)contact.getPhones().get(0)).getId());
	}
	
	@Test
	public void testSetPhones() {
		List<Phone> phones = new ArrayList<Phone>();
		Phone phone = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		phones.add(phone);
		Contact contact = new Contact();
		contact.setPhones(phones);
		assertEquals(1,contact.getPhones().size());
	}
	
	@Test
	public void testGetsAndSets() {
		Date hoje = new Date();
		Contact contact = new Contact();
		contact.setEmail("teste@teste.com.br");
		contact.setBirthday(hoje);
		contact.setId(Long.valueOf(1));
		assertEquals(Long.valueOf(1),contact.getId());
		assertEquals("teste@teste.com.br",contact.getEmail());
		assertEquals(hoje,contact.getBirthday());	
	}
	
}
