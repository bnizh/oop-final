package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.ItemManager;
import Managers.ManagerFactory;
import Objects.Item;
import Objects.ObjectFactory;
import Objects.Rating;
import Objects.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Writer;

import static Managers.SiteConstants.ITEM;
import static Managers.SiteConstants.USER;

@MultipartConfig
@WebServlet("/item-edit")
public class ItemEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           ItemManager im = ManagerFactory.getItemManager();
           DBConnection dbc = DBFactory.getDBConnection();
           if (request.getParameter("ID") == null) {
               RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
               dispatch.forward(request, response);
               return;
           }
           int id = Integer.parseInt(request.getParameter("ID"));
           Item item = dbc.getItemById(id);
           User user = dbc.getSellerByID(item.getOwnerID());
           String price = request.getParameter("price");
           String desc = request.getParameter("description");
            String tag = request.getParameter("tag");
           Part file = request.getPart("image");
           if (price != null) {
               Double prc = Double.valueOf(price);
               im.editItemPrice(item, prc);
           } else if (desc != null) {
               im.editItemDesc(desc, item);
           } else if (file != null) {
               im.editItemImage(item.getID(), user.getUserName(), item.getName(), item.getImage(), file);
           }else if(tag!=null){
               DBFactory.getDBConnection().addHashTagToItem(id, tag);
           }
        RequestDispatcher dispatch = request.getRequestDispatcher("item-owner.jsp");
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer rate = null;
        User user = (User) request.getSession().getAttribute(USER);
        if (request.getParameter("rate") == null || request.getParameter("ID") == null) return;
        System.out.println(rate);
        DBConnection db = DBFactory.getDBConnection();
        rate = Integer.valueOf(request.getParameter("rate"));
        int id = Integer.valueOf(request.getParameter("ID"));
        Item item = db.getItemById(id);
        int votes = item.getVoters();
        int rating = item.getRating();
        int newRating = (votes * rating + rate) / (votes + 1);
        item.setVoters(votes + 1);
        Rating rat = db.getRating(item.getID(), user.getID(), ITEM);
        if (rat == null) rat = ObjectFactory.getNewRating(item.getID(), user.getID(), rate, ITEM);
        else {
            Writer out = response.getWriter();
            out.write("failed");
            out.close();
            return;
        }
        db.addWrittenRatingToBase(rat);
        item.setRating(newRating);
        db.updateItemWithoutImage(item);
        Writer out = response.getWriter();
        out.write("success");
        out.close();
    }
}
