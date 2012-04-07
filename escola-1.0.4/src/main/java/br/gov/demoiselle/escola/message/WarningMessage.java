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
package br.gov.demoiselle.escola.message;

import java.util.Locale;

import br.gov.framework.demoiselle.core.message.IMessage;
import br.gov.framework.demoiselle.core.message.Severity;

public enum WarningMessage implements IMessage{	
	
	ALUNO_NAO_ENCONTRADO("aluno_nao_encontrado"),	
	NOTA_NAO_CADASTRADA("nota_nao_cadastrada"), 
	USUARIO_NAO_ENCONTRADO("usuario_nao_encontrado"), 
	DISCIPLINA_NAO_ENCONTRADA("disciplina_nao_encontrada");
	
	private String key;
	
	private WarningMessage(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getLabel() {
		return getKey();
	}

	public Locale getLocale() {
		return new Locale("pt", "BR");
	}

	public Severity getSeverity() {
		return Severity.WARNING;
	}
	
	public String getResourceName() {
		return null;
	}

}
