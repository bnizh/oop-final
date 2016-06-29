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

@WebServlet("/superadmin")
public class SuperAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringId = request.getParameter("ID");
        DBConnection dbc = DBFactory.getDBConnection();
        if (request.getParameter("delete") != null && stringId != null) {
            int id = Integer.valueOf(stringId);
            dbc.deleteAdmin(id);
            RequestDispatcher dispatch = request.getRequestDispatcher("superadmin.jsp");
            dispatch.forward(request, response);
            return;
        }
        Writer out = response.getWriter();
        out.write("ERr0r Has Occured And its your problem");
    }
}

