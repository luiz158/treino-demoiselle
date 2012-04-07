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
package contactlist.domain;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Cacheable
public class Phone implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	// @ManyToOne
	// private Contact contact;

	private String number;

	@Enumerated(EnumType.STRING)
	private PhoneType type;

	public Phone() {
	}

	public Phone(Phone phone) {
		this.number = phone.getNumber();
		this.type = phone.getType();
		// this.contact = phone.getContact();
	}

	public Phone(final String number, final PhoneType type) {
		this(null, number, type);
	}

	public Phone(final Long id, final String number, final PhoneType type) {
		this.number = number;
		this.type = type;
		this.id = id;
	}

	// public Contact getContact() {
	// return this.contact;
	// }

	public Long getId() {
		return this.id;
	}

	public String getNumber() {
		return this.number;
	}

	public PhoneType getType() {
		return this.type;
	}

	// public void setContact(final Contact contact) {
	// this.contact = contact;
	// }

	public void setId(final Long id) {
		this.id = id;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	public void setType(final PhoneType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		
		// Not strictly necessary, but often a good optimization
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Phone)) {
			return false;
		}

		Phone phone = (Phone) obj;

		return ((id == null) ? phone.id == null : id.equals(phone.id))
				&& ((number == null) ? phone.number == null : number.equals(phone.number));
		
	}

}
