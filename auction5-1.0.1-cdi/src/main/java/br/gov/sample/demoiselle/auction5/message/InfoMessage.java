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

import br.gov.framework.demoiselle.core.message.IMessage;
import br.gov.framework.demoiselle.core.message.Severity;

/**
 * @author CETEC/CTJEE
 */
public enum InfoMessage implements IMessage {
	
	ADM_CATEGORY_CONFIRM_DELETE("Do you really want to delete this category?"),
	ADM_CATEGORY_SAVE_OK("Category was successfully saved."),
	ADM_CATEGORY_DELETE_OK("Category was successfully deleted."),
	
	ADM_ITEM_SAVE_OK("Item saved"),
	ADM_ITEM_DELETED_OK("Item deleted"), 
	ADM_ITEM_LIST_LOAD_OK("Showing list of items"), 
	ADM_ITEM_LIST_LOAD_EMPITY("No items found"), 
	ADM_ITEM_OPERATION_CANCELED("Operation canceled"),
	
	ADM_AUCTION_SAVE_OK("Auction saved"),
	ADM_AUCTION_DELETED_OK("Auction deleted"), 
	
	BUY_ITEM_OK("Item was successfully odered"),
	BID_ITEM_OK("Bid was successfully placed");
	
	private String label;

	private InfoMessage(String label) {
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
		return Severity.INFO;
	}

	public String getResourceName() {
		return "infomessage";
	}
	
}