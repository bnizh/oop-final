package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Comment;
import Objects.Item;
import Objects.ObjectFactory;
import Objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Managers.SiteConstants.LOGGED_IN;
import static Managers.SiteConstants.USER;


@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String comm;
        User writer= (User) request.getSession().getAttribute(USER);
        if (request.getParameter("ID") != null && request.getParameter("CommentServlet") != null) {
            DBConnection dbc= DBFactory.getDBConnection();
            Comment comment;
            id = Integer.valueOf(request.getParameter("ID"));
            comm = request.getParameter("CommentServlet");
            Item it=dbc.getItemById(id);
            if(it!=null){
                comment= ObjectFactory.getNewComment(it.getID(),writer.getID(),comm);
                dbc.addCommentToItem(comment);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
