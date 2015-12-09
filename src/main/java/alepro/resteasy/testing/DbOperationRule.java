package alepro.resteasy.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;

/**
 *
 * @author alepro
 */
public class DbOperationRule extends JmxAwareRule {

    private static final String JMX_DATASOURCE_NAME
            = "Catalina:type=DataSource,class=javax.sql.DataSource,name=\"ds/MyDS\"";

    public DbOperationRule(String url) {
        super(url);
    }

    @Override
    protected void after(JMXConnector connector) throws Throwable {
        ObjectName dsName = new ObjectName(JMX_DATASOURCE_NAME);
        MBeanServerConnection jmxConn = connector.getMBeanServerConnection();
        String dbUser = (String) jmxConn.getAttribute(dsName, "username");
        String dbPassword = (String) jmxConn.getAttribute(dsName, "password");
        String dbUrl = (String) jmxConn.getAttribute(dsName, "url");
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            cleanupDatabase(con);
        }
    }

    private void cleanupDatabase(Connection con) {
        // code for db manipulation
    }

}
