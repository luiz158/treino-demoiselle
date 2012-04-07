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
package br.gov.demoiselle.escola.ui.managedbean;/* Imports list */

import java.util.List;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.business.IDisciplinaBC;
import br.gov.demoiselle.escola.business.IProfessorBC;
import br.gov.demoiselle.escola.business.ITurmaBC;
import br.gov.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.frameworkdemoiselle.pagination.Page;

public class TurmaMB extends AbstractManagedBean{/* Fields declarations */
	
	@Inject
	private ITurmaBC turmaBC;

	@Inject
	private IProfessorBC professorBC;

	@Inject
	private IDisciplinaBC disciplinaBC;

	private Turma turma = new Turma();
	
	private PagedResultDataModel<Turma> listaPgTurma;
	

	/**
	 * Construtor
	 */
	public TurmaMB() {
		listaPgTurma = new PagedResultDataModel<Turma>();
	}	


	/**
	 * Lista de turmaes paginada
	 * @return
	 */
	public PagedResultDataModel<Turma> getListaTurma() {
		return listaPgTurma;
	}	
	
	
	public List<Turma> getListaTurmas(){
		return turmaBC.listar();
	}


	public String inserir() {
		turmaBC.inserir(turma);			
		carregarLista();	
		return AliasNavigationRule.TURMA_LISTAR;
	}

	public String listar() {		
		carregarLista();
		return AliasNavigationRule.TURMA_LISTAR;
	}

	public String alterar() {
		turmaBC.alterar(turma);
		carregarLista();	
		return AliasNavigationRule.TURMA_LISTAR;
	}

	public String excluir() {
		turmaBC.remover(turma);
		carregarLista();
		return AliasNavigationRule.TURMA_LISTAR;
	}	
	
	public List<Professor> getProfessores() {				
		List<Professor> listProf = professorBC.listar();
		return listProf;		
	}
	
	public List<Disciplina> getDisciplinas() {				
		List<Disciplina> listDisc = disciplinaBC.listar();
		return listDisc;		
	}

	
	/**
	 *  Atualiza a lista de turma realizando consulta no banco 
	 *  com paginação
	 */
	public void carregarListas(Page page) {	
		listaPgTurma.bind(turmaBC.listar(page));
	}
	
	/**
	 *  Atualiza a lista de aluno passando sempre a primeira página
	 */
	public void carregarLista() {
		carregarListas(new Page(getRows(), 1));
	}
	
	/**
	 * Atualiza a página de alunos
	 */
	public void updatePage(DataScrollerEvent e){
		int index = ((UIDatascroller) e.getComponent()).getPageIndex();
		Page pagina = new Page(getRows(), index);
		carregarListas(pagina);	
	}

	//**************** NAVEGAÇÃO ****************
	
	public String preInserir() {
		turma = new Turma();
		return AliasNavigationRule.TURMA_EDITAR;
	}
	
	public String cancelar() {
		return AliasNavigationRule.TURMA_LISTAR;
	}
	
	public String menu() {		
		return AliasNavigationRule.ESCOLA_MENU;
	}
	
	public String preExcluir() {
		return AliasNavigationRule.TURMA_VISUALIZAR;
	}


	public String preAlterar() {
		Turma prof = turmaBC.buscar(turma);
		if (prof == null){
			throw new ApplicationRuntimeException(ErrorMessage.TURMA_001);
		}
		return AliasNavigationRule.TURMA_EDITAR;
	}
	
	//SET e GETS
	public Turma getTurma() {
		return this.turma;
	}

	public void setTurma(Turma arg0) {
		this.turma = arg0;
	}
	
	
}//End of class