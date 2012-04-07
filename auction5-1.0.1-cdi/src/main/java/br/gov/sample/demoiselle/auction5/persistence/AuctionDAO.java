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
package br.gov.sample.demoiselle.auction5.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.bean.Item;
import br.gov.sample.demoiselle.auction5.bean.Mode;
import br.gov.sample.demoiselle.auction5.bean.Status;

/**
 * This DAO implementation class exhibits the usage of JPQL queries with bind
 * named parameters. Moreover, it exemplifies the usage of native queries
 * programmaticaly and also through named queries.
 * 
 * @author CETEC/CTJEE
 * @see IDAO
 * @see JPAGenericDAO
 */
public class AuctionDAO extends JPAGenericDAO<Auction> {

	public Auction findById(Long id) {
		return super.findById(id);
	}

	public List<Auction> listOpenAuctionsByCategory(Category category) {

		String jpql = "select a from Auction a " + "where a.status = :status "
				+ "  and a.deadline > :deadline "
				+ "  and a.item.category = :category "
				+ "order by a.item.description";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", Status.OPEN);
		params.put("deadline", new Date());
		params.put("category", category);

		List<Auction> result = findByJPQL(jpql, params);

		return result;
	}

	public List<Auction> listMostOfferedAuctions(int quantity) {

		String sql = "select d.* from ( "
				+ "  select b.auction_id, count(b.id) " + "  from bids b "
				+ "  inner join auctions a on (a.id = b.auction_id) "
				+ "  where a.status = ?1 and a.mode <> ?2 "
				+ "  group by b.auction_id " + "  order by count(b.id) desc "
				+ "  limit ?3) c "
				+ "join auctions d on (d.id = c.auction_id) "
				+ "where d.bestbid_id is not null " + "order by c.count desc";

		List<Auction> result = findByNativeQuery(sql, Status.OPEN.ordinal(),
				Mode.SELLING.ordinal(), quantity);

		return result;
	}

	public List<Auction> listNewestAuctions(int quantity) {

		List<Auction> result = findByNamedQuery("newestAuctions", Status.OPEN
				.ordinal(), quantity);

		return result;
	}

	public List<Auction> listEndingSoonAuctions(int seconds, int quantity) {

		String sql = "select * "
				+ "from auctions "
				+ "where status = ?1 "
				+ "  and deadline - current_timestamp between '0 s' and cast(?2 as interval) "
				+ "order by deadline " + "limit ?3";

		List<Auction> result = findByNativeQuery(sql, Status.OPEN.ordinal(),
				seconds + " s", quantity);

		return result;
	}

	public List<Auction> listCheapestPriceAuctions(int quantity) {

		// WARNING: use "?" instead of ":" on named parameters for TopLink and
		// EclipseLink
		String sql = "select d.* from ("
				+ "  select a.id, coalesce(b.amount, a.startingprice, a.sellingprice) as price "
				+ "  from auctions a left join bids b on (b.id = a.bestbid_id) "
				+ "  where a.status = ?status " + // :status
				"  order by 2 asc " + "  limit ?quantity) c " + // :quantity
				"join auctions d on c.id = d.id " + "order by c.price";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", Status.OPEN.ordinal());
		params.put("quantity", quantity);

		List<Auction> result = findByNativeQuery(sql, params);

		return result;
	}

	public List<Auction> listOpenAuctionsByItem(Item item) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", Status.OPEN);
		params.put("deadline", new Date());
		params.put("item", item);

		List<Auction> result = findByNamedQuery("openAuctionsByItem", params);

		return result;
	}

	public List<Auction> listAllAuctionsByItem(Item item) {

		String jpql = "select a from Auction a where a.item = ?1";

		List<Auction> result = findByJPQL(jpql, item);

		return result;
	}

	public Auction insert(Auction auction) {

		Date now = Calendar.getInstance().getTime();
		auction.setCreation(now);

		super.insert(auction);

		return auction;
	}

	public List<Auction> listOpenEndedAuctions(Date timestamp) {

		String jpql = "select a from Auction a " + "where (a.status = ?1) "
				+ "  and (a.deadline is not null) "
				+ "  and (a.deadline <= ?2)";

		List<Auction> result = findByJPQL(jpql, Status.OPEN, timestamp);

		return result;
	}

}
