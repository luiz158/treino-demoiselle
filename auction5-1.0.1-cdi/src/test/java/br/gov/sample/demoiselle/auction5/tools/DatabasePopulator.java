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
package br.gov.sample.demoiselle.auction5.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Bid;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.bean.Item;
import br.gov.sample.demoiselle.auction5.bean.Mode;
import br.gov.sample.demoiselle.auction5.bean.Order;
import br.gov.sample.demoiselle.auction5.bean.Status;

/**
 * This utility class provides a simple way to have TopLink populate the sample
 * data to the test database using JPA.
 * 
 * @author CETEC/CTJEE
 */
public class DatabasePopulator {

	private EntityManager em;

	public static void main(String args[]) {
		DatabasePopulator populator = new DatabasePopulator();
		populator.start();
	}

	/**
	 * Starting point for the tool.
	 */
	public void start() {

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("toplink.ddl-generation", "drop-and-create-tables");		// toplink
		properties.put("toplink.ddl-generation.output-mode", "database");
		properties.put("eclipselink.ddl-generation", "drop-and-create-tables");	// eclipselink
		properties.put("eclipselink.ddl-generation.output-mode", "database");
		properties.put("hibernate.hbm2ddl.auto", "create");						// hibernate

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				Constants.TEST_PERSISTENCE_UNIT_NAME, properties);
		em = emf.createEntityManager();

		populateAll();

		em.close();
		emf.close();
	}

	/**
	 * Begins a transaction.
	 */
	protected void begin() {
		em.getTransaction().begin();
	}

	/**
	 * Commits a transaction.
	 */
	protected void commit() {
		em.getTransaction().commit();
	}

	/**
	 * Populates all entities.
	 */
	private void populateAll() {

		begin();
		populateCategories();
		commit();

		begin();
		populateItems();
		populateAuctions();
		commit();
		
		populateBids();
		populateOrders();
	}

	/**
	 * Populates categories entities.
	 */
	private void populateCategories() {

		Category c1, c2;

		c1 = new Category(100, "Computers & Networking", null);
		em.persist(c1);

		em.persist(new Category(101, "Desktops", c1));
		em.persist(new Category(102, "Laptops", c1));

		c2 = new Category(200, "Electronics", null);
		em.persist(c2);

		em.persist(new Category(201, "DVD & Home Theater", c2));
		em.persist(new Category(202, "iPod & MP3 Players", c2));
		em.persist(new Category(203, "Televisions", c2));
	}

	/**
	 * Populates items entities.
	 */
	private void populateItems() {

		InputStreamReader input = new InputStreamReader(this.getClass()
				.getResourceAsStream("sample-items.csv"));
		BufferedReader reader = new BufferedReader(input);

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(";");

				Category category = new Category();
				category.setId(Short.parseShort(values[0]));
				String description = values[1];

				Item item = new Item();
				item.setCategory(category);
				item.setDescription(description);

				em.persist(item);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Populates auctions entities.
	 */
	@SuppressWarnings("unchecked")
	private void populateAuctions() {

		List<Item> list = em.createQuery("select i from Item i").getResultList();

		for (Item item : list) {

			Auction auction = new Auction();
			auction.setItem(item);
			auction.setStatus(Status.OPEN);
			auction.setCreation(Calendar.getInstance().getTime());

			Mode mode = Mode.values()[(item.getId() * 10) % 3];
			auction.setMode(mode);

			if (mode.equals(Mode.AUCTION) || mode.equals(Mode.BOTH)) {
				auction.setStartingPrice(getRandomPrice(5.0));
				auction.setReservePrice(getRandomPrice(500.0));
				auction.setDeadline(new Date(
						(long) (System.currentTimeMillis() + Math.random() * 2E9)));
			}

			if (mode.equals(Mode.SELLING) || mode.equals(Mode.BOTH)) {
				auction.setSellingPrice(getRandomPrice(1000.00));
			}

			em.persist(auction);
		}
	}

	/**
	 * Generates a random price based on the given ceil.
	 * 
	 * @param ceil
	 * @return	a double
	 */
	private double getRandomPrice(double ceil) {
		
		int value = (int) (Math.random() * 100 * ceil);
		
		return value / 10.0;
	}

	static final String[] users = {"john", "donald", "frank", "bart", "berta"};

	/**
	 * Populates bids entities.
	 */
	@SuppressWarnings("unchecked")
	private void populateBids() {
		
		final int MAX_BIDS_PER_AUCTION = 10;
		
		List<Category> listCategories = em.createQuery(
			"select c from Category c where c.active = true order by c.name"
			).getResultList();
		
		for (Category category : listCategories) {
		
			List<Auction> listAuctions = em.createQuery(
				"select a from Auction a where a.item.category = :category"
				).setParameter("category", category).getResultList();
			
			for (Auction auction : listAuctions) {
				
				if (auction.getMode().equals(Mode.SELLING))
					continue;
				
				int qty_bids = (int) (Math.random() * MAX_BIDS_PER_AUCTION);
				
				for (int count = 0; count < qty_bids; count++) {
					
					String login = users[(int) (Math.random() * users.length)];
					Date date = new Date(Calendar.getInstance().getTime().getTime() + 45 * 1000 * count);
					
					Double amount = null;
					Bid bestBid = auction.getBestBid();
					if (bestBid != null) {
						amount = getRandomAmount(bestBid.getAmount());
					} else {
						amount = getRandomAmount(auction.getStartingPrice());
					}
					
					Bid bid = new Bid();
					
					bid.setAuction(auction);
					bid.setTimestamp(date);
					bid.setAmount(amount);
					bid.setLogin(login);
					
					auction.setBestBid(bid);
					auction.getBids().add(bid);
					
					begin();
					em.persist(bid);
					em.merge(auction);
					commit();
				}
			}
		}
	}

	/**
	 * Generates a random amount based on the specified ceil.
	 * 
	 * @param ceil
	 * @return	a double
	 */
	private double getRandomAmount(Double ceil) {
		
		if (ceil == null)
			return 0.99;
		
		int value = (int) (ceil * (1 + 2 * Math.random() / 10) * 10);
		
		return value / 10.0;
	}

	/**
	 * Populates orders entities.
	 */
	@SuppressWarnings("unchecked")
	private void populateOrders() {

		List<Auction> listAuctions = em.createQuery(
			"select a from Auction a where a.mode <> :mode order by a.item.category.id"
			).setParameter("mode", Mode.AUCTION).getResultList();
		
		Category lastCategory = null;
		
		for (Auction auction : listAuctions) {
			
			if (lastCategory != null && lastCategory.equals(auction.getItem().getCategory())) {
				continue;
			}
			
			String login = users[(int) (Math.random() * users.length)];
			
			Order order = new Order();
			order.setAuction(auction);
			order.setDate(Calendar.getInstance().getTime());
			order.setLogin(login);
			order.setWinningMode(Mode.SELLING);
			auction.setStatus(Status.CLOSED);
			
			begin();
			em.persist(order);
			em.merge(auction);
			commit();
			
			lastCategory = auction.getItem().getCategory();
		}
	}

}
