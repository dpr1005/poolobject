package ubu.gii.dass.test.c01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ubu.gii.dass.c01.Reusable;

import java.util.Vector;

/**
 * DuplicatedInstanceException Tester.
 *
 * @author <a href="mailto:dpr1005@alu.ubu.es">Daniel Puente Ramirez</a>
 * @author <a href="mailto:phf1001@alu.ubu.es">Patricia Hernando Fernandez</a>
 * @version 1.0
 * @since
 * 
 *        <pre>
 * feb. 17, 2022
 *        </pre>
 */
public class DuplicatedInstanceExceptionTest {

	private Vector<Reusable> reusables;
	private Reusable r1;

	@Before
	public void before() throws Exception {
		reusables = new Vector<>();
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: lock()
	 */
	@Test
	public void testLock() throws Exception {
		reusables.add(r1);
		try {
			reusables.add(r1);
		} catch (Exception e) {
			System.out.println("testLock ok");
		}
	}

}
