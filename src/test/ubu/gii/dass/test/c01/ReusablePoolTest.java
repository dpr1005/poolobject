package ubu.gii.dass.test.c01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:dpr1005@alu.ubu.es">Daniel Puente Ramírez</a>
 * @author <a href="mailto:phf1001@alu.ubu.es">Patricia Hernando Fernandez</a>
 * @version 1.0
 * @since <pre>feb. 17, 2022</pre>
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
        System.out.print("To be implemented");
    }

    /**
     * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
     */
    @Test
    public void testAcquireReusable() {
        System.out.print("To be implemented");
    }

    /**
     * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
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
