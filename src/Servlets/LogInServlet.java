package Servlets;

import Managers.ManagerFactory;
import Managers.UserManager;
import Objects.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;


@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println( request.getSession().getId());
        UserManager um = ManagerFactory.getUserManager();
        User user = null;
        try {
            user = um.checkSellerLoginValidation(userName, password);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (user != null) {
            request.getSession().setAttribute("loggedIn", true);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("type", "seller");
            Cookie username = new Cookie("user", user.getUserName());
            username.setMaxAge(3*60*60);
            Cookie sesID= new Cookie("ID",request.getSession().getId());
            sesID.setMaxAge(3*60*60);
            response.addCookie(sesID);
            response.addCookie(username);
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
            return;
        }
        try {
            user = um.checkBuyerLoginValidation(userName, password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (user != null) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("type", "buyer");
            request.getSession().setAttribute("loggedIn", true);
            Cookie username = new Cookie("user", user.getUserName());
            username.setMaxAge(3*60*60);
            Cookie sesID= new Cookie("ID",request.getSession().getId());
            sesID.setMaxAge(3*60*60);
            response.addCookie(sesID);
            response.addCookie(username);
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
            return;
        }
        PrintWriter out = response.getWriter();
        out.write("failed");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
