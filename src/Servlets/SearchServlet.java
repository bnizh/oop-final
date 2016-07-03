package Servlets;

import DataBase.DBFactory;
import Objects.Item;
import Objects.Seller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;



/**
 * Created by Boris on 03.07.2016.
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("value")!=null){
            List<Item> ls = DBFactory.getDBConnection().getItemsByName(request.getParameter("value"));
            PrintWriter out = response.getWriter();
            String st = "";
            for (Item item : ls) {
                Seller owner = DBFactory.getDBConnection().getSellerByID(item.getOwnerID());
                String ownerName = owner.getName();
                String itemName = item.getName();
                String itemImage = item.getImage();
                if (ownerName == null) ownerName = "";
                if (itemName == null) itemName = "";
                if (itemImage == null) itemImage = "";
                st+="<div class=\"product\">\n" +
                        "                <div>" + ownerName + "</div>\n" +
                        "                <img src=\"ImageLoader?FileName=" + itemImage + "\">\n" +
                        "               " + "<div>" + itemName + "</div>\n" +
                        "                <div>Price:" + item.getPrice() + "</div>" +
                        "<form action=\"item\" method=\"get\">" +
                        "<input name=\"ID\" type=\"hidden\" value=\"" + item.getID() + "\">\n" +
                        "<button  type=\"submit\" class=\"button\"> Details</button>\n" +
                        "</form>" + "</div>";
            }
            Writer out1 = response.getWriter();
            out1.write(st);
            out1.close();
            return;
        }
        Writer out = response.getWriter();
        out.write("failed");
        out.close();
    }
}
