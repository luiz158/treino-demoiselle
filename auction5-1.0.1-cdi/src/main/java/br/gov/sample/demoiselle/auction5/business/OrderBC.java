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
import java.util.List;

import br.gov.sample.demoiselle.auction5.bean.Auction;
import br.gov.sample.demoiselle.auction5.bean.Mode;
import br.gov.sample.demoiselle.auction5.bean.Order;
import br.gov.sample.demoiselle.auction5.bean.Status;
import br.gov.sample.demoiselle.auction5.constant.AliasRole;
import br.gov.sample.demoiselle.auction5.message.ErrorMessage;
import br.gov.sample.demoiselle.auction5.persistence.AuctionDAO;
import br.gov.sample.demoiselle.auction5.persistence.OrderDAO;

/**
 * @author CETEC/CTJEE
 * @see IBusinessController
 */
public class OrderBC {

	@Injection
	private AuctionDAO auctionDAO;

	@Injection
	private OrderDAO orderDAO;

	public Order findOrderById(Long id) {
		try {
			return orderDAO.findById(id);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(
					ErrorMessage.ORDER_FIND_BY_ID, e);
		}
	}

	@RequiredRole(roles = AliasRole.ROLE_USER)
	public Order buyItem(Auction auction, String login) {
		Order order = null;
		try {
			auction = auctionDAO.findById(auction.getId());

			// insert order
			order = new Order();
			order.setAuction(auction);
			order.setDate(Calendar.getInstance().getTime());
			order.setLogin(login);
			order.setWinningMode(Mode.SELLING);
			orderDAO.insert(order);

			// update auction
			auction.setStatus(Status.CONCLUDED);
			auctionDAO.update(auction);

		} catch (Exception e) {
			throw new ApplicationRuntimeException(ErrorMessage.BUY_ITEM_NOK, e);
		}
		return order;
	}

	@RequiredRole(roles = AliasRole.ROLE_USER)
	public List<Order> listOrdersByLogin(String login) {
		try {
			return orderDAO.listOrdersByLogin(login);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(ErrorMessage.ORDER_LIST_NOK,
					e);
		}
	}

}
