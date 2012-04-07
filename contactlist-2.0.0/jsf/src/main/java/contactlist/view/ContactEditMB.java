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

import static contactlist.view.ViewId.CONTACT_LIST;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Faces;
import contactlist.business.ContactBC;
import contactlist.domain.Contact;
import contactlist.domain.Phone;
import contactlist.domain.PhoneType;
import contactlist.exception.DuplicatedCpfException;
import contactlist.template.DelegateEditPageBean;

@ViewController
@PreviousView(CONTACT_LIST)
public class ContactEditMB extends DelegateEditPageBean<Contact, Long, ContactBC> {
	
	private static final long serialVersionUID = 1L;

	private DataModel<Phone> phones;
	
	@Inject
	@Name("messages")
	private ResourceBundle bundle;

	public void addPhone() {
		getBean().addPhone(new Phone());
	}

	public void deletePhone() {
		getBean().getPhones().remove(getPhones().getRowData());
	}

	public DataModel<Phone> getPhones() {
		if (phones == null) {
			phones = new ListDataModel<Phone>(getBean().getPhones());
		}
		
		return phones;
	}

	@ExceptionHandler
	public void handleException(final DuplicatedCpfException cause) {
		Faces.addMessage("cpf", cause);
	}

	public List<SelectItem> getPhoneTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		for (PhoneType phoneType : PhoneType.values()) {
			items.add(new SelectItem(phoneType, bundle.getString(phoneType.toString())));
		}

		return items;
	}
}
