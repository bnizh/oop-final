package Servlets;

import DataBase.DBFactory;

import Objects.Buyer;
import Objects.ObjectFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import static Managers.SiteConstants.*;

/**
 * Created by Boris on 02.07.2016.
 */
@WebServlet("/FbServlet")
public class FbServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(DBFactory.getDBConnection().getSellerByEmail(request.getParameter("mail"))==null) {
            if (DBFactory.getDBConnection().getBuyerByEmail(request.getParameter("mail"))==null) {
                Buyer b = ObjectFactory.getNewBuyer(request.getParameter("userName"),
                        ObjectFactory.getRandom().getRandomString(), request.getParameter("mail"),
                        request.getParameter("name"), 0, "", 0, request.getParameter("image"));
                DBFactory.getDBConnection().addNewBuyer(b);
                b = DBFactory.getDBConnection().getBuyerByEmail(request.getParameter("mail"));
                b.setConfirmed(true);
                DBFactory.getDBConnection().updateBuyerWithoutImage(b);

            }
            Buyer buyer = DBFactory.getDBConnection().getBuyerByEmail(request.getParameter("mail"));
            request.getSession().setAttribute(USER, buyer);
            request.getSession().setAttribute(TYPE, BUYER);
            request.getSession().setAttribute(LOGGED_IN, true);
            Cookie username = new Cookie(USER, buyer.getUserName());
            username.setMaxAge(3 * 60 * 60);
            response.addCookie(username);
            RequestDispatcher dispatch = request.getRequestDispatcher("user-panel.jsp");
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
