package Servlets;

import Managers.FileManager;
import Managers.ManagerFactory;
import Managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserManager um = ManagerFactory.getUserManager();
        if (!um.checkUsernameVacancy(request.getParameter("username"))) {
            PrintWriter out = response.getWriter();
            out.write("used");
            out.close();
            return;
        }

        FileManager fm = ManagerFactory.getFileManager();
        Part filePart = request.getPart("file");
        String imgUrl = fm.saveFile(request.getParameter("username"), filePart);
        if (request.getParameter("type").equals("seller"))
            um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                    request.getParameter("company"), request.getParameter("mobile"), imgUrl, request.getParameter("type"));
        else {
            um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                    request.getParameter("name") + " " + request.getParameter("surname"), request.getParameter("mobile"), imgUrl, request.getParameter("type"));
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
