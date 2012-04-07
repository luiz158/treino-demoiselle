/*
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 *
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework intented to exemplify its usage.
 *
 * Demoiselle Framework is released under the terms of the LGPL license 3
 * http://www.gnu.org/licenses/lgpl.html  LGPL License 3
 *
 * This file is part of Demoiselle Sample.
 *
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License 3 as published by
 * the Free Software Foundation.
 *
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Demoiselle Sample.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.sample.demoiselle.auction5.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.component.demoiselle.common.pojo.extension.Description;
import br.gov.component.demoiselle.common.pojo.extension.EqualsField;
import br.gov.component.demoiselle.common.pojo.extension.IPojoExtension;
import br.gov.framework.demoiselle.core.bean.IPojo;

/**
 * @author CETEC/CTJEE
 * @see IPojo
 */
@Entity
@Table(name = "items")
@SequenceGenerator(name = "ItemSequence", sequenceName = "items_seq")
@NamedQuery(
	name = "itemsByCategory",
	query = "select i from Item i where i.category.id = ?1 order by i.description"
)
public class Item implements IPojoExtension {

	private static final long serialVersionUID = 444L;

	@Id
	@GeneratedValue(generator = "ItemSequence", strategy = GenerationType.SEQUENCE)
	@EqualsField
	@Description
	private Integer id;

	@Column(length = 50, nullable = false)
	@Description
	private String description;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String toLog() {
		return toString();
	}
	
}
