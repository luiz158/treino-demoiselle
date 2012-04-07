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
package br.gov.sample.demoiselle.escola.business.implementation;/* Imports list */

import java.util.List;

import javax.inject.Inject;

import br.gov.component.demoiselle.authorization.RequiredRole;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.sample.demoiselle.escola.bean.Turma;
import br.gov.sample.demoiselle.escola.business.ITurmaBC;
import br.gov.sample.demoiselle.escola.constant.AliasRole;
import br.gov.sample.demoiselle.escola.persistence.dao.ITurmaDAO;

public class TurmaBC implements ITurmaBC {
	
	@Inject
	private ITurmaDAO turmaDAO;	
	
	@RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Turma turma) {
		turmaDAO.insert(turma);
	}

	@RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Turma turma) {
		turmaDAO.update(turma);
	}

	@RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Turma turma) {
		turmaDAO.remove(turma);
	}

	public PagedResult<Turma> listar(Page page) {
		return turmaDAO.listar(page);
	}
	
	public List<Turma> listar() {
		return turmaDAO.listar();
	}

	public Turma buscar(Turma turma) {
		return turmaDAO.buscar(turma);
	}
	
}