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

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sun.rmi.transport.Transport;
import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Nota;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.business.IAlunoBC;
import br.gov.demoiselle.escola.business.INotaBC;
import br.gov.demoiselle.escola.constant.AliasNavigationRule;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public class NotaMB extends AbstractManagedBean {
	
	@Inject
	private INotaBC notaBC;
	
	@Inject
	private IAlunoBC alunoBC;
	
	private Nota nota;
	
	private PagedResultDataModel<Nota> listaNota;
	
	public NotaMB() {		
		nota = new Nota();		
		listaNota = new PagedResultDataModel<Nota>();
	}

	public String inserir() {
		notaBC.inserir(nota);			
		carregarLista();		
		return AliasNavigationRule.NOTA_LISTAR;		
	}	
	
	public String enviarEmail(){
		Session mailSession = null;
		Context initCtx;
		try {
			initCtx = new InitialContext();
			mailSession = (javax.mail.Session)initCtx.lookup("java:/Mail");
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject("teste");
			message.setContent("corpo de teste", "text/plain");
			message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("vanderson.silva@serpro.gov.br")});			
			//MailClient mailClient = new MailClient();
			//mailClient.send(message);
			Transport.send(message);
		} catch (NamingException e) {
			e.printStackTrace();
			//throw new ApplicationRuntimeException(ErrorMessage.NOTA_001_01, e);
		} catch (MessagingException e) {
			e.printStackTrace();
			//throw new ApplicationRuntimeException(ErrorMessage.NOTA_001_01, e);
		}
		return "";
	}
	
	public String excluir() {
		notaBC.remover(nota);
		carregarLista();
		return AliasNavigationRule.NOTA_LISTAR;
	}
	
	public String alterar() {
		notaBC.alterar(nota);
		carregarLista();
		return AliasNavigationRule.NOTA_LISTAR;
	}
	
	public String mostrarNota(){
		Usuario usuario = (Usuario) ManagedBeanUtil.getSession().getAttribute("user");			
		Aluno aluno = alunoBC.buscarPorUsuario(usuario);
		Page pg = new Page(getRows(), 1);		
		listaNota.bind(notaBC.listar(aluno), pg);
		return AliasNavigationRule.NOTA_LISTAR;
	}
	
	public String listar() {
		carregarLista();		
		return AliasNavigationRule.NOTA_LISTAR;
	}	
	
	/**
	 *  Atualiza a lista de nota realizando consulta no banco 
	 *  com paginação
	 */
	public void carregarLista(Page page) {
		try{
			listaNota = new PagedResultDataModel<Nota>();
			PagedResult<Nota> listaPg = notaBC.listar(page);
			if (listaPg != null){
				listaNota.bind(listaPg);
			}
		}catch(ApplicationRuntimeException e){
			ManagedBeanUtil.addMessage(e.getObjectMessage(), e);
		}		
	}
	
	/**
	 *  Atualiza a lista de aluno passando sempre a primeira página
	 */
	public void carregarLista() {
		carregarLista(new Page(getRows(), 1));
	}
	
	public void updatePage(DataScrollerEvent e){
		int index = ((UIDatascroller) e.getComponent()).getPageIndex();
		Page pagina = new Page(getRows(), index);
		carregarLista(pagina);	
	}
	
	/*--------------------------------- NAVEGAÇÃO ---------------------------------*/
	
	public String preInserir() {		
		nota = new Nota();		
		return AliasNavigationRule.NOTA_EDITAR;
	}
	
	public String preAlterar() {		
		nota = notaBC.buscar(nota);			
		return AliasNavigationRule.NOTA_EDITAR;
	}	
	
	public String preExcluir() {
		nota = notaBC.buscar(nota);
		return AliasNavigationRule.NOTA_VISUALIZAR;
	}	
	
	public String cancelar() {
		nota = new Nota();		
		return AliasNavigationRule.NOTA_LISTAR;
	}	
	
	public String menu(){	
		return AliasNavigationRule.ESCOLA_MENU;
	}

	/*--------------------------------- GETs SETs ---------------------------------*/
	
	public Nota getNota() {
		return this.nota;
	}

	public void setNota(Nota arg0) {
		this.nota = arg0;
	}
	
	public PagedResultDataModel<Nota> getListaNota() {
		return this.listaNota;
	}

}//End of class