 /* 
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 * 
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework
 *   
 * Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html  GPL License 3
 *   
 * This file is part of Demoiselle Sample.
 * 
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 * 
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample.  If not, see <http://www.gnu.org/licenses/>
 */ 
package br.gov.demoiselle.escola.ui.listener;



import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.message.IMessage;
import br.gov.framework.demoiselle.core.message.IMessageContext;

public class MessagemListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Adicionar todas as mensagens no contexto de mensagem. Ao final limpa o
	 * contexto de mensagens
	 */
	public void afterPhase(PhaseEvent arg0) {		
		IMessageContext messageContext = ContextLocator.getInstance().getMessageContext();
		for (IMessage imsg : messageContext.getMessages()) {
			ManagedBeanUtil.addMessage(imsg);
		}
		messageContext.clear();
	}

	public void beforePhase(PhaseEvent arg0) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.INVOKE_APPLICATION;
	}
}