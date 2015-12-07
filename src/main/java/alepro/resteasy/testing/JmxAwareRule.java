package alepro.resteasy.testing;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @author alepro
 */
public abstract class JmxAwareRule implements TestRule {
    private final String url;

    public JmxAwareRule(String url) {
        this.url = url;
    }
    
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try(JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL(url))) {
                    before(connector);
                    base.evaluate();
                    after(connector);
                } 
            }
        };
    }
    
    protected void before(JMXConnector connector) throws Throwable {};
    protected void after(JMXConnector connector) throws Throwable {};
}
