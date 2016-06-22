package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import static Managers.SiteConstants.ITEM;
import static Managers.SiteConstants.LOGGED_IN;
import static Managers.SiteConstants.USER;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer rate = null;
        User writer = (User) request.getSession().getAttribute(USER);
        if (request.getParameter("rate") == null || request.getParameter("ID") == null) return;
        DBConnection db = DBFactory.getDBConnection();
        rate = Integer.valueOf(request.getParameter("rate"));
        int id = Integer.valueOf(request.getParameter("ID"));

        Seller seller = db.getSellerByID(id);
        if (seller != null) {
            int votes = seller.getVoters();
            int rating = seller.getRating();
            int newRating = (votes * rating + rate) / (votes + 1);
            seller.setVoters(votes + 1);
            Rating rat = ObjectFactory.getNewRating(seller.getID(),writer.getID(),  rate, USER);
            db.addWrittenRatingToBase(rat);
            seller.setRating(newRating);
            db.updateSellerWithoutImage(seller);
            Writer out = response.getWriter();
            out.write("success");
            out.close();
        }
        Buyer buyer = db.getBuyerByID(id);
        if (buyer != null) {
            int votes = buyer.getVoters();
            int rating = buyer.getRating();
            int newRating = (votes * rating + rate) / (votes + 1);
            buyer.setVoters(votes + 1);
            Rating rat = ObjectFactory.getNewRating(buyer.getID(), writer.getID(), rate, USER);
            db.addWrittenRatingToBase(rat);
            buyer.setRating(newRating);
            db.updateBuyerWithoutImage(buyer);
            Writer out = response.getWriter();
            out.write("success");
            out.close();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("ID") != null) {
            int id = Integer.valueOf(request.getParameter("ID"));

            DBConnection dbc=DBFactory.getDBConnection();
            if(dbc.getSellerByID(id)==null&&dbc.getBuyerByID(id)==null){
                response.sendRedirect("index.jsp");
            }
            Boolean logged = (Boolean) request.getSession().getAttribute(LOGGED_IN);
            if (logged) {
                User user = (User) request.getSession().getAttribute(USER);
                if (user.getID() == id) {
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
