package ubu.gii.dass.test.c01;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:dpr1005@alu.ubu.es">Daniel Puente Ram√≠rez</a>
 * @author <a href="mailto:phf1001@alu.ubu.es">Patricia Hernando Fernandez</a>
 * @version 1.0
 * @since
 * 
 *        <pre>
 * feb. 17, 2022
 *        </pre>
 */
public class ReusablePoolTest {

	private static ReusablePool pool;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pool = ReusablePool.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {

		// Caso 1: referencia vacia (deberia obtener la misma instancia que pool ya que
		// es static)

		ReusablePool poolPrueba = null;

		poolPrueba = ReusablePool.getInstance();
		assertTrue("No se ha inicializado el objeto", poolPrueba != null);

		// Caso 2: est· creado, comprobamos que es unico para la clase

		assertTrue("El pool devuelto es distinto",
				poolPrueba.getInstance().hashCode() == pool.getInstance().hashCode());
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */

	@Test
	public void testAcquireReusable() {

		// Guardamos todos los objetos hasta que salte la excepcion
		List<Reusable> reusablesObtenidos = new LinkedList<Reusable>();

		try {
			while (true) {
				Reusable r = pool.acquireReusable();
				// Comprobamos que se devuelve un reusable.
				assertTrue("El objeto no es reusable", r.getClass().equals(Reusable.class));
				reusablesObtenidos.add(r);
			}

		} catch (NotFreeInstanceException e) {
			assertTrue("Empty pool raises NotFreeInstanceException. No items left", true);
		} finally {
			// Error en codigo, se permiten mas de dos reusables en el pool
			assertTrue("Se han obtenido mas de dos reusables", reusablesObtenidos.size() <= 2);
		}
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		try {
			Reusable r = new Reusable();
			pool.releaseReusable(r);
			try {
				pool.releaseReusable(r);
			} catch (DuplicatedInstanceException e) {
				assertTrue(true);
			} catch (Exception ex) {
				System.err.println(ex);
				assertTrue("another exception has been raised", false);
			}

		} catch (DuplicatedInstanceException ex1) {
			assertTrue("Empty pool raises DuplicatedInstanceException", false);
		} catch (Exception ex2) {
			System.err.println(ex2);
			assertTrue("Empty pool raises Exception", false);
		}

	}

}
