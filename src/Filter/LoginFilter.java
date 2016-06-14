package Filter;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        boolean loggedIn=false;
        String userName=null;
        String id=null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
               if( cookie.getName().equals("username")) userName=cookie.getValue();
                if(cookie.getName().equals("ID")) id=cookie.getValue();
            }
            if(req.getSession().getId().equals(id)&&userName!=null) loggedIn =true;
        }
        if(loggedIn){
            req.getSession().setAttribute("loggedIn",true);
            DBConnection db= DBFactory.getDBConnection();
            User user;
           user=db.getSellerByUsername(userName);
            if (user != null) {
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("type", "seller");
                return;
            }

            user=db.getBuyerByUsername(userName);
            if (user != null) {
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("type", "buyer");
                return;
            }
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    public void destroy() {
        //we can close resources here
    }

}