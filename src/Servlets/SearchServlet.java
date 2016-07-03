package Servlets;

import DataBase.DBFactory;
import Objects.Buyer;
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
        Writer out = response.getWriter();
        if(request.getParameter("value")!=null&& request.getParameter("type") != null){
            if(request.getParameter("type").equals("ITEM")) {
                List<Item> ls = DBFactory.getDBConnection().getItemsByName(request.getParameter("value"));
                String st = "";
                for (Item item : ls) {
                    Seller owner = DBFactory.getDBConnection().getSellerByID(item.getOwnerID());
                    String ownerName = owner.getName();
                    String itemName = item.getName();
                    String itemImage = item.getImage();
                    if (ownerName == null) ownerName = "";
                    if (itemName == null) itemName = "";
                    if (itemImage == null) itemImage = "";
                    st += "<div class=\"product\">\n" +
                            "                <div>" + ownerName + "</div>\n" +
                            "                <img src=\"ImageLoader?FileName=" + itemImage + "\">\n" +
                            "               " + "<div>" + itemName + "</div>\n" +
                            "                <div>Price:" + item.getPrice() + "</div>" +
                            "<form action=\"item\" method=\"get\">" +
                            "<input name=\"ID\" type=\"hidden\" value=\"" + item.getID() + "\">\n" +
                            "<button  type=\"submit\" class=\"button\"> Details</button>\n" +
                            "</form>" + "</div>";
                }
                out.write(st);
                out.close();
                return;
            }else if(request.getParameter("type").equals("USER")){
                List<Seller> ls = DBFactory.getDBConnection().getSellerByName(request.getParameter("value"));
                String st= "";
                for (Seller user : ls) {
                    String image ="";
                    int voters=user.getVoters();
                    if(voters ==0)voters=1;
                    if(user.getImage().contains("http")||user.getImage().contains("https")){
                        image = user.getImage();
                    }else{image = "ImageLoader?FileName="+user.getImage();}
                    st+= "<div class=\"product\">\n" +
                            "                <div>" + user.getUserName() + "</div>\n" +
                            "                <img src=\"" + image + "\">\n" +
                            "               " + "<div>" + "SELLER" + "</div>\n" +
                            "                <div>Rating:" + (user.getRating()/voters) + "</div>" +
                            "<form action=\"user\" method=\"get\">" +
                            "<input name=\"ID\" type=\"hidden\" value=\"" + user.getID() + "\">\n" +
                            "<button  type=\"submit\" class=\"button\"> Details</button>\n" +
                            "</form>" + "</div>";
                }
                List<Buyer> list = DBFactory.getDBConnection().getBuyerByName(request.getParameter("value"));
                for (Buyer user : list) {
                    String image ="";
                    int voters=user.getVoters();
                    if(voters ==0)voters=1;
                    if(user.getImage().contains("http")||user.getImage().contains("https")){
                        image = user.getImage();
                    }else{image = "ImageLoader?FileName="+user.getImage();}
                    st+= "<div class=\"product\">\n" +
                            "                <div>" + user.getUserName() + "</div>\n" +
                            "                <img src=\"" + image + "\">\n" +
                            "               " + "<div>" + "BUYER" + "</div>\n" +
                            "                <div>Rating:" + (user.getRating()/voters) + "</div>" +
                            "<form action=\"user\" method=\"get\">" +
                            "<input name=\"ID\" type=\"hidden\" value=\"" + user.getID() + "\">\n" +
                            "<button  type=\"submit\" class=\"button\"> Details</button>\n" +
                            "</form>" + "</div>";
                }
                out.write(st);
                out.close();
                return;
            }
        }
        out.write("failed");
        out.close();
    }
}
