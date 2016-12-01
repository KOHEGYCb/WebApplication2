package filters;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dmitry
 */
@WebFilter(urlPatterns = {"/*"})
public class ServletFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if (req.getParameter("enter") != null || request.getParameter("register") != null) {
            filterChain.doFilter(request, response);
        }else{
            if (session.getAttribute("sessionLogin") == null) {
                ServletContext ctx = filterConfig.getServletContext();
                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }else{
                filterChain.doFilter(request, response);
            }
        }        
    }

    @Override
    public void destroy() {

    }

}
