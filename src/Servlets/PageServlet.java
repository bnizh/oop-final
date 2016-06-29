package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Item;
import Objects.Seller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static Managers.SiteConstants.NUMBER_OF_ITEMS_ON_PAGE;

@WebServlet("/page")
public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection dbc = DBFactory.getDBConnection();
        List<Item> list = null;
        Writer out = response.getWriter();
        if (request.getParameter("cat") == null) {
            if (request.getParameter("page") == null) {
                out.close();
                return;
            } else {
                int page = Integer.valueOf(request.getParameter("page"));
                list = dbc.getTopItems(NUMBER_OF_ITEMS_ON_PAGE, page * NUMBER_OF_ITEMS_ON_PAGE);
                for (Item it : list) {
                    Seller seller = dbc.getSellerByID(it.getOwnerID());
                    out.write("<div class=\"product\">\n" +
                            "                <div>" + seller.getName() + "</div>\n" +
                            "                <img src=\"ImageLoader?FileName=" + it.getImage() + "\">\n" +
                            "               <div>" + it.getName() + "</div>\n" +
                            "                <div>Price:" + it.getPrice() + "</div><form action=\"item\" method=\"get\"><input name=\"ID\" " +
                            "type=\"hidden\" value=\"" + it.getID() + "\">\n" +
                            "<button type=\"submit\" class=\"button\"> Details</button>\n" +
                            "</form></div>");
                }
                if (list.size() == NUMBER_OF_ITEMS_ON_PAGE) {
                    page += 1;
                    System.out.println(page);
                    out.write("<input type=\"hidden\" id=\"current-page\" name=\"page\" value=\"" + page + "\">");
                }
            }
        } else {
            int cat = Integer.valueOf(request.getParameter("cat"));
            if (request.getParameter("page") == null) {
                out.close();
                return;
            } else {
                int page = Integer.valueOf(request.getParameter("page"));
                list = dbc.getItemsByCategoryId(cat, NUMBER_OF_ITEMS_ON_PAGE, NUMBER_OF_ITEMS_ON_PAGE * page);
                for (Item it : list) {
                    Seller seller = dbc.getSellerByID(it.getOwnerID());
                    out.write("<div class=\"product\">\n" +
                            "                <div>" + seller.getName() + "</div>\n" +
                            "                <img src=\"ImageLoader?FileName=" + it.getImage() + "\">\n" +
                            "               <div>" + it.getName() + "</div>\n" +
                            "                <div>Price:" + it.getPrice() + "</div><form action=\"item\" method=\"get\"><input name=\"ID\" " +
                            "type=\"hidden\" value=\"" + it.getID() + "\">\n" +
                            "<button type=\"submit\" class=\"button\"> Details</button>\n" +
                            "</form></div>");
                }
                page+=1;
                if (list.size() == NUMBER_OF_ITEMS_ON_PAGE) {
                    out.write("<input href=\"page?page=" + page  + "cat=" + cat + "\">next page</input>");
                }
            }

        }
        out.close();
    }
}
