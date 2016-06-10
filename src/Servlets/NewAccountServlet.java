package Servlets;

import Managers.ErrorStatus;
import Managers.FileManager;
import Managers.ManagerFactory;
import Managers.UserManager;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@MultipartConfig
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserManager um = ManagerFactory.getUserManager();
        FileManager fm = ManagerFactory.getFileManager();
        Part filePart = request.getPart("file");
        ErrorStatus es=ErrorStatus.CORRECT;
        PrintWriter out = response.getWriter();


        if (request.getParameter("type").equals("seller"))
            try {
               es=   um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                        request.getParameter("company"), request.getParameter("mobile"), filePart, request.getParameter("type"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        else {
            try {
                 es= um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                        request.getParameter("name") + " " + request.getParameter("surname"), request.getParameter("mobile"),
                          filePart, request.getParameter("type"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        if(es==ErrorStatus.USED_USERNAME){
            out.write("usedusername");
        }
        else if(es==ErrorStatus.INCORRECT_EMAIL_STRUCTURE){
            out.write("emailstructure");
        }
        else if(es==ErrorStatus.WEAK_PASSWORD){
            out.write("weakpassword");
        }
        else if(es==ErrorStatus.USED_EMAIL){
            out.write("usedemail");
        }
        out.write("correct");
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
