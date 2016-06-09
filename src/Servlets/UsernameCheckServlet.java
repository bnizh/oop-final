package Servlets;

import Managers.ManagerFactory;
import Managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/UsernameCheckServlet")
public class UsernameCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        UserManager um = ManagerFactory.getUserManager();
        boolean free = um.checkUsernameVacancy(userName);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (free)
            out.write("free");
        else out.write("used");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
