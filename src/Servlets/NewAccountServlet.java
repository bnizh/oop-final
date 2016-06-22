package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.ErrorStatus;
import Managers.FileManager;
import Managers.ManagerFactory;
import Managers.UserManager;
import Objects.Buyer;
import Objects.Seller;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;

import static Managers.SiteConstants.USER;

@MultipartConfig
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserManager um = ManagerFactory.getUserManager();
        Part filePart = request.getPart("file");
        ErrorStatus es = ErrorStatus.CORRECT;
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        String userName=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        if(name==null||userName==null||email==null||password==null||type==null){
            out.write("null");
            out.close();
            return;
        }

        if (request.getParameter("type").equals("seller"))
            try {
                es = um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                        request.getParameter("name"), request.getParameter("mobile"), filePart, request.getParameter("type"));
                System.out.println(es);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        else {
            try {
                es = um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                        request.getParameter("name") + " " + request.getParameter("surname"), request.getParameter("mobile"),
                        filePart, request.getParameter("type"));
                System.out.println(es);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        if (es == ErrorStatus.USED_USERNAME) {
            out.write("usedusername");
            out.close();
            return;
        } else if (es == ErrorStatus.INCORRECT_EMAIL_STRUCTURE) {
            out.write("emailstructure");
            out.close();
            return;
        } else if (es == ErrorStatus.WEAK_PASSWORD) {
            out.write("weakpassword");
            out.close();
            return;
        } else if (es == ErrorStatus.USED_EMAIL) {
            out.write("usedemail");
            out.close();
            return;
        }
        DBConnection dbc = DBFactory.getDBConnection();
        if (type.equals("seller")) {
            Seller seller = dbc.getSellerByUsername(request.getParameter("username"));
            HttpSession session = request.getSession();
            Cookie username = new Cookie(USER, seller.getUserName());
            username.setMaxAge(3*60*60);
            response.addCookie(username);
            session.setAttribute("user", seller);
            session.setAttribute("type", "seller");
            RequestDispatcher dispatch = request.getRequestDispatcher("user-panel.jsp");
            dispatch.forward(request, response);
        }
        else{
            Buyer buyer = dbc.getBuyerByUsername(request.getParameter("username"));
            HttpSession session = request.getSession();
            session.setAttribute("user", buyer);
            session.setAttribute("type", "buyer");
            Cookie username = new Cookie(USER, buyer.getUserName());
            username.setMaxAge(3*60*60);
            response.addCookie(username);
            RequestDispatcher dispatch = request.getRequestDispatcher("user-panel.jsp");
            dispatch.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
