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
package br.gov.demoiselle.escola.persistence.dao.implementation;/* Imports list */

import java.util.List;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Nota;
import br.gov.demoiselle.escola.persistence.dao.INotaDAO;
import br.gov.demoiselle.escola.persistence.dao.filter.FiltroNota;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;



public class NotaDAO extends HibernateFilterGenericDAO<Nota> implements INotaDAO {


	public NotaDAO() {
	}
	
	public PagedResult<Nota> listar(Page page) {
		HibernateUtil.getInstance().getSession().flush();
		PagedResult<Nota> retorno = find(new FiltroNota(), page);
		if (retorno != null && retorno.getTotalResults() > 0 )
			return retorno;
		return null;
	}
	
	public List<Nota> listar(Disciplina disciplina) {		
		FiltroNota f = new FiltroNota();
		f.addEquals(FiltroNota.DISCIPLINA, disciplina.getId());
		List<Nota> retorno = find(f);
		if (retorno != null && retorno.size() > 0 )
			return retorno;
		return null;
	}

	public List<Nota> listar(Aluno aluno) {		
		FiltroNota f = new FiltroNota();
		f.addEquals(FiltroNota.ALUNO, aluno.getId());
		List<Nota> retorno = find(f);
		if (retorno != null && retorno.size() > 0 )
			return retorno;
		return null;
	}
	
	public Nota buscar(Nota nota) {		
		FiltroNota f = new FiltroNota();
		f.addEquals(FiltroNota.ID, nota.getId());
		List<Nota> retorno = find(f);
		if (retorno != null && retorno.size() > 0 )
			return retorno.get(0);
		return null;
	}	

	public Nota buscar(Aluno aluno, Disciplina disciplina) {	
		FiltroNota f = new FiltroNota();
		f.addEquals(FiltroNota.ALUNO, aluno.getId());
		f.addEquals(FiltroNota.DISCIPLINA, disciplina.getId());
		List<Nota> retorno = find(f);
		if (retorno != null && retorno.size() > 0 )
			return retorno.get(0);
		return null;
	}

	public boolean beforeInsert(Nota arg0) {
		return true;
	}

	public boolean beforeUpdate(Nota arg0) {
		return true;
	}

	public boolean beforeRemove(Nota arg0) {
		return true;
	}/*@fwk-methods-end*/

	public List<Nota> buscar(Aluno aluno) {			
		List<Nota> retorno = findHQL("from Nota as nota where nota.aluno.id = " + aluno.getId());
		if (retorno != null && retorno.size() > 0 )
			return retorno;
		return null;		
	}






}//End of class