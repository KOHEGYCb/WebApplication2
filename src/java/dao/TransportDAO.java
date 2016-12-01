package dao;

import beans.transport.Bus;
import beans.transport.Tram;
import beans.transport.Transport;
import beans.transport.Trolleybus;
import connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry
 */
public class TransportDAO {

    private static TransportDAO INSTANCE = new TransportDAO();

    private TransportDAO() {
    }

    public static TransportDAO getINSTANCE() {
        return INSTANCE;
    }

    public void createTransport(Transport transport) {
        if (!isFound(transport)) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement("insert into transport (type, number, idRoutes, idDriver) values ("
                        + transport.getType() + ", "
                        + transport.getNumber() + ", "
                        + transport.getIdRoute() + ", "
                        + transport.getIdDriver() + ");");
                statement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(TransportDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement);
            }
        }

    }

    public void updateTransport() {
    }

    public void deleteDriverTransport(int idDriver) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("delete from transport where idDriver = " + idDriver + ";");
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement);
        }
    }

    public void deleteTransport(Transport transport) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("delete from transport where idTransport = " + transport.getId() + ";");
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement);
        }
    }

    public Transport getTransport(int idTransport) {
        Transport transport = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from transport where idTransport = " + idTransport + ";");
            result = statement.executeQuery();
            if (result.next()) {
                transport = createTransport(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return transport;
    }

    public List<Transport> getAllTransport() {
        List<Transport> transport = new ArrayList<Transport>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from transport;");
            result = statement.executeQuery();
            while (result.next()) {
                transport.add(createTransport(result));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return transport;
    }

    public List<Transport> getTransportByIdDriver(int idDriver) {
        List<Transport> transport = new ArrayList<Transport>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from transport where idDriver = " + idDriver + ";");
            result = statement.executeQuery();
            while (result.next()) {
                transport.add(createTransport(result));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return transport;
    }

    public List<Transport> getTransportByIdRoute(int idRoute) {
        List<Transport> transport = new ArrayList<Transport>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from transport where idRoutes = " + idRoute + ";");
            result = statement.executeQuery();
            while (result.next()) {
                transport.add(createTransport(result));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return transport;
    }

    public boolean isFound(Transport transport) {
        boolean isFound = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from transport where "
                    + "type = " + transport.getType() + " &&"
                    + "number = " + transport.getNumber() + ";");
            result = statement.executeQuery();
            if (result.next()) {
                isFound = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return isFound;
    }

    private Transport createTransport(ResultSet result) {
        Transport transport = null;

        try {
            int id = result.getInt("idTransport");
            int number = result.getInt("number");
            int idDriver = result.getInt("idDriver");
            int idRoute = result.getInt("idRoutes");

            switch (result.getInt("type")) {
                case 0:
                    transport = new Bus(id, number, idDriver, idRoute);
                    break;
                case 1:
                    transport = new Trolleybus(id, number, idDriver, idRoute);
                    break;
                case 2:
                    transport = new Tram(id, number, idDriver, idRoute);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return transport;
    }

    private void closeJDBC(Connection connection, PreparedStatement statement, ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
        }
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private void closeJDBC(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
        }
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
