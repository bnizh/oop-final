package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.HashCreator;
import Objects.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static Managers.SiteConstants.*;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            DBConnection dbc = DBFactory.getDBConnection();
            Admin admin = dbc.getAdminByEmail(username);
            if (admin == null) admin = dbc.getAdminByUsername(username);
            if (admin == null) {
                RequestDispatcher dispatch = request.getRequestDispatcher("admin-login.html");
                dispatch.forward(request, response);
                return;
            }
            String hashed = "";
            try {
                hashed = HashCreator.getHash(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (admin.getPassword().equals(hashed)) {
                Cookie uname = new Cookie(ADMIN, admin.getUserName());
                uname.setMaxAge(3 * 60 * 60);
                response.addCookie(uname);
                request.getSession().setAttribute(ADMIN_LOGGED_IN, true);
                request.getSession().setAttribute(ADMIN, admin);
                if (admin.getTypeOfAdmin() == SUPER_ADMIN_TYPE) {

                    RequestDispatcher dispatch = request.getRequestDispatcher("superadmin.jsp");
                    dispatch.forward(request, response);
                } else {
                    RequestDispatcher dispatch = request.getRequestDispatcher("admin.jsp");
                    dispatch.forward(request, response);
                }
            } else {
                RequestDispatcher dispatch = request.getRequestDispatcher("admin-login.html");
                dispatch.forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ADMIN)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                request.getSession().setAttribute(ADMIN_LOGGED_IN, false);
            }
            if (request.getSession() != null) {
                request.getSession().invalidate();
            }
            response.sendRedirect("/index.jsp");
        }
    }
}
