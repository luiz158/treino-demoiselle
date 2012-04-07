/*
 * Demoiselle Sample Copyright (c) 2009 Serpro and other contributors as indicated by the @author tag. See the
 * copyright.txt in the distribution for a full listing of contributors. Demoiselle Sample is a set of open source Java
 * EE project samples using the Demoiselle Framework Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html GPL License 3 This file is part of Demoiselle Sample. Demoiselle Sample is free
 * software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3. Demoiselle Sample is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details. You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample. If not, see <http://www.gnu.org/licenses/>
 */
package br.gov.demoiselle.escola.persistence.dao.implementation;

import java.util.List;

import javax.persistence.Query;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.persistence.dao.IAlunoDAO;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;
import br.gov.frameworkdemoiselle.template.JPACrud;

public class AlunoDAO extends JPACrud<Aluno> implements IAlunoDAO {

	/**
	 * Lista todos os alunos sem paginação. Uso de HQL
	 */
	public List<Aluno> listarAluno() {
		List<Aluno> list = null;
		try {
			Query query = getEntityManager().createQuery("from Aluno order by nome asc");
			list = query.getResultList();
		} catch (Exception e) {
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_001, e);
		}
		return list;
	}

	/**
	 * Lista todos os alunos com paginação. Uso de HQL
	 */
	public PagedResult<Aluno> listarAluno(Page page) {
		return getPagedResult(page, findAll());
	}

	private PagedResult<Aluno> getPagedResult(Page page, List<Aluno> list) {
		PagedResult<Aluno> pr = new PagedResult<Aluno>(page, (long) list.size(), list);
		return pr;
	}

	/**
	 * Busca pelo campo id
	 */
	public Aluno buscarAluno(Aluno arg0) {
		return load(arg0.getId());
	}

	/**
	 * Filtra aluno sem paginação Uso do findByExample
	 */
	public List<Aluno> filtrarAluno(Aluno pojo) {
		// TODO descobrir a melhor maneira de substituir este método
		// return findByExample(pojo);
		return findAll();
	}

	/**
	 * Filtra aluno com paginação Consulta do findByExample
	 */
	public PagedResult<Aluno> filtrarAluno(Aluno pojo, Page page) {
		// return findByExample(pojo, page);
		return getPagedResult(page, findAll());
	}

	public void alterarDetalhe(Object arg0) {
		// HibernateUtil.getInstance().getSession().update(arg0);
		getEntityManager().merge(arg0);
	}

	public boolean beforeInsert(Aluno arg0) {
		return true;
	}

	public boolean beforeUpdate(Aluno arg0) {
		return true;
	}

	public boolean beforeRemove(Aluno arg0) {
		return true;
	}/* @fwk-methods-end */

	public Aluno buscarAluno(Usuario arg0) {
		List<Aluno> retorno = getEntityManager().createQuery(
				"from Aluno as aluno where aluno.usuario.id = " + arg0.getId()).getResultList();
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		return null;
	}

	/**
	 * Busca o aluno associado a um dado usuário
	 */
	public Aluno buscarPorUsuario(Usuario usuario) {
		List<Aluno> retorno = getEntityManager().createQuery(
				"from Aluno as aluno where aluno.usuario.id = " + usuario.getId()).getResultList();
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		return null;
	}
}
