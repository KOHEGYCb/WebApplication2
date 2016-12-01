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
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement(""
                        + "insert into routes (startStation, finalStation) values ("
                        + route.getStartStation().getId() + ", "
                        + route.getFinalStation().getId() + ");");
                statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement);
            }
        }
    }

    public void deleteRoute(Route route) {
        if (isFound(route)) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement("delete from routes where idRoutes = " + route.getId() + ";");
                statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement);
            }
        }
    }

    public List<Route> getAllRoute() {
        List<Route> routes = new ArrayList<Route>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from routes;");
            result = statement.executeQuery();
            while (result.next()) {
                routes.add(new Route(result.getInt("idRoutes"), StationDAO.getINSTANCE().getStationById(result.getInt("startStation")), StationDAO.getINSTANCE().getStationById(result.getInt("finalStation"))));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }

        return routes;
    }

    public Route getRouteById(int idRoute) {
        Route route = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement("select * from routes where idRoutes = " + idRoute + ";");
            result = statement.executeQuery();
            if (result.next()) {
                route = new Route(idRoute, StationDAO.getINSTANCE().getStationById(result.getInt("startStation")), StationDAO.getINSTANCE().getStationById(result.getInt("finalStation")));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeJDBC(connection, statement, result);
        }

        return route;
    }

    public Route getRouteByStations(String startStation, String finalStation) {
        Route route = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        if (isFound(new Route(0, StationDAO.getINSTANCE().getStationByName(startStation), StationDAO.getINSTANCE().getStationByName(finalStation)))) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement("select * from routes where "
                        + "startStation = " + startStation + " && "
                        + "finalStation = " + finalStation + ";");
                result = statement.executeQuery();
                if (result.next()) {
                    route = new Route(result.getInt("idRoutes"), StationDAO.getINSTANCE().getStationById(result.getInt("startStation")), StationDAO.getINSTANCE().getStationById(result.getInt("finalStation")));
                }
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement, result);
            }
        }
        return route;
    }

    public void updateRoute(Route route) {
        if (isFound(route)) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.prepareStatement("update routes set "
                        + "startStation = " + route.getStartStation().getId()
                        + ", finalStation = " + route.getFinalStation().getId()
                        + " where idRoutes = " + route.getId() + ";");
                statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeJDBC(connection, statement);
            }
        }
    }

    public boolean isFound(Route route) {
        boolean isFound = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(""
                    + "select * from routes;");
            result = statement.executeQuery();
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
        } finally {
            closeJDBC(connection, statement, result);
        }

        return isFound;
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
