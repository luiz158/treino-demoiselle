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

import static br.gov.framework.demoiselle.view.faces.util.ManagedBeanUtil.addIMessages;
import static br.gov.framework.demoiselle.view.faces.util.ManagedBeanUtil.addMessage;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.inject.Inject;

import org.richfaces.component.UIDatascroller;
import org.richfaces.component.html.HtmlTabPanel;
import org.richfaces.event.DataScrollerEvent;

import br.gov.component.demoiselle.authorization.AuthorizationException;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.core.transaction.ITransactionContext;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.framework.demoiselle.view.faces.util.PagedResultDataModel;
import br.gov.framework.demoiselle.web.message.WebMessageContext;
import br.gov.sample.demoiselle.escola.bean.Aluno;
import br.gov.sample.demoiselle.escola.bean.Email;
import br.gov.sample.demoiselle.escola.bean.Endereco;
import br.gov.sample.demoiselle.escola.bean.Foto;
import br.gov.sample.demoiselle.escola.bean.Telefone;
import br.gov.sample.demoiselle.escola.bean.Turma;
import br.gov.sample.demoiselle.escola.business.IAlunoBC;
import br.gov.sample.demoiselle.escola.config.EscolaConfig;
import br.gov.sample.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.sample.demoiselle.escola.message.ErrorMessage;
import br.gov.sample.demoiselle.escola.message.FatalMessage;
import br.gov.sample.demoiselle.escola.message.WarningMessage;

/**
 * @author SERPRO/CETEC/CTJEE
 */
public class AlunoMB extends AbstractManagedBean {

	@Injection
	private IAlunoBC alunoBC;

	@Inject
	private EscolaConfig config;

	private Aluno aluno;
	private Endereco detalheEndereco;
	private Email detalheEmail;
	private Telefone detalheTelefone;
	private Turma detalheTurma;
	
	private PagedResultDataModel<Aluno> listaAluno;
	
//	private UploadedFile uploadFoto;
	private HtmlTabPanel tabPanel;
	private HtmlInputText txtFiltro;
	private ITransactionContext transCtx;

	/**
	 * Construtor
	 */
	public AlunoMB() {
		aluno = new Aluno();
		detalheEndereco = new Endereco();
		detalheEmail = new Email();
		detalheTurma = new Turma();
		detalheTelefone = new Telefone();
		listaAluno = new PagedResultDataModel<Aluno>();
		txtFiltro = new HtmlInputText();
		tabPanel = new HtmlTabPanel();
		transCtx = ContextLocator.getInstance().getTransactionContext();
	}

	/**
	 * Lista Alunos
	 * 
	 * @return Navegação
	 */
	public String listar() {
		carregarLista();
		return AliasNavigationRule.ALUNO_LISTAR;
	}

