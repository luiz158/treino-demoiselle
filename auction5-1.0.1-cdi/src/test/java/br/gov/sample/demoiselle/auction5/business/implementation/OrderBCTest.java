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
package br.gov.sample.demoiselle.auction5.business.implementation;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.framework.demoiselle.core.layer.IFacade;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.bean.Mode;
import br.gov.sample.demoiselle.auction5.bean.Order;
import br.gov.sample.demoiselle.auction5.business.IAuctionBC;
import br.gov.sample.demoiselle.auction5.business.IOrderBC;

/**
 * @author CETEC/CTJEE
 */
public class OrderBCTest implements IFacade {

	private static Logger log = Logger.getLogger(OrderBCTest.class);

	@Injection
	private IAuctionBC auctionBC;

	@Injection
	private IOrderBC orderBC;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		log.debug("OrderBCTest.setUp()");
		WebTransactionContext.getInstance().init();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("OrderBCTest.tearDown()");
		WebTransactionContext.getInstance().end();
	}

	@Test
	public void testBuyItem() {
		log.debug("OrderBCTest.testBuyItem()");
		
		final String[] users = {"john", "doe", "fooks", "bart", "jakarta"};
		
		List<Category> listCategories = auctionBC.listAvailableCategories();
		for (Category category : listCategories) {
			
			List<Auction> listAuctions = auctionBC.listOpenAuctionsByCategory(category);
			for (Auction auction : listAuctions) {
				
				if (!auction.getMode().equals(Mode.AUCTION)) {
					
					String login = users[(int) (Math.random() * users.length)];
					
					//WebTransactionContext.getInstance().init();
					Order order = orderBC.buyItem(auction, login);
					//WebTransactionContext.getInstance().end();
					
					assertNotNull(order);
				}
			}
		}
	}

}
