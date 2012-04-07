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
package br.gov.sample.demoiselle.escola.persistence.dao;

import java.util.List;

import br.gov.framework.demoiselle.core.layer.IDAO;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.sample.demoiselle.escola.bean.Disciplina;

/**
 * @author CETEC/CTJEE
 */
public interface IDisciplinaDAO extends IDAO<Disciplina> {

	/**
	 * Busca disciplina pelo campo identificador.
	 * 
	 * @param disciplina
	 * @return
	 */
	public Disciplina buscar(Disciplina disciplina);

	/**
	 * Lista todas as disciplinas.
	 * 
	 * @return
	 */
	public List<Disciplina> listar();

	/**
	 * Lista todas as disciplinas de forma paginada.
	 * 
	 * @param page
	 * @return
	 */
	public PagedResult<Disciplina> listar(Page page);

	/**
	 * Lista de forma paginada as disciplinas que satisfizerem o filtro especificado.
	 * 
	 * @param disciplina
	 * @param page
	 * @return
	 */
	public PagedResult<Disciplina> filtrar(Disciplina disciplina, Page page);

}
