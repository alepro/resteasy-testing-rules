package alepro.resteasy.testing;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author alepro
 */
public class DbCleanupTest {
    
    @Rule 
    public DbOperationRule dbRule = new DbOperationRule("service:jmx:rmi:///jndi/rmi://localhost:18070/jmxrmi");
    
    @Test
    public void test() {
        assertTrue(true);
    }
    
}
