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
package br.gov.sample.demoiselle.escola.message;

import java.util.Locale;

import br.gov.framework.demoiselle.core.message.IMessage;
import br.gov.framework.demoiselle.core.message.Severity;

/**
 * Enumerador com lista de <b>mensagens de erro críticas</b>.
 * <p>
 * As descrições das mensagens serão atribuídas em tempo de execução de acordo com o
 * idioma preferido do usuário.
 * <p>
 * Para o idioma default (português do Brasil - pt_BR) o texto se encontra no próprio
 * código da enumeração (ex: "Turma já inserida"). 
 * <p>
 * Caso o idioma não seja o padrão, o texto da mensagem será buscado no arquivo
 * <code>"errors*.properties"</code> do idioma correspondente. Por exemplo,
 * <code>"errors_en_US.properties"</code> caso o idioma seja inglês.
 * 
 * @author CETEC/CTJEE
 */
public enum FatalMessage implements IMessage {
	
	ERRO_INESPERADO("Ocorreu um erro inesperado. " +
			"Favor entrar em contato com o gestor da aplicação.");
	
	private String label;
	
	private FatalMessage(String label) {
		this.label = label;
	}

	public String getKey() {
		return this.name();
	}

	public String getLabel() {
		return label;
	}

	public Locale getLocale() {
		return new Locale("pt", "BR");
	}

	public Severity getSeverity() {
		return Severity.FATAL;
	}
	
	public String getResourceName() {
		return "errors";
	}

}
