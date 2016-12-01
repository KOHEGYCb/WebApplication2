package dao;

import beans.driver.Driver;
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
public class DriversDAO {

    private static DriversDAO INSTANCE = new DriversDAO();

    private DriversDAO() {
    }

    public static DriversDAO getINSTANCE() {
        return INSTANCE;
    }

    public List<Driver> getAllDriver() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Driver> drivers = new ArrayList<Driver>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from driver;");
            result = statement.executeQuery();
            while (result.next()) {
                if (result.getInt("idDriver") == 1) {
                    continue;
                }
                drivers.add(new Driver(result.getInt("idDriver"), result.getString("login"), result.getString("password"), result.getString("name"), result.getString("surname")));
            }

        } catch (SQLException e) {
            // Error Handling
        } finally {
            closeJDBC(connection, statement, result);
        }
        return drivers;
    }

    public void createDriver(Driver driver) {
        if (!isFound(driver)) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement("insert into driver (Login, Password, Name, Surname) values (\""
                        + driver.getLogin() + "\", \""
                        + driver.getPassword() + "\", \""
                        + driver.getName() + "\", \""
                        + driver.getSurname() + "\");");
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement);
            }
        }
    }

    public void deleteDriver(Driver driver) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
//            TransportDAO.getINSTANCE().deleteDriverTransport(driver.getId());
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("delete from driver where idDriver = " + driver.getId() + ";");
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement);
        }
    }

    public boolean isFound(Driver driver) {
        boolean isFound = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select login from driver WHERE Login = \"" + driver.getLogin() + "\"");
            result = statement.executeQuery();
            if (result.next()) {
                isFound = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return isFound;
    }

    public boolean isCurentPassword(Driver driver) {
        boolean curentPassword = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select login, password from driver WHERE Login = \"" + driver.getLogin() + "\"");
            result = statement.executeQuery();
            while (result.next()) {
                String str = result.getString("password");
                if (str.equals(driver.getPassword())) {
                    curentPassword = true;
                    break;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return curentPassword;
    }

    public boolean logIn(Driver driver) {
        boolean login = false;
//        if(isFound(driver))
        if (isCurentPassword(driver)) {
            login = true;
        }
        return login;
    }

    public Driver getDriverByLogin(String login) {
        Driver driver = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from driver where login = \"" + login + "\";");
            result = statement.executeQuery();
            if (result.next()) {
                driver = new Driver(result.getInt("idDriver"), login, result.getString("password"), result.getString("name"), result.getString("surname"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return driver;
    }

    public Driver getDriverById(int idDriver) {
        Driver driver = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from driver where idDriver = " + idDriver + ";");
            result = statement.executeQuery();
            if (result.next()) {
                driver = new Driver(result.getInt("idDriver"), result.getString("login"), result.getString("password"), result.getString("name"), result.getString("surname"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return driver;
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
