package br.gov.demoiselle.escola.persistence.dao.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.persistence.dao.IDisciplinaDAO;

@RunWith(Arquillian.class)
public class DisciplinaDAOTest {

	@Deployment
	public static JavaArchive createTestArchive() throws IllegalArgumentException, IOException {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addManifestResource(new File("src/test/resources/beans.xml"),
						ArchivePaths.create("beans.xml"))
				.addClasses(DisciplinaDAO.class);
	}

	private static final String NOME_DISCIPLINA = "Eletromagnetismo";
	
	@Inject
	private IDisciplinaDAO dao;

	private static Long lastId;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		lastId = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuscar() {
		
		Disciplina entity = new Disciplina();
		entity.setNome(NOME_DISCIPLINA);
		
		dao.insert(entity);
		assertTrue(true);
		
		lastId = entity.getId();
		
		Disciplina retrieved = dao.buscar(new Disciplina(lastId));
		assertNotNull(retrieved);
		assertEquals(lastId, retrieved.getId());
		assertEquals(NOME_DISCIPLINA, retrieved.getNome());
		assertSame(entity, retrieved);
		
		dao.delete(entity);
		assertTrue(true);
	}

	@Test
	public void testListar() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testFiltrar() {
		fail("Not yet implemented");
	}

}
