package br.gov.sample.demoiselle.escola.init;

import static br.gov.frameworkdemoiselle.annotation.Shutdown.MIN_PRIORITY;
import static br.gov.frameworkdemoiselle.annotation.Startup.MAX_PRIORITY;

import javax.inject.Inject;

import org.hsqldb.Server;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Shutdown;
import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class DatabaseServer {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	private final HsqldbServer server;

	public DatabaseServer() {
		this.server = new HsqldbServer();
	}

	@Startup(priority = MAX_PRIORITY)
	public void start() {
		this.server.start();
		this.logger.info(bundle.getString("database-loaded"));
	}

	@Shutdown(priority = MIN_PRIORITY)
	public void stop() {
		this.server.shutdown();
		this.logger.info(bundle.getString("database-stopped"));
	}

}

class HsqldbServer extends Thread {

	static final String DATABASE_NAME = "banco_escola";
	static final String DATABASE_PATH = "database/escola";

	private final Server server;

	HsqldbServer() {
		this.server = create();
		setDaemon(false);
	}

	private Server create() {
		
		Server server = new Server();
		server.setDatabaseName(0, DATABASE_NAME);
		server.setDatabasePath(0, DATABASE_PATH);
		server.setPort(9001);
		server.setSilent(true);
		
		return server;
	}

	@Override
	public void run() {
		this.server.start();
	}

	public void shutdown() {
		this.server.stop();
	}
	
}