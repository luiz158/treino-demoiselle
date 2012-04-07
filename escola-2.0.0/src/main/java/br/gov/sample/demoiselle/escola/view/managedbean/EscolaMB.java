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

import static br.gov.framework.demoiselle.view.faces.util.ManagedBeanUtil.getRemoteIP;
import static br.gov.framework.demoiselle.view.faces.util.ManagedBeanUtil.getRequest;
import static br.gov.framework.demoiselle.view.faces.util.ManagedBeanUtil.getResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

import br.gov.component.demoiselle.jsf.skin.Skin;
import br.gov.component.demoiselle.jsf.skin.SkinManager;
import br.gov.framework.demoiselle.view.cookie.CookieManager;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.escola.config.EscolaConfig;
import br.gov.sample.demoiselle.escola.constant.AliasNavigationRule;

public class EscolaMB extends AbstractManagedBean {
	
	@Inject
	private Logger log;
	
	@Inject
	private EscolaConfig config;

	private final String CK_ULTIMO_LOGIN = "CK_ULTIMO_LOGIN";
	private String msgUltimoLogin;
	
	/**
	 * Ao iniciar a sessão do usuário um cookie é criado para gravar a data do último login realizado.
	 */
	public EscolaMB() {
		String agora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
		Cookie ck = CookieManager.getCookie(getRequest(), CK_ULTIMO_LOGIN);
		msgUltimoLogin = (ck == null) ? agora : ck.getValue();
		CookieManager.saveCookie(getResponse(), CK_ULTIMO_LOGIN, agora);
	}	
	
	/**
	 * Redireciona a página de menu
	 * @return
	 */
	public String menu() {
		return AliasNavigationRule.ESCOLA_MENU;
	}
	
	public void gerarRelatorio() {
		log.debug("relatorio");
//		WebReportUtil.invoke(getServletContext(), getRequest(), getResponse());
	}
	
	public String relatorio() {
		return AliasNavigationRule.ESCOLA_RELATORIO;
	}
		
	/**
	 * Redireciona a página de skin
	 * @return
	 */
	public String listarSkin() {
		return AliasNavigationRule.ESCOLA_SKIN;
	}	
	
	/**
	 * Retorna a lista das skins
	 * @return
	 */
	public Collection<Skin> getSkins() {
		return SkinManager.getInstance().getSkins();		
	}
	
	/**
	 * Faz o logout da aplicação
	 * @return
	 */
	public void sair() {		
		log.debug("sair");
		super.logout();
	}
	
	/**
	 * Retorna a mensagem de último login realizado
	 * @return
	 */
	public String getUltimoLogin() {
		return msgUltimoLogin + " na máquina: " + getRemoteIP();
	}
	
	public Collection<String> getPapeis() {
		return config.getPapeis();
	}
	
}