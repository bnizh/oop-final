package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Buyer;
import Objects.Seller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringId = request.getParameter("ID");
        String type = request.getParameter("type");
        DBConnection dbc = DBFactory.getDBConnection();
        if (request.getParameter("delete") != null && stringId != null && type != null) {
            int id = Integer.valueOf(stringId);
            if (type.equals("seller")) {
                dbc.deleteSeller(id);
            } else {
                dbc.deleteBuyer(id);
            }
            RequestDispatcher dispatch = request.getRequestDispatcher("admin.jsp");
            dispatch.forward(request, response);
            return;
        } else if (request.getParameter("ban") != null && stringId != null && type != null) {
            int id = Integer.valueOf(stringId);
            if (request.getParameter("ban").equals("1")) {
                if (type.equals("seller")) {
                    Seller seller = dbc.getSellerByID(id);
                    seller.setBanned(true);
                    dbc.updateSellerWithoutImage(seller);
                } else {
                    Buyer buyer = dbc.getBuyerByID(id);
                    buyer.setBanned(true);
                    dbc.updateBuyerWithoutImage(buyer);
                }
            }
            else{
                if (type.equals("seller")) {
                    Seller seller = dbc.getSellerByID(id);
                    seller.setBanned(false);
                    dbc.updateSellerWithoutImage(seller);
                } else {
                    Buyer buyer = dbc.getBuyerByID(id);
                    buyer.setBanned(false);
                    dbc.updateBuyerWithoutImage(buyer);
                }
            }
            RequestDispatcher dispatch = request.getRequestDispatcher("admin.jsp");
            dispatch.forward(request, response);
            return;
        }
        Writer out = response.getWriter();
        out.write("ERr0r Has Occured And its your problem");
    }
}
