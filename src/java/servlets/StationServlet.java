package servlets;

import beans.route.Station;
import dao.StationDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmitry
 */
public class StationServlet extends ManagerServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("backToAdmin") != null) {
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("createStation") != null) {
            String stationName = request.getParameter("stationName");
            if (!"".equals(stationName)) {
                if (!StationDAO.getINSTANCE().isFound(stationName)) {
                    StationDAO.getINSTANCE().createStation(new Station(stationName));
                }
            }
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("deleteStation") != null) {
            Station station = StationDAO.getINSTANCE().getStationByName(request.getParameter("stationName"));
            if (station != null) {
                StationDAO.getINSTANCE().deleteStation(station);
            }
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("updateStation") != null) {
            String oldName = request.getParameter("oldStationName");
            String newName = request.getParameter("newStationName");

            if (StationDAO.getINSTANCE().isFound(oldName)) {
                Station station = StationDAO.getINSTANCE().getStationByName(oldName);
                station.setName(newName);
                StationDAO.getINSTANCE().updateStation(station);
            }
            forward("/jsp/admin.jsp", request, response);
        }
    }

}
