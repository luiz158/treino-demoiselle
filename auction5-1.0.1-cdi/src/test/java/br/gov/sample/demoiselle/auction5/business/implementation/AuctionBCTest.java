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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.IFacade;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Bid;
import br.gov.sample.demoiselle.auction5.bean.BidAudit;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.business.IAuctionBC;

/**
 * @author CETEC/CTJEE
 */
public class AuctionBCTest implements IFacade {
	
	private static Logger log = Logger.getLogger(AuctionBCTest.class);
	
	@Injection
	private IAuctionBC auctionBC;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		log.debug("AuctionBCTest.setUp()");
		WebTransactionContext.getInstance().init();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("AuctionBCTest.tearDown()");
		WebTransactionContext.getInstance().end();
	}

	@Test
	public void testListAvailableCategories() {
		log.debug("AuctionBCTest.testListAvailableCategories()");
		
		List<Category> list = auctionBC.listAvailableCategories();
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() > 0);
		
		for (Category category : list) {
			assertNotNull(category);
		}
	}

	@Test
	public void testPlaceBid() {
		log.debug("AuctionBCTest.testPlaceBid()");
		
		final String[] users = {"john", "doe", "fooks", "bart", "jakarta"};
		
		List<Category> listCategories = auctionBC.listAvailableCategories();
		for (Category category : listCategories) {
			
			List<Auction> listAuctions = auctionBC.listOpenAuctionsByCategory(category);
			for (Auction auction : listAuctions) {
				
				String login = users[(int) (Math.random() * users.length)];
				//String login = users[0];
				//for (String login : users) {
					
					//auction = auctionBC.findAuctionById(auction.getId());
				
					Double amount = null;
					Bid bestBid = auction.getBestBid();
					if (bestBid != null) {
						amount = getRandomAmount(bestBid.getAmount());
					} else {
						amount = getRandomAmount(auction.getStartingPrice());
					}
					
					WebTransactionContext.getInstance().init();
					Bid newBid = null;
					try {
						newBid = auctionBC.placeBid(auction, amount, login, new BidAudit());
					} catch (ApplicationRuntimeException e) {
						e.printStackTrace();
					}
					log.debug(newBid);
					WebTransactionContext.getInstance().end();
				//}
			}
		}
	}

	private double getRandomAmount(double ceil) {
		int value = (int) (ceil * (1 + 2 * Math.random() / 10) * 10);
		return value / 10.0;
	}

	@Test
	public void testListLastBidsForAuction() {
		//fail("Not yet implemented");
	}

}
