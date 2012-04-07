package br.gov.sample.demoiselle.auction5.business.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.IFacade;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.core.message.IMessage;
import br.gov.framework.demoiselle.web.message.WebMessageContext;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.auction5.bean.Category;
import br.gov.sample.demoiselle.auction5.bean.Item;
import br.gov.sample.demoiselle.auction5.business.IItemBC;
import br.gov.sample.demoiselle.auction5.message.ErrorMessage;

public class ItemBCTest implements IFacade {

	private static Logger log = Logger.getLogger(ItemBCTest.class);

	@Injection
	private IItemBC itemBC;

	@Before
	public void setUp() throws Exception {
		log.debug("Starting contexts");
		WebTransactionContext.getInstance().init();
		WebMessageContext.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("Finalizing contexts");
//		if (WebTransactionContext.getInstance().hasResource()) {
//			WebTransactionContext.getInstance().getResource().rollback();
//		}
		WebTransactionContext.getInstance().end();
		WebMessageContext.getInstance().clear();
	}

	@Test
	public void testListAvailableCategories() {
		List<Category> categories = itemBC.listAvailableCategories();
		assertNotNull(categories);
		assertFalse(categories.isEmpty());
	}
	
	@Test
	public void testSave() {
		Item item = new Item();

		// Validation
		try {
			itemBC.save(item);
		} catch (ApplicationRuntimeException e) {
			assertNotNull(e.getObjectMessage());
			IMessage[] msgs = {
					ErrorMessage.ADM_ITEM_VALIDATE_CATEGORY_NOK,
					ErrorMessage.ADM_ITEM_VALIDATE_DESCRIPTION_NOK };

			assertTrue(msgs[0].equals(e.getObjectMessage())
					|| msgs[1].equals(e.getObjectMessage()));
		}

		item.setCategory(new Category());
		item.getCategory().setId(new Short((short) 100));
		item.setDescription("JUnit test:" + new Date().getTime());

		// Test insert
		itemBC.save(item);
		assertNotNull(item.getId());
	}

	@Test
	public void testDelete() {
		Item item = new Item();
		item.setCategory(new Category());
		item.getCategory().setId(new Short((short) 100));
		item.setDescription("JUnit test:" + new Date().getTime());
		itemBC.save(item);

		itemBC.delete(item);

	}

	@Test
	public void testFind() {
		Item item = new Item();
		item.setCategory(new Category());
		item.getCategory().setId(new Short((short) 99999));
		
		try{
			itemBC.filterByCategory(item);
		}catch (ApplicationRuntimeException e) {
			assertEquals(ErrorMessage.ADM_ITEM_LIST_LOAD_NOK, e.getObjectMessage());
		}
		
		item.setCategory(itemBC.listAvailableCategories().get(0));
		List<Item> lista =  itemBC.filterByCategory(item);
		for (Item itemFiltered:lista) {
			assertEquals("Founded item with different category", item
					.getCategory(), itemFiltered.getCategory());
		}
	}

}
