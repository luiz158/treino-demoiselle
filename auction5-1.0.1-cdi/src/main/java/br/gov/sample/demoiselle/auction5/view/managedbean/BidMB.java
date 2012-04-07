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

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


import br.gov.component.demoiselle.authorization.AuthorizationException;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.core.security.ISecurityContext;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Bid;
import br.gov.sample.demoiselle.auction5.bean.BidAudit;
import br.gov.sample.demoiselle.auction5.business.IAuctionBC;
import br.gov.sample.demoiselle.auction5.constant.AliasNavigationRule;
import br.gov.sample.demoiselle.auction5.message.ErrorMessage;
import br.gov.sample.demoiselle.auction5.message.InfoMessage;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
public class BidMB extends AbstractManagedBean implements AliasNavigationRule {

	@Injection
	private IAuctionBC auctionBC;

	private Auction auction;
	private String userName;
	private Double amount;
	
	private List<Bid> listLastBids;

	private static final int BIDS_LIST_COUNT = 10;
	
	public BidMB() {
		try {
			
			// as the bean is in session scope, we'll retrieve the user once
			ISecurityContext ctx = ContextLocator.getInstance().getSecurityContext();
			this.userName = ctx.getUserPrincipal().getName();
			
		} catch (ApplicationRuntimeException e) {
			messageContext.addMessage(e.getObjectMessage());
		}
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<Bid> getListLastBids() {
		return listLastBids;
	}

	public void setListLastBids(List<Bid> listBids) {
		this.listLastBids = listBids;
	}

	private void reloadLastBidsList() {
		if (this.auction != null) {
			this.listLastBids = auctionBC.listLastBidsForAuction(this.auction, BIDS_LIST_COUNT);
		} else {
			this.listLastBids = null;
		}
	}
	
	/**
	 * Action fired when user clicks a "Place a Bid" link.
	 * 
	 * @return String
	 */
	public String actionPreBid() {
		
		this.amount = null;
		reloadLastBidsList();
		
		return ALIAS_BID;
	}
	
	/**
	 * Action fired when the user finally places a bid, after clicking "Place Bid" link.
	 * 
	 * @return	a String
	 */
	public String actionBid() {
		try {
			
			HttpServletRequest request = (HttpServletRequest)
				FacesContext.getCurrentInstance().getExternalContext().getRequest();
			BidAudit audit = createBidAudit(request);
			
			Bid bid = auctionBC.placeBid(this.auction, this.amount, this.userName, audit);
			messageContext.addMessage(InfoMessage.BID_ITEM_OK);
			
			this.auction.setBestBid(bid);
			this.amount = null;
			reloadLastBidsList();
			
		} catch (ApplicationRuntimeException e) {
			messageContext.addMessage(e.getObjectMessage());
		} catch (AuthorizationException e) {
			messageContext.addMessage(ErrorMessage.USER_NOT_AUTHORIZED);
		}
		return ALIAS_STAY;
	}

	/**
	 * Creates a bid auditing entity based on the given HTTP request.
	 * 
	 * @param request	an HttpServletRequest
	 * @return	a BidAudit
	 */
	private BidAudit createBidAudit(HttpServletRequest request) {
		
		BidAudit audit = new BidAudit();
		
		audit.setHost(request.getRemoteHost());
		audit.setUser(request.getRemoteUser());
		audit.setSession(request.getRequestedSessionId());
		audit.setEncoding(request.getCharacterEncoding());
		audit.setLocale(request.getLocale().toString());
		audit.setAgent(request.getHeader("user-agent"));
		
		return audit;
	}
	
}
