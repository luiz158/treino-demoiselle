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
package br.gov.sample.demoiselle.auction5.constant;

/**
 * @author CETEC/CTJEE
 */
public interface AliasNavigationRule {
	
	public String ALIAS_STAY = "";
	
	public String ALIAS_BID = "bid";
	public String ALIAS_BUY = "buy";
	
	public String ALIAS_AUCTION = "auction";
	public String ALIAS_ORDER   = "order";
	
	public String ALIAS_LIST_CATEGORY = "list_category";
	public String ALIAS_EDIT_CATEGORY = "edit_category";
	public String ALIAS_VIEW_CATEGORY = "view_category";
	public String ALIAS_LIST_ITEM = "list_item";
	public String ALIAS_EDIT_ITEM = "edit_item";
	public String ALIAS_VIEW_ITEM = "view_item";
	
}