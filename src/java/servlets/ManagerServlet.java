package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
/**
 *
 * @author Dmitry
 */
public class ManagerServlet extends HttpServlet{
    
    public void forward(String to, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(to);
        requestDispatcher.forward(request, response);
    }
    
}
