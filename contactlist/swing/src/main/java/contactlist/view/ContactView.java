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
package contactlist.view;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Strings;
import br.gov.frameworkdemoiselle.validation.annotation.Validate;
import contactlist.business.ContactBC;
import contactlist.domain.Contact;
import contactlist.exception.DuplicatedCpfException;
import contactlist.tablemodel.ContactTableModel;

@ViewController
@SuppressWarnings("serial")
public class ContactView implements Serializable {

	private ContactTableModel contactList;

	private Contact selectedContact = new Contact();

	@Inject
	private ContactBC contactBC;

	private boolean isDeleteEnabled = false;

	private boolean isSaveEnabled = false;

	private String title;

	private boolean hasError = false;

	private String errorMessage = "";

	@Inject
	private ResourceBundle resourceBundle;

	@PostConstruct
	public void init() {
		contactList = new ContactTableModel(contactBC.findAll(), resourceBundle);
		title = Strings.getString(resourceBundle.getString("title"));
	}

	public void contactChanged() {
		isSaveEnabled = true;
		isDeleteEnabled = true;
	}

	@ExceptionHandler
	public void handleExceptions(DuplicatedCpfException exception) {
		errorMessage = exception.getMessage();
		hasError = true;
	}

	@ExceptionHandler
	public void handleExceptions(ConstraintViolationException exception) {
		errorMessage = "Validações: \n";
		for (ConstraintViolation<?> c : exception.getConstraintViolations()) {
			String msg = c.getMessage();
			if (msg.startsWith("{")) {
				msg = msg.substring(1, msg.lastIndexOf("}"));
			}
			msg = resourceBundle.getString(msg);
			errorMessage += msg + "\n";
		}
		hasError = true;
	}

	@Transactional
	public void save() {
		if (selectedContact.getId() != null) {
			contactBC.update(selectedContact);
		} else {
			contactBC.insert(selectedContact);
		}
		isSaveEnabled = false;
		isDeleteEnabled = false;
		selectedContact = new Contact();
		contactList = new ContactTableModel(contactBC.findAll(), resourceBundle);
	}

	@Transactional
	@Validate
	public void delete() {
		if (selectedContact.getId() != null) {
			contactBC.delete(selectedContact.getId());
		}
		isSaveEnabled = false;
		isDeleteEnabled = false;
		selectedContact = new Contact();
		this.contactList = new ContactTableModel(contactBC.findAll(), resourceBundle);
	}

	public boolean isDeleteEnabled() {
		return isDeleteEnabled;
	}

	public boolean isSaveEnabled() {
		return isSaveEnabled;
	}

	public void setContactList(ContactTableModel contactList) {
		this.contactList = contactList;
	}

	public ContactTableModel getContactList() {
		return contactList;
	}

	public void setSelectedContact(int selected) {
		selectedContact = contactList.get(selected);
		isDeleteEnabled = true;
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public String getName() {
		return selectedContact.getName();
	}

	public void setName(String name) {
		selectedContact.setName(name);
	}

	public String getCpf() {
		return selectedContact.getCpf();
	}

	public void setCpf(String cpf) {
		selectedContact.setCpf(cpf);
	}

	public void setContactBC(ContactBC contactBC) {
		this.contactBC = contactBC;
	}

	public ContactBC getContactBC() {
		return contactBC;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public boolean hasError() {
		return hasError;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
