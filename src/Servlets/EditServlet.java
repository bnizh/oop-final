package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.ManagerFactory;
import Managers.UserManager;
import Objects.Seller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

import static Managers.SiteConstants.LOGGED_IN;
import static Managers.SiteConstants.USER;

@MultipartConfig
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("simage");
        UserManager um = ManagerFactory.getUserManager();
        System.out.println(request.getPart("simage").getName());
        System.out.println(filePart.getSize());
        if (filePart != null) {
            System.out.println("gacda null shemowmebas");
            Seller seller = (Seller) request.getSession().getAttribute(USER);
            System.out.println(seller);
            um.editImageSeller(filePart, seller);
            request.setAttribute(USER,seller);
            RequestDispatcher dispatch = request.getRequestDispatcher("SellerPage.jsp");
            dispatch.forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
