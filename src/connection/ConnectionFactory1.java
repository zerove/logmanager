package connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionFactory1 {
	 private static interface Singleton {
	        final ConnectionFactory1 INSTANCE = new ConnectionFactory1();
	    }
	 
	    private final DataSource dataSource;
	 
	    private ConnectionFactory1() {
	        Properties properties = new Properties();
	        properties.setProperty("user", "system");
	        properties.setProperty("password", "admin"); 
	        
	        try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        
	        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
	        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
	                "jdbc:oracle:thin:@127.0.0.1:1521:oracle", properties
	        );
	        new PoolableConnectionFactory(
	                connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED
	        );
	 
	        this.dataSource = new PoolingDataSource(pool);
	    }
	 
	    public static Connection getDatabaseConnection() throws SQLException {
	        return Singleton.INSTANCE.dataSource.getConnection();
	    }
	}