	/**
	 * Inclui Aluno
	 * 
	 * @return Navegação
	 */
	public String inserir() {
		try {
//			Foto foto = (uploadFoto == null) ? null : new Foto(uploadFoto
//					.getName(), uploadFoto.getInputStream());
//			alunoBC.inserir(aluno, foto);
			alunoBC.inserir(aluno);
			carregarLista();
		} catch (ApplicationRuntimeException ex) {
			carregarLista();
			transCtx.add(ex);
		} catch (Exception ex) {
			transCtx.add(ex);
		}
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	/**
	 * Exclui aluno
	 * 
	 * @return
	 */
	public String excluir() {
		try {
			alunoBC.remover(aluno);
			carregarLista();
		} catch (Exception e) {
			transCtx.add(e);
		}
		return AliasNavigationRule.ALUNO_LISTAR;
	}

	/**
	 * Atualiza a página de alunos
	 */
	public void updatePage(DataScrollerEvent e) {
		try {
			int index = ((UIDatascroller) e.getComponent()).getPageIndex();
			Page pagina = new Page(getRows(), index);
			carregarLista(pagina);
		} catch (Exception ex) {
			transCtx.add(ex);
		}
	}

	/**
	 * Atualiza a lista de aluno realizando consulta no banco com paginaÃ§Ã£o
	 */
	public void carregarLista(Page page) {
		try {
			this.listaAluno.bind(alunoBC.listar(page));
		} catch (Exception e) {
			transCtx.add(e);
		}
	}

	/**
	 * Atualiza a lista de aluno passando sempre a primeira pÃ¡gina
	 */
	public void carregarLista() {
		try{
			carregarLista(new Page(getRows(), 1));
		}catch (Exception e) {
			transCtx.add(e);
		}
	}

	/**
	 * Altera o aluno
	 * 
	 * @return Navegação
	 */
	public String alterar() {
		try {
//			Foto foto = (uploadFoto == null) ? null : new Foto(uploadFoto
//					.getName(), uploadFoto.getInputStream());
//			alunoBC.alterar(aluno, foto);
			alunoBC.alterar(aluno);
		} catch (AuthorizationException e) {
			addMessage(e.getMessage());
			transCtx.add(e);
		} catch (Exception e) {
			transCtx.add(e);
			addMessage(ErrorMessage.ALUNO_001);
		}
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	/**
	 * Filtra aluno
	 * 
	 * @return Navegação
	 */
	public String filtrar() {
		try {
			PagedResult<Aluno> listapg = alunoBC.filtrar((String) txtFiltro
					.getValue(), new Page(getRows(), 1));
			if (listapg != null && listapg.getResults().size() > 0) {
				listaAluno.bind(listapg);
			} else {
				addMessage(WarningMessage.ALUNO_NAO_ENCONTRADO);
			}
			aluno = new Aluno();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return AliasNavigationRule.ALUNO_LISTAR;
	}

	/*--------------------------------- ENDERECO ---------------------------------*/

	public String incluirEndereco() {
		try {
			aluno = alunoBC.inserirEndereco(aluno, detalheEndereco);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheEndereco = new Endereco();
		tabPanel.setSelectedTab("tabEndereco");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String alterarEndereco() {
		try {
			aluno = alunoBC.alterarEndereco(aluno, detalheEndereco);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheEndereco = new Endereco();
		tabPanel.setSelectedTab("tabEndereco");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String removerEndereco() {
		try {
			aluno = alunoBC.removerEndereco(aluno, detalheEndereco);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheEndereco = new Endereco();
		tabPanel.setSelectedTab("tabEndereco");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	/*--------------------------------- EMAIL ---------------------------------*/

	public String incluirEmail() {
		try {
			aluno = alunoBC.inserirEmail(aluno, detalheEmail);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheEmail = new Email();
		tabPanel.setSelectedTab("tabEmail");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String alterarEmail() {
		try {
			aluno = alunoBC.alterarEmail(aluno, detalheEmail);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheEmail = new Email();
		tabPanel.setSelectedTab("tabEmail");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String removerEmail() {
		try {
			aluno = alunoBC.removerEmail(aluno, detalheEmail);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheEmail = new Email();
		tabPanel.setSelectedTab("tabEmail");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	/*--------------------------------- TELEFONE ---------------------------------*/

	public String incluirTelefone() {
		try {
			aluno = alunoBC.inserirTelefone(aluno, detalheTelefone);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheTelefone = new Telefone();
		tabPanel.setSelectedTab("tabTelefone");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String alterarTelefone() {
		try {
			aluno = alunoBC.alterarTelefone(aluno, detalheTelefone);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheTelefone = new Telefone();
		tabPanel.setSelectedTab("tabTelefone");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String removerTelefone() {
		try {
			aluno = alunoBC.removerTelefone(aluno, detalheTelefone);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheTelefone = new Telefone();
		tabPanel.setSelectedTab("tabTelefone");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	/*--------------------------------- TURMA ---------------------------------*/
	public String incluirTurma() {
		try {
			aluno = alunoBC.incluirTurma(aluno, detalheTurma);
		} catch (AuthorizationException e) {
			addMessage(ErrorMessage.ALUNO_001, e);
		} catch (ApplicationRuntimeException e) {
			addMessage(ErrorMessage.ALUNO_001, e);
		} catch (Exception e) {
			addMessage(FatalMessage.ERRO_INESPERADO, e);
		}
		addIMessages(WebMessageContext.getInstance().getMessages());
		detalheTurma = new Turma();
		tabPanel.setSelectedTab("tabTurma");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String removerTurma() {
		try {
			aluno = alunoBC.removerTurma(aluno, detalheTurma);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		detalheTurma = new Turma();
		tabPanel.setSelectedTab("tabTurma");
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	/*--------------------------------- NAVEGAÇÃO ---------------------------------*/

	public String preInserir() {
		aluno = new Aluno();
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String preAlterar() {
		try {
			aluno = alunoBC.buscar(aluno);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		return AliasNavigationRule.ALUNO_EDITAR;
	}

	public String preExcluir() {
		messageContext.addMessage(WarningMessage.ALUNO_CONFIRMA_EXCLUSAO);
		return visualizar();
	}

	public String visualizar() {
		try {
			aluno = alunoBC.buscar(aluno);
		} catch (Exception e) {
			// addMessages(e.getFacesMessages());
		}
		return AliasNavigationRule.ALUNO_VISUALIZAR;
	}

	public String cancelar() {
		aluno = new Aluno();
		carregarLista();
		return AliasNavigationRule.ALUNO_LISTAR;
	}

	public String menu() {
		return AliasNavigationRule.ESCOLA_MENU;
	}

	public Endereco getDetalheEndereco() {
		return detalheEndereco;
	}

	public void setDetalheEndereco(Endereco endereco) {
		this.detalheEndereco = endereco;
	}

	/*--------------------------------- GETs SETs ---------------------------------*/

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno arg0) {
		this.aluno = arg0;
	}

	public Email getDetalheEmail() {
		return detalheEmail;
	}

	public void setDetalheEmail(Email detalheEmail) {
		this.detalheEmail = detalheEmail;
	}

	public Telefone getDetalheTelefone() {
		return detalheTelefone;
	}

	public void setDetalheTelefone(Telefone detalheTelefone) {
		this.detalheTelefone = detalheTelefone;
	}

	public HtmlTabPanel getPainel() {
		return tabPanel;
	}

	public void setPainel(HtmlTabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}

//	public UploadedFile getUploadFoto() {
//		return uploadFoto;
//	}
//
//	public void setUploadFoto(UploadedFile uploadFoto) {
//		this.uploadFoto = uploadFoto;
//	}

	public String getFoto() {
		String foto = (aluno.getFoto() == null || aluno.getFoto().equals("")) ? "no_image.png"
				: aluno.getFoto();
		return config.getUploadPath() + foto;
	}

	public Turma getDetalheTurma() {
		return detalheTurma;
	}

	public void setDetalheTurma(Turma detalheTurma) {
		this.detalheTurma = detalheTurma;
	}

	public PagedResultDataModel<Aluno> getListaAluno() {
		return listaAluno;
	}

	public List<Aluno> getListaTodosAlunos() {
		return alunoBC.listar();
	}

	public HtmlInputText getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(HtmlInputText txtFiltro) {
		this.txtFiltro = txtFiltro;
	}
	
}
