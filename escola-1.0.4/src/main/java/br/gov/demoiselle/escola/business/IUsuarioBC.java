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
package br.gov.demoiselle.escola.business;

import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.framework.demoiselle.core.layer.IBusinessController;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public interface IUsuarioBC extends IBusinessController {

	public void inserir(Usuario Usuario);

	public void remover(Usuario arg0);

	public void alterar(Usuario Usuario);

	public PagedResult<Usuario> listar(Page page);

	public PagedResult<Usuario> filtrar(Usuario Usuario, Page page);

	public Usuario buscar(Usuario Usuario);

	public PagedResult<Usuario> filtrar(String value, Page page);

	public Usuario inserirPapel(Usuario usuario, PapelUsuario papel);

	public Usuario alterarPapel(Usuario usuario, PapelUsuario papel);

	public Usuario removerPapel(Usuario usuario, PapelUsuario papel);

}
