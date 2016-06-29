package Filter;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Admin;
import Objects.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Managers.SiteConstants.*;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        ServletContext context = fConfig.getServletContext();
        context.log("RequestLoggingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        boolean loggedIn = false;
        boolean adminLoggedIn = false;
        String userName = null;
        String adminUserName = null;
        String uri = req.getRequestURI();
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(USER)) userName = cookie.getValue();
                if (cookie.getName().equals(ADMIN)) adminUserName = cookie.getValue();

            }
            if (userName != null) loggedIn = true;
            if (adminUserName != null) adminLoggedIn = true;
        }
        if (adminLoggedIn) {
            req.getSession().setAttribute(ADMIN_LOGGED_IN, true);
            DBConnection db = DBFactory.getDBConnection();
            Admin admin = db.getAdminByUsername(adminUserName);
            req.getSession().setAttribute(ADMIN, admin);
        }
        if (loggedIn) {
            req.getSession().setAttribute(LOGGED_IN, true);
            DBConnection db = DBFactory.getDBConnection();
            User user;
            user = db.getSellerByUsername(userName);
            if (user != null) {
                req.getSession().setAttribute(USER, user);
                req.getSession().setAttribute(TYPE, SELLER);
            }

            user = db.getBuyerByUsername(userName);
            if (user != null) {
                req.getSession().setAttribute(USER, user);
                req.getSession().setAttribute(TYPE, BUYER);
            }
        } else {
            req.getSession().setAttribute(LOGGED_IN, false);
        }
        if (!loggedIn && (uri.endsWith("user-page.jsp") || uri.endsWith("user-panel.jsp"))) {
            res.sendRedirect("/index.jsp");
            return;
        }
        if (!adminLoggedIn && (uri.endsWith("admin.jsp") || uri.endsWith("superadmin.jsp") || uri.endsWith("add-admin.jsp")
                || uri.endsWith("adminpanel.jsp"))) {
            res.sendRedirect("admin-login.html");
            return;
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }


    public void destroy() {
        //we can close resources here
    }

}