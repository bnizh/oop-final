package Filter;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static Managers.SiteConstants.*;

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
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
               if( cookie.getName().equals(USER)) userName=cookie.getValue();
            }
            if(userName!=null) loggedIn =true;
        }
        if(loggedIn){
            req.getSession().setAttribute(LOGGED_IN,true);
            DBConnection db= DBFactory.getDBConnection();
            User user;
           user=db.getSellerByUsername(userName);
            if (user != null) {
                req.getSession().setAttribute(USER, user);
                req.getSession().setAttribute(TYPE, SELLER);
            }

            user=db.getBuyerByUsername(userName);
            if (user != null) {
                req.getSession().setAttribute(USER, user);
                req.getSession().setAttribute(TYPE, BUYER);
            }
        }
        else{
            req.getSession().setAttribute(LOGGED_IN,false);
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }


    public void destroy() {
        //we can close resources here
    }

}