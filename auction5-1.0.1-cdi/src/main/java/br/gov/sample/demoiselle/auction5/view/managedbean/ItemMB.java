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


import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.bean.Item;
import br.gov.sample.demoiselle.auction5.bean.Status;
import br.gov.sample.demoiselle.auction5.business.IAuctionBC;
import br.gov.sample.demoiselle.auction5.business.IItemBC;
import br.gov.sample.demoiselle.auction5.constant.AliasNavigationRule;
import br.gov.sample.demoiselle.auction5.message.InfoMessage;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
public class ItemMB extends AbstractManagedBean implements AliasNavigationRule {
	
	@Injection
	protected IItemBC itemBC;
	
	@Injection
	protected IAuctionBC auctionBC;

	private Item item = new Item();
	private List<Item> listItem = new ArrayList<Item>();
	
	private List<Category> listCategory = null;
	
	private Auction auction;
	private List<Auction> listAuctionsByItem;	

	public String save() {// save
		try {
			item = itemBC.save(item);
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ADM_ITEM_SAVE_OK);
		} catch (ApplicationRuntimeException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
			return AliasNavigationRule.ALIAS_STAY;
		}
		return list();
	}

	public String edit() {
		return AliasNavigationRule.ALIAS_EDIT_ITEM;
	}

	/**
	 * Navegate to a View of Item for confirm the exclusion.
	 * 
	 * @return String
	 */
	public String delete() {
		return AliasNavigationRule.ALIAS_VIEW_ITEM;
	}

	public String deleteConfirmed() {
		try {
			Category category = item.getCategory();
			itemBC.delete(item);
			item = new Item();
			item.setCategory(category);
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ADM_ITEM_DELETED_OK);
		} catch (ApplicationRuntimeException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
			return AliasNavigationRule.ALIAS_STAY;
		}
		return list();
	}

	public String list() {
		try {
			loadItemList();
		} catch (ApplicationRuntimeException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
			return AliasNavigationRule.ALIAS_STAY;
		}		
		return AliasNavigationRule.ALIAS_LIST_ITEM;
	}
	
	private void loadItemList(){
		if(item.getCategory()==null || item.getCategory().getId()==null){
			item.setCategory(getListCategory().get(0));
		}
		try {
			this.listItem = itemBC.filterByCategory(item);
		} catch (ApplicationRuntimeException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
		}	
		if(listItem.isEmpty()){
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ADM_ITEM_LIST_LOAD_EMPITY);
		}
	}

	
	public String cancel(){
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ADM_ITEM_OPERATION_CANCELED);
		return AliasNavigationRule.ALIAS_LIST_ITEM;
	}
	
	public String newItem(){
		Category category = item.getCategory();
		item = new Item();
		item.setCategory(category);
		this.auction = new Auction();
		return AliasNavigationRule.ALIAS_EDIT_ITEM;
	}
	
	public String saveAuction(){
		this.auction.setItem(this.item);
		try{
			this.auction.setStatus(Status.OPEN);			
			this.auctionBC.save(this.auction);
			this.auction = new Auction();
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ADM_AUCTION_SAVE_OK);
		}catch(ApplicationRuntimeException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
		}
		return "";
	}
	
	public String editAuction(){		
		return "";
	}
	
	public String deleteAuction(){
		try{	
			this.auctionBC.delete(this.auction);
			this.auction = new Auction();
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ADM_AUCTION_DELETED_OK);
		}catch(ApplicationRuntimeException e) {
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
		}
		return "";
	}
	
	public ItemMB() {
		this.listItem = new ArrayList<Item>();
		this.item = new Item();
		this.auction = new Auction();
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item arg0) {
		this.item = arg0;
	}

	public List<Item> getListItem() {
		return this.listItem;
	}

	public void setListItem(List<Item> arg0) {
		this.listItem = arg0;
	}

	public List<Category> getListCategory() {
		try{
			if(listCategory==null)
				listCategory = itemBC.listAvailableCategories();
		}catch(ApplicationRuntimeException e){
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
		}
		return listCategory;
	} 
	
	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public List<Auction> getListAuctionsByItem() {
		try{			
			this.listAuctionsByItem = this.auctionBC.listOpenAuctionsByItem(item);
		}catch(ApplicationRuntimeException e){
			ContextLocator.getInstance().getMessageContext().addMessage(e.getObjectMessage());
		}
		return this.listAuctionsByItem;
	}

	public boolean isItemAdded(){		
		if (this.item.getId()==null)
			return false;
		else
			return true;
	}
	
	public void setListAuctionsByItem(List<Auction> listAuctionsByItem) {
		this.listAuctionsByItem = listAuctionsByItem;
	}
	
}