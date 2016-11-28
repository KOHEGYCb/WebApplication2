package connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Dmitry
 */
public class ConnectionPool {

    private Connection connection;
    private DataSource dataSource;
    
    private ConnectionPool() {
        InitialContext initContext;
        try {
            initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/netc");
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionPool getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public Connection getConnection() throws SQLException{
        connection = dataSource.getConnection();
        return connection;
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private static class SingletonHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
