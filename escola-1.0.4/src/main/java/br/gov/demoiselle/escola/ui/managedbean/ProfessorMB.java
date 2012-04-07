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

import javax.faces.component.html.HtmlInputText;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.business.IProfessorBC;
import br.gov.demoiselle.escola.business.implementation.ProfessorBC;
import br.gov.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;
//START SNIPPET: snip001
//START SNIPPET: snip002
public class ProfessorMB extends AbstractManagedBean{/* Fields declarations */
	
	@Inject
	private IProfessorBC professorBC; 	
	//END SNIPPET: snip001
	private Professor professor = new Professor();
	private PagedResultDataModel<Professor> listaProfessor;
	private HtmlInputText txtFiltro;
	
	private static Logger log = Logger.getLogger(ProfessorMB.class);

	/**
	 * Construtor
	 */
	public ProfessorMB() {
		log.debug("construtor");
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

	//**************** NAVEGAÇÃO ****************
	
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
		return AliasNavigationRule.PROFESSOR_VISUALIZAR;
	}


	public String preAlterar() {
		Professor prof = professorBC.buscar(professor);
		if (prof == null){
			throw new ApplicationRuntimeException(ErrorMessage.PROFESSOR_001);
		}
		log.debug("preAlterar [ok]");
		return AliasNavigationRule.PROFESSOR_EDITAR;
	}
	
	//SET e GETS
	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor arg0) {
		this.professor = arg0;
	}


	public HtmlInputText getTxtFiltro() {
		return txtFiltro;
	}


	public void setTxtFiltro(HtmlInputText txtFiltro) {
		this.txtFiltro = txtFiltro;
	}
	
	
	
	
}//End of class
//END SNIPPET: snip002