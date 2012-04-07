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
package br.gov.demoiselle.escola.factory;

import javax.inject.Inject;

import br.gov.demoiselle.escola.config.EscolaConfig;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.framework.demoiselle.core.bean.IPojo;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.IDAO;
import br.gov.framework.demoiselle.core.layer.integration.IDAOFactory;
import br.gov.framework.demoiselle.core.layer.integration.InjectionContext;

public class EscolaDAOFactory implements IDAOFactory {

	@Inject
	private EscolaConfig config;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IDAO<? extends IPojo> create(InjectionContext ctx) {
		IDAO result = null;
		String className = getClassNameStub(ctx);
		try {
			Class c = Class.forName(className);
			result = (IDAO) c.newInstance();
		} catch (Exception e) {
			throw new ApplicationRuntimeException(ErrorMessage.ESCOLA_001);
		}
		return result;
	}

	/**
	 * Retorna o nome da classe DAO a partir da pacote implementation, onde o nome da classe não
	 * possui o prefixo "I" e termina com o sufixo registrado na propriedade
	 * "escola.dao.funcionario.fabrica" do arquivo de configuração 'escola.properties'
	 * 
	 * @param ctx Contexto de Injeção
	 */
	private String getClassNameStub(InjectionContext ctx) {
		
		String fabrica = config.getFactoryEscolaDAO();
		String className = ctx.getFieldType().getName();
		
		className = className.replace(".dao.", ".dao.implementation.");
		className = className.replace(".I", ".");
		className += fabrica;
		
		return className;
	}

}
