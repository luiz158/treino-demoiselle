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
package br.gov.sample.demoiselle.escola.business.implementation;

import java.util.List;

import javax.inject.Inject;

import br.gov.component.demoiselle.authorization.RequiredRole;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.sample.demoiselle.escola.bean.Disciplina;
import br.gov.sample.demoiselle.escola.business.IDisciplinaBC;
import br.gov.sample.demoiselle.escola.constant.AliasRole;
import br.gov.sample.demoiselle.escola.message.InfoMessage;
import br.gov.sample.demoiselle.escola.persistence.dao.IDisciplinaDAO;

/**
 * @author CETEC/CTJEE
 */
public class DisciplinaBC implements IDisciplinaBC {
	
	@Inject
	private IDisciplinaDAO disciplinaDAO;	

	// TODO: criar produtor MessageContextFactory no Demoiselle 2.0
//	@Inject
//	private MessageContext msgCtx;
	
	@RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Disciplina disciplina) {
		disciplinaDAO.insert(disciplina);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.DISCIPLINA_INSERIDA_OK);		
	}

	@RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Disciplina disciplina) {
		disciplinaDAO.update(disciplina);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.DISCIPLINA_ALTERADA_OK);
	}

	@RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Disciplina disciplina) {
		disciplinaDAO.remove(disciplina);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.DISCIPLINA_EXCLUIDA_OK);
	}

	public PagedResult<Disciplina> listar(Page page) {
		return disciplinaDAO.listar(page);
	}
	
	public PagedResult<Disciplina> filtrar(Disciplina disciplina, Page page) {
		return disciplinaDAO.filtrar(disciplina, page);
	}

	public Disciplina buscar(Disciplina disciplina) {
		return disciplinaDAO.buscar(disciplina);
	}

	public List<Disciplina> listar() {		
		return disciplinaDAO.listar();
	}
	
}