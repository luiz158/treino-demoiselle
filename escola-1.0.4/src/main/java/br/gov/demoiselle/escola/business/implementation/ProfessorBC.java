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

import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.business.IProfessorBC;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.persistence.dao.IProfessorDAO;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class ProfessorBC implements IProfessorBC {

	@Inject
	private IProfessorDAO professorDAO;
	
	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Professor arg0) {
		professorDAO.insert(arg0);
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Professor arg0) {
		professorDAO.update(arg0);
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Professor arg0) {
		professorDAO.remove(arg0);
	}

	public PagedResult<Professor> listar(Page page) {
		return professorDAO.listar(page);
	}
	
	public List<Professor> listar() {
		return professorDAO.listar();
	}
	
	public PagedResult<Professor> filtrar(String profName, Page page) {
		Professor prof = new Professor();
		prof.setNome(profName);
		return professorDAO.filtrar(prof, page);
	}

	public Professor buscar(Professor arg0) {
		return professorDAO.buscar(arg0);
	}
	
}