package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.List;

import static Managers.SiteConstants.*;


@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        System.out.println(request.getRequestURI());
        String comm;
        Writer out = response.getWriter();
        User writer = (User) request.getSession().getAttribute(USER);
        if (request.getParameter("ID") != null && request.getParameter("comment") != null) {
            DBConnection dbc = DBFactory.getDBConnection();
            Comment comment;
            id = Integer.valueOf(request.getParameter("ID"));
            comm = request.getParameter("comment");
            Item it = dbc.getItemById(id);
            if (it != null && request.getParameter(TYPE).equals(ITEM)) {
                comment = ObjectFactory.getNewComment(it.getID(), writer.getID(), comm);
                dbc.addCommentToItem(comment);
                response.sendRedirect("/item?ID=" + id);
                return;
            } else if (dbc.getSellerByID(id) != null) {
                Seller seller = dbc.getSellerByID(id);
                comment = ObjectFactory.getNewComment(seller.getID(), writer.getID(), comm);
                dbc.addCommentToUser(comment);
                response.sendRedirect("/user?ID=" + id);
                return;
            } else {
                Buyer buyer = dbc.getBuyerByID(id);
                comment = ObjectFactory.getNewComment(buyer.getID(), writer.getID(), comm);
                dbc.addCommentToUser(comment);
                response.sendRedirect("/user?ID=" + id);
                return;
            }
        }
        out.write("fail");
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection dbc = DBFactory.getDBConnection();
        List<Item> list = null;
        Writer out = response.getWriter();

        if (request.getParameter("page") == null) {
            out.close();
            return;
        } else {
            if (request.getParameter(TYPE).equals(USER)) {
                int page = Integer.valueOf(request.getParameter("page"));
                page += 1;
                int id = Integer.valueOf(request.getParameter("ID"));
                List<Comment> comList = dbc.getUserCommentsByOwner(id, NUMBER_OF_ITEMS_ON_PAGE * page, NUMBER_OF_ITEMS_ON_PAGE);
                for (Comment comment : comList) {
                    User user = dbc.getSellerByID(comment.getWriterID());
                    if (user == null) user = dbc.getBuyerByID(comment.getWriterID());
                    out.write(" <div class=\"dialogbox\">\n" +
                            "                    <div style=\"margin-left: 10%\">\n" +
                            "                        <div style=\"border: 1px solid #ff5e01;border-radius:15%;padding-left: 5px;padding-right:5px;    float:left\">\n" +
                            "                            <span style=\"font-size: 15px; margin-top: 20px;text-align: center\">" + user.getUserName() + "</span>\n" +
                            "                            <div style=\"width: 100%\"><img src=\"ImageLoader?FileName=" + user.getImage() + "\"\n" +
                            "                                                          style=\"width: 50px;height: 50px;text-align: center\"></div>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"comment-body\">\n" +
                            "                            <span class=\"tip tip-left\"></span>\n" +
                            "                            <div class=\"message\">\n" + "<span style=\"float:right;padding-right:20px;color:red\"> " +
                            "                                " + comment.getDateOfWrite() + "   </span>" + "<br>" +
                            "                                <span>" + comment.getComment() + "</span>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </div>");
                }


                if (comList.size() > 0) {
                    out.write("<input type=\"hidden\" id=\"comment-owner-type\" value=\"user\" >\n");
                    out.write("<input type=\"hidden\" id=\"comment-page\" value=\"" + page + "\" >\n");
                }
                System.out.println("<input type=\"hidden\" id=\"comment-page\" value=\"" + page + "\" >\n");
            }
            if (request.getParameter(TYPE).equals(ITEM)) {
                int page = Integer.valueOf(request.getParameter("page"));
                int id = Integer.valueOf(request.getParameter("ID"));
                page += 1;
                List<Comment> comList = dbc.getItemCommentsByOwner(id, NUMBER_OF_ITEMS_ON_PAGE * page, NUMBER_OF_ITEMS_ON_PAGE);
                for (Comment comment : comList) {
                    User user = dbc.getSellerByID(comment.getWriterID());
                    if (user == null) user = dbc.getBuyerByID(comment.getWriterID());
                    out.write(" <div class=\"dialogbox\">\n" +
                            "                    <div style=\"margin-left: 10%\">\n" +
                            "                        <div style=\"border: 1px solid #ff5e01;border-radius:15%;padding-left: 5px;padding-right:5px;    float:left\">\n" +
                            "                            <span style=\"font-size: 15px; margin-top: 20px;text-align: center\">" + user.getUserName() + "</span>\n" +
                            "                            <div style=\"width: 100%\"><img src=\"ImageLoader?FileName=" + user.getImage() + "\"\n" +
                            "                                                          style=\"width: 50px;height: 50px;text-align: center\"></div>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"comment-body\">\n" +
                            "                            <span class=\"tip tip-left\"></span>\n" +
                            "                            <div class=\"message\">\n" + "<span style=\"float:right;padding-right:20px;color:red\"> " +
                            "                                " + comment.getDateOfWrite() + "   </span>" + "<br>" +
                            "                                <span>" + comment.getComment() + "</span>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </div>");
                }

                page += 1;
                if (comList.size() > 0) {
                    out.write("<input type=\"hidden\" id=\"comment-owner-type\" value=\"item\" >\n");
                    out.write("<input type=\"hidden\" id=\"comment-page\" value=\"" + page + "\" >\n");
                }
                System.out.println("<input type=\"hidden\" id=\"comment-page\" value=\"" + page + "\" >\n");
            }
            out.close();
        }
    }
}
