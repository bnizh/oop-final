package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.ItemManager;
import Managers.ManagerFactory;
import Objects.Item;
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
import java.io.PrintWriter;

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
        int id = Integer.valueOf(request.getParameter("ID"));
        Item item = dbc.getItemById(id);
        User user = dbc.getSellerByID(item.getOwnerID());
        String price = request.getParameter("price");
        String desc = request.getParameter("description");
        Part file = request.getPart("image");
        if (price != null) {
            Double prc = Double.valueOf(price);
            im.editItemPrice(item, prc);
        } else if (desc != null) {
            im.editItemDesc(desc, item);
        } else if (file != null) {
            im.editItemImage(item.getID(), String.valueOf(user.getID()), item.getName(), item.getImage(), file);
        }
        RequestDispatcher dispatch = request.getRequestDispatcher("item-owner.jsp");
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
