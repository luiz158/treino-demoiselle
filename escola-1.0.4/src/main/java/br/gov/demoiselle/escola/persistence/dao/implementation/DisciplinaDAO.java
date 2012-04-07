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

import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.persistence.dao.IDisciplinaDAO;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;
import br.gov.frameworkdemoiselle.template.JPACrud;

@SuppressWarnings("serial")
public class DisciplinaDAO extends JPACrud<Disciplina> implements IDisciplinaDAO {
	
	public PagedResult<Disciplina> listar(Page page) {
		
		@SuppressWarnings("unchecked")
		List<Disciplina> list = getEntityManager().
			createQuery("select d from Disciplina d order by d.nome").getResultList();
		
		// TODO: funciona paginação no BD desse jeito..?
		
		return getPagedResult(page, list);
	}	

	public Disciplina buscar(Disciplina disc) {
		
		return getEntityManager().find(Disciplina.class, disc.getId());
		
		/*
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Disciplina> query = builder.createQuery(Disciplina.class);
		Root<Disciplina> d = query.from(Disciplina.class);
		
		query.select(d).where(builder.equal(d.get("id"), disc.getId()));
		*/
	}

	public PagedResult<Disciplina> filtrar(Disciplina disc, Page page) {
		
		// TODO: como fazer QBE usando JPA 2...?
		PagedResult<Disciplina> retorno = null; //findByExample(prof, page);
		
		return retorno;
	}

	public List<Disciplina> listar() {
		
		@SuppressWarnings("unchecked")
		List<Disciplina> list = getEntityManager().
			createQuery("select d from Disciplina d order by d.nome").getResultList();
		
		return list;
	}

	private PagedResult<Disciplina> getPagedResult(Page page, List<Disciplina> list) {
		PagedResult<Disciplina> pr = new PagedResult<Disciplina>(page, (long) list.size(), list);
		return pr;
	}

}
