 /* 
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 * 
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework
 *   
 * Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html  GPL License 3
 *   
 * This file is part of Demoiselle Sample.
 * 
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 * 
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample.  If not, see <http://www.gnu.org/licenses/>
 */ 
package br.gov.sample.demoiselle.escola.config;

import static org.junit.Assert.*;

import javax.inject.Inject;

//import org.junit.Test;

public class EscolaConfigTest {
	
	@Inject
	private EscolaConfig config;
	
	//@Test
	public void testCarregar(){
		assertEquals(3, config.getPapeis().size());
		assertEquals("Aluno", config.getPapeis().get(0));
		assertEquals("Professor", config.getPapeis().get(1));
		assertEquals("Administrador", config.getPapeis().get(2));		
	}
	
	//@Test
	public void testUploaPath(){
		assertEquals("/private/upload/", config.getUploadPath());
	}
	
}