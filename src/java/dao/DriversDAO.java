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

    public static DriversDAO getINSTANCE(){
        return INSTANCE;
    }

    public List<Driver> getAllDriver(){
        List <Driver> drivers = new ArrayList<Driver>();
        try{
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from driver;");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if(result.getInt("idDriver") == 1){
                    continue;
                }
                drivers.add(new Driver(result.getInt("idDriver"), result.getString("login"), result.getString("password"), result.getString("name"), result.getString("surname")));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return drivers;
    }
    
    public void createDriver(Driver driver) throws SQLException {
        if (!isFound(driver)) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into driver (Login, Password, Name, Surname) values (\""
                    + driver.getLogin() + "\", \""
                    + driver.getPassword() + "\", \""
                    + driver.getName() + "\", \""
                    + driver.getSurname() + "\");");
            statement.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
        } else {

        }
    }

    public void deleteDriver(Driver driver){
        try{
//            TransportDAO.getINSTANCE().deleteDriverTransport(driver.getId());
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from driver where idDriver = " + driver.getId() + ";");
            statement.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isFound(Driver driver) {
        boolean isFound = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select login from driver WHERE Login = \"" + driver.getLogin() + "\"");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                isFound = true;
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isFound;
    }

    public boolean isCurentPassword(Driver driver) {
        boolean curentPassword = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select login, password from driver WHERE Login = \"" + driver.getLogin() + "\"");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String str = result.getString("password");
                if (str.equals(driver.getPassword())) {
                    curentPassword = true;
                    break;
                }
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Driver getDriverByLogin(String login){
        Driver driver = null;
        try{
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from driver where login = \"" + login + "\";");
            ResultSet result = statement.executeQuery();
            if(result.next()){
                driver = new Driver(result.getInt("idDriver"), login, result.getString("password"), result.getString("name"), result.getString("surname"));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return driver;
    }
    public Driver getDriverById(int idDriver){
        Driver driver = null;
        try{
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from driver where idDriver = " + idDriver + ";");
            ResultSet result = statement.executeQuery();
            if(result.next()){
                driver = new Driver(result.getInt("idDriver"), result.getString("login"), result.getString("password"), result.getString("name"), result.getString("surname"));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DriversDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return driver;
    }
}
