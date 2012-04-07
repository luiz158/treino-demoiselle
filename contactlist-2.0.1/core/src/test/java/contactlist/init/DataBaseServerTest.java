package contactlist.init;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.powermock.reflect.Whitebox.setInternalState;

import javax.inject.Inject;

import org.easymock.EasyMock;
import org.hsqldb.Server;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import contactlist.persistence.DatabaseServer;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.util.DemoiselleRunner;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@RunWith(DemoiselleRunner.class)
public class DataBaseServerTest {
	
	@Test
	public void testStop() {
		
		Logger logger = EasyMock.createMock(Logger.class);
		logger.info(EasyMock.anyObject(String.class));
		replay(logger);
		
		Server server = EasyMock.createMock(Server.class);
		server.shutdown();
		replay(server);
		
		DatabaseServer dbServer = new DatabaseServer();
		setInternalState(dbServer, "server", server);
		setInternalState(dbServer, "logger", logger);
		
		dbServer.stop();
		
		verify(server,logger);
	}
	
}
