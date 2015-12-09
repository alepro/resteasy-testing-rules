package alepro.resteasy.testing;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author alepro
 */
public class JmxOperationTest {
    
   @Rule 
   public JmxOperationRule jmx = new JmxOperationRule("service:jmx:rmi:///jndi/rmi://localhost:18070/jmxrmi");
    
    @Test
    public void test() {
        assertTrue(true);
    }
}
