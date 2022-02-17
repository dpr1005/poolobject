package ubu.gii.dass.test.c01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ubu.gii.dass.c01.Reusable;

import java.util.Vector;

/**
 * NotFreeInstanceException Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>feb. 17, 2022</pre>
 */
public class NotFreeInstanceExceptionTest {

    private Vector<Reusable> reusables;

    @Before
    public void before() throws Exception {
        reusables = new Vector<>();
        reusables.add(new Reusable());
        reusables.add(new Reusable());
        reusables.add(new Reusable());
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: lock()
     */
    @Test
    public void testLock() throws Exception {
        try {
            int size = reusables.size() + 1;
            for (int i = 0; i < size; i++) {
                reusables.remove(new Reusable());
            }
        } catch (Exception e) {
            System.out.println("lock ok");
        }
    }


} 
