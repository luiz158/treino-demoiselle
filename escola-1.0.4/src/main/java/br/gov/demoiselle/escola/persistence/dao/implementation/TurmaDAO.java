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
package br.gov.demoiselle.escola.persistence.dao.implementation;

import java.util.List;

import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.persistence.dao.ITurmaDAO;
import br.gov.demoiselle.escola.persistence.dao.filter.FiltroTurma;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class TurmaDAO extends HibernateFilterGenericDAO<Turma> implements ITurmaDAO{
			
	/**
	 * Lista todos os alunos com paginação.
	 * Uso de HQL
	 */
	public PagedResult<Turma> listar(Page page) {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from Turma order by nome asc", page);
	}	

	/**
	 * Busca pelo campo id
	 */
	public Turma buscar(Turma professor) {		
		FiltroTurma f = new FiltroTurma();
		f.addEquals(FiltroTurma.ID, professor.getId());
		List<Turma> retorno = find(f);
		if (retorno != null && retorno.size() > 0 )
			return retorno.get(0);
		return null;
	}

	public List<Turma> listar() {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from Turma order by nome asc");
	}

}
