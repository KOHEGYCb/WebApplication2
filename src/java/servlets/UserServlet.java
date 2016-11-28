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
public class UserServlet extends ManagerServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("logout") != null) {
            HttpSession session = null;
            forward("/index.jsp", request, response);
        }
        if (request.getParameter("backToAdmin") != null) {
            forward("/jsp/admin.jsp", request, response);
        }
        if (request.getParameter("delete") != null){
            int idDriver = Integer.parseInt(request.getParameter("idDriver"));
            Driver driver = DriversDAO.getINSTANCE().getDriverById(idDriver);
            if(driver != null){
                DriversDAO.getINSTANCE().deleteDriver(driver);
            }
            forward("/jsp/admin.jsp", request, response);
        }
    }
    
}
