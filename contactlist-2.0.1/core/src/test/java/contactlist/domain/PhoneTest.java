package contactlist.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PhoneTest {

	@Test
	public void testConstructorWithPhoneParam() {
		Phone phone = new Phone();
		phone.setNumber("321022172");
		phone.setType(PhoneType.WORK);
		
		Phone phone2 = new Phone(phone);
		assertEquals("321022172",phone2.getNumber());
		assertEquals(PhoneType.WORK,phone2.getType());
	}
	
	@Test
	public void testConstructorWithPhoneNumberAndPhoneTypeParam() {
		Phone phone = new Phone("321022172",PhoneType.WORK);
		assertEquals("321022172",phone.getNumber());
		assertEquals(PhoneType.WORK,phone.getType());
	}
	
	@Test
	public void testConstructorWithPhoneIdPhoneNumberAndPhoneTypeParam() {
		Phone phone = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		assertEquals(Long.valueOf(1),phone.getId());
		assertEquals("321022172",phone.getNumber());
		assertEquals(PhoneType.WORK,phone.getType());
	}
	
	@Test
	public void testEqualsWithNullParam() {
		Phone phone = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		Phone phone2 = null;
		assertFalse(phone.equals(phone2));
	}
	
	@Test
	public void testEqualsWithDiferentObjects() {
		Phone phone = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		Phone phone2 = new Phone(Long.valueOf(2),"321022172",PhoneType.WORK);
		assertFalse(phone.equals(phone2));
	}
	
	@Test
	public void testEqualsWithEqualsObjects() {
		Phone phone = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		Phone phone2 = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		assertTrue(phone.equals(phone2));
	}
	
	@Test
	public void testEqualsWithOneObjectWithNullId() {
		Phone phone = new Phone(Long.valueOf(1),"321022172",PhoneType.WORK);
		Phone phone2 = new Phone("321022172",PhoneType.WORK);
		phone2.setId(null);
		assertFalse(phone.equals(phone2));
	}
	
}
