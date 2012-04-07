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
package contactlist.init;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.alfredlibrary.utilitarios.cpfcnpj.CPF;

import br.gov.frameworkdemoiselle.annotation.Shutdown;
import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import contactlist.business.AuditBC;
import contactlist.business.ContactBC;
import contactlist.domain.Audit;
import contactlist.domain.Contact;
import contactlist.domain.Phone;
import contactlist.domain.PhoneType;

@ApplicationScoped
public class ApplicationLoader {

	@Inject
	private ContactBC contactBC;

	@Inject
	private AuditBC auditBC;

	@Startup
	@Transactional
	public void load() {

		if (contactBC.findAll().isEmpty()) {
			
			Contact contact;
			
			for (int i = 1; i < 30; i++) {
				contact = new Contact();
				contact.setName("Contact " + i);
				contact.setEmail("contact" + i + "@contact.com.br");
				contact.setCpf(CPF.gerar());
				contact.addPhone(new Phone("(71) 9999-99" + String.format("%02d", i), PhoneType.MOBILE));
				contact.addPhone(new Phone("(71) 2121-00" + String.format("%02d", i), PhoneType.WORK));
				contactBC.insert(contact);
			}
			
		}
		
	}

	@Shutdown
	public void unload() {
		Audit audit = new Audit("Closing application at " + new Date().toString());
		auditBC.insert(audit);
	}
}
