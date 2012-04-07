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
 * Enumerador com lista de <b>mensagens informativas</b>.
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
public enum InfoMessage implements IMessage {
	
	DISCIPLINA_INSERIDA_OK("disciplina_inserir_ok"),
	DISCIPLINA_ALTERADA_OK("disciplina_alterar_ok"),
	DISCIPLINA_EXCLUIDA_OK("disciplina_excluir_ok"), 
	
	ALUNO_INSERIDO_OK("aluno_inserido_ok"),
	ALUNO_ALTERADO_OK("aluno_alterado_ok"),
	ALUNO_MATRICULADO_OK("aluno_matriculado_ok"),
	ALUNO_EXCLUIDO_OK("aluno_excluido_ok"),

	PROFESSOR_INSERIDO_OK("professor_inserido_ok"),
	PROFESSOR_ALTERADO_OK("professor_alterado_ok"),
	PROFESSOR_EXCLUIDO_OK("professor_excluido_ok"),

	NOTA_INSERIDA_OK("nota_inserida_ok"),
	NOTA_ALTERADA_OK("nota_alterada_ok"),
	NOTA_EXCLUIDA_OK("nota_excluida_ok"),
	
	FUNCIONARIO_001("funcionario_inserido_ok"), 
	FUNCIONARIO_002("funcionario_alterado_ok"), 
	FUNCIONARIO_003("funcionario_excluido_ok");
	
	private String key;
	
	private InfoMessage(String key) {
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
		return Severity.INFO;
	}
	
	public String getResourceName() {
		return "messages";
	}

}
