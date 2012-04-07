package contactlist;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import contactlist.business.AudiBCTest;
import contactlist.business.ContactBCTest;
import contactlist.config.AuditConfigTest;
import contactlist.domain.AuditTest;
import contactlist.domain.ContactTest;
import contactlist.domain.PhoneTest;
import contactlist.init.ApplicationLoaderTest;
import contactlist.init.DataBaseServerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AudiBCTest.class, ContactBCTest.class,
		AuditConfigTest.class, AuditTest.class, ContactTest.class,
		PhoneTest.class, ApplicationLoaderTest.class, DataBaseServerTest.class })
public class AllContactListTests {
}