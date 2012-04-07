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

import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.template.Crud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;

public abstract class DelegateEditPageBean<T, I, C extends Crud<T, I>> extends AbstractEditPageBean<T, I> {

	private static final long serialVersionUID = 1L;

	private Class<C> delegateClass;

	private C delegate;

	@Override
	protected void handleLoad() {
		setBean(this.getDelegate().load(getId()));
	}

	@Override
	@Transactional
	@RequiredPermission
	public String insert() {
		getDelegate().insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	@RequiredPermission
	public String update() {
		getDelegate().update(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	@RequiredPermission
	public String delete() {
		getDelegate().delete(getId());
		return getPreviousView();
	}

	@Override
	@RequiredPermission(operation = "view")
	public T getBean() {
		return super.getBean();
	}

	protected C getDelegate() {
		if (this.delegate == null) {
			this.delegate = Beans.getReference(getDelegateClass());
		}
		return this.delegate;
	}

	protected Class<C> getDelegateClass() {
		if (this.delegateClass == null) {
			this.delegateClass = Reflections.getGenericTypeArgument(this.getClass(), 2);
		}
		return this.delegateClass;
	}
}
