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

    public static StationDAO getINSTANCE(){
        return INSTANCE;
    }
    public void createStation(Station station) {
        if (!isFound(station.getName())) {
            try {
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(""
                        + "insert into stations (name, roadType) values ("
                        + "\"" + station.getName() + "\", "
                        + new Random().nextInt(4) + ");");
                preparedStatement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteStation(Station station) {
        if (isFound(station.getName())) {
            Connection connection;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("delete from stations where idStations = " + station.getId() + ";");
                statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Station getStationById(int idStation) {
        Station station = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(""
                    + "select * from stations where idStations = " + idStation + ";");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                station = new Station(result.getInt("idStations"), result.getString("name"), result.getInt("roadType"));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return station;
    }

    public Station getStationByName(String name) {
        Station station = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(""
                    + "select * from stations where name = \"" + name + "\";");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                station = new Station(result.getInt("idStations"), result.getString("name"), result.getInt("roadType"));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return station;
    }
    
    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<Station>();

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from stations;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int idStatioon = result.getInt("idStations");
                String name = result.getString("name");
                int roadType = result.getInt("roadType");

                stations.add(new Station(idStatioon, name, roadType));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return stations;
    }

    public void updateStation(Station station) {
//        if (isFound(station.getName())) {
            try {
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("update stations set name = \"" + station.getName()
                        + "\", roadType = " + station.getTypeRoad() + " where idStations = " + station.getId() + ";");
                statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
//        }
    }

    public boolean isFound(String name) {
        boolean newStation = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select name from stations where name = \"" + name + "\";");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                newStation = true;
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newStation;
    }
}
