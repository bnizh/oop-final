package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Category;
import Objects.Item;
import Objects.ObjectFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Managers.SiteConstants.NUMBER_OF_ITEMS_ON_PAGE;

@WebServlet("/cat")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String stringId = request.getParameter("ID");
        System.out.println(category + stringId);
        if (category != null && stringId != null) {
            int id = Integer.valueOf(stringId);
            DBConnection dbc = DBFactory.getDBConnection();
            Category ct = dbc.getCategory(id);
            ct.setName(category);
            dbc.updateCategory(ct);
            RequestDispatcher dispatch = request.getRequestDispatcher("admin-category.jsp");
            dispatch.forward(request, response);
            return;
        } else if (stringId != null) {
            int id = Integer.valueOf(stringId);
            DBConnection dbc = DBFactory.getDBConnection();
            dbc.deleteCategory(id);
            RequestDispatcher dispatch = request.getRequestDispatcher("admin-category.jsp");
            dispatch.forward(request, response);
            return;
        } else if (category != null) {
            DBConnection dbc = DBFactory.getDBConnection();
            Category cat = ObjectFactory.getNewCategory(category);
            dbc.addCategory(cat);

        }
        RequestDispatcher dispatch = request.getRequestDispatcher("admin-category.jsp");
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("cat") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        DBConnection dbc = DBFactory.getDBConnection();
        int id = Integer.valueOf(request.getParameter("cat"));
        List<Item> list = dbc.getItemsByCategoryId(id, NUMBER_OF_ITEMS_ON_PAGE, 0);
        request.getSession().setAttribute("itemList", list);
        RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
        dispatch.forward(request, response);
    }
}
