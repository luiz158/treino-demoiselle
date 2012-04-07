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
package br.gov.sample.demoiselle.escola.persistence.dao.implementation;

import java.util.List;

import br.gov.component.demoiselle.hibernate.filter.dao.HibernateFilterGenericDAO;
import br.gov.framework.demoiselle.core.bean.IPojo;
import br.gov.framework.demoiselle.persistence.hibernate.HibernateUtil;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.sample.demoiselle.escola.bean.Aluno;
import br.gov.sample.demoiselle.escola.bean.Usuario;
import br.gov.sample.demoiselle.escola.persistence.dao.IAlunoDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.filter.FiltroAluno;

/**
 * @author SERPRO/CETEC/CTJEE
 */
public class AlunoDAO extends HibernateFilterGenericDAO<Aluno> implements IAlunoDAO {

	/**
	 * Lista todos os alunos sem paginação. Uso de HQL
	 */
	public List<Aluno> listarAluno() {
		HibernateUtil.getInstance().getSession().flush();
		List<Aluno> list = findHQL("from Aluno order by nome asc");
		return list;
	}

	/**
	 * Lista todos os alunos com paginação. Uso de HQL
	 */
	public PagedResult<Aluno> listarAluno(Page page) {
		HibernateUtil.getInstance().getSession().flush();
		return findHQL("from Aluno", page);
	}

	/**
	 * Busca pelo campo id
	 */
	public Aluno buscarAluno(Aluno arg0) {
		FiltroAluno f = new FiltroAluno();
		f.addEquals(FiltroAluno.ID, arg0.getId());
		List<Aluno> retorno = find(f);
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		return null;
	}

	/**
	 * Filtra aluno sem paginação Uso do findByExample
	 */
	public List<Aluno> filtrarAluno(Aluno pojo) {
		return findByExample(pojo);
	}

	/**
	 * Filtra aluno com paginação Consulta do findByExample
	 */
	public PagedResult<Aluno> filtrarAluno(Aluno pojo, Page page) {
		return findByExample(pojo, page);
	}

	public IPojo alterarDetalhe(IPojo detalhe) {
		HibernateUtil.getInstance().getSession().update(detalhe);
		return detalhe;
	}

	public boolean beforeInsert(Aluno arg0) {
		return true;
	}

	public boolean beforeUpdate(Aluno arg0) {
		return true;
	}

	public boolean beforeRemove(Aluno arg0) {
		return true;
	}

	/**
	 * Busca o aluno associado a um dado usuário
	 */
	public Aluno buscarAluno(Usuario usuario) {
		List<Aluno> retorno = findHQL("from Aluno as aluno where aluno.usuario.cpf = " + usuario.getCpf());
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		return null;
	}

}
