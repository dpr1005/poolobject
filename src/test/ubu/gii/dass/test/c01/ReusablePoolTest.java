package ubu.gii.dass.test.c01;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:dpr1005@alu.ubu.es">Daniel Puente Ramirez</a>
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
	private final long timeout = 1;
	@Rule
	public Timeout testTimeout = new Timeout(timeout, TimeUnit.SECONDS);
	Logger logger = Logger.getLogger("c01");

	/**
	 * Generates an instance of the object pool to be used during the test.
	 */
	@Before
	public void setUp() throws Exception {
		logger.config("Setting up...");
		pool = ReusablePool.getInstance();
	}

	/**
	 * Execution done.
	 */
	@After
	public void tearDown() throws Exception {
		try {
			while (true) {
				pool.acquireReusable();
			}
		} catch (Exception ex) {
			logger.config("Finalized test.");
		}
	}

	/**
	 * Case of study 1. Check in empty reference. Case of study 2. Check whether two
	 * given *pools* have the same instance.
	 * <p>
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool poolPrueba;

		poolPrueba = ReusablePool.getInstance();
		assertNotNull("Object has not been initialized.", poolPrueba);

		assertSame("Pools are not equal.", poolPrueba, pool);
	}

	/**
	 * Case of study 1. Check whether *r* object is not null. Given the nature of
	 * *pool* it is not necessary to check whether is an intance of Reusable or not.
	 * Case of study 2. Check whether a <<new>> instance of ReusablePool has more
	 * than two Reusable objects.
	 * <p>
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */

	@Test
	public void testAcquireReusable() {
		Vector<Reusable> reusablesObtenidos = new Vector<>();
		try {

			while (true) {
				Reusable r = pool.acquireReusable();
				assertNotNull("Object is not an instance of Reusable", r);
				reusablesObtenidos.add(r);
			}

		} catch (NotFreeInstanceException e) {
			assertTrue("Empty pool raises NotFreeInstanceException. No items left", true);
		} finally {
			assertTrue("More than two Reusable objects have been retrieved.",
					reusablesObtenidos.size() <= 2);
		}
	}

	/**
	 * Case of study. Check if Reusable.releaseReusable() throws correctly
	 * DuplicatedInstanceException.
	 * <p>
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
				fail("Another exception has been raised");
			}

		} catch (DuplicatedInstanceException ex1) {
			fail("Empty pool raises DuplicatedInstanceException");
		} catch (Exception ex2) {
			fail("Empty pool raises Exception");
		}

	}

}
