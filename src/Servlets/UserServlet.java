package Servlets;

import Objects.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Managers.SiteConstants.LOGGED_IN;
import static Managers.SiteConstants.USER;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("ID")!=null){
            int id = Integer.valueOf(request.getParameter("ID"));
            Boolean logged= (Boolean) request.getSession().getAttribute(LOGGED_IN);
            if(logged){
                User user= (User) request.getSession().getAttribute(USER);
                if(user.getID()==id){
                    RequestDispatcher dispatch = request.getRequestDispatcher("user-page.jsp");
                    dispatch.forward(request, response);
                    return;
                }
            }
            RequestDispatcher dispatch = request.getRequestDispatcher("visitor-user.jsp");
            dispatch.forward(request, response);
        }
    }
}
