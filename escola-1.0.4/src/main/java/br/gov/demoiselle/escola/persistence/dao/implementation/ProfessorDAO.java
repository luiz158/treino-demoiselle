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
//START SNIPPET: snip001
package br.gov.demoiselle.escola.persistence.dao.implementation;

import java.util.List;

import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.persistence.dao.IProfessorDAO;
import br.gov.demoiselle.escola.persistence.dao.filter.FiltroProfessor;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class ProfessorDAO extends HibernateFilterGenericDAO<Professor> implements IProfessorDAO{
			
	/**
	 * Lista todos os alunos com paginação.
	 * Uso de HQL
	 */
	public PagedResult<Professor> listar(Page page) {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from Professor order by nome asc", page);
	}	
	
	
	public List<Professor> listar() {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from Professor order by nome asc");
	}

	/**
	 * Busca pelo campo id
	 */
	public Professor buscar(Professor professor) {		
		FiltroProfessor f = new FiltroProfessor();
		f.addEquals(FiltroProfessor.ID, professor.getId());
		List<Professor> retorno = find(f);
		if (retorno != null && retorno.size() > 0 )
			return retorno.get(0);
		return null;
	}

	public PagedResult<Professor> filtrar(Professor prof, Page page) {
		return findByExample(prof, page);
	}

}
//END SNIPPET: snip001