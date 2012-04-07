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
package br.gov.demoiselle.escola.business.implementation;

import java.util.List;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.business.IDisciplinaBC;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.dao.IDisciplinaDAO;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class DisciplinaBC implements IDisciplinaBC {
	
	@Inject
	private IDisciplinaDAO disciplinaDAO;	
	
	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Disciplina disc) {
		disciplinaDAO.insert(disc);		
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.DISCIPLINA_INSERIDA_OK);		
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Disciplina disc) {
		disciplinaDAO.update(disc);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.DISCIPLINA_ALTERADA_OK);
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Disciplina disc) {
		disciplinaDAO.delete(disc);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.DISCIPLINA_EXCLUIDA_OK);
	}

	public PagedResult<Disciplina> listar(Page page) {
		return disciplinaDAO.listar(page);
	}
	
	public PagedResult<Disciplina> filtrar(Disciplina prof, Page page) {
		return disciplinaDAO.filtrar(prof, page);
	}

	public Disciplina buscar(Disciplina disc) {
		return disciplinaDAO.buscar(disc);
	}

	public List<Disciplina> listar() {		
		return disciplinaDAO.listar();
	}
	
}