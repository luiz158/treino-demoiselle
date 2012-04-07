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
package br.gov.sample.demoiselle.escola.view.managedbean;

import java.util.List;

import org.apache.log4j.Logger;
import org.richfaces.component.UIDatascroller;
import org.richfaces.event.DataScrollerEvent;

import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.framework.demoiselle.view.faces.util.PagedResultDataModel;
import br.gov.sample.demoiselle.escola.bean.Disciplina;
import br.gov.sample.demoiselle.escola.bean.Professor;
import br.gov.sample.demoiselle.escola.bean.Turma;
import br.gov.sample.demoiselle.escola.business.IDisciplinaBC;
import br.gov.sample.demoiselle.escola.business.IProfessorBC;
import br.gov.sample.demoiselle.escola.business.ITurmaBC;
import br.gov.sample.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.sample.demoiselle.escola.message.WarningMessage;

/**
 * @author CETEC/CTJEE
 */
public class TurmaMB extends AbstractManagedBean {

	private static Logger log = Logger.getLogger(TurmaMB.class);

	@Injection
	private ITurmaBC turmaBC;

	@Injection
	private IProfessorBC professorBC;

	@Injection
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
	 * Lista de turmas paginada
	 * 
	 * @return
	 */
	public PagedResultDataModel<Turma> getListaTurma() {
		return listaPgTurma;
	}

	public List<Turma> getListaTurmas() {
		return turmaBC.listar();
	}

	public String inserir() {
		log.debug("TurmaMB.inserir()");
		turmaBC.inserir(turma);
		carregarLista();
		return AliasNavigationRule.TURMA_LISTAR;
	}

	public String listar() {
		carregarLista();
		return AliasNavigationRule.TURMA_LISTAR;
	}

	public String alterar() {
		log.debug("TurmaMB.alterar()");
		turmaBC.alterar(turma);
		carregarLista();
		return AliasNavigationRule.TURMA_LISTAR;
	}

	public String excluir() {
		log.debug("TurmaMB.excluir()");
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
	 * Atualiza a lista de turma realizando consulta no banco com paginação
	 */
	public void carregarListas(Page page) {
		listaPgTurma.bind(turmaBC.listar(page));
	}

	/**
	 * Atualiza a lista de aluno passando sempre a primeira página
	 */
	public void carregarLista() {
		carregarListas(new Page(getRows(), 1));
	}

	/**
	 * Atualiza a página de alunos
	 */
	public void updatePage(DataScrollerEvent e) {
		int index = ((UIDatascroller) e.getComponent()).getPageIndex();
		Page pagina = new Page(getRows(), index);
		carregarListas(pagina);
	}

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
		turma = turmaBC.buscar(turma);
		messageContext.addMessage(WarningMessage.TURMA_CONFIRMA_EXCLUSAO);
		return AliasNavigationRule.TURMA_VISUALIZAR;
	}

	public String preAlterar() {
		turma = turmaBC.buscar(turma);
		return AliasNavigationRule.TURMA_EDITAR;
	}

	public String visualizar() {
		log.debug("visualizar");
		turma = turmaBC.buscar(turma);
		return AliasNavigationRule.TURMA_VISUALIZAR;
	}

	public Turma getTurma() {
		return this.turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}