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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.persistence.platform.database.PostgreSQLPlatform;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.gov.framework.demoiselle.core.layer.IFacade;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.bean.Item;
import br.gov.sample.demoiselle.auction5.bean.Status;
import br.gov.sample.demoiselle.auction5.persistence.dao.IAuctionDAO;

/**
 * @author CETEC/CTJEE
 */
public class AuctionDAOTest implements IFacade {

	private static Logger log = Logger.getLogger(AuctionDAOTest.class);
	
	@Injection
	private IAuctionDAO dao;

	public AuctionDAOTest() {
		// Warning: the code below is needed when using TopLink/EclipseLink + PostgreSQL + native query
		// FIXME: remove the line below when solving this bug on the provider
		PostgreSQLPlatform.setShouldIgnoreCaseOnFieldComparisons(true);
	}

	@Before
	public void setUp() throws Exception {
		log.debug("AuctionDAOTest.setUp()");
		WebTransactionContext.getInstance().init();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("AuctionDAOTest.tearDown()");
		WebTransactionContext.getInstance().end();
	}

	@Test
	public void testListOpenAuctionsByCategory() {
		log.debug("AuctionDAOTest.testListOpenAuctionsByCategory()");
		
		short[] codes = {101, 102, 201, 202, 203};
		
		for (short code : codes) {
			
			Category category = new Category();
			category.setId((short) code);
			
			List<Auction> list = dao.listOpenAuctionsByCategory(category);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertTrue(list.size() > 0);
			
			for (Auction auction : list) {
				log.debug(auction);
				assertNotNull(auction);
				assertEquals(Status.OPEN, auction.getStatus());
				assertEquals(category.getId(), auction.getItem().getCategory().getId());
			}
		}
		
		Category category = new Category();
		category.setId((short) 999);
		
		List<Auction> list = dao.listOpenAuctionsByCategory(category);
		assertNotNull(list);
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void testListNewestAuctions() {
		log.debug("AuctionDAOTest.testListNewestAuctions()");
		
		List<Auction> list = dao.listNewestAuctions(20);
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() > 0);
		
		Date lastDate = null;
		for (Auction auction : list) {
			log.debug(auction);
			assertNotNull(auction);
			assertEquals(Status.OPEN, auction.getStatus());
			assertTrue(lastDate == null || auction.getCreation().compareTo(lastDate) <= 0);
			lastDate = auction.getCreation();
		}
	}

	@Test
	public void testListMostOfferedAuctions() {
		log.debug("AuctionDAOTest.testListMostOfferedAuctions()");
		
		List<Auction> list = dao.listMostOfferedAuctions(20);
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() > 0);
		
		for (Auction auction : list) {
			log.debug(auction);
			assertNotNull(auction);
			assertEquals(Status.OPEN, auction.getStatus());
			int currentBidsCount = auction.getBids().size();
			assertTrue(currentBidsCount > 0);
		}
	}

	@Test
	public void testListEndingSoonAuctions() {
		log.debug("AuctionDAOTest.testListEndingSoonAuctions()");
		
		int threeDays = 3 * 24 * 3600;
		
		List<Auction> list = dao.listEndingSoonAuctions(threeDays, 10);
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() > 0);
		
		Date lastDeadline = null;
		for (Auction auction : list) {
			log.debug(auction);
			assertNotNull(auction);
			assertEquals(Status.OPEN, auction.getStatus());
			assertTrue(lastDeadline == null || auction.getDeadline().compareTo(lastDeadline) >= 0);
			lastDeadline = auction.getDeadline();
		}
	}

	//@Test
	public void testListCheapestPriceAuctions() {
		log.debug("AuctionDAOTest.testListCheapestPriceAuctions()");
		
		List<Auction> list = dao.listCheapestPriceAuctions(10);
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() > 0);
		
		Double lastPrice = null;
		for (Auction auction : list) {
			log.debug(auction);
			assertNotNull(auction);
			assertEquals(Status.OPEN, auction.getStatus());
			double currentPrice = auction.getBestBidOrStartingPrice();
			assertTrue(lastPrice == null || currentPrice >= lastPrice);
			lastPrice = currentPrice;
		}
	}

	@Test
	public void testListOpenAuctionsByItem() {
		log.debug("AuctionDAOTest.testListOpenAuctionsByItem()");
		
		for (int i = 0; i < 10; i++) {
			
			Item item = new Item();
			item.setId(i + 1);
			
			List<Auction> list = dao.listOpenAuctionsByItem(item);
			assertNotNull(list);
			
			for (Auction auction : list) {
				log.debug(auction);
				assertNotNull(auction);
				assertEquals(Status.OPEN, auction.getStatus());
				assertEquals(item.getId(), auction.getItem().getId());
			}
		}
		
		Item item = new Item();
		item.setId(0);
		
		List<Auction> list = dao.listOpenAuctionsByItem(item);
		assertNotNull(list);
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void testListOpenEndedAuctions() {
		log.debug("AuctionDAOTest.testListOpenEndedAuctions()");
		
		long now = Calendar.getInstance().getTime().getTime();
		long oneDay = 24 * 3600 * 1000;
		Date timestamp = new Date(now + 15 * oneDay);
		
		List<Auction> list = dao.listOpenEndedAuctions(timestamp);
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() > 0);
		
		for (Auction auction : list) {
			log.debug(auction);
			assertNotNull(auction);
			assertEquals(Status.OPEN, auction.getStatus());
			assertNotNull(auction.getDeadline());
			assertTrue(auction.getDeadline().compareTo(timestamp) <= 0);
		}
	}
	
}
