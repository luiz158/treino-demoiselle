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
package br.gov.demoiselle.escola.business.implementation;

import java.util.Set;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.business.IUsuarioBC;
import br.gov.demoiselle.escola.persistence.dao.IUsuarioDAO;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class UsuarioBC implements IUsuarioBC {

	@Inject
	private IUsuarioDAO usuarioDAO;

	// TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Usuario usuario) {
		try {
			usuarioDAO.insert(usuario);
		} catch (ApplicationRuntimeException e) {
			// TODO: e agora, José?
//			ManagedBeanUtil.addMessage(e.getObjectMessage(), e.getCause());
		}
	}

	// TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Usuario usuario) {
		usuarioDAO.remove(usuario);
	}

	// TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	public PagedResult<Usuario> listar(Page pagina) {
		return usuarioDAO.listar(pagina);
	}

	public Usuario buscar(Usuario usuario) {
		return usuarioDAO.buscar(usuario);
	}

	public PagedResult<Usuario> filtrar(Usuario usuario, Page pagina) {
		return usuarioDAO.filtrar(usuario, pagina);
	}

	public PagedResult<Usuario> filtrar(String value, Page page) {
		Usuario usuario = new Usuario();
		usuario.setNome(value);
		return filtrar(usuario, page);
	}

	/*---------------------- PAPEL ----------------------------------*/

	public Usuario inserirPapel(Usuario usuario, PapelUsuario detalhePapel) {
		usuario = buscar(usuario);
		Set<PapelUsuario> listaPapeis = usuario.getPapeis();
		detalhePapel.setUsuario(usuario);
		listaPapeis.add(detalhePapel);
		return usuario;
	}

	public Usuario alterarPapel(Usuario usuario, PapelUsuario detalhePapel) {
		usuarioDAO.alterarDetalhe(detalhePapel);
		return buscar(usuario);
	}

	public Usuario removerPapel(Usuario usuario, PapelUsuario detalhePapel) {
		usuario = buscar(usuario);
		Set<PapelUsuario> listaPapel = usuario.getPapeis();
		listaPapel.remove(detalhePapel);
		return usuario;
	}

}
