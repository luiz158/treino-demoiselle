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

import br.gov.demoiselle.escola.bean.PapelUsuario;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.business.IUsuarioBC;
import br.gov.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.message.WarningMessage;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.security.ISecurityContext;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

/**
 * @author Vanderson Botelho
 * 
 */
public class UsuarioMB extends AbstractManagedBean {

	@Inject
	private IUsuarioBC usuarioBC;

	private Usuario usuario;
	private PagedResultDataModel<Usuario> listaUsuario;
	private HtmlInputText txtFiltro;
	private PapelUsuario detalhepapel;

	/**
	 * Construtor sem parâmetros
	 */
	public UsuarioMB() {
		listaUsuario = new PagedResultDataModel<Usuario>();
		txtFiltro = new HtmlInputText();
		usuario = new Usuario();
		detalhepapel = new PapelUsuario();
	}

	public String getNomeUsuario() {
		return usuario.getNome();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario arg0) {
		this.usuario = arg0;
	}

	/**
	 * Retorna a lista de usuários
	 * 
	 * @return
	 */
	public PagedResultDataModel<Usuario> getListaUsuario() {
		return this.listaUsuario;
	}

	public String listar() {
		carregarLista();
		return AliasNavigationRule.USUARIO_LISTAR;
	}

	public String inserir() {
		usuarioBC.inserir(usuario);
		carregarLista();
		return AliasNavigationRule.USUARIO_LISTAR;
	}

	public String excluir() {
		usuarioBC.remover(usuario);
		carregarLista();
		return AliasNavigationRule.USUARIO_LISTAR;
	}
	
	public String getPrincipalNome(){
		ISecurityContext ctx = ContextLocator.getInstance().getSecurityContext();
		return ctx.getUserPrincipal().getName();
	}

	public String alterar() {
		usuarioBC.alterar(usuario);
		return AliasNavigationRule.USUARIO_LISTAR;
	}

	public String preAlterar() {
		usuario = usuarioBC.buscar(usuario);
		return AliasNavigationRule.USUARIO_EDITAR;
	}

	/**
	 * Redireciona para a página de edição
	 * 
	 * @return
	 */
	public String preInserir() {
		usuario = new Usuario();
		return AliasNavigationRule.USUARIO_EDITAR;
	}

	/**
	 * Atualiza a página de usuarios
	 */
	public void updatePage(DataScrollerEvent e) {
		int index = ((UIDatascroller) e.getComponent()).getPageIndex();
		Page pagina = new Page(getRows(), index);
		carregarLista(pagina);
	}

	public String filtrar() {
		try {
			PagedResult<Usuario> listapg = usuarioBC.filtrar((String) txtFiltro
					.getValue(), new Page(getRows(), 1));
			if (listapg != null) {
				this.listaUsuario.bind(listapg);
			} else {
				addMessage(WarningMessage.USUARIO_NAO_ENCONTRADO);
			}
			usuario = new Usuario();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return AliasNavigationRule.USUARIO_LISTAR;
	}

	/**
	 * Atualiza a lista de usuario realizando consulta no banco com paginação
	 */
	public void carregarLista(Page page) {
		this.listaUsuario.bind(usuarioBC.listar(page));
	}

	/**
	 * Atualiza a lista de usuario passando sempre a primeira página
	 */
	public void carregarLista() {
		carregarLista(new Page(getRows(), 1));
	}

	/**
	 * Utiliza o contexto de mensagem para verificar se o usuário possui a role
	 * de administrador
	 * 
	 * @return true se for adminstrador, caso contrário false
	 */
	public boolean getIsAdministrador() {
		WebSecurityContext ctx = WebSecurityContext.getInstance();		
		return ctx.isUserInRole(AliasRole.ROLE_ADMINISTRADOR);
	}

	/**
	 * Informa se o usuário corrente tem o perfil de professor
	 * 
	 * @return
	 */
	public boolean getIsProfessor() {
		WebSecurityContext ctx = WebSecurityContext.getInstance();
		return ctx.isUserInRole(AliasRole.ROLE_PROFESSOR);
	}

	/**
	 * Informa se o usuário corrente temo perfil de usuario
	 * 
	 * @return
	 */
	public boolean getIsAluno() {
		WebSecurityContext ctx = WebSecurityContext.getInstance();
		return ctx.isUserInRole(AliasRole.ROLE_ALUNO);
	}

	// NAVEGAÇÃO
	public String preExcluir() {
		usuario = usuarioBC.buscar(usuario);
		return AliasNavigationRule.USUARIO_VISUALIZAR;
	}/* @fwk-methods-end */

	/**
	 * Cancela operação e volta a listagem de usuarios
	 * 
	 * @return
	 */
	public String cancelar() {
		carregarLista();
		return AliasNavigationRule.USUARIO_LISTAR;
	}

	public HtmlInputText getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(HtmlInputText txtFiltro) {
		this.txtFiltro = txtFiltro;
	}

	public PapelUsuario getDetalhepapel() {
		return detalhepapel;
	}

	public void setDetalhepapel(PapelUsuario detalhepapel) {
		this.detalhepapel = detalhepapel;
	}

	/**
	 * Modifica a pele da aplicação e salva base de dados
	 */
	public void alterarSkin() {
		SkinManager.getInstance().changeSkin(ManagedBeanUtil.getResponse(),
				usuario.getSkin());
		// usuarioBC.alterar(usuario); //TODO Aguarda conclusão do LoginModulo
	}

	/*--------------------------------- PAPEIS ---------------------------------*/

	public String incluirPapel() {
		try {
			usuario = usuarioBC.inserirPapel(usuario, detalhepapel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		detalhepapel = new PapelUsuario();
		return AliasNavigationRule.USUARIO_EDITAR;
	}

	public String alterarPapel() {
		try {
			usuario = usuarioBC.alterarPapel(usuario, detalhepapel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		detalhepapel = new PapelUsuario();
		return AliasNavigationRule.USUARIO_EDITAR;
	}

	public String removerPapel() {
		try {
			usuario = usuarioBC.removerPapel(usuario, detalhepapel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		detalhepapel = new PapelUsuario();
		return AliasNavigationRule.USUARIO_EDITAR;
	}

}// End of class
