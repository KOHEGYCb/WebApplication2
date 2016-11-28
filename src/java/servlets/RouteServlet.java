package servlets;

import beans.route.Route;
import beans.route.Station;
import dao.RouteDAO;
import dao.StationDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmitry
 */
public class RouteServlet extends ManagerServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorCreate", null);
        if (request.getParameter("backToAdmin") != null) {
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("createRoute") != null) {
            int startStationName = Integer.parseInt(request.getParameter("startStation"));
            int finalStationName = Integer.parseInt(request.getParameter("finalStation"));
            if (!(startStationName == finalStationName)) {
                Station startStation = StationDAO.getINSTANCE().getStationById(startStationName);
                Station finalStation = StationDAO.getINSTANCE().getStationById(finalStationName);
                Route route = new Route(0, startStation, finalStation);
                if (RouteDAO.getINSTANCE().isFound(route)) {
                    request.setAttribute("errorCreate", "Route already create");
                    forward("/jsp/admin/Route.jsp", request, response);
                } else {
                    RouteDAO.getINSTANCE().createRoute(route);
                    System.out.println("Create Route");
                }
            } else {
                request.setAttribute("errorCreate", "Same stations");
                forward("/jsp/admin/Route.jsp", request, response);
            }
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("deleteRoute") != null) {
//            System.out.println(request.getParameter("idRoute"));
            int idRoute = Integer.parseInt(request.getParameter("idRoute"));
            RouteDAO.getINSTANCE().deleteRoute(RouteDAO.getINSTANCE().getRouteById(idRoute));
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("updateRoute") != null) {
            int idRoute = Integer.parseInt(request.getParameter("idRoute"));
            String startStation = request.getParameter("startStation");
            String finalStation = request.getParameter("finalStation");
            Route route = RouteDAO.getINSTANCE().getRouteById(idRoute);
            if (route != null) {
                route.setStartStation(StationDAO.getINSTANCE().getStationByName(startStation));
                route.setFinalStation(StationDAO.getINSTANCE().getStationByName(finalStation));
                RouteDAO.getINSTANCE().updateRoute(route);
            }
            forward("/jsp/admin.jsp", request, response);
        }
    }

}
