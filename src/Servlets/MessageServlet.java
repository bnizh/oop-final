package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Message;
import Objects.ObjectFactory;
import Objects.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import static Managers.SiteConstants.MESSAGE_USER_TO_ADMIN;
import static Managers.SiteConstants.MESSAGE_USER_TO_USER;
import static Managers.SiteConstants.USER;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection dbc = DBFactory.getDBConnection();
        if (request.getParameter("from") != null && request.getParameter("from").equals("admin")) {
            String text = request.getParameter("text");
            String receiver = request.getParameter("receiver");
            User rec = dbc.getSellerByUsername(receiver);
            if (rec == null) rec = dbc.getBuyerByUsername(receiver);
            Message message = ObjectFactory.getNewMessage(1, rec.getID(), text);
            dbc.addMessage(message, MESSAGE_USER_TO_USER);
            RequestDispatcher dispatch = request.getRequestDispatcher("admin-message-inbox.jsp");
            dispatch.forward(request, response);
        }
        String type = request.getParameter("type");
        User user = (User) request.getSession().getAttribute(USER);
        if (type == null) return;
        if (type.equals("admin")) {
            String text = request.getParameter("text");
            Message message = ObjectFactory.getNewMessage(user.getID(), 1, text);
            dbc.addMessage(message, MESSAGE_USER_TO_ADMIN);
        } else if (type.equals("user")) {
            String text = request.getParameter("text");
            String receiver = request.getParameter("receiver");
            User rec = dbc.getSellerByUsername(receiver);
            if (rec == null) rec = dbc.getBuyerByUsername(receiver);
            Message message = ObjectFactory.getNewMessage(user.getID(), rec.getID(), text);
            dbc.addMessage(message, MESSAGE_USER_TO_USER);
        }
        RequestDispatcher dispatch = request.getRequestDispatcher("user-message-inbox.jsp");
        dispatch.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("messageID"));
        Writer out = response.getWriter();
        DBConnection dbc = DBFactory.getDBConnection();
        if (request.getParameter("messageID") != null && request.getParameter("type") != null && request.getParameter("type").equals("user")) {
            int messageId = Integer.valueOf(request.getParameter("messageID"));
            Message message = dbc.getMessageById(messageId);
            request.getSession().setAttribute("message", message);
            out.write("user-message.jsp");
            out.close();
        }

    }
}
