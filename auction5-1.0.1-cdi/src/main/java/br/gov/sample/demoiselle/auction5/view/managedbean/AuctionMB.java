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

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.business.IAuctionBC;
import br.gov.sample.demoiselle.auction5.constant.AliasNavigationRule;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
public class AuctionMB extends AbstractManagedBean implements AliasNavigationRule {

	@Injection
	private IAuctionBC auctionBC;
	
	private Auction auction;
	private Category category;

	private List<Category> listCategories;
	private List<Auction> listAuctions;

	public AuctionMB() {
		try {
			reloadCategories();
			if (listCategories != null) {
				category = (Category) listCategories.get(0);
				listAuctions = auctionBC.listOpenAuctionsByCategory(category);
			}
		} catch (ApplicationRuntimeException e) {
			messageContext.addMessage(e.getObjectMessage());
		}
	}

	private void reloadCategories() {
		listCategories = auctionBC.listAvailableCategories();
	}

	public List<SelectItem> getListCategories() {
		
		if (listCategories == null) {
			reloadCategories();
		}
	
		List<SelectItem> items = new ArrayList<SelectItem>(listCategories.size());
		for (Category cat : listCategories) {
			items.add(new SelectItem(cat.getId(), cat.getName()));
		}
		
		return items;
	}

	public List<Auction> getListAuctions() {
		return this.listAuctions;
	}

	public void setListAuctions(List<Auction> listAuctions) {
		this.listAuctions = listAuctions;
	}

	public void actionLoadListItemsByCategory(ValueChangeEvent event) {
		this.category = new Category();
		this.category.setId(Short.valueOf(event.getNewValue().toString()));
		this.updateData();
	}

	public String listOpenAuctions() {
		updateData();
		return ALIAS_AUCTION;
	}

	public void updateData() {
		if (this.category != null) {
			try {
				listAuctions = auctionBC.listOpenAuctionsByCategory(this.category);
				for (Auction auction : listAuctions) {
					// avoid Hibernate lazy exception
					auction.getBids().size();
				}
			} catch (ApplicationRuntimeException e) {
				messageContext.addMessage(e.getObjectMessage());
			}
		} else {
			listAuctions = null;
		}
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// TODO: ???
	public List<Auction> getList() {
		return null;
	}
	
}
