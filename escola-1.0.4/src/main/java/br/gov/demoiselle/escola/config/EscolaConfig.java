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
package br.gov.demoiselle.escola.config;

import java.util.List;

import br.gov.frameworkdemoiselle.configuration.ConfigType;
import br.gov.frameworkdemoiselle.configuration.Key;
import br.gov.frameworkdemoiselle.stereotype.Configuration;

@Configuration(resource = "escola.properties")
public class EscolaConfig {

	@Key(name = "escola.dao.factory")
	private String escolaDaoFactory;

	@Key(name = "papeis.papel", type = ConfigType.XML, resourceName = "escola-papeis.xml")
	private List<String> papeis;

	public String getFactoryEscolaDAO() {
		return escolaDaoFactory;
	}

	public List<String> getPapeis() {
		return papeis;
	}

//	/**
//	 * Retorna a local onde serão gravados os logs da aplicação
//	 */
//	public String getLogPath() {
//		return cfg.getLogPath();
//	}
//
//	/**
//	 * Retorna o local onde serão gravados arquivos temporários
//	 */
//	public String getTemporaryPath() {
//		return cfg.getTemporaryPath();
//	}
//
//	public String getUploadPath() {
//		return cfg.getUploadPath();
//	}

}
