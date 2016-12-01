package dao;

import beans.route.Station;
import connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry
 */
public class StationDAO {

    private static StationDAO INSTANCE = new StationDAO();

    private StationDAO() {
    }

    public static StationDAO getINSTANCE() {
        return INSTANCE;
    }

    public void createStation(Station station) {
        if (!isFound(station.getName())) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement(""
                        + "insert into stations (name, roadType) values ("
                        + "\"" + station.getName() + "\", "
                        + new Random().nextInt(4) + ");");
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement);
            }
        }
    }

    public void deleteStation(Station station) {
        if (isFound(station.getName())) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement("delete from stations where idStations = " + station.getId() + ";");
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement);
            }
        }
    }

    public Station getStationById(int idStation) {
        Station station = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(""
                    + "select * from stations where idStations = " + idStation + ";");
            result = statement.executeQuery();
            if (result.next()) {
                station = new Station(result.getInt("idStations"), result.getString("name"), result.getInt("roadType"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }
        return station;
    }

    public Station getStationByName(String name) {
        Station station = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(""
                    + "select * from stations where name = \"" + name + "\";");
            result = statement.executeQuery();
            if (result.next()) {
                station = new Station(result.getInt("idStations"), result.getString("name"), result.getInt("roadType"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }

        return station;
    }

    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<Station>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from stations;");
            result = statement.executeQuery();
            while (result.next()) {
                int idStatioon = result.getInt("idStations");
                String name = result.getString("name");
                int roadType = result.getInt("roadType");

                stations.add(new Station(idStatioon, name, roadType));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }

        return stations;
    }

    public void updateStation(Station station) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("update stations set name = \"" + station.getName()
                    + "\", roadType = " + station.getTypeRoad() + " where idStations = " + station.getId() + ";");
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeJDBC(connection, statement);
        }
    }

    public boolean isFound(String name) {
        boolean newStation = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select name from stations where name = \"" + name + "\";");
            result = statement.executeQuery();
            if (result.next()) {
                newStation = true;
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeJDBC(connection, statement, result);
        }
        return newStation;
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
