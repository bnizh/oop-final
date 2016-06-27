package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String comm;
        if (request.getParameter("ID") != null && request.getParameter("CommentServlet") != null) {
            DBConnection dbc= DBFactory.getDBConnection();
            id = Integer.valueOf(request.getParameter("ID"));
            comm = request.getParameter("CommentServlet");
            Item it=dbc.getItemById(id);
            if(it!=null){
                dbc.add
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
