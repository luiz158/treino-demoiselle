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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.gov.framework.demoiselle.persistence.hibernate.HibernateGenericDAO;
import br.gov.framework.demoiselle.persistence.hibernate.HibernateUtil;
import br.gov.sample.demoiselle.escola.bean.Funcionario;
import br.gov.sample.demoiselle.escola.persistence.dao.IFuncionarioDAO;

/**
 * @author CETEC/CTJEE
 */
public class FuncionarioDAO extends HibernateGenericDAO<Funcionario> implements IFuncionarioDAO {
	
	public static final String ID = "id";
	public static final String NOME = "nome";

	/**
	 * Este método mostra como fazer uma consulta usando a API Criteria do Hibernate.
	 */
	@SuppressWarnings("unchecked")
	public Funcionario buscar(Funcionario funcionario) {
		
		HibernateUtil.getInstance().getSession().flush();

		Criteria criterio = HibernateUtil.getInstance().getSession()
				.createCriteria(Funcionario.class).add(
						Restrictions.like(ID, funcionario.getId())).addOrder(
						Order.asc(NOME));

		List<Funcionario> retorno = (List<Funcionario>) criterio.list();
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		
		return null;
	}

	public List<Funcionario> filtrar(Funcionario pojo) {
		HibernateUtil.getInstance().getSession().flush();
		return findByExample(pojo);
	}

	/**
	 * Este método mostra como fazer uma consulta usando a linguagem HQL.
	 */
	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		
		HibernateUtil.getInstance().getSession().flush();
		
		String sql = "from Funcionario order by nome asc";
		List list = find(sql);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Funcionario object = (Funcionario) iterator.next();
			funcionarios.add(object);
		}

		return funcionarios;
	}

}