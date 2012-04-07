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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import br.gov.framework.demoiselle.core.message.IMessage;
import br.gov.framework.demoiselle.core.message.Severity;

/**
 * Enumerador com lista de <b>mensagens de erro</b>.
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
public enum ErrorMessage implements IMessage {
	
	DISCIPLINA_INSERIR_NOK("Erro ao inserir disciplina"), 
	DISCIPLINA_ALTERAR_NOK("Erro ao alterar disciplina"),
	DISCIPLINA_EXCLUIR_NOK("Erro ao excluir disciplina"),
	
	PROFESSOR_001("Professor não inserido"),
	PROFESSOR_002("Professor não alterado"),
	PROFESSOR_003("Professor não excluído"),
	
	TURMA_001("Turma não inserida"), 
	ALUNO_001("Aluno não inserido"), 
	
	ESCOLA_001("Fábrica não instanciada"),
	ALUNO_002("Aluno não alterado"),
	ALUNO_002_01("Turma lotada"), 
	ALUNO_002_02("Turma já inserida"),
	
	NOTA_001("Nota não inserida"),
	NOTA_001_01("Nota não inserida. Problema no envio de e-mail"),
	NOTA_002("Nota não alterada"),
	NOTA_003("Nota não excluída"),
	NOTA_004("Nenhuma nota cadastrada"),
	
	FUNCIONARIO_001("Funcionario não inserido"),
	FUNCIONARIO_002("Funcionario não alterado"),
	FUNCIONARIO_003("Funcionario não excluído"),
	FUNCIONARIO_004("Funcionario não encontrado"),
	FUNCIONARIO_005("Funcionarios não listado"), 
	FUNCIONARIO_006("Sequencia sq_funcionario não exibida"),
	FUNCIONARIO_007("Funcionario não encontrado");
		
	private String label;	
	private Collection<Object> params = new ArrayList<Object>();
	
	private ErrorMessage(String label) {
		this.label = label;
	}
	
	public String getKey() {
		return this.toString();
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
		
	public Object[] getParams(){
		return params.toArray();
	}

}
