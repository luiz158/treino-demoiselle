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
package br.gov.sample.demoiselle.escola.persistence.dao.implementation;/* Imports list */

import java.util.List;

import br.gov.component.demoiselle.hibernate.filter.dao.HibernateFilterGenericDAO;
import br.gov.framework.demoiselle.persistence.hibernate.HibernateUtil;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.sample.demoiselle.escola.bean.PapelUsuario;
import br.gov.sample.demoiselle.escola.persistence.dao.IPapelUsuarioDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.filter.FiltroPapelUsuario;

public class PapelUsuarioDAO extends HibernateFilterGenericDAO<PapelUsuario>
		implements IPapelUsuarioDAO {/* @fwk-methods-begin *//* @fwk-methods-end */

	/**
	 * Lista todos os papéis com paginação. Uso de HQL
	 */
	public PagedResult<PapelUsuario> listar(Page page) {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from PapelUsuario order by nome asc", page);
	}

	/**
	 * Busca pelo campo id
	 */
	public PapelUsuario buscar(PapelUsuario arg0) {
		FiltroPapelUsuario f = new FiltroPapelUsuario();

		if (arg0.getId() != null)
			f.addEquals(FiltroPapelUsuario.ID, arg0.getId());
		if (arg0.getPapel() != null)
			f.addEquals(FiltroPapelUsuario.PAPEL, arg0.getPapel());

		List<PapelUsuario> retorno = find(f);
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		return null;
	}

	public PagedResult<PapelUsuario> filtrar(PapelUsuario papel, Page page) {
		PagedResult<PapelUsuario> retorno = findByExample(papel, page);
		return retorno;
	}

	public List<PapelUsuario> listar() {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from PapelUsuario order by nome asc");
	}

}