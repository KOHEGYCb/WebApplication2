package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dmitry
 */
public class AdminServlet extends ManagerServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("logout") != null) {
//            request.getSession().invalidate();
//            request.getSession().setAttribute("logout", "true");
            forward("/index.jsp", request, response);
        }
        if (request.getParameter("backToAdmin") != null) {
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("Station") != null) {
            forward("/jsp/admin/Station.jsp", request, response);
        }
        if (request.getParameter("Transport") != null) {
            forward("/jsp/admin/Transport.jsp", request, response);
        }
        if (request.getParameter("Route") != null) {
            forward("/jsp/admin/Route.jsp", request, response);
        }
        if (request.getParameter("Driver") != null) {
            forward("/jsp/admin/driver.jsp", request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.forward("/index.jsp", req, resp);
    }
    
}
