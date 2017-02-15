package connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionFactory {
	 private static interface Singleton {  
	       final ConnectionFactory INSTANCE = new ConnectionFactory();  
	    }  
	   
	   private DataSource dataSource = null;  
	   
	   private ConnectionFactory() {  
		   
	    try {  
	           Class.forName("oracle.jdbc.driver.OracleDriver"); 
	           Properties properties = new Properties();  
		       properties.setProperty("user", "system");  
		       properties.setProperty("password", "admin"); 
		       
		       Driver driver = DriverManager.getDriver("oracle.jdbc.driver.OracleDriver");
				GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
			       DriverConnectionFactory connectionFactory = new DriverConnectionFactory(driver, "jdbc:oracle:thin:@localhost:1521:oracle", properties);  
			       new PoolableConnectionFactory(
			                connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED
			        );
			 
			        this.dataSource = new PoolingDataSource(pool);
	       	} catch (ClassNotFoundException e) {  
	           e.printStackTrace();  
	           System.exit(0);  
	       	} catch (SQLException e) {
				e.printStackTrace();
			}  
	       
	   }  
	   
	   public static Connection getDatabaseConnection() throws SQLException {  
	       return Singleton.INSTANCE.dataSource.getConnection();  
	    }  
}
