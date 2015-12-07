package alepro.resteasy.testing;

import java.lang.management.MemoryMXBean;
import javax.management.JMX;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;

/**
 *
 * @author alepro
 */
public class JmxOperationRule extends JmxAwareRule {

    public JmxOperationRule(String url) {
        super(url);
    }

    @Override
    protected void before(JMXConnector connector) throws Throwable {
        MemoryMXBean cacheMBean = JMX.newMBeanProxy(
                connector.getMBeanServerConnection(),
                new ObjectName("java.lang:type=Memory"),
                java.lang.management.MemoryMXBean.class);

        cacheMBean.gc();
    }
}
