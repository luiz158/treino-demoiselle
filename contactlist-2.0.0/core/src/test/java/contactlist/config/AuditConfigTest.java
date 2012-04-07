package contactlist.config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.util.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class AuditConfigTest {

	@Inject
	private AuditConfig config;

	@Test
	public void testIsEnabledDefaultValue() {
		assertFalse(new AuditConfig().isEnabled());
	}

	@Test
	public void testIsEnabledSettedValue() {
		assertTrue(config.isEnabled());
	}
}
