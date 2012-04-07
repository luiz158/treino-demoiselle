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

import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
import br.gov.sample.demoiselle.auction5.constant.AliasRole;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
public class UserMB extends AbstractManagedBean {

	/**
	 * Returns the current user name.
	 * 
	 * @return the user principal name
	 */
	public String getUserName() {
		return ContextLocator.getInstance().getSecurityContext()
				.getUserPrincipal().getName();
	}

	/**
	 * Returns true if the user is logged in.
	 * 
	 * @return a boolean
	 */
	public boolean getIsLoggedIn() {
		return (ContextLocator.getInstance().getSecurityContext()
				.getUserPrincipal() != null);
	}

	/**
	 * Returns true if it is a regular user.
	 * 
	 * @return a boolean
	 */
	public boolean getIsRegularUser() {
		return ContextLocator.getInstance().getSecurityContext().isUserInRole(
				AliasRole.ROLE_USER);
	}

	/**
	 * Returns true if it is an administrator user.
	 * 
	 * @return a boolean
	 */
	public boolean getIsAdminUser() {
		return ContextLocator.getInstance().getSecurityContext().isUserInRole(
				AliasRole.ROLE_ADMIN);
	}

}
