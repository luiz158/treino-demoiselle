package contactlist.business;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.powermock.reflect.Whitebox.setInternalState;

import javax.inject.Inject;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.DemoiselleRunner;
import contactlist.config.AuditConfig;
import contactlist.domain.Audit;

@Transactional
@RunWith(DemoiselleRunner.class)
public class AudiBCTest {

	@Inject
	private AuditBC auditBC;
	private Audit audit;
	private AuditConfig auditConfig;
	
	@Before
	public void setUP() {
		this.auditConfig = EasyMock.createMock(AuditConfig.class);
		
		this.audit = new Audit();
		this.audit.setMessage("test");
		
		
	}
	
	@Test
	public void testWithAuditEnable() {
		expect(this.auditConfig.isEnabled()).andReturn(true);
		replay(this.auditConfig);
		setInternalState(this.auditBC, "config", this.auditConfig);
		
		this.auditBC.insert(this.audit);
		
		verify();
		assertNotNull(this.audit.getId());
	}
	
	@Test
	public void testWithAuditDisabled() {
		expect(this.auditConfig.isEnabled()).andReturn(false);
		replay(this.auditConfig);
		setInternalState(this.auditBC, "config", this.auditConfig);
		
		this.auditBC.insert(this.audit);		
		
		verify();
		assertNull(this.audit.getId());
	}
	
}
