package servlets;

import beans.driver.Driver;
import dao.DriversDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dmitry
 */
public class StartServlet extends ManagerServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        request.setAttribute("wrongPassword", null);
        if (request.getParameter("enter") != null) {
            Driver driver = new Driver(request.getParameter("login"), request.getParameter("password"));
            if (DriversDAO.getINSTANCE().isFound(driver)) {
                if (DriversDAO.getINSTANCE().isCurentPassword(driver)) {
                    session.setAttribute("sessionLogin", request.getParameter("login"));
                    session.setAttribute("sessionPassword", request.getParameter("password"));
                    session.setAttribute("sessionName", DriversDAO.getINSTANCE().getDriverByLogin(request.getParameter("login")).getName());
                    session.setAttribute("sessionSurname", DriversDAO.getINSTANCE().getDriverByLogin(request.getParameter("login")).getSurname());
                    if (driver.getLogin().equals("admin")) {
                        super.forward("/jsp/admin.jsp", request, response);
                    } else {
                        super.forward("/jsp/user.jsp", request, response);
                    }
                } else {
                    request.setAttribute("wrongPassword", "Wrong password");
                    request.setAttribute("login", driver.getLogin());
                    super.forward("/index.jsp", request, response);
                }
            } else {
                request.setAttribute("loginNotFound", "Login not found");
                super.forward("/index.jsp", request, response);
            }
        } else if (request.getParameter("register") != null) {
            super.forward("/jsp/register.jsp", request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.forward("/index.jsp", req, resp);
    }

}
