package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.ItemManager;
import Managers.ManagerFactory;
import Objects.Item;
import Objects.Seller;
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
import java.util.Enumeration;

import static Managers.SiteConstants.LOGGED_IN;
import static Managers.SiteConstants.USER;

@MultipartConfig
@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String>list=request.getParameterNames();
        while (list.hasMoreElements()){
            String a= list.nextElement();
            System.out.println(a);
        }
        String itemName = request.getParameter("item-name");
        if(itemName==null ||request.getParameter("price")==null) return;
        System.out.println(request.getParameter("price"));
        Double price = Double.valueOf(request.getParameter("price"));
        String desc = request.getParameter("description");
        Part file = request.getPart("file");
        System.out.println(request.getParameter("category"));
        int categoryID = Integer.valueOf(request.getParameter("category"));
        User user = (User) request.getSession().getAttribute(USER);
        ItemManager im = ManagerFactory.getItemManager();
        im.addNewItem(user.getUserName(), itemName, price, desc, user.getID(), file, categoryID
        );
        PrintWriter out = response.getWriter();
        out.write("success");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("ID"));
        DBConnection dbc = DBFactory.getDBConnection();
        Item item = dbc.getItemById(id);
        Seller seller = dbc.getSellerByID(item.getOwnerID());
        User user = (User) request.getSession().getAttribute(USER);
        request.getSession().setAttribute("itemID",id);
        if(user!=null&&user.getID()==seller.getID()){
            RequestDispatcher dispatch = request.getRequestDispatcher("item-owner.jsp");
            dispatch.forward(request, response);
        }
        else{
            RequestDispatcher dispatch = request.getRequestDispatcher("item.jsp");
            dispatch.forward(request, response);
        }
    }
}
