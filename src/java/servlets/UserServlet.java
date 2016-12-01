package servlets;

import beans.driver.Driver;
import dao.DriversDAO;
import dao.TransportDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dmitry
 */
public class UserServlet extends ManagerServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("logout") != null) {
            request.getSession().invalidate();
            forward("/index.jsp", request, response);
        }
        if (request.getParameter("backToAdmin") != null) {
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("deleteDriver") != null) {
            try {
                int idDriver = Integer.parseInt(request.getParameter("idDriver"));
                if (TransportDAO.getINSTANCE().getTransportByIdDriver(idDriver).isEmpty()) {
                    Driver driver = DriversDAO.getINSTANCE().getDriverById(idDriver);
                    DriversDAO.getINSTANCE().deleteDriver(driver);
                } else {
                    request.setAttribute("errorDelete", "Can not delete Driver");
                }
            } catch (NumberFormatException nfe) {
                request.setAttribute("errorDelete", "Can not delete Driver");
            }

            forward("/jsp/admin.jsp", request, response);
        }
    }

}
