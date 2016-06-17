package Servlets;

import Managers.ItemManager;
import Managers.ManagerFactory;
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

import static Managers.SiteConstants.LOGGED_IN;
import static Managers.SiteConstants.USER;

@MultipartConfig
@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("name");
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

    }
}
