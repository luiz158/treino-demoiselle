package br.gov.sample.demoiselle.auction5.message;


import junit.framework.Assert;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.gov.sample.demoiselle.auction5.message.ErrorMessage;

public class ErrorMessageTest {

	private Configuration config_pt_BR;
	
	@Before
	public void setUp() throws Exception {
		String resourceName = ErrorMessage.values()[0].getResourceName();
		String fileName = resourceName + "_pt_BR.properties";
		config_pt_BR = new PropertiesConfiguration(fileName);
		
		System.out.println("Considering properties file " + fileName);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAllMessagesFor_pt_BR(){
		boolean complete = true;
		for(ErrorMessage msg: ErrorMessage.values()){
			if(!config_pt_BR.containsKey(msg.getKey())){
				System.err.println(msg.getKey()+"=!! no message !!");
				complete = false;
			}
		}
		Assert.assertTrue("Properties is incomple, check the console for details ",complete);
	}

}
