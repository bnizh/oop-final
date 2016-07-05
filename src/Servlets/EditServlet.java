package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.HashCreator;
import Managers.ManagerFactory;
import Managers.UserManager;
import Objects.Buyer;
import Objects.Seller;
import Objects.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;

import static Managers.SiteConstants.*;

@MultipartConfig
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("simage");
        String sname = request.getParameter("sname");
        String curPas = request.getParameter("curpassword");
        String newPas = request.getParameter("newpassword");
        UserManager um = ManagerFactory.getUserManager();
        DBConnection db = DBFactory.getDBConnection();
        String tag = request.getParameter("tag");
        if(tag != null){
            User user = (User) request.getSession().getAttribute(USER);
            DBFactory.getDBConnection().addHashTagToUser(user.getID(), tag);
            RequestDispatcher dispatch = request.getRequestDispatcher("/user-page.jsp");
            dispatch.forward(request, response);
        }
        if (filePart != null) {
            User user = (User) request.getSession().getAttribute(USER);
            um.editImageUser(filePart, user);
            request.setAttribute(USER, user);
            RequestDispatcher dispatch = request.getRequestDispatcher("/user-page.jsp");
            dispatch.forward(request, response);
        }

        if (sname != null) {
            if (request.getSession().getAttribute(TYPE).equals(SELLER)) {
                Seller seller = (Seller) request.getSession().getAttribute(USER);
                seller.setName(sname);
                db.updateSellerWithoutImage(seller);
                RequestDispatcher dispatch = request.getRequestDispatcher("/user-page.jsp");
                dispatch.forward(request, response);
            }else{
                Buyer buyer = (Buyer) request.getSession().getAttribute(USER);
                buyer.setName(sname);
                db.updateBuyerWithoutImage(buyer);
                RequestDispatcher dispatch = request.getRequestDispatcher("/user-page.jsp");
                dispatch.forward(request, response);
            }
        }
        if (request.getParameter("smob") != null) {
            if (request.getSession().getAttribute(TYPE).equals(SELLER)) {
                Seller seller = (Seller) request.getSession().getAttribute(USER);
                seller.setMobileNumber(request.getParameter("smob"));
                db.updateSellerWithoutImage(seller);
                RequestDispatcher dispatch = request.getRequestDispatcher("/user-page.jsp");
                dispatch.forward(request, response);
            } else {
                Buyer buyer = (Buyer) request.getSession().getAttribute(USER);
                buyer.setMobileNumber(request.getParameter("smob"));
                db.updateBuyerWithoutImage(buyer);
                RequestDispatcher dispatch = request.getRequestDispatcher("/user-page.jsp");
                dispatch.forward(request, response);
            }
        }
        if (curPas != null && newPas != null) {
            String hashedCurPas = null;
            Writer out = response.getWriter();
            try {
                hashedCurPas = HashCreator.getHash(curPas);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            String type = (String) request.getSession().getAttribute(TYPE);
            if (type.equals(SELLER)) {
                Seller seller = (Seller) request.getSession().getAttribute(USER);
                if (seller.getPassword().equals(hashedCurPas)) {
                    try {
                        seller.setPassword(HashCreator.getHash(newPas));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    db.updateSellerWithoutImage(seller);
                    out.write("success");
                    out.close();
                } else {
                    out.write("wrong");
                    out.close();
                }
            } else if (type.equals(BUYER)) {
                Buyer buyer = (Buyer) request.getSession().getAttribute(USER);
                if (buyer.getPassword().equals(hashedCurPas)) {
                    try {
                        buyer.setPassword(HashCreator.getHash(newPas));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    db.updateBuyerWithoutImage(buyer);
                    out.write("success");
                    out.close();
                } else {
                    out.write("wrong");
                    out.close();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
