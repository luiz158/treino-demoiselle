/*
 * Demoiselle Framework
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 *
 * Demoiselle Framework is an open source Java EE library designed to accelerate
 * the development of transactional database Web applications.
 *
 * Demoiselle Framework is released under the terms of the LGPL license 3
 * http://www.gnu.org/licenses/lgpl.html  LGPL License 3
 *
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License 3 as published by
 * the Free Software Foundation.
 *
 * Demoiselle Framework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Demoiselle Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.sample.demoiselle.auction5.view.managedbean;

import java.util.ArrayList;
import java.util.List;


import br.gov.component.demoiselle.authorization.AuthorizationException;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.core.security.ISecurityContext;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Order;
import br.gov.sample.demoiselle.auction5.business.IOrderBC;
import br.gov.sample.demoiselle.auction5.constant.AliasNavigationRule;
import br.gov.sample.demoiselle.auction5.message.ErrorMessage;
import br.gov.sample.demoiselle.auction5.message.InfoMessage;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
public class OrderMB extends AbstractManagedBean implements AliasNavigationRule {

	@Injection
	private IOrderBC orderBC;
	
	private Order order;
	private Auction auction;
	private String userName;

	private List<Order> listOrders;
	
	public OrderMB() {
		// as the bean is in session scope, we'll retrieve the user once
		ISecurityContext ctx = ContextLocator.getInstance().getSecurityContext();
		this.userName = ctx.getUserPrincipal().getName();		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	public String getUserName() {
		return userName;
	}

	public List<Order> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<Order> listOrders) {
		this.listOrders = listOrders;
	}

	/**
	 * Action fired when user clicks a "Buy It Now" link.
	 * 
	 * @return String
	 */
	public String actionPreOrder() {
		order = null;
		return ALIAS_ORDER;
	}

	/**
	 * Action fired when the user finally orders the item, after clicking "Buy It" link.
	 * 
	 * @return	a String
	 */
	public String actionOrder() {
		try {
			
			order = orderBC.buyItem(this.auction, this.userName);
			messageContext.addMessage(InfoMessage.BUY_ITEM_OK);
			
		} catch (ApplicationRuntimeException e) {
			messageContext.addMessage(e.getObjectMessage());
		} catch (AuthorizationException e) {
			messageContext.addMessage(ErrorMessage.USER_NOT_AUTHORIZED);
		}
		return ALIAS_STAY;
	}

	// TODO: ???
	public String listUserOrders() {
		return "list_order";
	}
	
	// TODO: ???
	public List<Order> getListUserOrders() {
		
		List<Order> listOrder = new ArrayList<Order>();
		
		try {
			listOrder = orderBC.listOrdersByLogin(userName);
		} catch (ApplicationRuntimeException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
		} catch (AuthorizationException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(ErrorMessage.USER_NOT_AUTHORIZED);
		}
		
		return listOrder;
	}
	
	// TODO: ???
	public List<Order> getList() {
		return null;
	}
	
}
