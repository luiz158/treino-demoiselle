package contactlist.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.DemoiselleRunner;
import contactlist.domain.Contact;
import contactlist.exception.DatabaseSizeExceededException;
import contactlist.exception.DuplicatedCpfException;

@Transactional
@RunWith(DemoiselleRunner.class)
public class ContactBCTest {

	@Inject
	private ContactBC bc;

	@Before
	public void before() {
		for (Contact contact : bc.findAll()) {
			bc.delete(contact.getId());
		}
	}

	@Test
	public void testInsertMinimalValidContact() {
		Contact contact = getMinimalValidContact();
		bc.insert(contact);
		assertNotNull(contact.getId());
	}

	@Test
	public void testDetectDuplicatedCpf() {
		String cpf = "00000000000";
		Contact contact;

		contact = getMinimalValidContact();
		contact.setCpf(cpf);
		bc.insert(contact);

		try {
			contact = getMinimalValidContact();
			contact.setName(contact.getName() + "_");
			contact.setCpf(cpf);
			bc.insert(contact);
			fail();
		} catch (DuplicatedCpfException cause) {
		}
	}

	@Test
	public void testInsertContactWithEmptyCpf() {
		String cpf = "";
		Contact contact;

		contact = getMinimalValidContact();
		contact.setCpf(cpf);
		bc.insert(contact);
		assertNotNull(contact.getId());
	}

	@Test
	public void testUpdateContact() {
		Contact contact, contact2;
		contact = getMinimalValidContact();
		bc.insert(contact);
		contact.setName("contact 2");
		bc.update(contact);
		contact2 = bc.load(contact.getId());
		assertEquals("contact 2", contact2.getName());
	}

	@Test
	public void testIfContactBCIsABusinessController() {
		assertNotNull(ContactBC.class.getAnnotation(BusinessController.class));
	}

	@Test
	public void testInsertContactsMoreThanMAXIMUM() {
		Contact contact;
		for (int x = 1; x <= 50; x++) {
			contact = new Contact();
			contact.setName("contact" + x);
			bc.insert(contact);
		}
		contact = new Contact();
		contact.setName("contact51");
		try {
			bc.insert(contact);
			fail();
		} catch (DatabaseSizeExceededException cause) {

		}
	}

	private Contact getMinimalValidContact() {
		Contact contact = new Contact();
		contact.setName("Contact 1");
		return contact;
	}
}
