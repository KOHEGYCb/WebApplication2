package dao;

import beans.route.Route;
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
public class RouteDAO {

    private static RouteDAO INSTANCE = new RouteDAO();

    private RouteDAO() {
    }

    public static RouteDAO getINSTANCE() {
        return INSTANCE;
    }

    public void createRoute(Route route) {
        if (!isFound(route)) {
            try {
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(""
                        + "insert into routes (startStation, finalStation) values ("
                        + route.getStartStation().getId() + ", "
                        + route.getFinalStation().getId() + ");");
                preparedStatement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteRoute(Route route) {
        if (isFound(route)) {
            Connection connection;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("delete from routes where idRoutes = " + route.getId() + ";");
                statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Route> getAllRoute() {
        List<Route> routes = new ArrayList<Route>();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from routes;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                routes.add(new Route(result.getInt("idRoutes"), StationDAO.getINSTANCE().getStationById(result.getInt("startStation")), StationDAO.getINSTANCE().getStationById(result.getInt("finalStation"))));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return routes;
    }

    public Route getRouteById(int idRoute) {
        Route route = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from routes where idRoutes = " + idRoute + ";");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                route = new Route(idRoute, StationDAO.getINSTANCE().getStationById(result.getInt("startStation")), StationDAO.getINSTANCE().getStationById(result.getInt("finalStation")));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return route;
    }

    public Route getRouteByStations(String startStation, String finalStation) {
        Route route = null;
        if (isFound(new Route(0, StationDAO.getINSTANCE().getStationByName(startStation), StationDAO.getINSTANCE().getStationByName(finalStation)))) {
            try {
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("select * from routes where "
                        + "startStation = " + startStation + " && "
                        + "finalStation = " + finalStation + ";");
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    route = new Route(result.getInt("idRoutes"), StationDAO.getINSTANCE().getStationById(result.getInt("startStation")), StationDAO.getINSTANCE().getStationById(result.getInt("finalStation")));
                }
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return route;
    }

    public void updateRoute(Route route) {
        if (isFound(route)) {
            try {
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("update routes set "
                        + "startStation = " + route.getStartStation().getId()
                        + ", finalStation = " + route.getFinalStation().getId()
                        + " where idRoutes = " + route.getId() + ";");
                statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isFound(Route route) {
        boolean isFound = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(""
                    + "select * from routes;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int startStation = result.getInt("startStation");
                int finalStation = result.getInt("finalStation");
                if (startStation == route.getStartStation().getId() & finalStation == route.getFinalStation().getId()) {
                    isFound = true;
                }
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(RouteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isFound;
    }

}
