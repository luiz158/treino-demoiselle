package contactlist.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuditTest {

	@Test
	public void testConstructorWithMessageParam() {
		Audit audit = new Audit("test");
		assertEquals("test",audit.getMessage());
	}
	
	@Test
	public void testSetAndGetId() {
		Audit audit = new Audit();
		audit.setId(Long.valueOf(1));
		assertEquals(Long.valueOf(1),audit.getId());
	}
	
	@Test
	public void testSetAndGetMessage() {
		Audit audit = new Audit();
		audit.setMessage("test");
		assertEquals("test",audit.getMessage());
	}
	
}
