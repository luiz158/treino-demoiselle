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
package br.gov.sample.demoiselle.auction5.tools;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This utility class provides a simple way to have TopLink generate the
 * database schema by passing the toplink.ddl-generation flag on create of
 * EntityManagerFactory.
 * 
 * @author CETEC/CTJEE
 */
public class DDLGenerator {

	public static void main(String args[]) {

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("toplink.ddl-generation", "drop-and-create-tables");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				Constants.TEST_PERSISTENCE_UNIT_NAME, properties);
		emf.createEntityManager().close();
		emf.close();
	}

}
