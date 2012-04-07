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
package br.gov.sample.demoiselle.auction5.scheduler;

import org.apache.log4j.Logger;

import br.gov.component.demoiselle.scheduler.ISchedulerAction;
import br.gov.component.demoiselle.scheduler.Scheduler;
import br.gov.framework.demoiselle.core.layer.IFacade;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.auction5.business.IApplicationBC;

/**
 * This class has the objective of scheduling each half an hour a task that
 * searches for ended auctions. It then finishes the latter with the status
 * "concluded" if any bid above reserve prive or "closed" otherwise.
 * 
 * @author CETEC/CTJEE
 * @see ISchedulerAction
 */
@Scheduler(expression = "00:00:00 EVERY_MINUTE 30")
public class CheckEndedAuctionsScheduler implements ISchedulerAction, IFacade {

	private static Logger log = Logger.getLogger(CheckEndedAuctionsScheduler.class);

	@Injection
	private IApplicationBC applicationBC;
	
	public void execute() {
		
		log.debug("Executing ended auctions checking task...");
		
		// TODO: set current user to a "system" account
		//WebSecurityContext.getInstance()...
		
		WebTransactionContext.getInstance().init();
		
		int count = applicationBC.finishOpenEndedAuctions();
		
		WebTransactionContext.getInstance().end();
		
		if (count > 0) {
			log.debug("Total of auctions closed or concluded: " + count);
		}
	}

}
