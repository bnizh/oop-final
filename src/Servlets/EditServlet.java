package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.ManagerFactory;
import Managers.UserManager;
import Objects.Seller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = null;
        String id = null;
        DBConnection db = DBFactory.getDBConnection();
        UserManager um = ManagerFactory.getUserManager();
        boolean loggedIn = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) userName = cookie.getValue();
                if (cookie.getName().equals("ID")) id = cookie.getValue();
            }
            if (request.getSession().getId().equals(id) && userName != null) loggedIn = true;
        }
        System.out.println(userName);
        System.out.println(id);
        System.out.println(loggedIn);
        if (loggedIn) {
            if (request.getPart("simage") != null) {
                Seller seller = db.getSellerByUsername(userName);
                um.editImageSeller(request.getPart("simage"), seller);
                request.getSession().setAttribute("user", seller);
                RequestDispatcher dispatch = request.getRequestDispatcher("SellerPage.jsp");
                dispatch.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
