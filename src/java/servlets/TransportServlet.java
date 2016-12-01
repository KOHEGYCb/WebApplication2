package servlets;

import beans.transport.Bus;
import beans.transport.Tram;
import beans.transport.Transport;
import beans.transport.Trolleybus;
import dao.TransportDAO;
import java.io.IOException;
import java.util.List;
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
        if (request.getParameter("createTransport") != null) {
            try {
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
                if (!TransportDAO.getINSTANCE().isFound(transport)) {
                    TransportDAO.getINSTANCE().createTransport(transport);
                    forward("/jsp/admin.jsp", request, response);
                } else {
                    request.setAttribute("createError", "Transport already created");
                    forward("/jsp/admin/Transport.jsp", request, response);
                }
            } catch (NumberFormatException nfe) {
                request.setAttribute("createError", "Wrong Transport number");
                forward("/jsp/admin/Transport.jsp", request, response);
            }
        }

        if (request.getParameter("deleteTransport") != null) {
            Transport transport = TransportDAO.getINSTANCE().getTransport(Integer.parseInt(request.getParameter("idTransport")));
            if (transport != null) {
                TransportDAO.getINSTANCE().deleteTransport(transport);
                forward("/jsp/admin.jsp", request, response);
            }else{
                request.setAttribute("deleteError", "Somethig happend");
                forward("/jsp/admin/Transport.jsp", request, response);
            }
            
        }

    }

}
