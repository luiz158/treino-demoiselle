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
package contactlist.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import contactlist.domain.Contact;

public class ContactTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Contact> list;
	
	private ResourceBundle bundle;
	
	public ContactTableModel(ResourceBundle bundle) {
		this.bundle = bundle;
		this.list = new ArrayList<Contact>();
	}

	public ContactTableModel(List<Contact> list, ResourceBundle bundle) {
		this.bundle = bundle;
		this.list = list;
	}

	public void add(Contact contact) {
		list.add(contact);
		fireTableRowsInserted(list.size() - 1, list.size() - 1);
	}

	public Contact get(int row) {
		return list.get(row);
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
			case 0:
				return this.bundle.getString("nome");
			case 1:
				return this.bundle.getString("cpf");
			case 2:
				return this.bundle.getString("telefones");
			default:
				return "";
		}
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Contact contact = get(row);
		switch (column) {
			case 0:
				return contact.getName();
			case 1:
				return contact.getCpf();
			case 2:
				return "12312321";
			default:
				return null;
		}
	}

}