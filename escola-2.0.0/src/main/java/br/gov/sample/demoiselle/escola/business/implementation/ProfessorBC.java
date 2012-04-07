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
import br.gov.sample.demoiselle.escola.bean.Professor;
import br.gov.sample.demoiselle.escola.business.IProfessorBC;
import br.gov.sample.demoiselle.escola.constant.AliasRole;
import br.gov.sample.demoiselle.escola.message.InfoMessage;
import br.gov.sample.demoiselle.escola.persistence.dao.IProfessorDAO;

/**
 * @author CETEC/CTJEE
 */
public class ProfessorBC implements IProfessorBC {

	@Inject
	private IProfessorDAO professorDAO;

	public Professor buscar(Professor prof) {
		return professorDAO.buscar(prof);
	}

	public List<Professor> listar() {
		return professorDAO.listar();
	}

	public PagedResult<Professor> listar(Page page) {
		return professorDAO.listar(page);
	}

	public PagedResult<Professor> filtrar(String nome, Page page) {
		Professor prof = new Professor();
		prof.setNome(nome);
		return professorDAO.filtrar(prof, page);
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Professor prof) {
		professorDAO.insert(prof);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.PROFESSOR_INSERIDO_OK);		
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Professor prof) {
		professorDAO.update(prof);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.PROFESSOR_ALTERADO_OK);
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Professor prof) {
		professorDAO.remove(prof);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.PROFESSOR_EXCLUIDO_OK);
	}

}