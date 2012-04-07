package br.gov.sample.demoiselle.auction5.message;


import junit.framework.Assert;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.gov.sample.demoiselle.auction5.message.InfoMessage;

public class InfoMessageTest {

private Configuration config_pt_BR;
	
	@Before
	public void setUp() throws Exception {
		String resourceName = InfoMessage.values()[0].getResourceName();
		config_pt_BR = new PropertiesConfiguration(resourceName+"_pt_BR.properties");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAllMessagesFor_pt_BR(){
		boolean complete = true;
		for(InfoMessage msg: InfoMessage.values()){
			if(!config_pt_BR.containsKey(msg.getKey())){
				System.out.println(msg.getKey()+"=!! no message !!");
				complete = false;
			}
		}
		Assert.assertTrue("Properties is incomple, check the console for details ",complete);
	}

}
