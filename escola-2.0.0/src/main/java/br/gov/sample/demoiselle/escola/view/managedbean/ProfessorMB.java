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

import javax.faces.component.html.HtmlInputText;

import org.apache.log4j.Logger;
import org.richfaces.component.UIDatascroller;
import org.richfaces.event.DataScrollerEvent;

import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.framework.demoiselle.view.faces.util.PagedResultDataModel;
import br.gov.sample.demoiselle.escola.bean.Professor;
import br.gov.sample.demoiselle.escola.business.IProfessorBC;
import br.gov.sample.demoiselle.escola.business.implementation.ProfessorBC;
import br.gov.sample.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.sample.demoiselle.escola.message.WarningMessage;

/**
 * @author SERPRO/CETEC/CTJEE
 */
public class ProfessorMB extends AbstractManagedBean {
	
	private static Logger log = Logger.getLogger(ProfessorMB.class);

	@Injection
	private IProfessorBC professorBC;
	
	private Professor professor = new Professor();
	private PagedResultDataModel<Professor> listaProfessor;
	private HtmlInputText txtFiltro;
	
	/**
	 * Construtor
	 */
	public ProfessorMB() {
		professorBC = new ProfessorBC();
		listaProfessor = new PagedResultDataModel<Professor>();
		txtFiltro = new HtmlInputText();
	}	

	/**
	 * Lista de professores paginada
	 * @return
	 */
	public PagedResultDataModel<Professor> getListaProfessor() {
		return listaProfessor;
	}

	public String inserir() {
		professorBC.inserir(professor);			
		carregarLista();	
		log.debug("inserir [ok]");
		return AliasNavigationRule.PROFESSOR_LISTAR;
	}

	public String listar() {		
		carregarLista();
		log.debug("listar [ok]");
		return AliasNavigationRule.PROFESSOR_LISTAR;
	}
	
	public String filtrar(){		
		PagedResult<Professor> pg = professorBC.filtrar((String)txtFiltro.getValue(), new Page(getRows(), 1));
		listaProfessor.bind(pg);
		log.debug("filtrar [ok]");
		return AliasNavigationRule.PROFESSOR_LISTAR;
	}

	public String alterar() {
		professorBC.alterar(professor);
		carregarLista();	
		log.debug("alterar [ok]");
		return AliasNavigationRule.PROFESSOR_LISTAR;
	}

	public String excluir() {
		professorBC.remover(professor);
		log.debug("remover [ok]");
		carregarLista();
		return AliasNavigationRule.PROFESSOR_LISTAR;
	}	
	
	/**
	 *  Atualiza a lista de professor realizando consulta no banco 
	 *  com paginação
	 */
	public void carregarLista(Page page) {
		listaProfessor.bind(professorBC.listar(page));
		log.debug("carregarLista [ok]");
	}
	
	/**
	 *  Atualiza a lista de aluno passando sempre a primeira página
	 */
	public void carregarLista() {
		carregarLista(new Page(getRows(), 1));
	}
	
	/**
	 * Atualiza a página de alunos
	 */
	public void updatePage(DataScrollerEvent e){
		int index = ((UIDatascroller) e.getComponent()).getPageIndex();
		Page pagina = new Page(getRows(), index);
		carregarLista(pagina);	
		log.debug("updatePage [ok]");
	}
	
	public String preInserir() {
		professor = new Professor();
		log.debug("preInserir [ok]");
		return AliasNavigationRule.PROFESSOR_EDITAR;
	}
	
	public String cancelar() {
		log.debug("cancelar [ok]");
		return AliasNavigationRule.PROFESSOR_LISTAR;
	}
	
	public String menu() {		
		log.debug("menu [ok]");
		return AliasNavigationRule.ESCOLA_MENU;
	}
	
	public String preExcluir() {
		log.debug("preExcluir [ok]");
		professor = professorBC.buscar(professor);
		messageContext.addMessage(WarningMessage.PROFESSOR_CONFIRMA_EXCLUSAO);
		return AliasNavigationRule.PROFESSOR_VISUALIZAR;
	}

	public String preAlterar() {
		log.debug("preAlterar [ok]");
		professor = professorBC.buscar(professor);
		return AliasNavigationRule.PROFESSOR_EDITAR;
	}
	
	public String visualizar() {
		log.debug("visualizar [ok]");
		return AliasNavigationRule.PROFESSOR_VISUALIZAR;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor prof) {
		this.professor = prof;
	}

	public HtmlInputText getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(HtmlInputText txtFiltro) {
		this.txtFiltro = txtFiltro;
	}
	
}