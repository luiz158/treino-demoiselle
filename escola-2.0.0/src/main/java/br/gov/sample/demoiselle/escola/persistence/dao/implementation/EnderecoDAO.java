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
import br.gov.sample.demoiselle.escola.bean.Endereco;
import br.gov.sample.demoiselle.escola.persistence.dao.IEnderecoDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.filter.FiltroEndereco;

public class EnderecoDAO extends HibernateFilterGenericDAO<Endereco> implements
		IEnderecoDAO {

	public Endereco buscar(Endereco endereco) {
		FiltroEndereco f = new FiltroEndereco();
		f.addEquals(FiltroEndereco.ID, endereco.getId());
		List<Endereco> retorno = find(f);
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		return null;
	}

	public List<Endereco> filtrar(Endereco endereco) {
		FiltroEndereco f = new FiltroEndereco();
		if (endereco.getId() != null)
			f.addEquals(FiltroEndereco.ID, endereco.getId());
		if (endereco.getLogradouro() != null)
			f.addEquals(FiltroEndereco.LOGRADOURO, endereco.getLogradouro());
		if (endereco.getMunicipio() != null)
			f.addEquals(FiltroEndereco.MUNICIPIO, endereco.getMunicipio());
		if (endereco.getCep() != null)
			f.addEquals(FiltroEndereco.CEP, endereco.getCep());
		List<Endereco> retorno = find(f);
		if (retorno != null && retorno.size() > 0)
			return retorno;
		return null;
	}

	public PagedResult<Endereco> filtrar(Endereco endereco, Page page) {
		PagedResult<Endereco> retorno = findByExample(endereco, page);
		return retorno;
	}

	public List<Endereco> listar() {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from Endereco order by logradouro asc");
	}

	public PagedResult<Endereco> listar(Page page) {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from Endereco order by logradouro asc", page);
	}/* @fwk-methods-begin *//* @fwk-methods-end */
}