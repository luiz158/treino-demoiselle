 /* 
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
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

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.hsqldb.Server;

import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.persistence.dao.implementation.TurmaDAO;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;

public class DataBaseConfigurator implements ServletContextListener {

	private static Logger log = Logger.getLogger(DataBaseConfigurator.class);

	private static Server servidor;
	
	private static String dbName= "banco_escola";
	private static String path= "./escoladb";// Diretorio do usuario na pasta escoladb
	

	public void contextDestroyed(ServletContextEvent arg0) {
		log.debug("Database shuting down");
		if (servidor != null) {
			servidor.stop();			
		}
	}

	public void contextInitialized(ServletContextEvent evt) {
		log.debug("Database starting");
		
		testarArquivosDoBanco();
		
		servidor = new Server();
		servidor.setDatabaseName(0, dbName);
		servidor.setDatabasePath(0, path+dbName); log.debug(servidor.getDatabasePath(0, true));
		servidor.setSilent(true);
		servidor.start();
	}
	
	private void testarArquivosDoBanco(){
		File dbDir = new File(path);
		path = dbDir.getAbsolutePath().replace("/.", "/");
		dbDir = new File(path);
		log.debug(dbDir);
		if (!dbDir.exists()) {
			if (!dbDir.mkdirs()) {
				throw new ApplicationRuntimeException(ErrorMessage.BANCO_DADOS_001,dbDir);
			}
		}
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		
		File scriptFile = new File(path + dbName + ".script");
		File propertiesFile = new File(path + dbName + ".properties");
		if (!propertiesFile.exists() || !scriptFile.exists()) {
			throw new ApplicationRuntimeException(ErrorMessage.BANCO_DADOS_002);
		}
	
	}

	public static void main(String[] args) {		
		new DataBaseConfigurator().contextInitialized(null);
		try {
			ContextLocator.getInstance().setTransactionContext(
					WebTransactionContext.getInstance());
			List<Turma> lista = new TurmaDAO().findHQL("from Turma");
			for (Turma t : lista) {
				log.debug(t.getDisciplina().getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new DataBaseConfigurator().contextDestroyed(null);
	}

}
