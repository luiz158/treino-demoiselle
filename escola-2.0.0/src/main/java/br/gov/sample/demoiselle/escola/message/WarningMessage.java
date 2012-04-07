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
 * Enumerador com lista de <b>mensagens de aviso</b>.
 * <p>
 * A descrição das mensagens encontra-se nos arquivos <code>"messages*.properties"</code>,
 * sendo estes selecionados em tempo de execução de acordo com o idioma do usuário.
 * <p>
 * Por exemplo, caso utilize o navegador em inglês, será considerado o conteúdo do
 * arquivo <code>"messages_en_US.properties"</code>. Caso o idioma do usuário não esteja
 * disponível, será assumido como default o arquivo <code>"messages.properties"</code>.
 * 
 * @author CETEC/CTJEE
 */
public enum WarningMessage implements IMessage {	
	
	ALUNO_NAO_ENCONTRADO("aluno_nao_encontrado"),	
	NOTA_NAO_CADASTRADA("nota_nao_cadastrada"), 
	USUARIO_NAO_ENCONTRADO("usuario_nao_encontrado"), 
	DISCIPLINA_NAO_ENCONTRADA("disciplina_nao_encontrada"),
	
	ALUNO_CONFIRMA_EXCLUSAO("aluno_confirma_exclusao"),
	DISCIPLINA_CONFIRMA_EXCLUSAO("disciplina_confirma_exclusao"),
	PROFESSOR_CONFIRMA_EXCLUSAO("professor_confirma_exclusao"),
	FUNCIONARIO_CONFIRMA_EXCLUSAO("funcionario_confirma_exclusao"),
	TURMA_CONFIRMA_EXCLUSAO("turma_confirma_exclusao"),
	NOTA_CONFIRMA_EXCLUSAO("nota_confirma_exclusao");
	
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
		return null;
	}

	public Severity getSeverity() {
		return Severity.WARNING;
	}
	
	public String getResourceName() {
		return "messages";
	}

}
