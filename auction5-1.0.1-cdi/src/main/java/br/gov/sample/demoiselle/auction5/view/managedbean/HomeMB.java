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


import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.business.IAuctionBC;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
public class HomeMB extends AbstractManagedBean {

	@Injection
	private IAuctionBC auctionBC;

	private List<Auction> listNewest;
	private List<Auction> listMostOffered;
	private List<Auction> listEndingSoon;
	private List<Auction> listCheapestPrice;

	public List<Auction> getListNewest() {
		if (listNewest == null) {
			listNewest = auctionBC.listNewestAuctions();
			refreshAuctionsList(listNewest);
		}
		return listNewest;
	}

	public void setListNewest(List<Auction> listNewest) {
		this.listNewest = listNewest;
	}

	public List<Auction> getListMostOffered() {
		if (listMostOffered == null) {
			listMostOffered = auctionBC.listMostOfferedAuctions();
			refreshAuctionsList(listMostOffered);
		}
		return listMostOffered;
	}

	public void setListMostOffered(List<Auction> listMostOffered) {
		this.listMostOffered = listMostOffered;
	}

	public List<Auction> getListEndingSoon() {
		if (listEndingSoon == null) {
			listEndingSoon = auctionBC.listEndingSoonAuctions();
			refreshAuctionsList(listEndingSoon);
		}
		return listEndingSoon;
	}

	public void setListEndingSoon(List<Auction> listEndingSoon) {
		this.listEndingSoon = listEndingSoon;
	}

	public List<Auction> getListCheapestPrice() {
		if (listCheapestPrice == null) {
			listCheapestPrice = auctionBC.listCheapestPriceAuctions();
			refreshAuctionsList(listCheapestPrice);
		}
		return listCheapestPrice;
	}

	public void setListCheapestPrice(List<Auction> listCheapestPrice) {
		this.listCheapestPrice = listCheapestPrice;
	}

	private void refreshAuctionsList(List<Auction> listAuction) {
		if (listAuction != null && !listAuction.isEmpty()) {
			for (Auction auction : listAuction) {
				// avoid Hibernate lazy exception
				auction.getBids().size();
			}
		}
	}
	
}
