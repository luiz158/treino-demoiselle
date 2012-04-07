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
package br.gov.sample.demoiselle.auction5.persistence.dao.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.gov.framework.demoiselle.core.layer.IFacade;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.auction5.bean.Order;
import br.gov.sample.demoiselle.auction5.persistence.dao.IOrderDAO;

/**
 * @author CETEC/CTJEE
 */
public class OrderDAOTest implements IFacade {

	private static Logger log = Logger.getLogger(OrderDAOTest.class);

	@Injection
	private IOrderDAO dao;

	@Before
	public void setUp() throws Exception {
		log.debug("OrderDAOTest.setUp()");
		WebTransactionContext.getInstance().init();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("OrderDAOTest.tearDown()");
		WebTransactionContext.getInstance().end();
	}

	@Test
	public void testFindByIdLong() {
		log.debug("OrderDAOTest.testFindByIdLong()");
		
		//TODO implement this...!
		//fail("Not yet implemented");
	}

	@Test
	public void testListOrdersByLogin() {
		log.debug("OrderDAOTest.testListOrdersByLogin()");
		
		final String login = "frank";
		
		List<Order> list = dao.listOrdersByLogin(login);
		
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() > 0);
		
		for (Order order : list) {
			log.debug(order);
			assertNotNull(order);
			assertEquals(login, order.getLogin());
		}
	}

}
