package contactlist.init;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.powermock.reflect.Whitebox.setInternalState;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import contactlist.business.AuditBC;
import contactlist.business.ContactBC;
import contactlist.domain.Audit;
import contactlist.domain.Contact;


public class ApplicationLoaderTest {

	@Test
	public void testMethodLoad() {
		ContactBC localContactBC = EasyMock.createMock(ContactBC.class);
		
		expect(localContactBC.findAll()).andReturn(new ArrayList<Contact>());
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		localContactBC.insert(EasyMock.anyObject(Contact.class));
		replay(localContactBC);
		
		ApplicationLoader appLoader = new ApplicationLoader();
		setInternalState(appLoader, "contactBC", localContactBC);
		
		appLoader.load();
		verify(localContactBC);
	}
	
	@Test
	public void testUnload() {
		ApplicationLoader appLoader = new ApplicationLoader();
		AuditBC auditBC = EasyMock.createMock(AuditBC.class);
		auditBC.insert(EasyMock.anyObject(Audit.class));
		replay(auditBC);
		
		setInternalState(appLoader, "auditBC", auditBC);
		appLoader.unload();
		verify(auditBC);
	}
	
}
