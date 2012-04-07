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
package br.gov.demoiselle.escola.business.implementation;/* Imports list */

import java.util.List;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.business.ITurmaBC;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.persistence.dao.ITurmaDAO;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class TurmaBC implements ITurmaBC {
	
	@Inject
	private ITurmaDAO turmaDAO;	
	
	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Turma arg0) {
		turmaDAO.insert(arg0);
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Turma arg0) {
		turmaDAO.update(arg0);
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Turma arg0) {
		turmaDAO.remove(arg0);
	}

	public PagedResult<Turma> listar(Page page) {
		return turmaDAO.listar(page);
	}
	
	public List<Turma> listar() {
		return turmaDAO.listar();
	}

	public Turma buscar(Turma arg0) {
		return turmaDAO.buscar(arg0);
	}/*@fwk-methods-end*/


	
}