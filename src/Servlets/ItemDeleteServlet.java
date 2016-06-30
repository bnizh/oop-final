package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/item-delete")
public class ItemDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("ID") != null) {
            int id = Integer.valueOf(request.getParameter("ID"));
            DBConnection dbc = DBFactory.getDBConnection();
            dbc.deleteItem(id);
            RequestDispatcher dispatch = request.getRequestDispatcher("admin-product.jsp");
            dispatch.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
