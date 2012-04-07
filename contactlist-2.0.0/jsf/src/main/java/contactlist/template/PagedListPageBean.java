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
package contactlist.template;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.DataModel;

import org.primefaces.model.LazyDataModel;

import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public abstract class PagedListPageBean<T, I> extends AbstractListPageBean<T, I> {
	
	private static final long serialVersionUID = 1L;

	private DataModel<T> dataModel;

	@Override
	@SuppressWarnings("unchecked")
	public DataModel<T> getDataModel() {
		if (dataModel == null) {
			dataModel = new LazyDataModelImpl();
		}
		return dataModel;
	}

	private class LazyDataModelImpl extends LazyDataModel<T> {

		private static final long serialVersionUID = 1L;

		private boolean loaded = false;

		// FIXME: workaround para bugs no LazyDataModel do PrimeFaces 2.2-RC2
		// http://code.google.com/p/primefaces/issues/detail?id=969
		// http://code.google.com/p/primefaces/issues/detail?id=1513
		@Override
		public int getRowCount() {
			if (!loaded) {
				getPagination();
				handleResultList();
				loaded = true;
			}

			return getPagination().getTotalResults();
		}

		@Override
		public List<T> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters) {
			getPagination().setPageSize(pageSize);
			getPagination().setFirstResult(first);

			return handleResultList();
		}

	}

	@Transactional
	public String deleteSelection() {
		boolean delete = false;
		Iterator<I> iter = getSelection().keySet().iterator();

		while (iter.hasNext()) {
			I id = (I) iter.next();
			delete = getSelection().get(id);

			if (delete) {
				handleDelete(id);
				iter.remove();
			}
		}
		return getCurrentView();
	}

	protected abstract void handleDelete(I id);
}
