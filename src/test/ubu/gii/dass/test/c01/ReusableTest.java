package ubu.gii.dass.test.c01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ubu.gii.dass.c01.Reusable;

/**
 * Reusable Tester.
 *
 * @author <a href="mailto:dpr1005@alu.ubu.es">Daniel Puente Ramírez</a>
 * @author <a href="mailto:phf1001@alu.ubu.es">Patricia Hernando Fernandez</a>
 * @version 1.0
 * @since
 * 
 *        <pre>
 * feb. 17, 2022
 *        </pre>
 */
public class ReusableTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: util()
	 */
	@Test
	public void testUtil() throws Exception {
		Reusable re = new Reusable();
		assert re.util().contains(":Uso del objeto Reutilizable");
	}

}
