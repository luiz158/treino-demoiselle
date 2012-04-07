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

import static br.gov.framework.demoiselle.view.faces.util.ManagedBeanUtil.addMessage;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlInputText;

import org.apache.log4j.Logger;

import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.escola.bean.Funcionario;
import br.gov.sample.demoiselle.escola.business.IFuncionarioBC;
import br.gov.sample.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.sample.demoiselle.escola.message.ErrorMessage;
import br.gov.sample.demoiselle.escola.message.InfoMessage;
import br.gov.sample.demoiselle.escola.message.WarningMessage;

/**
 * @author CETEC/CTJEE
 */
public class FuncionarioMB extends AbstractManagedBean {

	private static Logger log = Logger.getLogger(FuncionarioMB.class);

	@Injection
	private IFuncionarioBC funcionarioBC;
	
	private Funcionario funcionario;
	private List<Funcionario> listaFuncionario;
	
	private HtmlInputText txtFiltro;

	public FuncionarioMB() {
		log.debug("construtor");
		listaFuncionario = new ArrayList<Funcionario>();
		txtFiltro = new HtmlInputText();
	}

	/**
	 * Lista de funcionários paginada.
	 * 
	 * @return
	 */
	public List<Funcionario> getListaFuncionario() {
		log.debug("getListaFuncionario");
		return listaFuncionario;
	}

	/**
	 * Inserir funcionário.
	 * 
	 * @return regra de navegação
	 */
	public String inserir() {
		log.debug("inserir");
		try {
			funcionarioBC.inserir(funcionario);
			addMessage(InfoMessage.FUNCIONARIO_001);
		} catch (RuntimeException re) {
			addMessage(ErrorMessage.FUNCIONARIO_001, re);
		} catch (Exception e) {
			addMessage(ErrorMessage.FUNCIONARIO_001, e);
		}
		return listar();
	}

	public String listar() {
		log.debug("listar");
		listaFuncionario = funcionarioBC.listar();
		return AliasNavigationRule.FUNCIONARIO_LISTAR;
	}
	
	public String filtrar() {
		Funcionario func = new Funcionario();
		func.setNome((String) txtFiltro.getValue());
		listaFuncionario = funcionarioBC.filtrar(func);
		return AliasNavigationRule.FUNCIONARIO_LISTAR;
	}

	public String alterar() {
		log.debug("alterar");
		funcionarioBC.alterar(funcionario);
		funcionario = new Funcionario();
		addMessage(InfoMessage.FUNCIONARIO_002);
		return listar();
	}

	public String excluir() {
		log.debug("excluir");
		funcionarioBC.remover(funcionario);
		funcionario = new Funcionario();
		addMessage(InfoMessage.FUNCIONARIO_003);
		return listar();
	}

	// **************** NAVEGAÇÃO ****************

	public String preInserir() {
		log.debug("preInserir");
		funcionario = new Funcionario();
		return AliasNavigationRule.FUNCIONARIO_EDITAR;
	}

	public String cancelar() {
		log.debug("cancelar");
		return AliasNavigationRule.FUNCIONARIO_LISTAR;
	}

	public String preExcluir() {
		log.debug("preExcluir");
		funcionario = funcionarioBC.buscar(funcionario);
		messageContext.addMessage(WarningMessage.FUNCIONARIO_CONFIRMA_EXCLUSAO);
		return AliasNavigationRule.FUNCIONARIO_VISUALIZAR;
	}

	public String preAlterar() {
		log.debug("preAlterar");
		funcionario = funcionarioBC.buscar(funcionario);
		return AliasNavigationRule.FUNCIONARIO_EDITAR;
	}

	public String visualizar() {
		log.debug("visualizar");
		funcionario = funcionarioBC.buscar(funcionario);
		return AliasNavigationRule.FUNCIONARIO_VISUALIZAR;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario arg0) {
		this.funcionario = arg0;
	}

	public int getTotalRegistros() {
		return listaFuncionario.size();
	}

	public HtmlInputText getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(HtmlInputText txtFiltro) {
		this.txtFiltro = txtFiltro;
	}

}