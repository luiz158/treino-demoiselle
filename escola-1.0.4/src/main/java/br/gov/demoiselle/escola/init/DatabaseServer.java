 /* 
 * Demoiselle Sample
 * Copyright (c) 2010 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 * 
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework
 *   
 * Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html  GPL License 3
 *   
 * This file is part of Demoiselle Sample.
 * 
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 * 
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample.  If not, see <http://www.gnu.org/licenses/>
 */ 
package br.gov.demoiselle.escola.init;

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

	private final HsqldbServer server;

	@Inject
	private ResourceBundle bundle;

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