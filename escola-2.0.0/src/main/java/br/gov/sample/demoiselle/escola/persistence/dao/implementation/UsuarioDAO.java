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
import br.gov.sample.demoiselle.escola.bean.Usuario;
import br.gov.sample.demoiselle.escola.persistence.dao.IUsuarioDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.filter.FiltroUsuario;

public class UsuarioDAO extends HibernateFilterGenericDAO<Usuario> implements
		IUsuarioDAO {

	public PagedResult<Usuario> listar(Page pagina) {
		HibernateUtil.getInstance().getSession().flush();
		return find(new FiltroUsuario(), pagina);
	}

	public Usuario buscar(Usuario arg0) {
		FiltroUsuario f = new FiltroUsuario();
		if (arg0.getId() != null)
			f.addEquals(FiltroUsuario.ID, arg0.getId());
		if (arg0.getLogin() != null)
			f.addEquals(FiltroUsuario.LOGIN, arg0.getLogin());
		if (arg0.getSenha() != null)
			f.addEquals(FiltroUsuario.SENHA, arg0.getSenha());
		if (arg0.getNome() != null)
			f.addEquals(FiltroUsuario.NOME, arg0.getNome());
		if (arg0.getCpf() != null)
			f.addEquals(FiltroUsuario.CPF, arg0.getCpf());
		List<Usuario> retorno = find(f);
		if (retorno != null && retorno.size() > 0)
			return retorno.get(0);
		return null;
	}

	public PagedResult<Usuario> filtrar(Usuario us, Page page) {
		PagedResult<Usuario> retorno = findByExample(us, page);
		if (retorno != null && retorno.getTotalResults() > 0) {
			return retorno;
		}
		return null;
	}

	public void alterarDetalhe(PapelUsuario detalhePapel) {
		HibernateUtil.getInstance().getSession().update(detalhePapel);
	}
}