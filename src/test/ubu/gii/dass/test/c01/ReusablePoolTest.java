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
 * @author <a href="mailto:dpr1005@alu.ubu.es">Daniel Puente RamÃ­rez</a>
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
		// Lo suyo sería acceder al vector y ver que se han creado correctamente los
		// objetos del tipo reusable, pero habría que hacer un método público que
		// devuelva el vector y no sé si podemos modificar el fuente.

		// Caso 1: inicialmente está vacío

		ReusablePool poolPrueba = null;
		// Me gustaría probar no que el objeto está vacío sino el vector, su tamaño etc
		assertTrue("El objeto inicialmente esta nulo", poolPrueba == null); // Obv la referencia no apunta a nada pero
																			// bueno
		poolPrueba = ReusablePool.getInstance();
		assertTrue("Se ha creado el pool", poolPrueba != null); // Comprobar el vector :(

		// Caso 2: está creado, comprobamos que es único

		int hashPrevio = poolPrueba.hashCode();
		poolPrueba = ReusablePool.getInstance();
		int hashPosterior = poolPrueba.hashCode();

		assertTrue("El pool devuelto es el mismo", hashPrevio == hashPosterior);
		// Suponiendo que se tiene en cuenta el vector para calcular el hash xd
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
				//Comprobamos que se devuelve un reusable.
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
