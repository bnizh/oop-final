package Servlets;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.ManagerFactory;
import Managers.UserManager;
import Objects.Buyer;
import Objects.Seller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

@MultipartConfig
@WebServlet("/superadmin")
public class SuperAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String a = en.nextElement();
            System.out.println(a);
        }
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        Part image = request.getPart("image");
        if (userName == null || email == null || password == null) return;
        if (name == null) name = "admin";
        if (mobile == null) mobile = "";
        UserManager um = ManagerFactory.getUserManager();
        try {
            um.createNewAdmin(userName, email, name, mobile, password, image);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    Writer out=response.getWriter();
        out.write("success");
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

