package servlets;

import beans.transport.Bus;
import beans.transport.Tram;
import beans.transport.Transport;
import beans.transport.Trolleybus;
import dao.TransportDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmitry
 */
public class TransportServlet extends ManagerServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("backToAdmin") != null) {
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("createTransport") != null) {
            int type = Integer.parseInt(request.getParameter("type"));
            int number = Integer.parseInt(request.getParameter("number"));
            int idDriver = Integer.parseInt(request.getParameter("idDriver"));
            int idRoute = Integer.parseInt(request.getParameter("idRoute"));
            Transport transport;
            switch (type) {
                case 0:
                    transport = new Bus(number, idDriver, idRoute);
                    break;
                case 1:
                    transport = new Trolleybus(number, idDriver, idRoute);
                    break;
                case 2:
                    transport = new Tram(number, idDriver, idRoute);
                    break;
                default:
                    transport = null;
                    break;
            }
            TransportDAO.getINSTANCE().createTransport(transport);
            forward("/jsp/admin.jsp", request, response);
        }
    }

}
