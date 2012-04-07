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

import javax.faces.component.html.HtmlInputText;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.business.IDisciplinaBC;
import br.gov.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.message.WarningMessage;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class DisciplinaMB extends AbstractManagedBean {/* Fields declarations */
	
	@Inject
	private IDisciplinaBC disciplinaBC;	
	private Disciplina disciplina = new Disciplina();
	private PagedResultDataModel<Disciplina> listaDisciplina;	
	private static Logger log = Logger.getLogger(DisciplinaMB.class);
	private HtmlInputText txtFiltro;
	
	/**
	 * Construtor
	 */
	public DisciplinaMB() {
		log.debug("construtor");
		listaDisciplina = new PagedResultDataModel<Disciplina>();
		txtFiltro = new HtmlInputText();
	}

	/**
	 * Lista de disciplinas paginada
	 * @return
	 */
	public PagedResultDataModel<Disciplina> getListaDisciplina() {
		log.debug("getListaDisciplina");
		return listaDisciplina;
	}
	
	/**
	 * Lista todas as disciplinas
	 * @return Lista de disciplinas
	 */
	public List<Disciplina> getListaTodasDisciplinas(){
		return disciplinaBC.listar();
	}


	/**
	 * Inserir disciplina
	 * @return regra de navegação
	 */
	public String inserir() {
		log.debug("inserir");
		try{
			disciplinaBC.inserir(disciplina);
		}catch(RuntimeException re){
			addMessage(ErrorMessage.DISCIPLINA_INSERIR_NOK, re);
		}catch(Exception e){
			addMessage(ErrorMessage.DISCIPLINA_INSERIR_NOK, e);
		}
		carregarLista();
		return AliasNavigationRule.DISCIPLINA_LISTAR;
	}

	public String listar() {
		log.debug("listar");
		carregarLista();
		return AliasNavigationRule.DISCIPLINA_LISTAR;
	}
	
	public String filtrar(){	
		log.debug("filtrar");
		Disciplina dis = new Disciplina();
		dis.setNome((String)txtFiltro.getValue());		
		PagedResult<Disciplina> pg = disciplinaBC.filtrar(dis, new Page(getRows(), 1));		
		listaDisciplina.bind(pg);
		return AliasNavigationRule.DISCIPLINA_LISTAR;
	}
	
	
	
	

	public String alterar() {
		log.debug("alterar");
		disciplinaBC.alterar(disciplina);
		addMessage(InfoMessage.DISCIPLINA_ALTERADA_OK);
		carregarLista();	
		return AliasNavigationRule.DISCIPLINA_LISTAR;
	}

	public String excluir() {
		log.debug("excluir");
		disciplinaBC.remover(disciplina);
		carregarLista();
		return AliasNavigationRule.DISCIPLINA_LISTAR;
	}	
	
	/**
	 *  Atualiza a lista de disciplina realizando consulta no banco 
	 *  com paginação
	 */
	public void carregarLista(Page page) {
		log.debug("carregarLista-Page");
		listaDisciplina.bind(disciplinaBC.listar(page));
	}
	
	/**
	 *  Atualiza a lista de aluno passando sempre a primeira página
	 */
	public void carregarLista() {
		log.debug("carregarLista");
		carregarLista(new Page(getRows(), 1));
	}
	
	/**
	 * Atualiza a p�gina de alunos
	 */
	public void updatePage(DataScrollerEvent e){
		log.debug("updatePage");
		int index = ((UIDatascroller) e.getComponent()).getPageIndex();
		Page pagina = new Page(getRows(), index);
		carregarLista(pagina);	
	}

	//**************** NAVEGA��O ****************
	
	public String preInserir() {
		log.debug("preInserir");
		disciplina = new Disciplina();
		return AliasNavigationRule.DISCIPLINA_EDITAR;
	}
	
	public String cancelar() {
		log.debug("cancelar");
		return AliasNavigationRule.DISCIPLINA_LISTAR;
	}
	
	public String preExcluir() {
		log.debug("preExcluir");
		return AliasNavigationRule.DISCIPLINA_VISUALIZAR;
	}


	public String preAlterar() {
		log.debug("preAlterar");
		Disciplina disc = disciplinaBC.buscar(disciplina);
		if (disc == null){
			throw new ApplicationRuntimeException(WarningMessage.DISCIPLINA_NAO_ENCONTRADA);
		}
		return AliasNavigationRule.DISCIPLINA_EDITAR;
	}
	
	//SET e GETS
	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina arg0) {
		this.disciplina = arg0;
	}

	public HtmlInputText getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(HtmlInputText txtFiltro) {
		this.txtFiltro = txtFiltro;
	}
	
	
}//End of class