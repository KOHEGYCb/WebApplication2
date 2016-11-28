package servlets;

import beans.driver.Driver;
import dao.DriversDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmitry
 */
public class RegistrationServlet extends ManagerServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("register") != null) {
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String password = request.getParameter("password");
            boolean isCorrectData = true;
            if ("".equals(login)) {
                request.setAttribute("badLogin", "Write login");
                isCorrectData = false;
            }
            if ("".equals(name)) {
                request.setAttribute("badName", "Write name");
                isCorrectData = false;
            }
            if ("".equals(surname)) {
                request.setAttribute("badSurname", "Write surname");
                isCorrectData = false;
            }
            if ("".equals(password)) {
                request.setAttribute("badPassword", "Write password");
                isCorrectData = false;
            }
            if (!isCorrectData) {
                forward("/jsp/register.jsp", request, response);
            } else {

                try {
                    Driver driver = new Driver(login, password, name, surname);
                    if (!DriversDAO.getINSTANCE().isFound(driver)) {
                        DriversDAO.getINSTANCE().createDriver(driver);
                        forward("/index.jsp", request, response);
                    } else {
                        request.setAttribute("setLogin", login);
                        request.setAttribute("setName", name);
                        request.setAttribute("setSurname", surname);

                        request.setAttribute("badLogin", "Login already exist");
                        forward("/jsp/register.jsp", request, response);
                    }
                } catch (SQLException ex) {
                    forward("/jsp/error.jsp", request, response);
                }
            }
        }
        if(request.getParameter("login") != null){
            forward("/index.jsp", request, response);
        }
    }

}
