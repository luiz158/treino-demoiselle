/*
 * Demoiselle Framework
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 *
 * Demoiselle Framework is an open source Java EE library designed to accelerate
 * the development of transactional database Web applications.
 *
 * Demoiselle Framework is released under the terms of the LGPL license 3
 * http://www.gnu.org/licenses/lgpl.html  LGPL License 3
 *
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License 3 as published by
 * the Free Software Foundation.
 *
 * Demoiselle Framework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Demoiselle Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.sample.demoiselle.auction5.persistence.dao.implementation;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.gov.framework.demoiselle.core.layer.IBusinessController;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.persistence.dao.ICategoryDAO;

/**
 * @author CETEC/CTJEE
 */
public class CategoryDAOTest implements IBusinessController {

	private static Logger log = Logger.getLogger(CategoryDAOTest.class);
	
	@Injection
	private ICategoryDAO dao;
	
	private static Short lastId;
	
	@Before
	public void setUp() throws Exception {
		log.debug("CategoryDAOTest.setUp()");
		WebTransactionContext.getInstance().init();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("CategoryDAOTest.tearDown()");
		WebTransactionContext.getInstance().end();
	}
	
	@Test
	public void testInsert() {
		
		final String NAME = "Electronics";
		
		Category cat = new Category();
		cat.setName(NAME);
		
		dao.insert(cat);
		lastId = cat.getId();
		assertNotNull(lastId);
		
		cat = dao.findById(lastId);
		assertNotNull(cat);
		assertEquals(lastId, cat.getId());
		assertEquals(NAME, cat.getName());
	}

	@Test
	public void testUpdate() {
		
		final String NAME = "Gadgets";
		
		Category cat = dao.findById(lastId);
		cat.setName(NAME);
		
		dao.update(cat);
		
		cat = dao.findById(lastId);
		assertNotNull(cat);
		assertEquals(lastId, cat.getId());
		assertEquals(NAME, cat.getName());
	}

	@Test
	public void testRemove() {
		
		Category cat = dao.findById(lastId);
		
		dao.remove(cat);
		
		cat = dao.findById(lastId);
		assertNull(cat);
	}
	
	@Test
	public void testListDisabledCategories() {

		Category cat = new Category();
		cat.setName("Disabled One");
		cat.setActive(false);

		dao.insert(cat);
		lastId = cat.getId();
		assertNotNull(lastId);
		
		List<Category> list = dao.listDisabledCategories();
		assertNotNull(list);
		
		int count = list.size();
		assertTrue(count >= 1);
		
		dao.remove(cat);

		list = dao.listDisabledCategories();
		assertNotNull(list);
		assertEquals(count - 1, list.size());
	}

}
