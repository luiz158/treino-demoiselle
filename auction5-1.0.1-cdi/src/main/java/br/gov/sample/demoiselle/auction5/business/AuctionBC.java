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
package br.gov.sample.demoiselle.auction5.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Bid;
import br.gov.sample.demoiselle.auction5.bean.BidAudit;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.bean.Item;
import br.gov.sample.demoiselle.auction5.bean.Mode;
import br.gov.sample.demoiselle.auction5.bean.Status;
import br.gov.sample.demoiselle.auction5.constant.AliasRole;
import br.gov.sample.demoiselle.auction5.message.ErrorMessage;
import br.gov.sample.demoiselle.auction5.persistence.AuctionDAO;
import br.gov.sample.demoiselle.auction5.persistence.BidAuditDAO;
import br.gov.sample.demoiselle.auction5.persistence.BidDAO;
import br.gov.sample.demoiselle.auction5.persistence.CategoryDAO;
import br.gov.sample.demoiselle.auction5.persistence.ItemDAO;

/**
 * @author CETEC/CTJEE
 * @see IBusinessController
 */
public class AuctionBC  {

	@Injection
	private AuctionDAO auctionDAO;

	@Injection
	private CategoryDAO categoryDAO;

	@Injection
	private ItemDAO itemDAO;

	@Injection
	private BidDAO bidDAO;

	@Injection
	private BidAuditDAO bidAuditDAO;

	private static final int AUCTIONS_LIST_COUNT = 20;	
	private static final int BIDS_LIST_COUNT = 10;	

	private static Logger log = Logger.getLogger(AuctionBC.class);
	
	public List<Category> listAvailableCategories() {	
		try {
			return categoryDAO.listAvailableCategories();
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.LIST_CATEGORY_NOK, e);
		}
	}

	public List<Item> listItemsByCategory(Category category) {
		try {
			return itemDAO.listByCategory(category);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.AUCTION_ITEM_LIST_CATEGORY_NOK, e);
		}
	}

	public Auction findAuctionById(Long id) {
		try {
			return auctionDAO.findById(id);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.AUCTION_FIND_BY_ID_NOK, e);
		}
	}

	public List<Auction> listOpenAuctionsByCategory(Category category) {
		try {
			return auctionDAO.listOpenAuctionsByCategory(category);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.AUCTION_LIST_AUCTIONS_BY_CATEGORY_NOK, e);
		}
	}

	public List<Auction> listOpenAuctionsByItem(Item item) {
		try {
			return auctionDAO.listOpenAuctionsByItem(item);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.AUCTION_LIST_AUCTIONS_BY_ITEM_NOK, e);
		}
	}

	public List<Auction> listMostOfferedAuctions() {
		return auctionDAO.listMostOfferedAuctions(AUCTIONS_LIST_COUNT);
	}

	public List<Auction> listCheapestPriceAuctions() {
		return auctionDAO.listCheapestPriceAuctions(AUCTIONS_LIST_COUNT);
	}

	public List<Auction> listNewestAuctions() {
		return auctionDAO.listNewestAuctions(AUCTIONS_LIST_COUNT);
	}

	public List<Auction> listEndingSoonAuctions() {
		int twoDays = 2 * 24 * 3600;
		return auctionDAO.listEndingSoonAuctions(twoDays, AUCTIONS_LIST_COUNT);
	}

	public List<Bid> listLastBidsForAuction(Auction auction, int qty) {
		try {
			return bidDAO.listLastBidsForAuction(auction, qty);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.AUCTION_LIST_LAST_BID_NOK, e);
		}
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMIN)
    public Auction save(Auction auction) {
		try {
			
			if (auction.getId() != null) {
				
				List<Bid> listBids = this.listLastBidsForAuction(auction, BIDS_LIST_COUNT);
				if (listBids != null && listBids.size() > 0) {
					throw new ApplicationRuntimeException(
							ErrorMessage.AUCTION_UPDATE_NOK_THERE_ARE_BIDS);
				}
				auctionDAO.update(auction);
				
			} else {
				auctionDAO.insert(auction);
			}
			
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.ADM_AUCTION_SAVE_NOK, e);
		}
		return auction;
	}
	
	@RequiredRole(roles = AliasRole.ROLE_ADMIN)
	public void delete(Auction auction) {
		try {
			
			auction = auctionDAO.findById(auction.getId());
			if (auction == null) {
				throw new ApplicationRuntimeException(
						ErrorMessage.OBJECT_NOT_FOUND);
			}
			
			List<Bid> listBids = this.listLastBidsForAuction(auction, BIDS_LIST_COUNT);
			
			if (listBids != null)
				if (listBids.size() > 0)
					throw new ApplicationRuntimeException(
							ErrorMessage.AUCTION_DELETE_NOK_THERE_ARE_BIDS);
			
			auctionDAO.remove(auction);
			
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.ADM_AUCTION_DELETED_NOK, e);
		}
	}
	
	@RequiredRole(roles = AliasRole.ROLE_USER)
	public Bid placeBid(Auction auction, Double amount, String login, BidAudit audit) {

		log.debug("Placing bid of " + amount + " for auction " + auction);
		
		// retrieve the auction instance
		auction = auctionDAO.findById(auction.getId());

		// check whether auction is still open
		if (!Status.OPEN.equals(auction.getStatus()))
			throw new ApplicationRuntimeException(ErrorMessage.AUCTION_STATUS_CLOSED);

		// check whether actual modality is auction
		if (Mode.SELLING.equals(auction.getMode()))
			throw new ApplicationRuntimeException(ErrorMessage.AUCTION_NO_ENABLED_FOR_BID);

		Bid bestBid = auction.getBestBid();
		if (bestBid != null) {
			
			// check if amount is better than current winning bid
			if (amount <= bestBid.getAmount()) {
				throw new ApplicationRuntimeException(ErrorMessage.AUCTION_AMOUNT_BID_LESSER_BEST_BID);
			}
			
		} else {
			
			// check if amount is greater than starting price
			if (amount <= auction.getStartingPrice()) {
				throw new ApplicationRuntimeException(ErrorMessage.AUCTION_AMOUNT_BID_LESSER_STARTING_PRICE);
			}
		}
		
		Bid bid = new Bid();
		Date now = Calendar.getInstance().getTime();
		try {
			
			// insert the bid
			bid.setAuction(auction);
			bid.setTimestamp(now);
			bid.setAmount(amount);
			bid.setLogin(login);
			bidDAO.insert(bid);
			
			// update the auction
			auction.setBestBid(bid);
			auction.getBids().add(bid);
			auctionDAO.update(auction);
			
			// insert auditing data
			audit.setDateTime(now);
			bidAuditDAO.insert(audit);
			
		} catch (Exception e) {
			throw new ApplicationRuntimeException(ErrorMessage.AUCTION_BID_ITEM_NOK, e);
		}
		
		return bid;
	}

}
