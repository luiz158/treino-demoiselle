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
import br.gov.sample.demoiselle.auction5.bean.Mode;
import br.gov.sample.demoiselle.auction5.bean.Order;
import br.gov.sample.demoiselle.auction5.bean.Status;
import br.gov.sample.demoiselle.auction5.constant.AliasRole;
import br.gov.sample.demoiselle.auction5.persistence.AuctionDAO;
import br.gov.sample.demoiselle.auction5.persistence.OrderDAO;

/**
 * @author CETEC/CTJEE
 * @see IBusinessController
 */
public class ApplicationBC {

	@Injection
	private AuctionDAO auctionDAO;

	@Injection
	private OrderDAO orderDAO;

	private static Logger log = Logger.getLogger(ApplicationBC.class);

	@RequiredRole(roles = AliasRole.ROLE_SYSTEM)
	public int finishOpenEndedAuctions() {

		int count = 0;
		log.debug("Finishing open ended auctions...");

		Date now = Calendar.getInstance().getTime();
		List<Auction> list = auctionDAO.listOpenEndedAuctions(now);

		for (Auction auction : list) {

			// close the auction
			auction.setStatus(Status.CLOSED);

			// check whether there was any bid...
			Bid bestBid = auction.getBestBid();
			if (!auction.getMode().equals(Mode.SELLING) && bestBid != null) {

				// check whether best bid was greater or equals reserve price...
				if (bestBid.getAmount() >= auction.getReservePrice()) {

					// send a message to the winner!
					noticeWinningBidder(auction, bestBid);

					// conclude the auction
					auction.setStatus(Status.CONCLUDED);

					// process a new order
					Order order = new Order();
					order.setAuction(auction);
					order.setDate(now);
					order.setLogin(bestBid.getLogin());
					order.setWinningMode(Mode.AUCTION);
					orderDAO.insert(order);
				}
			}

			// update the auction itself
			auctionDAO.update(auction);

			// increment the counter
			count++;
		}

		return count;
	}

	private void noticeWinningBidder(Auction auction, Bid bid) {

		log.debug("Noticing winning bidder \"" + bid.getLogin()
				+ "\" for auction " + auction);

		// TODO: send HTML formatted mail to the auction winner
		/*
		 * MailClient client = new MailClient(); client.sendHtml(from, to, cc,
		 * bcc, subject, text);
		 */
	}

}
