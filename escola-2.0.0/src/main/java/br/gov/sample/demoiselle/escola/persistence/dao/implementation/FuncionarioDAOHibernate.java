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
import br.gov.framework.demoiselle.persistence.hibernate.HibernateUtil;
import br.gov.sample.demoiselle.escola.bean.Funcionario;
import br.gov.sample.demoiselle.escola.persistence.dao.IFuncionarioDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.filter.FiltroFuncionario;

/**
 * Implementação do DAO Professor, por meio do Hibernate
 * @author CETEC/CTJEE
 */
public class FuncionarioDAOHibernate extends HibernateFilterGenericDAO<Funcionario> implements IFuncionarioDAO {

	/**
	 * Procurar um funcionário a partir do campo ID
	 */
	public Funcionario buscar(Funcionario funcionario) {
		HibernateUtil.getInstance().getSession().flush();
		FiltroFuncionario f = new FiltroFuncionario();
		f.addEquals(FiltroFuncionario.ID, funcionario.getId());
		List<Funcionario> retorno = find(f);
		if (retorno != null && retorno.size() > 0 )
			return retorno.get(0);
		return null;
	}

	/**
	 * Filtrar a lista de funcionários a partir de um POJO
	 */
	public List<Funcionario> filtrar(Funcionario pojo) {
		HibernateUtil.getInstance().getSession().flush();
		return findByExample(pojo);
	}

	/**
	 * Lista todos os funcionários
	 */
	public List<Funcionario> listar() {
		HibernateUtil.getInstance().getSession().flush();
		return find(new FiltroFuncionario());
	}

}
