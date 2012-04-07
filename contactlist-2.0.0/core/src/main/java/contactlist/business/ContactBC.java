/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da Licença Pública Geral GNU como publicada pela Fundação
 * do Software Livre (FSF); na versão 2 da Licença.
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/GPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse o Portal do Software Público
 * Brasileiro no endereço www.softwarepublico.gov.br ou escreva para a Fundação do Software
 * Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package contactlist.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import contactlist.domain.Audit;
import contactlist.domain.Contact;
import contactlist.exception.DuplicatedCpfException;
import contactlist.message.ResourceMessages;
import contactlist.persistence.ContactDAO;

@BusinessController
public class ContactBC extends DelegateCrud<Contact, Long, ContactDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AuditBC auditBC;

	@Inject
	@Name("messages-core")
	private ResourceBundle bundle;

	@Inject
	private MessageContext messageContext;

	private void validateInsert(Contact contact) {
		String cpf = contact.getCpf();
		if (cpf != null && !cpf.equals("") && !this.getDelegate().findByCpf(cpf).isEmpty()) {
			throw new DuplicatedCpfException(cpf, bundle);
		}
	}

	@Override
	@Transactional
	public void insert(Contact contact) {
		validateInsert(contact);
		this.getDelegate().insert(contact);
		auditBC.insert(new Audit(bundle.getString(ResourceMessages.INSERTING_CONTACT) + contact.getCpf()));
		messageContext.add(bundle.getString(ResourceMessages.CONTACT_INSERT_OK), contact.getName());
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Contact contact = load(id);
		super.delete(id);
		auditBC.insert(new Audit(bundle.getString(ResourceMessages.DELETING_CONTACT) + id));
		messageContext.add(bundle.getString(ResourceMessages.CONTACT_DELETE_OK), contact.getName());
	}

	@Override
	@Transactional
	public void update(Contact contact) {
		super.update(contact);
		auditBC.insert(new Audit(bundle.getString(ResourceMessages.UPDATING_CONTACT) + contact.getCpf()));
		messageContext.add(bundle.getString(ResourceMessages.CONTACT_UPDATE_OK), contact.getName());
	}

}
