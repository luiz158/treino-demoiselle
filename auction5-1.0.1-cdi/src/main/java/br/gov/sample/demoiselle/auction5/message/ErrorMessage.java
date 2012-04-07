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
package br.gov.sample.demoiselle.auction5.message;

import java.util.Locale;
import br.gov.framework.demoiselle.core.message.Severity;
import br.gov.framework.demoiselle.core.message.IMessage;

/**
 * @author CETEC/CTJEE
 */
public enum ErrorMessage implements IMessage {
	
	ADM_CATEGORY_DELETE_NOK_CHILDREN("Category cannot be deleted while being parent of other categories."),
	ADM_CATEGORY_DELETE_NOK_ITEMS("Category cannot be deleted because it has dependent items."),
	ADM_CATEGORY_DELETE_NOK("Category could not be deleted."),
	ADM_CATEGORY_SAVE_NOK("Category could not be saved."),

	ADM_ITEM_SAVE_NOK("Item cannot be saved"),
	ADM_ITEM_DELETED_NOK("Item cannot be deleted"), 
	ADM_ITEM_LIST_LOAD_NOK("Fail loading list of items"),
	ADM_ITEM_DELETE_NOK_THERE_ARE_AUCTIONS("There are Auctions for this Item. This Item can't be deleted"),	
	ADM_AUCTION_SAVE_NOK("Auction cannot be saved"),
	ADM_AUCTION_DELETED_NOK("Auction cannot be deleted"),
			
	BUY_ITEM_NOK("Item can't be ordered"),
	ORDER_FIND_BY_ID("Order can't be found"),
	ORDER_LIST_NOK("Orders can't be loaded"),
	LIST_CATEGORY_NOK("Category list can't be loaded"),
	AUCTION_ITEM_LIST_CATEGORY_NOK("Items by category can't be loaded"),
	AUCTION_LIST_LAST_BID_NOK("List last bid can't be loaded"),
	AUCTION_LIST_AUCTIONS_BY_ITEM_NOK("List auctions by item can't be loaded"),
	AUCTION_LIST_AUCTIONS_BY_CATEGORY_NOK("List auctions by category can't be loaded"),
	AUCTION_NULL("Auction is null"),
	AUCTION_STATUS_CLOSED("Auction is closed"),
	AUCTION_NO_ENABLED_FOR_BID("Auction is not enabled for bid"),
	AUCTION_AMOUNT_BID_LESSER_BEST_BID("Bid value must be greater than best bid"),
	AUCTION_AMOUNT_BID_LESSER_STARTING_PRICE("Bid value must be greater than starting price"),
	AUCTION_BID_ITEM_NOK("Bid could not be placed"),
	AUCTION_FIND_BY_ID_NOK("Auction can't be found"),
	AUCTION_DELETE_NOK_THERE_ARE_BIDS("There are Bids for this Auction. This Auction can't be deleted"),
	AUCTION_UPDATE_NOK_THERE_ARE_BIDS("There are Bids for this Auction. This Auction can't be updated"),
	ADM_ITEM_VALIDATE_CATEGORY_NOK("Category is required"), 
	ADM_ITEM_VALIDATE_DESCRIPTION_NOK("Description is required"), 
	
	VALIDATION_ID_REQUIRED("Id is required for this operation"), 
	OBJECT_NOT_FOUND("This object is null"),
	USER_NOT_AUTHORIZED("User is not authorized to perform this operation");

	private String label;

	private ErrorMessage(String label) {
		this.label = label;
	}

	public String getKey() {
		return this.toString();
	}

	public String getLabel() {
		return label;
	}

	public Locale getLocale() {
		return Locale.US;
	}
	
	public Severity getSeverity() {
		return Severity.ERROR;
	}

	public String getResourceName() {
		return "errormessage";
	}
	
}