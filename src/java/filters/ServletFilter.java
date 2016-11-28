package filters;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dmitry
 */
public class ServletFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("Filter Start");
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession();
//        if (request.getParameter("enter") != null) {
//            filterChain.doFilter(request, response);
//        } else {
//            if (session.getAttribute("logout").equals("true")) {
//
//                session.setAttribute("sessionLogin", null);
//                session.setAttribute("sessionPassword", null);
//                session.setAttribute("sessionName", null);
//                session.setAttribute("sessionSurname", null);
//                System.out.println("session = null");
//                ServletContext ctx = filterConfig.getServletContext();
//                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                System.out.println("session != null");
//                System.out.println(session.getAttribute("sessionLogin"));
                filterChain.doFilter(request, response);
//
//            }
//        }
    }

    @Override
    public void destroy() {

    }

}
